package service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product
{
//	private int venId;
//	private int catId ;
	//private double msrp;
	private String id;
	private String name;
//	private String description;
	//private int qty;
	private double cost;

	
	public Product()
	{
	}
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
//	
//	public int getVenId()
//	{
//		return venId;
//	}
//	public void setVenId(int venId)
//	{
//		this.venId = venId;
//	}
//	public int getCatId()
//	{
//		return catId;
//	}
//	public void setCatId(int catId)
//	{
//		this.catId = catId;
//	}
//	public double getMsrp()
//	{
//		return msrp;
//	}
//	public void setMsrp(double msrp)
//	{
//		this.msrp = msrp;
//	}
	public String getName()
	{
		return name;
	}
	public void setName(String name )
	{
		this.name = name;
	}
//	public String getDescription()
//	{
//		return description;
//	}
//	public void setDescription(String description)
//	{
//		this.description = description;
//	}
//	public int getQty()
//	{
//		return qty;
//	}
//	public void setQty(int qty)
//	{
//		this.qty = qty;
//	}
	public double getCost()
	{
		return cost;
	}
	public void setCost(double cost)
	{
		this.cost = cost;
	}

	

}
