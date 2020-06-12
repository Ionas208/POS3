package at.htlkaindorf.pairp;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Entry{
    private LocalTime time;
    private String text;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm");

    @Override
    public String toString() {
        return String.format("%s %s", dtf.format(time), text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(time, entry.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Entry(LocalTime time, String text) {
        this.time = time;
        this.text = text;
    }
}
