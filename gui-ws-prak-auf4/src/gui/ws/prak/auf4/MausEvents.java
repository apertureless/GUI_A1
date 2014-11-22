/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.ws.prak.auf4;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Jakub Juszczak <juszczak.j@googlemail.com>
 */
public class MausEvents implements MouseListener, MouseMotionListener {
    
   private enum Elemente {
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
        
        // Position der Maus.
        int x = e.getX();
        int y = e.getY();

        Elemente altesStadium = istInStadium;
        istInStadium = Elemente.ausserhalb;
         
        // Farben zurücksetzen.
        diagramm.farben[0] = Diagramm.FARBE_ZU_FRUEH;
        diagramm.farben[1] = new GradientPaint(0, 0, Diagramm.FARBE_ZU_FRUEH, 
                                               0, 0, Diagramm.FARBE_OPTIMAL);
        diagramm.farben[2] = Diagramm.FARBE_OPTIMAL;
        diagramm.farben[3] = Diagramm.FARBE_UEBERLAGERT;
        
        Stadium[] stadium = diagramm.stadien;
        
        // Wenn keine Stadien berechnet und gesetzt wurden erfolgt
        // ein Abbruch.
        if(diagramm.stadien == null) {
            return;
        }
        
        // Abfrage der Stadien 
        if (stadium[0].istImBreich(x, y)) {
            diagramm.farben[0] = ((Color) diagramm.farben[0]).darker();
            istInStadium = Elemente.unreif;
            
        } else if (stadium[1].istImBreich(x, y)) {
            diagramm.farben[1] = new GradientPaint(
                                        0, 0, Diagramm.FARBE_ZU_FRUEH.darker(), 
                                        0, 0, Diagramm.FARBE_OPTIMAL.darker());
            istInStadium = Elemente.steigerungsfaehig;
            
        } else if (stadium[2].istImBreich(x, y)) {
            diagramm.farben[2]
                    = ((Color) diagramm.farben[2]).darker();
            istInStadium = Elemente.optimal;
            
        } else if (stadium[3].istImBreich(x, y)) {
            diagramm.farben[3]
                    = ((Color) diagramm.farben[3]).darker();
            istInStadium = Elemente.ueberlagert;
        }

        // Bei Aenderung wird neugezeichnet
        if (altesStadium != istInStadium) {
            diagramm.repaint();
        }
    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        Diagramm diagramm = (Diagramm) e.getSource();
        Stadium stadium;
        
        switch(istInStadium) {
            case unreif:
                stadium = diagramm.stadien[0];
                System.out.printf("\nIn den Jahren %d - %d ist es für den "
                                + "Wein noch zu früh.\n", 
                                stadium.beginn, 
                                stadium.beginn + ((int) stadium.dauer) -1
                            );
                break;
                
            case steigerungsfaehig:
                 stadium = diagramm.stadien[1];
                 System.out.printf("\nIn den Jahren %d - %d kann sich der "
                                + "Geschmack des Weines  noch verbessern.\n",
                                stadium.beginn, 
                                stadium.beginn + ((int) stadium.dauer) -1
                        );
                break;
              
            case optimal:
                 stadium = diagramm.stadien[2];
                 System.out.printf("\nIn den Jahren %d - %d hat der Wein "
                                + "sein geschmackliches Optimum.\n",
                                stadium.beginn, 
                                stadium.beginn + ((int) stadium.dauer) -1
                        );
                break;
                
            case ueberlagert:
                 stadium = diagramm.stadien[3];
                 System.out.printf("\nAb dem Jahr %d ist der Wein leider "
                            + "überlagert.\n",
                            stadium.beginn
                            );
                break;       
        }
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
