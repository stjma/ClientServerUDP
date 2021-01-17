/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserverudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author info1
 */
public class ThreadRead implements Runnable {

    private final DatagramSocket datagramSocket;

    public ThreadRead(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {
        byte[] buf = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buf, 1024);
        while (true) {

            try {
                datagramSocket.receive(datagramPacket);
                String message =new String(datagramPacket.getData());
                System.out.println(String.format("message %s",message));
                System.out.println(String.format("emeteur %s", datagramPacket.getAddress().toString()));
                System.out.println(String.format("port %d",datagramPacket.getPort()));
            } catch (IOException ex) {
                Logger.getLogger(ThreadRead.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
