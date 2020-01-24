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



/*This class implements a coordinator which passes connection request to all the three clients
 * The class has 6 command-line arguments as input:
 * args[0]: hostname of client 1
 * args[1]: port number of client 1 socket 
 * args[2]: hostname of client 2
 * args[3]: port number of client 2 socket
 * args[4]: hostname of client 3
 * args[5]: port number of client 3 socket
 * THE REASON FOR PROVIDING 6 ARGUMENTS TO THE CLASS IS TO START ALL THE THREE CONNECTIONS AT THE SAME TIME SO THAT LATER THE CLIENTS CAN
 * SEND CONNECTION REQUESTS TO THE SERVER AT THE SAME TIME AS WELL.
 */




public class coordinator {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 6) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
 
        //PART 1 OF THE COORDINATOR, REQUESTING CONNECTION TO THE CLIENT01
        // hostname carries the name of the server
        String hostName1 = args[0];
        //portNumber variable carries the portNumber on which client will interact with the server
        int portNumber1 = Integer.parseInt(args[1]);

        try (
            Socket echoSocket1 = new Socket(hostName1, portNumber1);
            PrintWriter out1 =
                new PrintWriter(echoSocket1.getOutputStream(), true);
            BufferedReader in1 =
                new BufferedReader(
                    new InputStreamReader(echoSocket1.getInputStream()));
            BufferedReader stdIn1 =
                new BufferedReader(
                    new InputStreamReader(System.in));
                  ) {
              System.out.println("the data sent by the NodeAsServer: " + hostName1 + " is as follows:- ");
              System.out.println(in1.readLine());
        
        }    
         catch (UnknownHostException e1) {
            System.err.println("Don't know about host " + hostName1);
            System.exit(1);
        } catch (IOException e2) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName1);
            System.exit(1);
        }
        
      //PART 2 OF THE COORDINATOR, REQUESTING CONNECTION TO THE CLIENT02
        String hostName2 = args[2];
        //portNumber variable carries the portNumber on which client will interact with the server
        int portNumber2 = Integer.parseInt(args[3]);

        
        
        try (
                Socket echoSocket2 = new Socket(hostName2, portNumber2);
                PrintWriter out2 =
                    new PrintWriter(echoSocket2.getOutputStream(), true);
                BufferedReader in2 =
                    new BufferedReader(
                        new InputStreamReader(echoSocket2.getInputStream()));
                BufferedReader stdIn2 =
                    new BufferedReader(
                        new InputStreamReader(System.in));
                      ) {
                  System.out.println("the data sent by the NodeAsServer: " + hostName2 + " is as follows:- ");
                  System.out.println(in2.readLine());
            
            }    
             catch (UnknownHostException e3) {
                System.err.println("Don't know about host " + hostName2);
                System.exit(1);
            } catch (IOException e4) {
                System.err.println("Couldn't get I/O for the connection to " +
                    hostName2);
                System.exit(1);
            }
      //PART 3 OF THE COORDINATOR, REQUESTING CONNECTION TO THE CLIENT03
        String hostName3 = args[4];
        //portNumber variable carries the portNumber on which client will interact with the server
        int portNumber3 = Integer.parseInt(args[5]);

        try (
                Socket echoSocket3 = new Socket(hostName3, portNumber3);
                PrintWriter out3 =
                    new PrintWriter(echoSocket3.getOutputStream(), true);
                BufferedReader in3 =
                    new BufferedReader(
                        new InputStreamReader(echoSocket3.getInputStream()));
                BufferedReader stdIn3 =
                    new BufferedReader(
                        new InputStreamReader(System.in));
                      ) {
                  System.out.println("the data sent by the NodeAsServer: " + hostName3 + " is as follows:- ");
                  System.out.println(in3.readLine());
            
            }    
             catch (UnknownHostException e) {
                System.err.println("Don't know about host " + hostName3);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to " +
                    hostName3);
                System.exit(1);
            }
    }
}