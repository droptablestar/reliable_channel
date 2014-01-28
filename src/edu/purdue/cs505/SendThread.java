package edu.purdue.cs505;

import java.io.*;
import java.net.*;

public class SendThread extends Thread {
    DatagramSocket socket;
    DatagramPacket packet;
    InetAddress destIP;
    int destPort;
    
    SendThread(String destIP, int destPort) {
        try {
            this.destIP = InetAddress.getByName(destIP);
            this.destPort = destPort;
            socket = new DatagramSocket();
        } catch(IOException e) {
            System.err.println("CLIENT -- Host name error: " + e);
            System.exit(1);
        }
    }

    public void run(RMessage message) {
        try {
            System.out.println("Client: " + destIP + " " + destPort);
            byte[] buf = new byte[256];
            buf = (message.getMessageContents()).getBytes();
            packet = new DatagramPacket(buf, buf.length, destIP, destPort);
            socket.send(packet);
            System.out.println("client FIN.");
        } catch(IOException e) {
            System.err.println("CLIENT -- packet send error: " + e);
            System.exit(1);
        }
    }
}