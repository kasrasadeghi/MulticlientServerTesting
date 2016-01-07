/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientservertesting;

import java.net.ServerSocket;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Sarah M
 */
public class MulticlientServerTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket acceptor = null;
        short port = 8080;
        Scanner sysin = new Scanner(System.in);

        try {
            boolean cont = true;
            acceptor = new ServerSocket(port);
            int counter = 0;
            
            while (cont) {
                counter++;
                new ClientHandlingThread(counter, acceptor.accept()).start();
                System.out.println("type 'yes' to find another client");
                cont = sysin.next().equals("yes");
            }
            while (true) {
                System.out.println("press enter to close.");
                sysin.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
