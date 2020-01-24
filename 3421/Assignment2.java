import java.sql.*;

public class Assignment2 {
    
  // A connection to the database 
/*  
public static void main(String[] args)
{  
	
	Assignment2 a = new Assignment2();   		
	System.out.println("Connect to DB: " + a.connectDB("jdbc:postgresql://db:5432/rd110018",  "rd110018", "RIShab95!"));
	//a.disconnectDB();
	
	a.setSchema();
//	a.insertCountry(1,"India", 33, 12222);
	//a.insertCountry(2,"America", 43, 32222);
	//a.insertCountry(3,"Canada", 53, 1222);
	//a.getCountries();
	//System.out.println(a.getCountriesNextToOceanCount(2));
	//System.out.println("count = " + a.getCountriesNextToOceanCount(4));
	//System.out.println("ocean info : " + a.getOceanInfo(1));
	//System.out.println("chgHdi status : "  +a.chgHDI(1, 2009, 0.45f));
	//System.out.println("updateHeight status : "  +a.updateHeight(14, 10));
	//System.out.println("Update database status: " + a.updateDB());
	// System.out.println("Languages are: " + a.listCountryLanguages(1));
	  //System.out.println("delete neighbor status: " + a.deleteNeighbour(7,8));
	
} 
*/

// A connection to the database  
  Connection connection;
 
 // Statement to run queries
  Statement sql;
  
  // Prepared Statement
  PreparedStatement ps;
  
  // Resultset for the query
  ResultSet rs;
  
  
   boolean status = false;	
  //CONSTRUCTOR
  Assignment2(){
	// Load JDBC driver
		try
		{
		Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e) {
 
			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();
			return;
 
		}
  
  }
  
