/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Student;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10jon
 */
public class DB_Tester {
    
    private Connection connection;
    
    public DB_Tester() throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
    }
    
    public static void main(String[] args) {
        try {
            DB_Tester dbt = new DB_Tester();
            dbt.connect("studentdb");
            //dbt.createTable();
            dbt.insertStudent(new Student(0, 1, "Leon", "Anghel", LocalDate.of(2002, 10, 24)));
            dbt.insertStudent(new Student(0, 2, "Nico", "Baumann", LocalDate.of(2002, 7, 31)));
            dbt.insertStudent(new Student(0, 3, "Bernie", "Sanders", LocalDate.of(2003, 6, 12)));
            dbt.showTableContent();
            dbt.disconnect();
        } catch (ClassNotFoundException ex) {          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void connect(String dbname) throws SQLException{
        String url = "jdbc:postgresql://localhost:5432/"+dbname;
        String user = "postgres";
        String password = "postgres";
        connection = DriverManager.getConnection(url, user, password);
    }
    
    
    public void createDB(String dbname) throws SQLException{
        String sqlString = "CREATE DATABASE " + dbname.toLowerCase();
        Statement statement = connection.createStatement();
        statement.execute(sqlString);
        statement.close();

    }
    
    public void insertStudent(Student s) throws SQLException{
        Statement statement = connection.createStatement();
        java.sql.Date sqlDate = java.sql.Date.valueOf(s.getDateOfBirth());
        String sqlStringg = "INSERT INTO student (catlognr, firstname, lastname, dateofbirth)\n" +
        "VALUES ("+s.getCatlognr()+", '"+s.getFirstname()+"', '"+s.getLastname()+"', '"+sqlDate.toString()+"');";
        statement.executeUpdate(sqlStringg);
    }
    
    public void createTable() throws SQLException{
        String sqlString = "CREATE TABLE student\n" +
        "(\n" +
        " student_id SERIAL PRIMARY KEY,\n" +
        " catlognr INTEGER NOT NULL,\n" +
        " firstname VARCHAR(100) NOT NULL,\n" +
        " lastname VARCHAR(100) NOT NULL,\n" +
        " dateofbirth DATE NOT NULL\n" +
        ");";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlString);
        statement.close();
    }
    
    public void showTableContent() throws SQLException{
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM student";
        ResultSet rs = statement.executeQuery(sqlQuery);
        while(rs.next()){
            int id = rs.getInt(1);
            int catlognr = rs.getInt(2);
            String firstname = rs.getString(3);
            String lastname = rs.getString(4);
            Date dateOfBirth = rs.getDate(5);
            Student s = new Student(id, catlognr, firstname, lastname, dateOfBirth.toLocalDate());
            System.out.println(s);
        }
        
    }
    
    public void disconnect() throws SQLException{
        if(connection != null){
            connection.close();
        }
    }  
}
