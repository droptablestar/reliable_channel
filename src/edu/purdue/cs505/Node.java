package edu.purdue.cs505;

import java.io.*;

public class Node {
    public static void main(String args[]) {
        System.out.println("Hello, world!");
        RChannel channel = new RChannel(Integer.parseInt(args[1]));
        try (
            BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in))
            ) {
                stdIn.readLine();
            } catch(IOException e) {
            System.err.println("Error in read: " + e);
            System.exit(1);
        }
        channel.init(args[0], 6666);
        RMessage m = new RMessage();
        m.setMessageContents("hello " + (6666+Integer.parseInt(args[1])));
        
        channel.rsend(m);
        System.out.println("main FIN.");
    }
}
