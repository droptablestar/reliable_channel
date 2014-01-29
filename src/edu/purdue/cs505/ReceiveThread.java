package edu.purdue.cs505;

import java.io.*;
import java.net.*;
import java.util.List;

public class ReceiveThread extends Thread {
    private DatagramSocket socket;
    private int portNumber;
    private RChannelReceiver rcr;
    
    private List<RMessage> ackList;
    private List<RMessage> toAck;

    public ReceiveThread(int portNumber, RChannelReceiver rcr,
                         List<RMessage> ackList, List<RMessage> toAck) {
        this.portNumber = portNumber;
        this.rcr = rcr;
        this.rcr.setAckList(ackList);
        this.rcr.setToAck(toAck);
        this.ackList = ackList;
        this.toAck = toAck;
    }
    
    public void run() {
        System.out.println("Receiver: " + portNumber);
        try {
            this.socket = new DatagramSocket(portNumber);
            socket.setSoTimeout(1);
            while (true) {
	    	try{
                    byte[] buf = new byte[65536];
                    DatagramPacket packet =
                    new DatagramPacket(buf, buf.length);
		    /* TODO: make this non-blocking??? */
        	    socket.receive(packet);
                    // if (!timeout) rcr.rreceive(message)
                    /* TODO: somehow if this message is an ACK put the sequence
                    number in ackList. i will remove the corresponding message
                    from the queue and from the ackList. ackList is threadsafe
                    so dont worry about access ;)
                    */
                    String msg =
                    new String(packet.getData(), 0, packet.getLength());
		    //build an RMessage out of this bidniss
		    RMessage finalProduct = new RMessage();
                    finalProduct.setMessageContents(msg);
                    System.out.println("Received msg: " + msg);
                    finalProduct.printMsg();
                
		    //if an ACK, put it on the ackList
		    // if(finalProduct.isACK()){
                    //     ackList.add(finalProduct);
		    // }
		    rcr.rreceive(finalProduct);
                    System.exit(1);
		} catch (SocketTimeoutException e) {
		    System.err.println(e);
		}
            }
        } catch (IOException e) {
            System.err.println("Init error: ServerThread()");
            System.err.println(e);
            System.exit(1);
        }
        System.out.println("receiver FIN.");
    }
}
