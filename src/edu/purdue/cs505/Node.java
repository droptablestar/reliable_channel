package edu.purdue.cs505;

import java.io.*;

public class Node {
    public static void main(String args[]) {
        System.out.println("Hello, world!");

        RChannelReceiver rcr = new RChannelReceiver();
        RChannel channel = new RChannel(Integer.parseInt(args[1]));
        channel.init(args[0], 6666);
        channel.rlisten(rcr);
        
        RMessage m = new RMessage();
        m.setMessageContents("hello " + (6666+Integer.parseInt(args[1])));
        
        channel.rsend(m);
        System.out.println("main FIN.");
    }
}
