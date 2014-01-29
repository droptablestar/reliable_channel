package edu.purdue.cs505;

import java.util.Arrays;

public class RMessage implements Message {
    public static final char ACK = 0x00;
    public static final char NACK = 0x01;
    public long timeout;

    private static int seqNum = 0;
    private String message;

    public RMessage() { this.message = ""; }
    public RMessage(int seqNum) { this.message = ACK + "" + seqNum + ":"; }
    
    public RMessage(String message) {
        this.message = NACK + "" + (seqNum++) + ":" + message;
        this.timeout = System.currentTimeMillis();
        // printMsg();
	//Got the whole msg serialized, gotta strip off the seq number
	// String seqString = "";
	// int index = 0;
	// while(true){
	// 	Character c = message.charAt(index);
	// 	if(Character.isDigit(c)){
	// 		seqString += c;
	// 	}
	// 	else if(c == ':'){
	// 		break;
	// 	}
	// 	index++;
	// 	//TODO: add more to strip off ACK info?
	// }
	// int seqNum = Integer.parseInt(seqString);
	// String strippedMsg = message.substring(index+1);

	// //set everything//
	// if(message.charAt(index+1) == '1'){
	// 	this.isACK = 1;
	// }
	// else{
	// 	this.isACK = 0;
	// }
	// this.seqNum = seqNum;
        // this.message = strippedMsg;
    }
    
    public String getMessageContents() {
        return message;
    }

    public void setMessageContents(String contents) {
        this.message = contents;
    }

    public boolean isACK() {
        return message.getBytes()[0] == ACK ? true : false;
    }
    
    public int getMessageID() {
        return Integer.parseInt(message.substring(1, message.indexOf(':')));
    }

    public void printMsg() {
        System.out.println("["+this.message+"]");
    }
}
