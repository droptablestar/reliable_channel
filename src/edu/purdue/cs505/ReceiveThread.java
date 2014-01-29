package edu.purdue.cs505;

import java.io.*;
import java.net.*;

public class ReceiveThread extends Thread {
    private DatagramSocket socket;
    private int portNumber;
    private RChannelReceiver rcr;
    
    public ReceiveThread(int portNumber, RChannelReceiver rcr) {
        this.portNumber = portNumber;
        this.rcr = rcr;
    }
    
    public void run() {
        System.out.println("Receiver: " + portNumber);
        try {
            this.socket = new DatagramSocket(portNumber);
            while (true) {
                byte[] buf = new byte[65536];
                DatagramPacket packet =
                    new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                // if (!timeout) rcr.rreceive(message)
                String msg =
                    new String(packet.getData(), 0, packet.getLength());
                System.out.println("received MSG: " + msg);
            }
        } catch (IOException e) {
            System.err.println("Init error: ServerThread()");
            System.err.println(e);
            System.exit(1);
        }
        System.out.println("receiver FIN.");
    }
}