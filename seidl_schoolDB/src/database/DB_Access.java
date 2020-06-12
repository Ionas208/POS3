/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Student;
import io.IO_Helper;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    private final String insertString = "INSERT INTO student (classid, catno, firstname, surname, gender, dateofbirth)\n"
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private PreparedStatement insertStatement = null;

    private List<Student> students;
    private Map<String, Integer> classidMap;

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

    public int getMaxCat(String className) throws SQLException {
        Statement statement = db.getStatement();
        String sqlString = String.format("SELECT MAX(catno) FROM STUDENT WHERE classid = (SELECT MIN(classid) FROM grade WHERE classname = '%s')", className);
        ResultSet rs = statement.executeQuery(sqlString);
        db.releaseStatement(statement);
        rs.next();
        return rs.getInt(1);
    }

    public void insertNewStudent(String className, Student student) throws SQLException {
        List<Student> studentsOfClass = new ArrayList<>();
        for (int i = 1; i <= getMaxCat(className); i++) {
            studentsOfClass.add(getStudent(className, i));
        }
        for (Student s : studentsOfClass) {
            deleteStudent(s.getStudentid());
        }
        studentsOfClass.add(student);
        studentsOfClass.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        }.thenComparing(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getFirstname().compareTo(o2.getFirstname());
            }
        }));
        for (int i = 1; i <= studentsOfClass.size(); i++) {
            studentsOfClass.get(i - 1).setCatno(i);
        }
        for (Student s : studentsOfClass) {
            insertStudent(s);
        }

    }

    public void deleteStudent(int studentid) throws SQLException {
        Statement statement = db.getStatement();
        String sqlString = "DELETE FROM student WHERE studentid = " + studentid;
        statement.execute(sqlString);
        db.releaseStatement(statement);
    }

    public Student getStudent(String className, int catNr) throws SQLException {
        Statement statement = db.getStatement();
        String sqlString = String.format("SELECT * FROM STUDENT WHERE classid = (SELECT MIN(classid) FROM grade WHERE classname = '%s') AND catno = %s", className, catNr + "");
        ResultSet rs = statement.executeQuery(sqlString);
        db.releaseStatement(statement);
        rs.next();
        int id = rs.getInt("studentid");
        int classid = rs.getInt("classid");
        int catno = rs.getInt("catno");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("surname");
        String gender = rs.getString("gender");
        LocalDate dateOfBirth = rs.getDate("dateofbirth").toLocalDate();
        Student s = new Student(id, catno, className, firstname, lastname, gender, dateOfBirth);
        return s;

    }

    public List<Student> getAllStudents() throws SQLException {
        Statement statement = db.getStatement();
        String sqlString = String.format("SELECT * FROM STUDENT");
        ResultSet rs = statement.executeQuery(sqlString);
        List<Student> students = new ArrayList<>();
        Map<Integer, String> classNamesMap = getClassNamesMap();
        while (rs.next()) {
            int id = rs.getInt("studentid");
            int classid = rs.getInt("classid");
            int catno = rs.getInt("catno");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("surname");
            String gender = rs.getString("gender");
            LocalDate dateOfBirth = rs.getDate("dateofbirth").toLocalDate();
            String className = classNamesMap.get(classid);
            Student s = new Student(id, catno, className, firstname, lastname, gender, dateOfBirth);
            students.add(s);
        }
        db.releaseStatement(statement);

        return students;
    }

    public boolean insertStudent(Student student) throws SQLException {
        insertStatement = db.getConnection().prepareStatement(insertString);
        
        insertStatement.setInt(1, classidMap.get(student.getClassname()));
        insertStatement.setInt(2, student.getCatno());
        insertStatement.setString(3, student.getFirstname());
        insertStatement.setString(4, student.getSurname().toUpperCase());
        insertStatement.setString(5, student.getGender().toLowerCase());
        insertStatement.setDate(6, Date.valueOf(student.getDateOfBirth()));

        insertStatement.executeUpdate();
        return true;
    }

    public Map<Integer, String> getClassNamesMap() throws SQLException {
        Map<Integer, String> map = new HashMap<>();
        Statement statement = db.getStatement();
        String sqlString = String.format("SELECT * FROM grade");
        ResultSet rs = statement.executeQuery(sqlString);
        db.releaseStatement(statement);
        while(rs.next()){
            map.put(rs.getInt("classid"), rs.getString("classname"));
        }
        return map;
    }
    
    public Map<String, Integer> getClassNamesMapReversed() throws SQLException {
        Map<String, Integer> map = new HashMap<>();
        Statement statement = db.getStatement();
        String sqlString = String.format("SELECT * FROM grade");
        ResultSet rs = statement.executeQuery(sqlString);
        db.releaseStatement(statement);
        while(rs.next()){
            map.put(rs.getString("classname"), rs.getInt("classid"));
        }
        return map;
    }

    public Set<String> getClassNames() throws SQLException {
        Set<String> classNames = new HashSet<>();
        Statement statement = db.getStatement();
        String sqlString = "SELECT classname FROM grade";
        ResultSet rs = statement.executeQuery(sqlString);
        db.releaseStatement(statement);
        while (rs.next()) {
            classNames.add(rs.getString(1));
        }
        return classNames;
    }

    public Set<String> insertStudents() throws IOException, SQLException {
        Set<String> classNames = new HashSet<>();
        students = IO_Helper.loadStudents();
        for (Student s : students) {
            String classname = s.getClassname();
            classNames.add(classname);
        }

        Statement statement = db.getStatement();
        String sqlString = "DELETE FROM student";
        statement.execute(sqlString);
        sqlString = "DELETE FROM grade";
        statement.execute(sqlString);
        db.releaseStatement(statement);
        String insString = "INSERT INTO grade (classname) VALUES (?)";
        PreparedStatement prep = db.getConnection().prepareStatement(insString);
        for (String className : classNames) {
            prep.setString(1, className);
            prep.executeUpdate();
        }
        classidMap = getClassNamesMapReversed();
        for (Student s : students) {
            insertStudent(s);
        }

        return classNames;
    }

}
