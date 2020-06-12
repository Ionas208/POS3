/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clock.GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import javax.swing.JLabel;

/**
 *
 * @author jonas
 */
public class TestLabel extends JLabel implements Runnable{

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int num;
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
        draw(0);
        draw(1);
        draw(2);
        draw(3);
        draw(4);
        draw(5);
        draw(6);
    }
    
    
    private void draw(int segment){
        Polygon p = new Polygon();
        int[] xcords = getXSegment(segment);
        int[] ycords = getYSegment(segment);
        for (int i = 0; i < 6; i++) {
            p.addPoint(xcords[i], ycords[i]);
        }
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
            default:
                return null;
                
        }
    }
    
    
}
