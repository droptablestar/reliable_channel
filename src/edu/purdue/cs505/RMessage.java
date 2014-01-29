package edu.purdue.cs505;

public class RMessage implements Message {
    public static final int ACK = 0x00;

    public long timeout;
    private String message;

    public RMessage(String message) {
        this.timeout = System.currentTimeMillis();
        this.message = message;
    }
    
    public String getMessageContents() { return message; }

    public void setMessageContents(String contents) {
        this.message = contents;
    }

    public int compare(RMessage m1, RMessage m2) {
        return (int)(m1.timeout - m2.timeout);
    }

    // TODO: return the ID for this message
    public int getMessageID(RMessage msg) {
        return 0;
    }
}