package edu.purdue.cs505;

import java.io.*;
import java.net.*;
import java.util.List;

public class ReceiveThread extends Thread {
    private DatagramSocket socket;
    private int portNumber;
    private RChannelReceiver rcr;
    
    private List<Integer> ackList;

    public ReceiveThread(int portNumber, RChannelReceiver rcr,
                         List<Integer> ackList) {
        this.portNumber = portNumber;
        this.rcr = rcr;
        this.ackList = ackList;
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
                /* TODO: somehow if this message is an ACK put the sequence
                   number in ackList. i will remove the corresponding message
                   from the queue and from the ackList. ackList is threadsafe
                   so dont worry about access ;)
                */
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