package edu.purdue.cs505;

import java.io.*;
import java.net.*;

public class ReceiveThread extends Thread {
    DatagramSocket socket;
    DatagramPacket packet;
    byte[] buf;
    String msg;
    int portNumber;
    RChannelReceiver rcr;
    
    ReceiveThread(int portNumber) {
        this.portNumber = portNumber;
        rcr = new RChannelReceiver();
    }
    
    public void run(RChannelReceiver rcr) {
        try {
            System.out.println("Server: " + portNumber);
            this.socket = new DatagramSocket(portNumber);
            buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            // if (!timeout) rcr.rreceive(message)
            msg = new String(packet.getData(), 0, packet.getLength());
            System.out.println("MSG: " + msg);
        } catch (IOException e) {
            System.err.println("Init error: ServerThread()");
            System.err.println(e);
            System.exit(1);
        }
        System.out.println("server FIN.");
    }
}