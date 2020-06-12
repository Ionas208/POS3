/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import beans.Mitarbeiter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10jon
 */
public class DB_Access {

    private Connection connection;
    public int currentPrimary;
    public String currentConnection = "Nicht verbunden";

    /*public static void main(String[] args) {
        DB_Access dba = null;
        try {
            dba = new DB_Access();
        } catch (ClassNotFoundException ex) {
        }
        try {
            dba.connect("postgres");
            dba.createDB("mitarbeiterdb");
            dba.disconnect();
            dba.connect("mitarbeiterdb");
            dba.createTable();
            dba.instertData();
            dba.getEmployeesFromDepartment(1);
            dba.getAverageSalary("F");
            Mitarbeiter test = new Mitarbeiter(dba.currentPrimary, "Hans", "Franz", LocalDate.of(2002, 8, 20), 3000, 1, "M");
            dba.insertMitarbeiter(test);
            dba.removeEmployee(test.getPers_nr());
        } catch (SQLException ex) {
            Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    public DB_Access() throws ClassNotFoundException {
        String path = System.getProperty("user.dir")+File.separator+"src"+File.separator+"res"+File.separator+"currentPrimary.txt";
        FileReader fr = null;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException ex) {
        }
        BufferedReader br = new BufferedReader(fr);
        try {
            String num = br.readLine();
            currentPrimary = Integer.parseInt(num);
        } catch (IOException ex) {
        }
        System.out.println("primary is "+currentPrimary);
        Class.forName("org.postgresql.Driver");
    }
    
    public void writeCurrentPrimaryToFile(){
        String path = System.getProperty("user.dir")+File.separator+"src"+File.separator+"res"+File.separator+"currentPrimary.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
        } catch (IOException ex) {
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write(currentPrimary+"");
            bw.close();
        } catch (IOException ex) {
        }
        System.out.println("priamry is now "+currentPrimary);
    }

    public void connect(String dbname) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/" + dbname;
        String user = "postgres";
        String password = "postgres";
        connection = DriverManager.getConnection(url, user, password);
        currentConnection = dbname;
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        currentConnection = "Nicht verbunden";
    }

    public void createDB(String dbname) {
        try {
            String sqlString = "CREATE DATABASE " + dbname.toLowerCase();
            Statement statement = connection.createStatement();
            statement.execute(sqlString);
            statement.close();
        } catch (SQLException ex) {
            try {
                this.disconnect();
                this.connect(dbname);
                String sqlString = "DROP TABLE mitarbeiter;";
                Statement statement = connection.createStatement();
                statement.execute(sqlString);
                statement.close();
            } catch (SQLException e) {

            }

        }
    }

    public void instertData() {
        try {
            String path = System.getProperty("user.dir")+File.separator+"src"+File.separator+"res"+File.separator+"mitarbeiter.csv";
             FileReader fr = null;
            try {
                fr = new FileReader(path);
            } catch (FileNotFoundException ex) {
            }
             
             BufferedReader br = new BufferedReader(fr);
            try {
                br.readLine();
            } catch (IOException ex) {
            }
             String line = "";
            try {
                while((line = br.readLine()) != null){
                    String[] parts = line.split("\\,");
                    String name = parts[0];
                    String vorname = parts[1];
                    String[] dateParts = parts[2].split("\\.");
                    int year = Integer.parseInt(dateParts[2]);
                    int month = Integer.parseInt(dateParts[1]);
                    int day = Integer.parseInt(dateParts[0]);
                    LocalDate geb_datum = LocalDate.of(year, month, day);
                    Double gehalt = Double.parseDouble(parts[3]);
                    int abt_nr = Integer.parseInt(parts[4]);
                    String geschlecht = parts[5];
                    Mitarbeiter m = new Mitarbeiter(currentPrimary, name, vorname, geb_datum, gehalt, abt_nr, geschlecht);
                    insertMitarbeiter(m);
                }
            } catch (IOException ex) {
            }
            catch(ArrayIndexOutOfBoundsException ex){
                //this is perfectly fine
            }
        } catch (SQLException ex) {
        }
    }

    public void insertMitarbeiter(Mitarbeiter m) throws SQLException {
        //No checking for "duplicates" as there wont be any because a 
        //mitarbeiter could share all of their attributes and still 
        //be different persons, if this wasn't our goal, we shouldn't use an
        //artificial primary key, rather a natural one and let the db handle it
        Statement statement = connection.createStatement();
        java.sql.Date sqlDate = java.sql.Date.valueOf(m.getGeb_datum());
        String sqlString = "INSERT INTO mitarbeiter (pers_nr, name, vorname, geb_datum, gehalt, abt_nr, geschlecht)\n"
                + "VALUES ("+m.getPers_nr()+", '"+m.getName()+"', '"+m.getVorname()+"','"+sqlDate.toString()+"',"+m.getGehalt()+", "+m.getAbt_nr()+",'"+m.getGeschlecht()+"');";
        statement.executeUpdate(sqlString);
        currentPrimary++;
    }

    public void createTable() throws SQLException {
        try{
            String sqlString = "CREATE TABLE mitarbeiter\n"
                + "(\n"
                + "	pers_nr INTEGER PRIMARY KEY NOT NULL,\n"
                + "	name VARCHAR(40) NOT NULL,\n"
                + "	vorname VARCHAR(40) NOT NULL,\n"
                + "	geb_datum DATE,\n"
                + "	gehalt NUMERIC(7,2),\n"
                + "	abt_nr INTEGER NOT NULL,\n"
                + "	geschlecht CHAR(1) NOT NULL\n"
                + ");";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlString);
        statement.close();
        }
        catch(SQLException e){
            String sqlString = "DELETE FROM mitarbeiter";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlString);
            statement.close();
        }
        
    }

    public List<Mitarbeiter> getEmployeesFromDepartment(int department) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM mitarbeiter WHERE abt_nr = " + department + " ORDER BY name, vorname";
        ResultSet rs = statement.executeQuery(sqlQuery);
        List<Mitarbeiter> list = new ArrayList<>();
        while (rs.next()) {
            int pers_nr = rs.getInt("pers_nr");
            String name = rs.getString("name");
            String vorname = rs.getString("vorname");
            Date geb_datum = rs.getDate("geb_datum");
            Double gehalt = rs.getDouble("gehalt");
            int abt_nr = rs.getInt("abt_nr");
            String geschlecht = rs.getString("geschlecht");
            Mitarbeiter m = new Mitarbeiter(pers_nr, name, vorname, geb_datum.toLocalDate(), gehalt, abt_nr, geschlecht);
            list.add(m);
        }
        return list;
    }

    public double getAverageSalary(String gender) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM mitarbeiter WHERE geschlecht = '" + gender.toUpperCase() + "'";
        ResultSet rs = statement.executeQuery(sqlQuery);
        double sum = 0;
        int counter = 0;
        while (rs.next()) {
            Double gehalt = rs.getDouble("gehalt");
            sum += gehalt;
            counter++;
        }
        return sum / counter;
    }
    
    public void removeEmployee(int pers_nr) throws SQLException{
        String sqlString = "DELETE FROM mitarbeiter WHERE pers_nr = "+pers_nr;
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlString);
        statement.close();
    }
    
}
