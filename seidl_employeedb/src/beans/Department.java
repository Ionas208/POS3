/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author 10jon
 */
public class Department {
    private String dept_no;
    private String dept_name;

    public Department(String dept_no, String dept_name) {
        this.dept_no = dept_no;
        this.dept_name = dept_name;
    }

    @Override
    public String toString() {
        return this.dept_name;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    
    
}
