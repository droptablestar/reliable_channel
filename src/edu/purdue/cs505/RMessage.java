package edu.purdue.cs505;

public class RMessage implements Message {
    public static final int ACK = 0x00;

    public long timeout;
    public int seqNum;
    public int isACK;
    private String message;

    public RMessage(String message) {
	//Got the whole msg serialized, gotta strip off the seq number
	String seqString = "";
	int index = 0;
	while(true){
		Character c = message.charAt(index);
		if(Character.isDigit(c)){
			seqString += c;
		}
		else if(c == ':'){
			break;
		}
		index++;
		//TODO: add more to strip off ACK info?
	}
	int seqNum = Integer.parseInt(seqString);
	String strippedMsg = message.substring(index+1);

	//set everything//
	if(message.charAt(index+1) == '1'){
		this.isACK = 1;
	}
	else{
		this.isACK = 0;
	}
	this.seqNum = seqNum;
        this.timeout = System.currentTimeMillis();
        this.message = strippedMsg;
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
