/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Department;
import beans.Employee;
import beans.Manager;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10jon
 */
public class DB_Access {

    private static DB_Access theInstance = null;
    private DB_Database db;

    public static DB_Access getInstance() {
        if (theInstance == null) {
            theInstance = new DB_Access();
        }
        return theInstance;
    }

    private DB_Access() {
        try {
            db = new DB_Database();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void connect() throws SQLException {
        db.connect();
    }

    public void disconnect() throws SQLException {
        db.disconnect();
    }

    public List<Department> getDepartmentNames() throws SQLException {
        List<Department> departments = new ArrayList<>();

        String sqlString = "SELECT dept_name, dept_no FROM departments";
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            String dept_name = rs.getString("dept_name");
            String dept_no = rs.getString("dept_no");
            departments.add(new Department(dept_no, dept_name));
        }
        db.releaseStatement(statement);
        return departments;
    }

    public ResultSet getEmployess(boolean filterByDept, boolean filterByGender, boolean filterByDate, String dept, String gender, LocalDate date) throws SQLException {
        String sqlString = "SELECT e.emp_no, e.first_name, e.last_name, e.gender, e.birth_date, e.hire_date, de.from_date, de.to_date, d.dept_name\n"
                + "FROM employees e\n"
                + "INNER JOIN dept_emp de ON e.emp_no = de.emp_no\n"
                + "INNER JOIN departments d ON de.dept_no = d.dept_no\n"
                + "WHERE 1=1";
        if(filterByDept && !dept.equals("")){
            sqlString += String.format("\nAND d.dept_name = '%s'", dept);
        }
        if(filterByGender){
            sqlString += String.format("\nAND e.gender = '%s'", gender.toUpperCase());
        }
        if(filterByDate){
            sqlString += String.format("\nAND e.birth_date <= '%s'::date", date.toString());
        }
        sqlString += "\nORDER BY e.last_name, e.first_name, e.birth_date, hire_date;";
        Statement statement = db.getScrollableStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        //db.releaseStatement(statement); NOT WORKING WITH THIS
        return rs;
    }

    public List<Manager> getManagement(String dept_no) throws SQLException {
        List<Manager> managers = new ArrayList<>();
        String sqlString = String.format("SELECT e.first_name, e.last_name, dm.from_date, dm.to_date\n"
                + "FROM employees e\n"
                + "INNER JOIN dept_manager dm ON dm.emp_no = e.emp_no\n"
                + "WHERE dm.dept_no = '%s';", dept_no);
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while(rs.next()){
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            LocalDate from_date = rs.getDate("from_date").toLocalDate();
            LocalDate to_Date = rs.getDate("to_date").toLocalDate();
            Manager m = new Manager(first_name, last_name, from_date, to_Date);
            managers.add(m);
        }
        return managers;
    }

    public List<String> getSalaryHistory(int emp_no) throws SQLException{
        List<String> history = new ArrayList<>();
        
        String sqlString = "SELECT salary, from_date, to_date FROM salaries WHERE emp_no = "+emp_no+" ORDER BY from_date";
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while(rs.next()){
            int salary = rs.getInt("salary");
            LocalDate from_date = rs.getDate("from_date").toLocalDate();
            LocalDate to_date = rs.getDate("to_date").toLocalDate();
            String displaytext =
                    String.format("<span style='text-align: left'><b>%s$</b>:</span> "
                        + "<span style='text-align: right'>from <span style='color: red'>"
                            + "%s</span> to <span style='color: red'>%s</span><span><br>", salary+"", from_date, to_date);
            history.add(displaytext);
        }
        
        db.releaseStatement(statement);
        
        return history;
    }
}
