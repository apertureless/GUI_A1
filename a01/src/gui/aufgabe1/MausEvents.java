/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.aufgabe1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Jakub Juszczak <juszczak.j@googlemail.com>
 */
public class MausEvents implements MouseListener, MouseMotionListener {
    
    enum Elemente {
        unreif,
        steigerungsfaehig,
        optimal,
        ueberlagert,
        ausserhalb
    }
    
    Elemente istInStadium;
    
    
    public MausEvents() {
        
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
            Diagramm diagramm = (Diagramm) e.getSource();
            
            int x = e.getX();
            int y = e.getY();
            
            Elemente altesStadium = istInStadium;
            istInStadium = Elemente.ausserhalb;
            
            double[] stadien = diagramm.reifeStadien;
            
            if(diagramm.reifeStadien == null) {
                return;
            }
            
            
        }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        System.out.printf("\nClicked");
        
//        switch(istInStadium) {
//            case unreif:
//                System.out.printf("In den Jahren %d - %d ist es für den "
//                            + "Wein noch zu früh.\n");
//                break;
//                
//            case steigerungsfaehig:
//                 System.out.printf("In den Jahren %d - %d ist es für den "
//                        + "Wein noch zu früh.\n");
//                break;
//              
//            case optimal:
//                 System.out.printf("In den Jahren %d - %d ist es für den "
//                        + "Wein noch zu früh.\n");
//                break;
//                
//            case ueberlagert:
//                 System.out.printf("In den Jahren %d - %d ist es für den "
//                        + "Wein noch zu früh.\n");
//                break;
              
//        }
     
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

   
    
}
