package edu.purdue.cs505;

import java.io.*;
import java.net.*;
import java.util.PriorityQueue;
import java.util.List;

public class SendThread extends Thread {
    /** amount of time (in ms) to wait before resending a message */
    private final long TIMEOUT = 1000;
    
    private PriorityQueue<RMessage> messageQueue;
    private List<Integer> ackList;
    
    private DatagramSocket socket;
    private InetAddress destIP;
    private int destPort;

    private boolean stopped;
    
    public SendThread(String destIP, int destPort,
                      PriorityQueue<RMessage> messageQueue,
                      List<Integer> ackList) {
        this.stopped = false;
        this.messageQueue = messageQueue;
        this.ackList = ackList;
        this.destPort = destPort;
        
        try {
            this.destIP = InetAddress.getByName(destIP);
            socket = new DatagramSocket();
        } catch (UnknownHostException e) {
            System.err.println("SENDER -- Host name error: " + e);
            System.exit(1);
        } catch (SocketException e) {
            System.err.println("SENDER -- socket error: " + e);
            System.exit(1);
        }
    } // SendThread()

    public void run() {
        while (!stopped) {
            RMessage msg = messageQueue.peek();
            long now = System.currentTimeMillis();
            while (msg != null && (now - msg.timeout) >= TIMEOUT) {
                // send message, create new message, and put it back in queue
                msg = messageQueue.poll();
                send(msg);
                messageQueue.offer(new RMessage(msg.getMessageContents()));
                msg = messageQueue.peek();
                /* TODO: check to see if this sequence number is in the ACK
                   list. if it is just discard. might want to sort the ACK list
                   to allow quicker searching.
                */
            }
        }
    } // run()

    public void kill() {
        this.stopped = true;
    } // kill()

    private void send(RMessage msg) {
        try {
            String message = msg.getMessageContents();
            byte[] buf = new byte[message.length()];
            buf = message.getBytes();
            DatagramPacket packet =
                new DatagramPacket(buf, buf.length, destIP, destPort);
            socket.send(packet);
        } catch(IOException e) {
            System.err.println("SENDER -- packet send error: " + e);
        }
    } // send()
}