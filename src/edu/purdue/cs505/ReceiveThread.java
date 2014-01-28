package edu.purdue.cs505;

import java.io.*;
import java.net.*;

public class ReceiveThread extends Thread {
    DatagramSocket socket;
    DatagramPacket packet;
    byte[] buf;
    String msg;
    int portNumber;
    
    ReceiveThread(int portNumber) { this.portNumber = portNumber; } 
    
    public void run() {
        try {
            System.out.println("Server: " + portNumber);
            this.socket = new DatagramSocket(portNumber);
            buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            msg = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Server: " + msg);
        } catch (IOException e) {
            System.err.println("Init error: ServerThread()");
            System.err.println(e);
            System.exit(1);
        }
        System.out.println("server FIN.");
    }
}