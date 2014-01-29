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
    
    public ReceiveThread(int portNumber, RChannelReceiver rcr) {
        this.portNumber = portNumber;
        this.rcr = rcr;
    }
    
    public void run() {
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