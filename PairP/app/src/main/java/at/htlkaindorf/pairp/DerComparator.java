package at.htlkaindorf.pairp;

import java.util.Comparator;

public class DerComparator implements Comparator<Entry> {
    @Override
    public int compare(Entry o1, Entry o2) {
        if (o1.getTime().isBefore(o2.getTime())){
            return -1;
        }
        else if(o1.getTime().isAfter(o2.getTime())){
            return 1;
        }
        else{
            return 0;
        }
    }
}
