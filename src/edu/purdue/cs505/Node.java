package edu.purdue.cs505;

import java.io.*;
import java.util.Iterator;

public class Node {
    public static void main(String args[]) {
        System.out.println("Hello, world!");

        RChannelReceiver rcr = new RChannelReceiver();
        int id = Integer.parseInt(args[1]);
        RChannel channel = new RChannel(id);
        channel.init(args[0], 6666);
        channel.rlisten(rcr);
        
        // create message with timeou value of current system time.
        RMessage msg0 = new RMessage("msg0-->" + (6666 + id));
        RMessage msg1 = new RMessage("msg1-->" + (6666 + id));

        if (id == 0) 
            channel.rsend(msg0);
        
        // System.out.println("MAIN:");
        // for (Iterator<RMessage> m = channel.messageQueue.iterator();
        //      m.hasNext(); ) {
        //     RMessage msg = m.next();
        //     System.out.println(msg.timeout + " : " + msg.getMessageContents());
        // }
        // try {
        //     Thread.sleep(1000);
        // } catch(InterruptedException e) {
        //     System.out.println("HFDS:");
        // }
        // channel.rsend(msg0);
        // channel.rsend(msg1);
        // System.out.println("main FIN.");

        try {
            BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter: ");
            br.readLine();
        } catch(IOException e) {
            System.err.println("IO err: " + e);
        }

        System.out.print("\nHALT");
        channel.halt();
    }
}
