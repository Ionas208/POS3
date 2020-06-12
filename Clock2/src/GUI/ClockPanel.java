/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 10jon
 */
public class ClockPanel extends JPanel implements Runnable {
    private DigitLabel[] labels;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
    private int index;
    private ClockGUI parent;
    private int timezone;

    public ClockPanel(int index, ClockGUI parent, int timezone) {
        labels = new DigitLabel[8];
        setLayout(new GridLayout(1, 10));
        JLabel lbTimezone = new JLabel();
        this.add(lbTimezone);
        lbTimezone.setBackground(Color.white);
        lbTimezone.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        if(timezone<0){
            lbTimezone.setText("GMT"+timezone);
        }
        else if(timezone > 0){
            lbTimezone.setText("GMT+"+timezone);
        }
        else{
            lbTimezone.setText("GMT");
        }
        
        JButton btClose = new JButton();
        this.add(btClose);
        btClose.setBackground(Color.white);
        btClose.setText("Delete");
        btClose.addActionListener(evt -> {
            parent.stopClock(index);
        });

        for (int i = 0; i < 8; i++) {
            DigitLabel label = new DigitLabel();
            if(i == 2 || i == 5){
                label.setNum(10);
                label.repaint();
                label.setVisible(true);
            }
            this.add(label);
 
            labels[i] = label;
            
        }
        this.setVisible(true);
        this.index = index;
        this.parent = parent;
        this.timezone = timezone;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            LocalTime currentTime = LocalTime.now().plusHours(timezone-1);
            int hours = currentTime.getHour();
            int minutes = currentTime.getMinute();
            int seconds = currentTime.getSecond();
            
            //hours
            labels[0].setNum(hours/10);
            
            labels[1].setNum(hours%10);
            
            //minutes
            labels[3].setNum(minutes/10);
            
            labels[4].setNum(minutes%10);
            
            //seconds
            labels[6].setNum(seconds/10);
            
            labels[7].setNum(seconds%10);
            
            repaint();
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                //perfectly fine
            }
            
       
        }
    }

    

    
    
    
}
