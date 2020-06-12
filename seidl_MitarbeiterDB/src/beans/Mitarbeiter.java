/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.time.LocalDate;

/**
 *
 * @author 10jon
 */
public class Mitarbeiter {
    private int pers_nr;
    private String name;
    private String vorname;
    private LocalDate geb_datum;
    private double gehalt;
    private int abt_nr;
    private String geschlecht;

    public Mitarbeiter() {
    }

    public Mitarbeiter(int pers_nr, String name, String vorname, LocalDate geb_datum, double gehalt, int abt_nr, String geschlecht) {
        this.pers_nr = pers_nr;
        this.name = name;
        this.vorname = vorname;
        this.geb_datum = geb_datum;
        this.gehalt = gehalt;
        this.abt_nr = abt_nr;
        this.geschlecht = geschlecht;
    }

    @Override
    public String toString() {
        return pers_nr+"/"+name+"/"+vorname+"/"+geb_datum.toString()+"/"+gehalt+"/"+abt_nr+"/"+geschlecht;
    }

    public int getPers_nr() {
        return pers_nr;
    }

    public void setPers_nr(int pers_nr) {
        this.pers_nr = pers_nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public LocalDate getGeb_datum() {
        return geb_datum;
    }

    public void setGeb_datum(LocalDate geb_datum) {
        this.geb_datum = geb_datum;
    }

    public double getGehalt() {
        return gehalt;
    }

    public void setGehalt(double gehalt) {
        this.gehalt = gehalt;
    }

    public int getAbt_nr() {
        return abt_nr;
    }

    public void setAbt_nr(int abt_nr) {
        this.abt_nr = abt_nr;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    
    
    
}
