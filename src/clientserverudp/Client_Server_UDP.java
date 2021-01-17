/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserverudp;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author info1
 */
public class Client_Server_UDP extends Thread{
    private final String ipLocal;
    private final int localPort;

    public Client_Server_UDP(String ipLocal, int localPort) {
        this.ipLocal = ipLocal;
        this.localPort = localPort;
    }
    
    
    @Override
    public void run(){
        try {
            SocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(ipLocal),localPort);
            
            DatagramSocket datagramSocket = new DatagramSocket(socketAddress);
            
            
//            datagramSocket.bind(socketAddress);

            // create and start thread (read and write)
            new Thread(new ThreadRead(datagramSocket)).start();
            new Thread(new ThreadWrite(datagramSocket)).start();
        } catch (SocketException ex) {
            Logger.getLogger(Client_Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client_Server_UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
