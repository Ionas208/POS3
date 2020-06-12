/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.awt.Color;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author 10jon
 */
public class Book {

    private String title;
    private String isbnNr;
    private List<String> authors;
    private String publishers;
    private int pages;
    private float rating;
    private String genre;
    private LocalDate date;

    public Book(String title, String isbnNr, List<String> authors, String publishers, int pages, float rating, String genre, LocalDate date) {
        this.title = title;
        this.isbnNr = isbnNr;
        this.authors = authors;
        this.publishers = publishers;
        this.pages = pages;
        this.rating = rating;
        this.genre = genre;
        this.date = date;
    }

    

    @Override
    public String toString() {
        return this.title;
    }

    public String toDetailString() {
        String part1 = String.format("<html>\n"
                + "<body style='text-align: center; font-size: 14px'>\n"
                + "<div style='color: blue; font-size: 25px'>\n"
                + "    %s\n"
                + "</div>", title);
        String part2 = "";
        for (int i = 0; i < authors.size(); i++) {
            part2 += String.format("<div style='font-size: 18px'>\n"
                    + "    %s\n"
                    + "</div>", authors.get(i));
        }
        String part3 = "<hr>\n"
                + "<div>\n"
                + "    <span style=\"color: green; text-align: left; width: 10%\">ISBN: </span>\n"
                + "    <span style=\"text-align: right\">"+isbnNr+"</span>\n"
                + "</div>\n"
                + "<div>\n"
                + "    <span style=\"color: green; text-align: left; width: 10%\">Seitenzahl: </span>\n"
                + "    <span style=\"text-align: right\">"+pages+"</span>\n"
                + "</div>\n"
                + "<div>\n"
                + "    <span style=\"color: green; text-align: left; width: 10%\">Genre: </span>\n"
                + "    <span style=\"text-align: right\">"+genre+"</span>\n"
                + "</div>\n"
                + "<div>\n"
                + "    <span style=\"color: green; text-align: left; width: 10%\">Bewertungen: </span>\n"
                + "    <span style=\"text-align: right\">"+rating+"</span>\n"
                + "</div>\n"
                + "<div>\n"
                + "    <span style=\"color: green; text-align: left; width: 10%\">Erscheinungsdatum: </span>\n"
                + "    <span style=\"text-align: right\">"+date+"</span>\n"
                + "</div>\n"
                + "<div>\n"
                + "    <span style=\"color: green; text-align: left; width: 10%\">Verlag: </span>\n"
                + "    <span style=\"text-align: right\">"+publishers+"</span>\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";
        return part1 + part2 + part3;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbnNr() {
        return isbnNr;
    }

    public void setIsbnNr(String isbnNr) {
        this.isbnNr = isbnNr;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublishers() {
        return publishers;
    }

    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.pages != other.pages) {
            return false;
        }
        if (Float.floatToIntBits(this.rating) != Float.floatToIntBits(other.rating)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.isbnNr, other.isbnNr)) {
            return false;
        }
        if (!Objects.equals(this.publishers, other.publishers)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.authors, other.authors)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
    

}
