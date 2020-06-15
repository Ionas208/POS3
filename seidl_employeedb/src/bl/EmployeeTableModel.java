/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Employee;
import database.DB_Access;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 10jon
 */
public class EmployeeTableModel extends AbstractTableModel {

    private ResultSet rs;
    private DB_Access dba = DB_Access.getInstance();
    private List<String> colNames = Arrays.asList("Name", "Gender", "Birthdate", "Hiredate");

    public EmployeeTableModel() throws SQLException {
        rs = dba.getEmployess(false, false, false, null, null, null);
    }

    @Override
    public int getRowCount() {
        try {
            rs.last();
            return rs.getRow();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return colNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            rs.absolute(rowIndex + 1);
            switch (columnIndex) {
                case 0:
                    return rs.getString("last_name") + ", " + rs.getString("first_name");
                case 1:
                    return rs.getString("gender");
                case 2:
                    return rs.getDate("birth_date").toLocalDate().toString();
                case 3:
                    return rs.getDate("hire_date").toLocalDate().toString();
                default:
                    return "error";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "error";
    }

    public void filter(boolean filterByDept, boolean filterByGender, boolean filterByDate, String deptname, String gender, LocalDate dateBefore) throws SQLException {
        rs = dba.getEmployess(filterByDept, filterByGender, filterByDate, deptname, gender, dateBefore);
        this.fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return colNames.get(column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            rs.absolute(rowIndex + 1);
            switch (columnIndex) {
                case 0:
                    String[] parts = ((String) (aValue)).split(", ");
                    if (parts.length > 1) {
                        rs.updateString("last_name", parts[0]);
                        rs.updateString("first_name", parts[1]);
                        rs.updateRow();
                    }
                case 1:
                    String gender = (String)aValue;
                    if((gender.toUpperCase()).equals("M") ||(gender.toUpperCase()).equals("F")){
                        /*rs.updateString("gender", gender+"::employees_gender_enum");
                        rs.updateRow();*/
                        //How to update an enum???
                    }
                    
                case 2:
                    try{
                        parts = ((String)aValue).split("-");
                        LocalDate lDate = LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                        Date date = Date.valueOf(lDate);
                        rs.updateDate("birth_date", date);
                        rs.updateRow();
                    }
                    catch(NumberFormatException e){     
                    }
                    catch(ArrayIndexOutOfBoundsException e){  
                    }
                case 3:
                    try{
                        parts = ((String)aValue).split("-");
                        LocalDate lDate = LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                        Date date = Date.valueOf(lDate);
                        rs.updateDate("hire_date", date);
                        rs.updateRow();
                    }
                    catch(NumberFormatException e){     
                    }
                    catch(ArrayIndexOutOfBoundsException e){  
                    }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }

}
