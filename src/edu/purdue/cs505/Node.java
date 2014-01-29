package edu.purdue.cs505;

import java.io.*;
import java.util.Iterator;

public class Node {
    public static void main(String args[]) {
        System.out.println("Hello, world!");

        RChannelReceiver rcr = new RChannelReceiver();
        int port = Integer.parseInt(args[1]);
        RChannel channel = new RChannel(port);
        channel.init(args[0], 6666);
        channel.rlisten(rcr);
        
        // create message with timeou value of current system time.
        RMessage msg0 = new RMessage("msg0: " + (6666 + port));
        channel.rsend(msg0);

        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("HFDS:");
        }
        RMessage msg1 = new RMessage("msg1: " + (6666 + port));
        channel.rsend(msg1);

        for (Iterator<RMessage> m = channel.sThread.messageQueue.iterator();
             m.hasNext(); ) {
            RMessage msg = m.next();
            System.out.println(msg.timeout + " : " + msg.getMessageContents());
        }
        System.out.println("main FIN.");
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        try {
            br.readLine();
        } catch(IOException e) {
            System.err.println("IO err: " + e);
        }
        channel.halt();
    }
}
