package at.htlkaindorf.travelplanner.bl;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Trip implements Serializable {
    private String city;
    private String country;
    private String countryCode;
    private LocalDate startDate;
    private int duration;
    private static final long serialVersionUID = 1234567890L;

    public Trip(String city, String country, String countryCode, LocalDate startDate, int duration) {
        this.city = city;
        this.country = country;
        this.countryCode = countryCode;
        this.startDate = startDate;
        this.duration = duration;
    }

    public Trip(String line){
        // city - country - country_code - startDate - duration in days
        String[] tokens = line.split(" - ");
        this.city = tokens[0];
        this.country = tokens[1];
        this.countryCode = tokens[2];

        String[] dateParts = tokens[3].split("\\.");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        this.startDate = LocalDate.of(year, month, day);

        this.duration = Integer.parseInt(tokens[4]);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getStartDatePlusDuration(){
        return this.startDate.plusDays(this.duration);
    }

}
