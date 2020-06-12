package at.htlkaindorf.contactlist.bl;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class Contact
{
    private String firstname;
    private String lastname;
    private String language;
    private char gender;
    private URL picture;
    private String phoneNumber;

    public Contact(String line){
        String[] tokens = line.split("\\,");
        this.firstname = tokens[1];
        this.lastname = tokens[2];
        this.language = tokens[3];
        this.gender = tokens[4].charAt(0);
        try {
            this.picture = new URL(tokens[5]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.phoneNumber=tokens[6];
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public URL getPicture() {
        return picture;
    }

    public void setPicture(URL picture) {
        this.picture = picture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
