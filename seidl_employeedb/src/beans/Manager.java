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
public class Manager {
    private String first_name;
    private String last_name;
    private LocalDate from_date;
    private LocalDate to_Date;

    public Manager(String first_name, String last_name, LocalDate from_date, LocalDate to_Date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.from_date = from_date;
        this.to_Date = to_Date;
    }

    @Override
    public String toString() {
        return "Manager{" + "first_name=" + first_name + ", last_name=" + last_name + ", from_date=" + from_date + ", to_Date=" + to_Date + '}';
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDate from_date) {
        this.from_date = from_date;
    }

    public LocalDate getTo_Date() {
        return to_Date;
    }

    public void setTo_Date(LocalDate to_Date) {
        this.to_Date = to_Date;
    }
    
    
    
    
}
