/* 
 * Name: Rishab Dhamija
 * Student number: 213223334
 * course: EECS 3214 Fall 2018
 * Assignment #: 1
 * Date: 30 september, 2018
 * Source: EECS 3214 Fall 2018 course webpage 
 * Source: https://docs.oracle.com/javase/tutorial/networking/sockets/examples/EchoClient.java
 * Source: https://docs.oracle.com/javase/7/docs/api/java/io/FileWriter.html
 */
import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
 
        // hostname carries the name of the server
        String hostName = args[0];
        //portNumber variable carries the portNumber on which client will interact with the server
        int portNumber = Integer.parseInt(args[1]);

        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
           // a bufferedWriter to create a new file "received.out" and to write to it.
            BufferedWriter stdOut = 
        		   new BufferedWriter(new FileWriter("received.out"));
        		//Scanner sc = new Scanner(System.in);
        ) {
            // number variable represents the string version of the input integer from the client
        	String number = stdIn.readLine();
        	//userInput variable holds the string to be sent to the server
        	String userInput =stdIn.readLine();
             // sending number to the server   
        	out.println(number);
        	//sending string userInput to the server
            out.println(userInput);
             
            // sample holds the value read from the server each time
              String sample;
             // name of server as well as the output sent by it will be displayed below.
              System.out.println("the data sent by the Server: " + hostName + " is as follows:- ");
              //this loop will work until the server keeps on sending the string to client
              while((sample = in.readLine())!=null)
              {
            	  
                    System.out.println(sample);
                  //adding the value received from the server to files(without erasing the existing data inside it)
                    stdOut.append(sample);
                    //inserting a newline after each string gets inserted into the file "received.out"
                    stdOut.newLine();
              }
        
        }    
         catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
    }
}