package edu.purdue.cs505;

import java.io.*;

public class RMessage implements Message {
    public static final int ACK = 0x00;

    public long timeout;
    private String message;

    public RMessage(long timeout, String message) {
        this.timeout = timeout;
        this.message = message;
    }
    
    public String getMessageContents() { return message; }

    public void setMessageContents(String contents) {
        this.message = contents;
    }
}