  //Using the input parameters, establish a connection to be used for this session. Returns true if connection is sucessful
  public boolean connectDB(String URL, String username, String password){
	try {
	  connection = DriverManager.getConnection(URL,username, password); 
	return (connection != null);      
	}
	catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return false;
 
		}
  }
  
  //Closes the connection. Returns true if closure was sucessful
  public boolean disconnectDB(){
	try{
  connection.close();      
	return (connection == null);
  }
  catch(SQLException e)
	{
		System.out.println("Connection termination Failed!");
                  e.printStackTrace();
                  return false;
}   
  }
    
  public boolean insertCountry(int cid, String name, int height, int population)
  {	
  boolean val = false;
	try {
	  String sqlText = "INSERT INTO country " +  "VALUES (?,?,?,?);";  
	  System.out.println("Prepared Statement: " + sqlText.replaceAll("\\s+", " ") + "\n");
	  ps = connection.prepareStatement(sqlText);
	  ps.setInt(1,cid);
	  ps.setString(2, name);
	  ps.setInt(3, height);
	  ps.setInt(4,population);
		int val1 =	ps.executeUpdate();
		  if( val1 != 0)
        {
        val = true;
        }
    ps.close();
		
  }	catch(SQLException e)
	{
		System.out.println("Query Exection Failed!");
                  e.printStackTrace();
                  val = false;
} 
  return val; 
	}
  
  public int getCountriesNextToOceanCount(int oid) {
	  int r = 0;
	  try {
		     String sqlText = "SELECT count(cid) " +
			        " FROM oceanAccess WHERE oid = ? ";
		          System.out.println("Prepared Statement: " + sqlText.replaceAll("\\s+", " ") + "\n");
		         ps = connection.prepareStatement(sqlText);
		         ps.setInt(1,oid);
		  		rs = ps.executeQuery();
				if (rs != null){
				while (rs.next()){
					r = rs.getInt(1);
				}
				}

				//Close the resultset
				ps.close();
				rs.close();
			 }
			 catch(SQLException e)
				{
					System.out.println("Query Exection Failed!");
          e.printStackTrace();
          r = -1;
			                 
			}  
	 return r;   
  }
   
  public String getOceanInfo(int oid){
	  String info = "";
	  try {
			 
		     String sqlText = "SELECT oid, oname, depth " +
			        " FROM ocean WHERE oid = ? ";
		          System.out.println("Prepared Statement: " + sqlText.replaceAll("\\s+", " ") + "\n");
		         ps = connection.prepareStatement(sqlText);
		         ps.setInt(1,oid);
		  		rs = ps.executeQuery();
				if (rs != null){
				while (rs.next()){
					info += rs.getInt(1) + ":" + rs.getString(2) + ":" + rs.getInt(3);
				}
					
				}

				//Close the resultset
				ps.close();
				rs.close();
			 }
			 catch(SQLException e)
				{
					System.out.println("Query Exection Failed!");
			                  e.printStackTrace();
			                  info ="";
			}  
	 return info;   

  }

  public boolean chgHDI(int cid, int year, float newHDI){
	  boolean r = false;
	  try {
		     String sqlText = "UPDATE hdi " +
			        " SET hdi_score = ?  WHERE cid = ? AND year = ? ";
		          System.out.println("Prepared Statement: " + sqlText.replaceAll("\\s+", " ") + "\n");
		         ps = connection.prepareStatement(sqlText);
		         ps.setFloat(1,newHDI);
		         ps.setInt(2, cid);
		         ps.setInt(3, year);
		  		int val = ps.executeUpdate();
                  if(val != 0)
                  {
		  		          r = true;
					        }
				//Close the resultset
				ps.close();
				
			 }
			 catch(SQLException e)
				{
					System.out.println("Query Exection Failed!");
			                  e.printStackTrace();
			                  r = false;
			}  
	 return r;   
  
  }

  public boolean deleteNeighbour(int c1id, int c2id){
        boolean r = false;
	  try {
		     String sqlText = "DELETE FROM neighbour WHERE country = ? AND neighbor = ? ;";
                sqlText += "DELETE FROM neighbour WHERE country = ? AND neighbor = ? ;";
		          System.out.println("Prepared Statement: " + sqlText.replaceAll("\\s+", " ") + "\n");
		         ps = connection.prepareStatement(sqlText);
		         ps.setInt(1,c1id);
		         ps.setInt(2, c2id);
		         ps.setInt(3, c2id);
             ps.setInt(4, c1id);
		  		int val = ps.executeUpdate();
            if( val != 0)
            {
		  		r = true;
             } 
				//Close the resultset
				ps.close();
			 }
			 catch(SQLException e)
				{
					System.out.println("Query Exection Failed!");
			                  e.printStackTrace();
			                  r = false;
			}  
	 return r;   
     
  }
  
  public String listCountryLanguages(int cid){
	String info = ""; 
	  try {
			 
		     String sqlText = "SELECT language.lid as lid , language.lname as lname , language.lpercentage * country.population as population " +
			        " FROM country, language WHERE country.cid = ? AND language.cid = ?  ORDER BY population ";
		          System.out.println("Prepared Statement: " + sqlText.replaceAll("\\s+", " ") + "\n");
		         ps = connection.prepareStatement(sqlText);
		         ps.setInt(1,cid);
             ps.setInt(2,cid);         
		  		rs = ps.executeQuery();
				if (rs != null){
				while (rs.next()){
					info += rs.getInt(1) + ":" + rs.getString(2) + ":" + rs.getInt(3) + "\n";
				}
					
				}

				//Close the resultset
				ps.close();
				rs.close();
			 }
			 catch(SQLException e)
				{
					System.out.println("Query Exection Failed!");
			                  e.printStackTrace();
			                  info ="";
			}  
	 return info;   

  }
  
  public boolean updateHeight(int cid, int decrH){
	  boolean r = false;
	  try {
		     String sqlText = "UPDATE country " +
			        " SET height = height - ?  WHERE cid = ? ";
		          System.out.println("Prepared Statement: " + sqlText.replaceAll("\\s+", " ") + "\n");
		         ps = connection.prepareStatement(sqlText);
		         ps.setInt(1,decrH);
		         ps.setInt(2, cid);
		  		 int val = ps.executeUpdate();
          if(val != 0)
          {
		  		r = true;
					}
				

				//Close the resultset
				ps.close();
			 }
			 catch(SQLException e)
				{
					System.out.println("Query Exection Failed!");
			                  e.printStackTrace();
			                  r = false;
			}  
	 return r;   

  }
    
  public boolean updateDB(){
	  boolean val = false;
    try {
		  String sqlText = "CREATE TABLE mostPopulousCountries( " + 
				  			" cid INTEGER , cname VARCHAR(20)); ";
		  		sqlText += "INSERT INTO mostPopulousCountries(cid, cname) SELECT cid, cname FROM country WHERE population > 100000000 order by cid ASC;";	
		  System.out.println("Prepared Statement: " + sqlText.replaceAll("\\s+", " ") + "\n");
		  sql = connection.createStatement();
				sql.executeUpdate(sqlText);
			sql.close();
		val = true;	
	  }	catch(SQLException e)
		{
			System.out.println("Query Exection Failed!");
	                  e.printStackTrace();
	                  val = false;
	}   
  return val;   
  }

 public void setSchema()
{
	 
	 		try
	 		{
	 			sql = connection.createStatement();
	 		String sqlText;
			sqlText = "DROP TABLE IF EXISTS country CASCADE;\n" + 
					"DROP TABLE IF EXISTS language CASCADE;\n" + 
					"DROP TABLE IF EXISTS religion CASCADE;\n" + 
					"DROP TABLE IF EXISTS hdi CASCADE;\n" + 
					"DROP TABLE IF EXISTS ocean CASCADE;\n" + 
					"DROP TABLE IF EXISTS neighbour CASCADE;\n" + 
					"DROP TABLE IF EXISTS oceanAccess CASCADE;\n" +
          "DROP TABLE IF EXISTS mostPopulousCountries CASCADE;\n"+      
					"CREATE TABLE country ("
					+ "	    cid 		INTEGER 	PRIMARY KEY," + 
						   " cname 		VARCHAR(20)	NOT NULL," +
						    "height 		INTEGER 	NOT NULL," +
						   " population	INTEGER 	NOT NULL);";
			sqlText += "CREATE TABLE language ("+
				   " cid 		INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,"+
				    "lid 		INTEGER 	NOT NULL,"+
				    "lname 		VARCHAR(20) NOT NULL,"+
				    "lpercentage	REAL 		NOT NULL,"+
					"PRIMARY KEY(cid, lid));";
			sqlText += "CREATE TABLE religion (\n" + 
					"    cid 		INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,\n" + 
					"    rid 		INTEGER 	NOT NULL,\n" + 
					"    rname 		VARCHAR(20) NOT NULL,\n" + 
					"    rpercentage	REAL 		NOT NULL,\n" + 
					"	PRIMARY KEY(cid, rid));";
			sqlText += "CREATE TABLE hdi (\n" + 
					"    cid 		INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,\n" + 
					"    year 		INTEGER 	NOT NULL,\n" + 
					"    hdi_score 	REAL 		NOT NULL,\n" + 
					"	PRIMARY KEY(cid, year));";
			sqlText += "CREATE TABLE ocean (\n" + 
					"    oid 		INTEGER 	PRIMARY KEY,\n" + 
					"    oname 		VARCHAR(20) NOT NULL,\n" + 
					"    depth 		INTEGER 	NOT NULL);";
			sqlText += "CREATE TABLE neighbour (\n" + 
					"    country 	INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,\n" + 
					"    neighbor 	INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT, \n" + 
					"    length 		INTEGER 	NOT NULL,\n" + 
					"	PRIMARY KEY(country, neighbor));";
			sqlText += "CREATE TABLE oceanAccess (\n" + 
					"    cid 	INTEGER 	REFERENCES country(cid) ON DELETE RESTRICT,\n" + 
					"    oid 	INTEGER 	REFERENCES ocean(oid) ON DELETE RESTRICT, \n" + 
					"    PRIMARY KEY(cid, oid));";
			sqlText += "delete from country cascade;\n" + 
					"delete from language ;\n" + 
					"delete from religion ;\n" + 
					"delete from ocean ;\n" + 
					"delete from neighbour ;\n" + 
					"delete from oceanAccess ;\n" + 
					"delete from hdi;\n" + 
					"\n" + 
					"\n" + 
					"INSERT into country\n" + 
					"values\n" + 
					" (1, 'India', 10, 2500),\n" + 
					"(2, 'Australia', 20, 1000),\n" + 
					"(3, 'Canada', 15, 5000),\n" + 
					"(4, 'America', 16, 3000),\n" + 
					"(5, 'Italy', 25, 5050),\n" + 
					"(6,'Greece', 20, 9555);\n" + 
					"\n" + 
					"INSERT into language\n" + 
					"values\n" + 
					" (1,1,'Hindi', 0.40),\n" + 
					"(1,2,'English', 0.30),\n" + 
					"(1,3,'Kannada', 0.30),\n" + 
					"(2,2,'English', 0.60),\n" + 
					"(2,4,'French', 0.20),\n" + 
					"(3,2,'English', 0.60),\n" + 
					"(3,4,'French' , 0.40),\n" + 
					"(4,2,'English', 0.85),\n" + 
					"(5,2,'English', 0.20);\n" + 
					"\n" + 
					"INSERT into religion\n" + 
					"values\n" + 
					"(1, 1 , 'Hindu', 0.30),\n" + 
					"(1, 2 , 'Muslim', 0.20),\n" + 
					"(1, 3 , 'Sikh', 0.30),\n" + 
					"(1, 4 , 'Christian', 0.20),\n" + 
					"(2, 4 , 'Christian', 0.60),\n" + 
					"(2, 5 , 'Jewish', 0.10),\n" + 
					"(3, 6 , 'English', 0.50),\n" + 
					"(3, 5 , 'Jewish', 0.20),\n" + 
					"(4, 6 , 'English', 0.80);\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"INSERT into ocean\n" + 
					"values\n" + 
					"(1, 'Indian Ocean', 1000),\n" + 
					"(2, 'Pacific Ocean', 2000),\n" + 
					"(3, 'Atlantic Ocean', 3000),\n" + 
					"(4, 'Arctic Ocean', 4000);\n" + 
					"\n" + 
					"INSERT into neighbour\n" + 
					"values\n" + 
					"(1,2,200),\n" + 
					"(1,3,500),\n" + 
					"(1,4,1000),\n" + 
					"(2,1,100),\n" + 
					"(2,3,4400),\n" + 
					"(3,1,1000),\n" + 
					"(3,2,1110),\n" + 
					"(4,1,1000),\n" + 
					"(4,2,600),\n" + 
					"(4,3,100),\n" + 
					"(5,1,3000),\n" + 
					"(5,3,11000),\n" + 
					"(5,4,4000);\n" + 
					"\n" + 
					"INSERT into oceanAccess\n" + 
					"values\n" + 
					"(1,1),\n" + 
					"(2,1),\n" + 
					"(2,4),\n" + 
					"(3,1),\n" + 
					"(4,1),\n" + 
					"(4,2),\n" + 
					"(5,1),\n" + 
					"(5,4);\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"insert into hdi\n" + 
					"values \n" + 
					"(1,2009,0.50),\n" + 
					"(1,2010,0.51),\n" + 
					"(1,2011,0.52),\n" + 
					"(1,2012,0.53),\n" + 
					"(1,2013,0.54),\n" + 
					"(1,2014,0.55),\n" + 
					"(1,2015,0.56),\n" + 
					"\n" + 
					"(2,2009,0.60),\n" + 
					"(2,2010,0.51),\n" + 
					"(2,2011,0.52),\n" + 
					"(2,2012,0.53),\n" + 
					"(2,2013,0.64),\n" + 
					"(2,2014,0.75),\n" + 
					"(2,2015,0.16),\n" + 
					"\n" + 
					"(3,2009,0.50),\n" + 
					"(3,2010,0.51),\n" + 
					"(3,2011,0.22),\n" + 
					"(3,2012,0.53),\n" + 
					"(3,2013,0.84),\n" + 
					"(3,2014,0.55),\n" + 
					"(3,2015,0.51),\n" + 
					"\n" + 
					"(4,2009,0.90),\n" + 
					"(4,2010,0.81),\n" + 
					"(4,2011,0.72),\n" + 
					"(4,2012,0.63),\n" + 
					"(4,2013,0.54),\n" + 
					"(4,2014,0.45),\n" + 
					"(4,2015,0.36),\n" + 
					"\n" + 
					"(5,2009,0.50),\n" + 
					"(5,2010,0.71),\n" + 
					"(5,2011,0.62),\n" + 
					"(5,2012,0.43),\n" + 
					"(5,2013,0.34),\n" + 
					"(5,2014,0.15),\n" + 
					"(5,2015,0.16),\n" + 
					"\n" + 
					"(6,2009,0.50),\n" + 
					"(6,2010,0.71),\n" + 
					"(6,2011,0.72),\n" + 
					"(6,2012,0.83),\n" + 
					"(6,2013,0.84),\n" + 
					"(6,2014,0.95),\n" + 
					"(6,2015,0.96);\n" + 
					"";
			sqlText += "UPDATE country SET population = population * 100000; ";
			
				
			System.out.println("Executing this command: \n" + sqlText.replaceAll("\\s+", " ") + "\n");
    			sql.executeUpdate(sqlText);	
    			sql.close();
    			
}
	 		catch(SQLException e)
	 		{
	 			System.out.println("Query Exection Failed!");
	 	                  e.printStackTrace();
	 	                  return ;
	 	}  
} 
 
 public void getCountries()
 {
	 try {
	 sql = connection.createStatement();
	 String sqlText = "SELECT *       " +
		        " FROM country";
		System.out.println("Now executing the command: " + sqlText.replaceAll("\\s+", " ") + "\n");
			rs = sql.executeQuery(sqlText);
		if (rs != null){
		while (rs.next()){
		System.out.println("cid = " + rs.getInt(1) + "; country = "+rs.getString(2) + "; height = " + rs.getInt(3) + "; population = "+ rs.getInt(4) + "\n");
		}
		}

		//Close the resultset
		rs.close();
	 }
	 catch(SQLException e)
		{
			System.out.println("Query Exection Failed!");
	                  e.printStackTrace();
	                  return ;
	}  

 
 }
}


