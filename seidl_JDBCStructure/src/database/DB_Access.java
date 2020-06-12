/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Student;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10jon
 */
public class DB_Access {

    private static DB_Access theInstance = null;
    private DB_Database db;
    private final String ins = "INSERT INTO student (catlognr, firstname, lastname, dateofbirth)\n"
                         + "VALUES(?, ?, ?, ?)";
    private PreparedStatement insertS = null;

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

    public List<Student> getAllStudents() throws SQLException {
        db.connect();
        List<Student> students = new ArrayList<Student>();
        String sqlString = "SELECT * FROM student";
        Statement statement = db.getStatement();
        ResultSet rs = statement.executeQuery(sqlString);
        while (rs.next()) {
            int id = rs.getInt("student_id");
            int catNr = rs.getInt("catlognr");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            Date dateOfBirth = rs.getDate("dateofbirth");
            Student s = new Student(id, catNr, firstname, lastname, dateOfBirth.toLocalDate());
            students.add(s);
            System.out.println(s);
        }
        db.releaseStatement(statement);
        return students;
    }

    public boolean insertStudent(Student student) throws SQLException {
        
        if(insertS == null){
            insertS = db.getConnection().prepareStatement(ins);
        }
        
        insertS.setInt(1, student.getCatNr());
        insertS.setString(2, student.getFirstname());
        insertS.setString(3, student.getLastname());
        insertS.setDate(4, Date.valueOf(student.getDateOfBirth()));
        insertS.executeUpdate();
        return true;
    }

    public static void main(String[] args) {
        DB_Access dba = DB_Access.getInstance();
        try {
            dba.db.connect();
            dba.getAllStudents();
            dba.insertStudent(new Student(0, 69, "Lmao", "Lmbao", LocalDate.now()));
            dba.getAllStudents();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
