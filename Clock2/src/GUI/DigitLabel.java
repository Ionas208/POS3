/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import javax.swing.JLabel;

/**
 *
 * @author jonas
 */
public class DigitLabel extends JLabel implements Runnable{

    
    //Run only for Test GUI
    @Override
    public void run() {
            while(!Thread.interrupted()){
            for (int i = 0; i < 10; i++) {
                num = i;
                repaint();
                try{
                   Thread.sleep(500); 
                }
                catch(InterruptedException e){
                    break;
                }
            }
           
        }    
    }

    private Integer num;
    private Graphics2D g2;
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g2 = (Graphics2D)g;
        double scaleX = this.getWidth() / 11.;
        double scaleY = this.getHeight() / 18.;
        AffineTransform aff = new AffineTransform();
        aff.scale(scaleX, scaleY);
        g2.transform(aff);
        g2.setBackground(new Color(0xFFD6D9DF));
        if(num != null){
            drawNumber(num);
        }
    }
    
    
    private void drawNumber(int n){
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        int[] segments = getSegmentsForNumber(n);
        for (int i = 0; i < segments.length; i++) {
            drawSegment(segments[i]);
        }
    }
    
    private void drawSegment(int segment){
        Polygon p = new Polygon();
        int[] xcords = getXSegment(segment);
        int[] ycords = getYSegment(segment);
        for (int i = 0; i < xcords.length; i++) {
            p.addPoint(xcords[i], ycords[i]);
        }
        g2.setPaint(Color.red);
        g2.fillPolygon(p);
        g2.setPaint(Color.black);
        g2.drawPolygon(p);
    }
    
    private int[] getXSegment(int segment){
        switch(segment){
            
            case 0:
                return new int[]{2,3,8,9,8,3};
            case 1:
                return new int[]{9,10,10,9,8,8};
            case 2:
                return new int[]{9,10,10,9,8,8};
            case 3:
                return new int[]{2,3,8,9,8,3};
            case 4:
                return new int[]{2,3,3,2,1,1};
            case 5:
                return new int[]{2,3,3,2,1,1};
            case 6:
                return new int[]{2,3,8,9,8,3};
            case 7:
                return new int[]{5,6,6,5};
            case 8:
                return new int[]{5,6,6,5};
            default:
                return null;
        }
    }
    
    private int[] getYSegment(int segment){
        switch(segment){
            case 0:
                return new int[]{2,1,1,2,3,3};
            case 1:
                return new int[]{2,3,8,9,8,3};
            case 2:
                return new int[]{9,10,15,16,15,10};
            case 3:
                return new int[]{16,17,17,16,15,15};
            case 4:
                return new int[]{9,10,15,16,15,10};
            case 5:
                return new int[]{2,3,8,9,8,3};
            case 6:
                return new int[]{9,10,10,9,8,8};
            case 7:
                return new int[]{7,7,8,8};
            case 8:
                return new int[]{10,10,11,11};
            default:
                return null;
                
        }
    }
    
    private int[] getSegmentsForNumber(int n){
        switch(n){
            case 0:
                return new int[]{0,1,2,3,4,5};
            case 1:
                return new int[]{1,2};
            case 2:
                return new int[]{0,1,3,4,6};
            case 3:
                return new int[]{0,1,2,3,6};
            case 4:
                return new int[]{1,2,5,6};
            case 5:
                return new int[]{0,2,3,5,6};
            case 6:
                return new int[]{0,2,3,4,5,6};
            case 7:
                return new int[]{0,1,2};
            case 8:
                return new int[]{0,1,2,3,4,5,6};
            case 9:
                return new int[]{0,1,2,3,5,6};
            case 10:
                return new int[]{7,8};
            default:
                return new int[0];
        }
    }

    public void setNum(Integer num) {
        this.num = num;
        repaint();
    }
    
    
}
