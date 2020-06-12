package at.htlkaindorf.pairp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DayPlanner {
    private Map<LocalDate, List<Entry>> entries = new TreeMap<>();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.YYYY");


    public void addEntry(LocalDate date, Entry entry){
        if(!entries.containsKey(date)){
            List<Entry> list = new ArrayList<>();
            list.add(entry);
            list.sort(new DerComparator());
            entries.put(date, list);
        }
        else{
            List<Entry> e = entries.get(date);
            e.add(entry);
            e.sort(new DerComparator());
            entries.remove(date);
            entries.put(date, e);

        }
    }

    public  void showAllDays(){
        System.out.printf("Datum \t Einträge\n");
        System.out.printf("----------------------------------\n");
        for (Map.Entry<LocalDate, List<Entry>> entrySet : entries.entrySet()) {
            System.out.printf("<%s>\n", dtf.format(entrySet.getKey()));
            for (Entry entry: entrySet.getValue()) {
                System.out.printf("\t%s\n", entry);
            }
        }
    }

    public void showEntriesOfDay(LocalDate date){
        System.out.printf(dtf.format(date)+"\n");
        List<Entry> list = entries.get(date);
        for (Entry entry: list) {
            System.out.printf("%s\n", entry);
        }
    }

    public static void main(String[] args) {
        System.out.println("HAllo");
    }

}
