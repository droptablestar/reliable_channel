package edu.purdue.cs505;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class RChannel implements ReliableChannel {
    public ReceiveThread rThread;
    public SendThread sThread;

    public List<RMessage> ackList;
    public List<RMessage> toAck;
    public PriorityQueue<RMessage> messageQueue;

    private int id;
    
    public RChannel(int id) {
        Comparator<RMessage> comparator = new RMessageComparator();
        messageQueue = new PriorityQueue<RMessage>(10, comparator);
        ackList = Collections.synchronizedList(new ArrayList<RMessage>());
        toAck = Collections.synchronizedList(new ArrayList<RMessage>());
        this.id = id;
    }

    public void init(String destinationIP, int destinationPort) {
        int dest = destinationPort == (6666+id) ? 6667 : 6666;
        sThread = new SendThread(destinationIP, dest, messageQueue,
                                 ackList, toAck);
        sThread.start();
    }
    
    public void rsend(Message m) {
        // put message m in buffer with timed out value.
        messageQueue.offer((RMessage)m);
    }
    
    public void rlisten(ReliableChannelReceiver rcr) {
        rThread = new ReceiveThread(6666 + this.id, (RChannelReceiver)rcr,
                                    ackList, toAck);
        rThread.start();
    }
    
    public void halt() {
        sThread.kill();
        try {
            sThread.join();
        } catch(InterruptedException e) {
            System.err.println("halt() " + e);
        }
    }
}