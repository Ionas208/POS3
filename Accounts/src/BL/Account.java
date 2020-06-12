/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import javax.swing.JLabel;

/**
 *
 * @author 10jon
 */
public class Account {
    private double balance;
    private JLabel label;

    public Account(double balance, JLabel label) {
        this.balance = balance;
        this.label = label;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
        this.label.setText(String.format("%1.2f €", this.balance));
    }

}
