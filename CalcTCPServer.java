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
import java.util.Stack;

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

            BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));

			DataOutputStream outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());

            
            //Read in the input string from the client. While the input string is
            //not DONE, keep reading in input.
            arithQuestion = inFromClient.readLine();

            while(arithQuestion != "DONE") {
                System.out.println(arithQuestion);
                System.out.println(!arithQuestion.equals("DONE"));
                
                if (arithQuestion == "DONE") {
                    break;
                }
                String currentAnswer = evalEx(arithQuestion) + "\n";

                arithAnswer = currentAnswer;
                outToClient.writeBytes(arithAnswer); 

                arithQuestion = inFromClient.readLine();
            }

            //Message to indicate the connection is closed.
            arithAnswer = "CLOSE\n";
            outToClient.writeBytes(arithAnswer);            
        }

    }

    /*
     * Private method evalEx() to parse input string fo an arithmetic expression
     * and calculate the answer.
     * Assumption: This function assumes that the input expression only have two operands and one operator 
     * Input: String expression containing the input string
     * Output: String result
     */
    private static String evalEx(String expression) {
        String result = "";
        double answer = 0.0;

        //Remove the first and last whitespace from expression
        expression.trim();

        //Split the input expression based on white space
        String[] term = expression.split("\\s+");

        //Parse the double value from the first and second operand in the expression
        double operand1 = Double.parseDouble(term[0]);
        double operand2 = Double.parseDouble(term[2]);

        //Depending on the operator, calculate the corresponding operation 
        //between operand 1 and 2.
        String operator = term[1];
        switch(operator) {
            case "+":
                answer = operand1 + operand2;
                break;
            case "-":
                answer = operand1 - operand2;
                break;
            case "*":
                answer = operand1 * operand2;
                break;
            case "/":
                answer = operand1 / operand2;
                break;
        }
        

        result = String.valueOf(answer);
        return result;
    }


}
