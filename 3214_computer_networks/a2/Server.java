/* 
 * Name: Rishab Dhamija
 * Student number: 213223334
 * course: EECS 3214 Fall 2018
 * Assignment #: 2
 * Date: 8 November, 2018
 * Source: EECS 3214 Fall 2018 course webpage 
 */
import java.net.*;
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
 * SERVER SENDS THE CONNECTION START TIME TO EACH OF THE CLIENTS AS WELL AS WRITES IT TO FILE SERVER.OUT
 * THE SERVER HOLDS THE CONNECTION FOR 10 SECONDS SO THAT THE CONNECTIONS OF ALL THE CLIENTS CAN OVERLAP (SECOND REQUIREMENT)
 * SERVER CLOSES THE CONNECTION AND WRITES THE CONNECTION TERMINATION TIME IN SERVER.LOG FILE
 */



/*This class implements the server which allows concurrent connections on a single TCP port
 * Aim is to send all the three connection requests at the same time to the server
 * there are 2 classes: KKMultiServer and KKMultiServerThread
 * KKMultiserver receives the request from the client at port number specified at args[0] 
 * Then it creates a separate thread each for a client in class KKMultiServerThread and starts the connection to that client in that respective thread
 * There is only 1 argument to this class:
 * args[0] : port number for the server to allow multiple client requests on it 
 */




public class Server {
    public static void main(String[] args) throws IOException {

    if (args.length != 1) {
        System.err.println("Usage: java KKMultiServer <port number>");
        System.exit(1);
    }
       int portNumber = Integer.parseInt(args[0]);
       boolean listening = true;
        try (ServerSocket serverSocket = new ServerSocket(portNumber);)   
        { 
            while (listening) {
            // on receiving a connection request, KKMultiServer a separate thread through class KKMultiServerThread	
	        new ServerThread(serverSocket.accept()).start();          
	        }
	    } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}