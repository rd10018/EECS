/* 
 * Name: Rishab Dhamija
 * Student number: 213223334
 * course: EECS 3214 Fall 2018
 * Assignment #: 2
 * Date: 8 November, 2018
 * Source: EECS 3214 Fall 2018 course webpage 
 */



import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;

/* THIS DESCRIPTION BLOCK IS REPRODUCED IN EVERY CLASS SO AS TO AID PROGRAM EXECUTION LOGIC:
 * THE EXECUTION CYCLE OF THE ENTIRE PROGRAM IS AS FOLLOWS:
 * FIRSTLY, WE RUN THE SERVER WITH A PORT NUMBER ( 1 ARGUMENT)
 * SECONDLY, WE RUN 3 DIFFERENT INSTANCES OF THE CLIENTS (EACH WITH 3 ARGUMENTS)
 * FINALLY, WE RUN THE COORDINATOR WITH 6 ARGUMENTS, REQUESTING THE CONNECTION AT THE SAME TIME TO ALL THE CLIENTS	  
 * THE CLIENT CODE RUNS IN 2 PHASES
 * PHASE 1: CLIENTS ACT AS A SERVER, THEREBY WAITING FOR THE COORDINATOR TO SEND THE CONNECTION REQUEST SO THAT THEY CAN ACCEPT IT.
 * SINCE THE COORDINATOR REQUEST OCCURES TO ALL THE CLIENT CLASSES AT THE SAME TIME, THE CLIENTS ACCEPT THE REQUEST AND TERMINATE THE CONNECTION, CLOSING THE PHASE 1
 * PHASE 2: CLIENTS ACT AS A CLIENT, THEREBY SENDIN CONNECTION REQUEST TO THE SERVER
 * SINCE PHASE 2 STARTED FOR ALL THE CLIENTS AT THE SAME TIME, ALL THE CLIENTS SEND THE CONNECTION REQUEST TO THE SERVER
 * AT THE SAME TIME TO THE SAME PORT, FULFILLING THE FIRST REQUIREMENT OF THE DDOS ATTACK 
 * SERVER SENDS THE CONNECTION START TIME TO EACH OF THE CLIENTS AS WELL AS WRITES IT TO FILE SERVER.LOG
 * THE SERVER HOLDS THE CONNECTION FOR 10 SECONDS SO THAT THE CONNECTIONS OF ALL THE CLIENTS CAN OVERLAP (SECOND REQUIREMENT)
 * SERVER CLOSES THE CONNECTION AND WRITES THE CONNECTION TERMINATION TIME IN SERVER.LOG FILE
 */




/*This class implements the client - server thread 
 * It prints the time connection starts as well as the time when connection ends to a  file named "server.log"
 * It also sends the connection start time to the client 
 * As soon as the connection begins, the KKMultiServerThread sends the connection time to the client, waits for 10 seconds, and then closes the connection
 */



import java.util.*;

public class ServerThread extends Thread {
    private Socket socket = null;
    public ServerThread(Socket socket) {
        super("ServerThread");
        this.socket = socket;    
    }
    public void run() {

        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        		BufferedWriter stdOut = 
        	      		   new BufferedWriter(new FileWriter("server.log",true));	
        		
        ) {
        	
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        	Date date = new Date();
        	
        	stdOut.append("CLIENT WITH IP ADDRESS : " + socket.getInetAddress() + " and with port# " + socket.getPort() + " Logged in at ->" +  dateFormat.format(date) + " time. \n" );
        	stdOut.newLine();
            	out.println(  dateFormat.format(date) );
            // The following try catch  is to pause/delay the execution of the program for 10 seconds(to overlap multiple client connections at the same time) 
            	try
            	{
            		Thread.sleep(10000);
            	}
            	catch(InterruptedException e1)
                {
              	  Thread.currentThread().interrupt();
                }
            	
           	 date = new Date();
           	socket.close();
           stdOut.append("CLIENT WITH IP ADDRESS : " + socket.getInetAddress() + " and with port# " + socket.getPort() + " Logged out at ->" +  dateFormat.format(date) + " time. \n" );
        	stdOut.newLine();
          }
          
           
         catch (IOException e) {
        	e.printStackTrace();
        }
    }
}