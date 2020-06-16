/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import beans.Department;
import beans.Manager;
import bl.EmployeeTableModel;
import database.DB_Access;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChartBuilder;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author 10jon
 */
public class EmployeeUI extends javax.swing.JFrame {

    /**
     * Creates new form EmployeeUI
     */
    private DB_Access dba;
    private EmployeeTableModel etm;
    private boolean filterByDept = false;
    private boolean filterByGender = false;
    private boolean filterByDate = false;

    public EmployeeUI() {
        initComponents();
        this.setSize(1200, 600);
        this.setLocationRelativeTo(null);
        dba = DB_Access.getInstance();
        try {
            dba.connect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unable to establish Connection!");
        }
        List<Department> departments = null;
        try {
            departments = dba.getDepartmentNames();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unable to load Department Names!");
        }
        cbDept.addItem(new Department("", ""));
        for (int i = 0; i < departments.size(); i++) {
            cbDept.addItem(departments.get(i));
        }
        try {
            etm = new EmployeeTableModel();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unable to load Employees!");
            ex.printStackTrace();
        }
        tbTable.setModel(etm);
        tbTable.setAutoCreateRowSorter(true);
        tbTable.removeColumn(tbTable.getColumn("ID"));
        tbTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int emp_no = Integer.parseInt(((tbTable.getModel()).getValueAt(tbTable.getSelectedRow(), 4)) + "");
                List<String> history = null;
                try {
                    history = dba.getSalaryHistory(emp_no);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                String displayText = "<html>";
                for (String h : history) {
                    displayText += h + "\n";
                }
                lbSalHistory.setText(displayText += "</html>");
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbDept = new javax.swing.JLabel();
        cbDept = new javax.swing.JComboBox<>();
        cbDateBefore = new javax.swing.JCheckBox();
        dpDate = new org.jdesktop.swingx.JXDatePicker();
        cbMale = new javax.swing.JCheckBox();
        cbFemale = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        lbManagement = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbSalHistory = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 3));

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));
        jPanel3.setLayout(new java.awt.GridLayout(3, 2));

        lbDept.setText("Department");
        jPanel3.add(lbDept);

        cbDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFilterByDept(evt);
            }
        });
        jPanel3.add(cbDept);

        cbDateBefore.setText("Birthdate before");
        cbDateBefore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDateBefore(evt);
            }
        });
        jPanel3.add(cbDateBefore);
        jPanel3.add(dpDate);

        cbMale.setSelected(true);
        cbMale.setText("Male");
        cbMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onGender(evt);
            }
        });
        jPanel3.add(cbMale);

        cbFemale.setSelected(true);
        cbFemale.setText("Female");
        cbFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onGender(evt);
            }
        });
        jPanel3.add(cbFemale);

        jPanel1.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Management"));
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel4.add(lbManagement, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        tbTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbTable);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Statistics"));
        jPanel5.setLayout(new java.awt.GridLayout(1, 1));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Salary History"));
        jPanel6.setLayout(new java.awt.BorderLayout());
        jPanel6.add(lbSalHistory, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6);

        getContentPane().add(jPanel5);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onFilterByDept(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onFilterByDept
        filterByDept = true;
        filter();
        displayManagement();
    }//GEN-LAST:event_onFilterByDept

    private void onGender(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onGender
        if (cbMale.isSelected() && cbFemale.isSelected()) {
            filterByGender = false;
        } else {
            filterByGender = true;
        }
        filter();
    }//GEN-LAST:event_onGender

    private void onDateBefore(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDateBefore
        if (cbDateBefore.isSelected()) {
            filterByDate = true;
        } else {
            filterByDate = false;
        }
        filter();
    }//GEN-LAST:event_onDateBefore

    private void filter() {
        if (etm != null) {
            LocalDate date = null;
            try {
                date = dpDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } catch (NullPointerException ex) {
                if (filterByDate) {
                    JOptionPane.showMessageDialog(this, "Please select a valid Date!");
                }
                filterByDate = false;
                cbDateBefore.setSelected(false);
            }
            String gender = "";
            if (cbMale.isSelected() && cbFemale.isSelected()) {
                filterByGender = false;
            } else if (cbMale.isSelected()) {
                gender = "M";
                filterByGender = true;
            } else if (cbFemale.isSelected()) {
                gender = "F";
                filterByGender = true;
            }
            try {
                etm.filter(filterByDept, filterByGender, filterByDate, cbDept.getSelectedItem().toString(), gender, date);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Unable to filter employees!");
            }
        }
    }

    private void displayManagement() {
        try {
            String dept_no = ((Department) cbDept.getSelectedItem()).getDept_no();
            List<Manager> managers = dba.getManagement(dept_no);
            String displayText = "<html>";
            for (Manager m : managers) {
                displayText += String.format("<span style='text-align: left'><b>%s, %s</b>:</span> "
                        + "<span style='text-align: right'>from <span style='color: red'>%s</span> to <span style='color: red'>%s</span><span><br>", m.getLast_name(), m.getFirst_name(), m.getFrom_date(), m.getTo_Date());
            }
            lbManagement.setText(displayText += "</html>");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Unable to load Management!");
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbDateBefore;
    private javax.swing.JComboBox<Department> cbDept;
    private javax.swing.JCheckBox cbFemale;
    private javax.swing.JCheckBox cbMale;
    private org.jdesktop.swingx.JXDatePicker dpDate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDept;
    private javax.swing.JLabel lbManagement;
    private javax.swing.JLabel lbSalHistory;
    private javax.swing.JTable tbTable;
    // End of variables declaration//GEN-END:variables
}
