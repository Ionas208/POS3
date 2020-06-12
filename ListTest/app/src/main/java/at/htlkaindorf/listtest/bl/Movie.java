package at.htlkaindorf.listtest.bl;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Movie {

    private String name;
    private YearMonth released;
    private int duration;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("MMMM - yyyy");

    public Movie(String name, YearMonth released, int duration) {
        this.name = name;
        this.released = released;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public YearMonth getReleased() {
        return released;
    }

    public void setReleased(YearMonth released) {
        this.released = released;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getReleasedString(){
        return DTF.format(released);
    }

    public String getDurationString(){
        return String.format("%02d:%02d", duration/60, duration%60);
    }


}
