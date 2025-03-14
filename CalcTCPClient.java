/*
 * A TCP Client that allows user to input text file and send it to the CalcTCPServer
 * for arithmetic calculatotion
 *  Input:  a text file containing artihemtic equations
 *  Output: the answer to each arithmetic question, displayed on the terminal
 * 
 * Author: Hy Pham
 * Date Created: march 13, 2025
 */

 import java.io.*;
 import java.net.*;
 
public class CalcTCPClient {
    public static void main(String args[]) throws Exception {

        //String variable to hold the equation from textfile
		String equation;

        //String variable to hold the answer to an equation
		String arithmeticOutput;

        //Input stream to accept String input from text file
		BufferedReader inFromUser = new BufferedReader(new FileReader(args[0]));

		Socket clientSocket = new Socket(args[0], 6789);

		DataOutputStream outToCalcServer = new DataOutputStream( 
				clientSocket.getOutputStream());
                
		BufferedReader inFromCalcServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));

       
        //integer i to denote question number        
        int i = 0;

        // Begin measuring execution time
        long startTime = System.nanoTime();

        //Read in the first line of String from the text file into variable equation
        equation = inFromUser.readLine();

        // While-loop to check if there is any questions left in the text file
        while (equation != "") {

            i++; 

            //Send the line of String to server for computation
            outToCalcServer.writeBytes(equation + '\n');

            //Store the output from the server into arithmeticOutput
            arithmeticOutput = inFromCalcServer.readLine();

            //Print out the question and the answer
            System.out.println("#" + i + "--------------------------------\n" 
                            + "Question from client:" + equation + "\n"
                            + "Answer from server:" + arithmeticOutput + "\n"
            );

            equation = inFromUser.readLine();

        }

        equation = "DONE/n";
        outToCalcServer.writeBytes(equation + '\n');
    
        //Stop measuring execution time
        long endTime = System.nanoTime(); 
		
        //Calculating the average time 
        long averageTime = ((endTime - startTime) / i) / 1000000;
        
        inFromUser.close();
		clientSocket.close();
	}
}
