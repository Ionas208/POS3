/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import beans.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 10jon
 */
public class IO_Helper {

    public static List<Student> loadStudents() throws FileNotFoundException, IOException{
        List<Student> students = new ArrayList<>();
        Map<String, Integer> catNrMap = new HashMap<>();
        Path studentFile = Paths.get(System.getProperty("user.dir"), "src", "res", "Studentlist_3XHIF.csv");
        FileReader fr = new FileReader(studentFile.toFile());
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        br.readLine();
        //Klasse;Familienname;Vorname;Geschlecht;Geburtsdatum
        while((line = br.readLine()) != null){
            String[] parts = line.split("\\;");
            String classname = parts[0];
            String surname = parts[1];
            String firstname = parts[2];
            String gender = parts[3];
            String[] dateOfBirth = parts[4].split("\\-");
            int year = Integer.parseInt(dateOfBirth[0]);
            int month = Integer.parseInt(dateOfBirth[1]);
            int day = Integer.parseInt(dateOfBirth[2]);
            LocalDate doB = LocalDate.of(year, month, day);
            int catNr;
            if(catNrMap.containsKey(classname)){
                catNr = catNrMap.get(classname);
                catNrMap.remove(classname);
                catNrMap.put(classname, catNr+1);
            }
            else{
                catNr = 1;
                catNrMap.put(classname, catNr+1);
            }
            Student s = new Student(0, catNr, classname, firstname, surname, gender, doB);
            students.add(s);
            
        }
        
        return students;
        
    }
    
    public static void exportStudents(List<Student> students, String filename) throws IOException{
        Path studentFile = Paths.get(System.getProperty("user.dir"), "src", "res", filename);
        FileWriter fw = new FileWriter(studentFile.toFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Klasse;Familienname;Vorname;Geschlecht;Geburtsdatum\n");
        for (Student s: students) {
            String fileString = String.format("%s;%s;%s;%s;%s\n", s.getClassname(), s.getSurname(), s.getFirstname(), s.getGender(), s.getDateOfBirth().toString());
            bw.write(fileString);
        }
        bw.close();
    }
    
}
