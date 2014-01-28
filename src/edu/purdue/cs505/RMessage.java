package edu.purdue.cs505;

import java.io.*;

public class RMessage implements Message {
    public static final int ACK = 0x00;

    private String message;
    public int timeout;

    public String getMessageContents() { return message; }

    public void setMessageContents(String contents) {
        this.message = contents;
    }
}