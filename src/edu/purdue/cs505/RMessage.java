package edu.purdue.cs505;

import java.util.Arrays;

public class RMessage implements Message {
    private final String ACK = "0";
    private final String NACK = "1";

    private long timeout;
    private static int seqNum = 0;
    private String message;

    public RMessage() { this.message = ""; }
    
    public RMessage(String message) {
        this.message = NACK + "" + (seqNum++) + ":" + message;
        this.timeout = System.currentTimeMillis();
    }
    
    public String getMessageContents() {
        return message;
    }

    public void setMessageContents(String contents) {
        this.message = contents;
    }

    public boolean isACK() {
        return message.substring(0,1).compareTo(ACK) == 0 ? true : false;
    }
    
    public int getMessageID() {
        return Integer.parseInt(message.substring(1, message.indexOf(':')));
    }

    public void makeACK() {
        message = ACK + "" + message.substring(1);
    }
    
    public void printMsg() {
        System.out.println("["+message+"]");
    }

    public long getTimeout() { return this.timeout; }
}
