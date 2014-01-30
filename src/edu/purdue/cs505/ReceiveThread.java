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
            // socket.setSoTimeout(1);
            while (true) {
                byte[] buf = new byte[65536];
                DatagramPacket packet =
                    new DatagramPacket(buf, buf.length);
                /* TODO: make this non-blocking??? */
                socket.receive(packet);
                String msg =
                    new String(packet.getData(), 0, packet.getLength());

		//build an RMessage out of this bidniss
		RMessage finalProduct = new RMessage();
                finalProduct.setMessageContents(msg);
                
		rcr.rreceive(finalProduct);
            }
        } catch (IOException e) {
            System.err.println("Init error: ServerThread()");
            System.err.println(e);
            System.exit(1);
        }
        System.out.println("receiver FIN.");
    }
}
