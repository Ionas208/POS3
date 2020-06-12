/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Student;
import database.DB_Access;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 10jon
 */
public class TableModel extends AbstractTableModel{
    private DB_Access dba = DB_Access.getInstance();
    private List<Student> students;
    private String[] colNames = {"CatNo", "Name", "Date of birth"};

    public TableModel() throws SQLException {
        students = dba.getAllStudents();
    }
    
    

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student s = students.get(rowIndex);
        switch(columnIndex){
            case 0:
                return s.getCatNr();
            case 1:
                return s.getFirstname() + " " + s.getLastname();
            case 2:
                return s.getDateOfBirth();
        }
        return null;
    }
}
