/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclientservertesting;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Sarah M
 */
public class ClientHandlingThread extends Thread{
    private Socket socket = null;
    int clientID = 0;
    
    public ClientHandlingThread(int clientID, Socket socket) {
        super("Client " + clientID);
        this.clientID = clientID;
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream());
            
            String readline;
            while((readline = in.nextLine()) != null) {
                System.out.println("client:" + readline);
                System.out.println("sending:\n\t" + readline);
                out.println(clientID + readline);
                
                if ("stop".equals(readline))
                    out.flush();
                if ("end".equals(readline))
                    out.close();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
