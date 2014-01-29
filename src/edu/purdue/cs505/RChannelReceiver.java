package edu.purdue.cs505;

import java.util.List;

public class RChannelReceiver implements ReliableChannelReceiver {
    private List<RMessage> ackList;
    private List<RMessage> toAck;

    public void rreceive(Message m) {
        RMessage msg = (RMessage)m;

        System.out.println(msg.isACK() + " " + msg.getMessageContents().getBytes()[0]);
        if (msg.isACK()) {
            System.out.println("ADDING ACK!!!");
            try {
                Thread.sleep(2000);
            } catch(InterruptedException e) {
                System.err.println(e);
            }
            ackList.add(msg);
        }
        else {
            System.out.print("received: ");
            msg.printMsg();
            toAck.add(msg);
        }
    }
    public void setAckList(List<RMessage> ackList) { this.ackList = ackList; }
    public List<RMessage> getToAck() { return this.ackList; }

    public void setToAck(List<RMessage> toAck) { this.toAck = toAck; }
    public List<RMessage> getAckList() { return this.toAck; }
}
