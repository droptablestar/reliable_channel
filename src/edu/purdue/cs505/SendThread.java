package edu.purdue.cs505;

import java.io.*;
import java.net.*;
import java.util.PriorityQueue;
import java.util.List;
import java.util.Iterator;

public class SendThread extends Thread {
    /** amount of time (in ms) to wait before resending a message */
    private final long TIMEOUT = 1000;
    
    private PriorityQueue<RMessage> messageQueue;
    private List<RMessage> ackList;
    private List<RMessage> toAck;
    
    private DatagramSocket socket;
    private InetAddress destIP;
    private int destPort;

    private boolean stopped;
    
    public SendThread(String destIP, int destPort,
                      PriorityQueue<RMessage> messageQueue,
                      List<RMessage> ackList, List<RMessage> toAck) {
        this.stopped = false;
        this.messageQueue = messageQueue;
        this.ackList = ackList;
        this.toAck = toAck;
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

                // message was already ACK'd. don't resend.
                if (ackList.remove(msg)) continue;
                
                send(msg);
                RMessage newMsg = new RMessage();
                newMsg.setMessageContents(msg.getMessageContents());
                messageQueue.offer(newMsg);
                msg = messageQueue.peek();
            }
            int size = toAck.size();
            RMessage[] tmpToAck = toAck.toArray(new RMessage[]{});
            /* TODO: send ACKs. they are stored in toAck. */
            for (int i=0; i<size; i++) {
                RMessage ack = new RMessage(tmpToAck[i].getMessageID());
                send(ack);
                toAck.remove(tmpToAck[i]);
            }
        }
    } // run()

    /**
     * Sets a boolean in the thread to exit the run() loop
     */
    public void kill() {
        this.stopped = true;
    } // kill()

    /**
     * Sends a message.
     *
     * @param msg message to be sent
     */
    private void send(RMessage msg) {
        try {
            String message = msg.getMessageContents();
            // System.out.print("sending: ");
            // msg.printMsg();
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