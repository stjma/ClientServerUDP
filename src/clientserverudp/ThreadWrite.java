/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserverudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author info1
 */
public class ThreadWrite implements Runnable {

    private final DatagramSocket datagramSocket;

    public ThreadWrite(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void run() {

        byte[] buf = new byte[1024];
        DatagramPacket dataGramPacket = new DatagramPacket(buf, 1024);

        Scanner keyboard = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("taper message");
                String message = keyboard.next();
                dataGramPacket.setData(message.getBytes());

                //client ip
                System.out.println("taper ip");
                String address = keyboard.next();
                dataGramPacket.setAddress(InetAddress.getByName(address));

                System.out.println("taper port");

                int port = keyboard.nextInt();

                dataGramPacket.setPort(port);

                datagramSocket.send(dataGramPacket);

            } catch (IOException ex) {
                Logger.getLogger(ThreadWrite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
