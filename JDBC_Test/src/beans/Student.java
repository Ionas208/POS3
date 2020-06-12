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
public class Student {
    private int student_id;
    private int catlognr;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;

    public Student() {
    }

    public Student(int student_id, int catlognr, String firstname, String lastname, LocalDate dateOfBirth) {
        this.student_id = student_id;
        this.catlognr = catlognr;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student{" + "student_id=" + student_id + ", catlognr=" + catlognr + ", firstname=" + firstname + ", lastname=" + lastname + ", dateOfBirth=" + dateOfBirth + '}';
    }

    
    
    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCatlognr() {
        return catlognr;
    }

    public void setCatlognr(int catlognr) {
        this.catlognr = catlognr;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    
    
}
