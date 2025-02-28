/*
 * A TCP Server class that computes simple arithmetic equations
 *  Input:  lines of arithematic questions (as String), obtained from 
 *          CalcTCPClient application.
 *  Output: the answer to each arithmetic question (converted toString), send to 
 *          CalcTCPClient line-by-line.
 * Author: Hy Pham
 * Date Created: Feb 28, 2025
 */

import java.io.*;
import java.net.*;

public class CalcTCPServer {
    public static void main (String args[]) throws Exception {
        String arithQuestion;
        String arithAnswer;
        ServerSocket welcomeSocket = new ServerSocket(2424);
        System.out.println("Waiting for Connection From CalcTCPClient ...");
    }
}
