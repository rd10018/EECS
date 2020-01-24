/* 
 * Name: Rishab Dhamija
 * Student number: 213223334
 * course: EECS 3214 Fall 2018
 * Assignment #: 2
 * Date: 8 November, 2018
 * Source: EECS 3214 Fall 2018 course webpage 
 */
import java.io.*;
import java.net.*;

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
 * SERVER SENDS THE CONNECTION START TIME TO EACH OF THE CLIENTS AS WELL AS WRITES IT TO FILE SERVER.OUT
 * THE SERVER HOLDS THE CONNECTION FOR 10 SECONDS SO THAT THE CONNECTIONS OF ALL THE CLIENTS CAN OVERLAP (SECOND REQUIREMENT)
 * SERVER CLOSES THE CONNECTION AND WRITES THE CONNECTION TERMINATION TIME IN SERVER.LOG FILE
 */

/* The client class has 3 arguments as input:   	
* args[0] = port number of client for accepting the connection of the coordinator
* args[1] = host name of the server the client wishes to connect
* args[2] = port number of the server socket client wants to request connection on
*
*/

public class Node {
    public static void main(String[] args) throws IOException {
      
        if (args.length != 3) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }   
         /*First Phase of client code:
          *  the client here behaves as a server and waits for the connection request from the coordinator 
          *  args[0] has the client port number on which coordinator can send the connection request
          */
        int portNumberClient = Integer.parseInt(args[0]);
        try (
             ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
             // Waiting for co-ordinator's request for connection
        	 Socket clientSocket = serverSocket.accept();     
             PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true);                   
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        	)	
        {
        	// closing the socket once the connection is established between the client as well as the coordinator
        	serverSocket.close();
        }
        catch (IOException e1) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumberClient + " or listening for a connection");
            System.out.println(e1.getMessage());
        }
        
        /*Second Phase of client code:
         * Here the client behaves as a client and send request to the server for TCP connection
         * args[1] and args[2] are the hostname as well as the port number of the server on which the client wasnts ot initiate the connection
         */
         
        
        
        String hostName = args[1];  
        int portNumber = Integer.parseInt(args[2]);
          try(
        	Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter outClient = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader inClient = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =  new BufferedReader(new InputStreamReader(System.in));     
	 )
          {
             // sample string consists of the data received from the server, here which is the login time at the server
        	 String sample;
             sample = inClient.readLine();
             System.out.println(sample);            
        }    
         catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        }catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}