package edu.purdue.cs505;

import java.io.*;
import java.util.Iterator;

public class Node {
    public static void main(String args[]) {
        int id = Integer.parseInt(args[1]);

        RChannelReceiver rcr = new RChannelReceiver();
        RChannel channel = new RChannel(id);
        channel.init(args[0], 6666);
        channel.rlisten(rcr);
        
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        if (id == 0) {
            try {
                String msg;
                while ((msg = br.readLine()) != null)
                    channel.rsend(new RMessage(msg));
            } catch(IOException e) {
                System.err.println("IO err: " + e);
            }
        }

        // channel.halt();

        // channel.init(args[0], 6666);
        // if (id == 0) {
        //     try {
        //         String msg;
        //         while ((msg = br.readLine()) != null)
        //             channel.rsend(new RMessage(msg));
        //         System.out.print("PRESS ENTER FOO!! ");
        //         br.readLine();
        //     } catch(IOException e) {
        //         System.err.println("IO err: " + e);
        //     }
        // }
        // System.out.print("\nHALT\n");
        // channel.halt();
    }
}
