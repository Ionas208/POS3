/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Employee;
import database.DB_Access;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    private List<Employee> employees;
    private List<Employee> filtered;
    private ResultSet rs;
    private DB_Access dba = DB_Access.getInstance();
    private List<String> colNames = Arrays.asList("Name", "Gender", "Birthdate", "Hiredate");
    private int counter = 0;

    public EmployeeTableModel() throws SQLException {
        rs = dba.getEmployess();
        synchronieRsAndList();
        filtered = new ArrayList<>();
        filtered.addAll(employees);
    }

    @Override
    public int getRowCount() {
        if (filtered == null) {
            return 0;
        }
        return filtered.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee e = filtered.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return e.getLast_name() + ", " + e.getFirst_name();
            case 1:
                return e.getGender();
            case 2:
                return e.getBirth_date().toString();
            case 3:
                return e.getHire_Date().toString();
            default:
                return "error";
        }
    }

    public void filter(boolean filterByDept, boolean filterByGender, boolean filterByDate, String deptname, boolean male, boolean female, LocalDate dateBefore) {
        if (filterByDept || filterByDate || filterByGender) {
            filtered.clear();
            filtered.addAll(employees);
        }

        if (filterByDept) {
            if (!deptname.equals("")) {
                filtered.removeIf(e -> !e.getDept_name().equals(deptname));
            }
        }

        if (filterByGender) {
            if (male) {
                filtered.removeIf(e -> !e.getGender().equals("M"));
            } else if (female) {
                filtered.removeIf(e -> !e.getGender().equals("F"));
            } else {
                filtered.clear();
            }
        }

        if (filterByDate) {
            filtered.removeIf(e -> !e.getBirth_date().isBefore(dateBefore));
        }

        this.fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return colNames.get(column);
    }

    private void synchronieRsAndList() throws SQLException {
        rs.first();
        employees = new ArrayList<>();
        while (rs.next()) {
            int emp_no = rs.getInt("emp_no");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String gender = rs.getString("gender");
            LocalDate birth_date = rs.getDate("birth_date").toLocalDate();
            LocalDate hire_date = rs.getDate("hire_Date").toLocalDate();
            String dept_name = rs.getString("dept_name");
            LocalDate from_date = rs.getDate("from_date").toLocalDate();
            LocalDate to_date = rs.getDate("to_date").toLocalDate();
            Employee e = new Employee(emp_no, first_name, last_name, gender, birth_date, hire_date, dept_name, from_date, to_date);
            employees.add(e);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            Employee e = filtered.get(rowIndex);
            int originalIndex = 0;
            for (int i = 0; i < employees.size(); i++) {
                if(employees.get(i).getEmp_no() == e.getEmp_no()){
                    originalIndex = i;
                }
            }
            switch (columnIndex) {
                case 0:
                    String[] parts = ((String) aValue).split(", ");
                    System.out.println(Arrays.toString(parts));
                    System.out.println((rs.absolute(originalIndex + 1))+"");
                    if (true) {
                        if (parts.length == 2) {
                            String last_name = parts[0];
                            String first_name = parts[1];
                            System.out.println(Arrays.toString(parts));
                            rs.updateString("last_name", last_name);
                            rs.updateString("first_name", first_name);
                            rs.updateRow();
                        }
                    }

                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
            synchronieRsAndList();
            this.fireTableCellUpdated(rowIndex, columnIndex);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
