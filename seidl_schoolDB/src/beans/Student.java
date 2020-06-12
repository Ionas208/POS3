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
    private int studentid;
    private int catno;
    private String classname;
    private String firstname;
    private String surname;
    private String gender;
    private LocalDate dateOfBirth;

    public Student(int studentid, int catno, String classname, String firstname, String surname, String gender, LocalDate dateOfBirth) {
        this.studentid = studentid;
        this.catno = catno;
        this.classname = classname;
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getCatno() {
        return catno;
    }

    public void setCatno(int catno) {
        this.catno = catno;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student{" + "studentid=" + studentid + ", catno=" + catno + ", classname=" + classname + ", firstname=" + firstname + ", surname=" + surname + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + '}';
    }

    
    
    
    
}
