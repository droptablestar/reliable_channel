package edu.purdue.cs505;

import java.io.*;
import java.net.*;

public class RChannel implements ReliableChannel {
    ReceiveThread rThread;
    SendThread sThread;
    int id;
    
    public RChannel(int id) {
        this.id = id;
    }

    public void init(String destinationIP, int destinationPort) {
        int dest = destinationPort == (6666+id) ? 6667 : 6666;
        sThread = new SendThread(destinationIP, dest);
        rThread = new ReceiveThread(6666+id);
        sThread.run();
    }
    
    public void rsend(Message m) {
        // put message m in buffer with timed out value
    }
    
    public void rlisten(ReliableChannelReceiver rcr) {
        rThread.run((RChannelReceiver)rcr);
    }
    
    public void halt() {

    }
}