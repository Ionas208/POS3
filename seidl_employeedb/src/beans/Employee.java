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
public class Employee {
    private int emp_no;
    private String first_name;
    private String last_name;
    private String gender;
    private LocalDate birth_date;
    private LocalDate hire_Date;
    private String dept_name;
    private LocalDate from_date;
    private LocalDate to_date;

    public Employee(int emp_no, String first_name, String last_name, String gender, LocalDate birth_date, LocalDate hire_Date, String dept_name, LocalDate from_date, LocalDate to_date) {
        this.emp_no = emp_no;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.hire_Date = hire_Date;
        this.dept_name = dept_name;
        this.from_date = from_date;
        this.to_date = to_date;
    }

    @Override
    public String toString() {
        return "Employee{" + "emp_no=" + emp_no + ", first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender + ", birth_date=" + birth_date + ", hire_Date=" + hire_Date + ", dept_name=" + dept_name + ", from_date=" + from_date + ", to_date=" + to_date + '}';
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public LocalDate getHire_Date() {
        return hire_Date;
    }

    public void setHire_Date(LocalDate hire_Date) {
        this.hire_Date = hire_Date;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public LocalDate getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDate from_date) {
        this.from_date = from_date;
    }

    public LocalDate getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDate to_date) {
        this.to_date = to_date;
    }

    

    
}
