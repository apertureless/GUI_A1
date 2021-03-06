
package gui.aufgabe1;

import java.io.IOException;
import javax.swing.JFrame;
import utilities.TastaturEingabe;

/**
 * Klasse für die Darstellung des Fensters für das Balkendiagramm, der 
 * Trinkreife des Weines.
 * 
 * @author Jakub Juszczak
 */
public class Fenster extends JFrame {
    
    /*
     * Variablen deklaration für die Erstellung des Fensters
     */
    private String titel;
    private int xPos;
    private int yPos;
    private int breite;
    private int hoehe;
    
    /**
     * Fesnterinstanz erzeugen. 
     * 
     * @param titel 
     * @param xPos
     * @param yPos
     * @param breite
     * @param hoehe 
     */
    public Fenster (String titel, int xPos, int yPos, int breite, int hoehe) {
        super ();
        this.initFenster(titel, xPos, yPos, breite, hoehe);
        this.erzeugeFenster();
    }

 
    /**
     * Initialisierung der Fensterwerte, Position, Titel, Breite und Höhe.
     * 
     * @param titel
     * @param xPos
     * @param yPos
     * @param breite
     * @param hoehe 
     */
    private void initFenster (String titel, int xPos, int yPos, 
                              int breite, int hoehe) {
        
       this.titel = titel;
       this.xPos = xPos;
       this.yPos = yPos;
       this.breite = breite;
       this.hoehe = hoehe;
    
    }
    
    /**
     * Erzeugt das Fenster mit den festgelegten Werten.
     */
   private void erzeugeFenster () {
       this.setTitle(titel);
       this.setLocation(xPos,yPos);
       this.setSize(breite, hoehe);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   /**
    * Methode die die Tastatureingabe abfragt und die Eingegebene Lagerdauer
    * und Jahrgang des Weines übergibt und repaint aufruft.
    * 
     * @param diagramm
    * @throws IOException 
    */
   public void zeichneKomponenten(Diagramm diagramm) throws IOException {
       
       this.add(diagramm);
          
       TastaturEingabe te = new TastaturEingabe();
       
       while (true) {
           te.pruefeEingabe();
           
           Lagergut lg = new Wein();
           
           lg.setJahr(te.jahrgang());
           lg.setDauer(te.lagerdauer());
           
           diagramm.setJahrgangUndDauer(lg);
           this.repaint();
       }
   }
}
