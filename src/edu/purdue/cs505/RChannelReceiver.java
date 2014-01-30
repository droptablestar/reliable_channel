package edu.purdue.cs505;

import java.util.List;

public class RChannelReceiver implements ReliableChannelReceiver {
    private List<RMessage> ackList;
    private List<RMessage> toAck;

    public void rreceive(Message m) {
        RMessage msg = (RMessage)m;

        System.out.print("adding: ");
        msg.printMsg();
        if (msg.isACK()) {
            ackList.add(msg);
            System.out.println("ADDING ACK!!!" + ackList.size());
        }
        else {
            System.out.println("received!!");
            toAck.add(msg);
        }
    }
    public void setAckList(List<RMessage> ackList) { this.ackList = ackList; }
    public List<RMessage> getToAck() { return this.ackList; }

    public void setToAck(List<RMessage> toAck) { this.toAck = toAck; }
    public List<RMessage> getAckList() { return this.toAck; }
}
