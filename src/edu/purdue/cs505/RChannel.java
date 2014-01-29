package edu.purdue.cs505;

public class RChannel implements ReliableChannel {
    public ReceiveThread rThread;
    public SendThread sThread;
    
    private int id;
    
    public RChannel(int id) {
        this.id = id;
    }

    public void init(String destinationIP, int destinationPort) {
        int dest = destinationPort == (6666+id) ? 6667 : 6666;
        sThread = new SendThread(destinationIP, dest);
        
        sThread.start();
    }
    
    public void rsend(Message m) {
        // put message m in buffer with timed out value.
        sThread.messageQueue.put((RMessage)m);
    }
    
    public void rlisten(ReliableChannelReceiver rcr) {
        rThread = new ReceiveThread(6666 + this.id, (RChannelReceiver)rcr);
        rThread.start();
    }
    
    public void halt() {
        sThread.kill();
        try {
            sThread.join();
        } catch(InterruptedException e) {
            System.err.println("halt() " + e);
        }
    }
}