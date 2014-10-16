
package gui.aufgabe1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Klasse zur Darstellung des Trink-Reife Diagramms.
 * 
 * @author Jakub Juszczak <juszczak.j@googlemail.com>
 */

public class Diagramm {
    
    /*
     * Definition der Farben für die Darstellung des Diagramms und für die
     * einzelnen Stadien der Lagerung
     */
    private static final Color FARBE_RAHMEN = Color.BLACK;
    private static final Color FARBE_AKTUELLES_JAHR = Color.RED;
    
    private static final Color FARBE_ZU_FRUEH = Color.GRAY;
    private static final Color FARBE_OPTIMAL = Color.GREEN;
    private static final Color FARBE_UEBERLAGERT = Color.YELLOW;

    private static final float ANTEIL_ZU_FRUEH = 8f;
    private static final float ANTEIL_OPTIMAL = 2f;
    
    private Graphics2D g;
    private int schriftgroesse;
    private int x, y, b, h;
    private int fensterBreite, fensterHoehe;
    
    private int lagerdauer, jahrgang;
    private int beginnStadium;
    
    private int aktuellesJahr;
    
    /**
     * Konstruktor mit Testdaten für das Diagramm.
     */
    public Diagramm() {
        this.lagerdauer = 1;
        this.jahrgang = 2011;
    }
    
    /**
     * 
     */
    public void erzeugeDiagramm() {
        this.g.draw(new Rectangle.Double(this.x, this.y, this.b, this.h));
    }
    
    
}
