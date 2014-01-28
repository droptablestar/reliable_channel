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
    }
    
    public void rsend(Message m) {
        sThread.run((RMessage)m);
    }
    
    public void rlisten(ReliableChannelReceiver rc) {
        this.rThread = new ReceiveThread(6666+id);
        this.rThread.start();
    }
    
    public void halt() {

    }
}