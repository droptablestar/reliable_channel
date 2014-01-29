package edu.purdue.cs505;

import java.io.*;
import java.net.*;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class SendThread extends Thread {
    private final long TIMEOUT = 1000;
    
    public PriorityBlockingQueue<RMessage> messageQueue;
    public Comparator<RMessage> comparator;

    private DatagramSocket socket;
    private DatagramPacket packet;
    private InetAddress destIP;
    private int destPort;

    private boolean stopped;
    
    public SendThread(String destIP, int destPort) {
        this.stopped = false;
        
        comparator = new RMessageComparator();
        messageQueue = new PriorityBlockingQueue<RMessage>(10, comparator);

        try {
            this.destIP = InetAddress.getByName(destIP);
            this.destPort = destPort;
            socket = new DatagramSocket();
        } catch(IOException e) {
            System.err.println("CLIENT -- Host name error: " + e);
            System.exit(1);
        }
    }

    public void run() {
        while (!stopped) {
            RMessage msg = messageQueue.peek();
            // long now = System.
            // while (msg != null && 
        }
        
        // try {
        //     // loop over timeout buffer
        //     // send anything timed out
            
        //     System.out.println("Client: " + destIP + " " + destPort);
        //     byte[] buf = new byte[256];
        //     buf = (message.getMessageContents()).getBytes();
        //     packet = new DatagramPacket(buf, buf.length, destIP, destPort);
        //     socket.send(packet);
        //     System.out.println("client FIN.");
        // } catch(IOException e) {
        //     System.err.println("CLIENT -- packet send error: " + e);
        //     System.exit(1);
        // }
    }
    public void kill() { this.stopped = true; }
}