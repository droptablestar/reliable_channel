package edu.purdue.cs505;

import java.util.Comparator;

public class RMessageComparator implements Comparator<RMessage> {
    @Override
    public int compare(RMessage m1, RMessage m2) {
        return (int)(m1.timeout - m2.timeout);
    }
}