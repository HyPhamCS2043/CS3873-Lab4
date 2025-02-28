/*
 * A TCP Server class that computes simple arithmetic equations
 *  Input:  lines of arithematic questions (as String), obtained from 
 *          CalcTCPClient application.
 *  Output: the answer to each arithmetic question (converted to String), send to 
 *          CalcTCPClient line-by-line.
 * 
 * Author: Hy Pham
 * Date Created: Feb 28, 2025
 */

import java.io.*;
import java.net.*;

public class CalcTCPServer {
    public static void main (String args[]) throws Exception {
        // String containing the aritmethic problem to be calculated
        String arithQuestion;

        // String containing the computed value of the arithmetic question in
        // arithQuestion
        String arithAnswer;

        // ServerSocket to accept connection from CalcTCPClient through port 2424
        ServerSocket welcomeSocket = new ServerSocket(2424);

        System.out.println("Waiting for Connection From CalcTCPClient ...");

        //While loop is always true to enable server to run until connection is closed
        while (true) {
            //ServerSocket to store the successful connection. 
            Socket connectionSocket = welcomeSocket.accept();

            System.out.println ("Connection to CalcTCPClient successful!");
		    System.out.println ("Waiting for input from CalcTCPClient.....");

            //
            BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));

            //
			DataOutputStream outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());

            arithQuestion = inFromClient.readLine();
        }
    }
}
