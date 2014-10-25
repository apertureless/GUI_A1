
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
    
    public static int FENSTER_BREITE = 500;
    public static int FENSTER_HOEHE = 300;
    private static final String FENSTER_TITEL = "GUI Aufgabe 1";
    
    
    public static void main(String[] args) throws IOException {
        
        Fenster f = new Fenster(FENSTER_TITEL , 20, 20, 
                                FENSTER_BREITE, FENSTER_HOEHE);
        f.setVisible(true);   
        f.zeichneKomponenten(new Diagramm());
    }
}
