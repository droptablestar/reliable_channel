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
            System.err.println("SENDER -- Host name error: " + e);
            System.exit(1);
        }
    }

    public void run() {
        while (!stopped) {
            RMessage msg = messageQueue.peek();
            long now = System.currentTimeMillis();
            while (msg != null && (now - msg.timeout) < TIMEOUT) {
                // send message, create new message, and put it back in queue
                msg = messageQueue.poll();
                send(msg);
                messageQueue.put(new RMessage(msg.getMessageContents()));
                msg = messageQueue.peek();
            }
        }
    }

    public void kill() { this.stopped = true; }

    private void send(RMessage msg) {
        try {
            String message = msg.getMessageContents();
            byte[] buf = new byte[message.length()];
            buf = message.getBytes();
            DatagramPacket packet =
                new DatagramPacket(buf, buf.length, destIP, destPort);
            // System.out.println("Sending to: " + destPort);
            socket.send(packet);
        } catch(IOException e) {
            System.err.println("SENDER -- packet send error: " + e);
            System.exit(1);
        }
    }
}