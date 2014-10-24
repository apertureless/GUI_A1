
package gui.aufgabe1;

import java.io.IOException;


/**
 * Aufgabe 1: Darstellung eines Diagramms für die Trinkreife von Wein.
 * Das Diagramm soll basierend auf Benutzereingabe von Lagerdauer und Jahrgang
 * generiert werden.
 * 
 * @author Jakub Juszczak
 */
public class Aufgabe1 {
    
    public static void main(String[] args) throws IOException {
        
        Fenster f = new Fenster("GUI Aufgabe" , 20, 20, 500, 300);
        f.setVisible(true);   
        f.zeichneKomponenten(new Diagramm());
    }
}
