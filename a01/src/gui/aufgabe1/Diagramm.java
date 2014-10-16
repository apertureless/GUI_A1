
package gui.aufgabe1;

import java.awt.*;
import java.util.Calendar;

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
    private double x, y, b, h;
    private double fensterBreite, fensterHoehe;
    
    private int lagerdauer, jahrgang;
    private int beginnStadium;
    
    private int aktuellesJahr;
    
    /**
     * Konstruktor mit Testdaten für das Diagramm.
     */
    public Diagramm() {
//        this.lagerdauer = 1;
//        this.jahrgang = 2011;
    }
    
    /**
     * 
     */
    public void erzeugeDiagramm() {
        
        // Zeichnet den Rahmen des Diagramms.
        this.g.draw(new Rectangle.Double(this.x, this.y, this.b, this.h));
        
        // Zeichnet die Reifestadien des Weins
        zeichneReifeStadien();
        
        // Zeichnet das aktuelle Jahr ein.
        zeichneAktuellesJahr();
    }
    
    /**
     * Benutzereigabe wird gesetzt.
     * 
     * @param jahrgang
     * @param lagedauer 
     */
    public void setEingabe(int jahrgang, int lagedauer){
        this.jahrgang = jahrgang;
        this.lagerdauer = lagedauer;
    }
    
    /**
     * Allgemeine Werte wie Fensterparameter und Schriftparameter werden 
     * gesetzt.
     * 
     * @param d
     * @param g 
     */
    public void setWerte(Dimension d, Graphics2D g){
        this.fensterBreite = (double) d.width;
        this.fensterHoehe = (double) d.height;
        
        this.g = g;
        
        FontMetrics metrics = g.getFontMetrics();      
        this.schriftgroesse = metrics.getHeight();
        
        this.aktuellesJahr = Calendar.getInstance().get(Calendar.YEAR);
        this.setDiagramm();
    }
    
    
    private void setDiagramm(){
        this.x = (this.fensterBreite * 10) / 100;
        this.y = (this.fensterHoehe * 10) / 100;
        
        this.b = (this.fensterBreite * 80) / 100;
        this.h = (this.fensterHoehe * 25) / 100;
        
        this.h -= this.schriftgroesse;
    }
    
    private void zeichneAktuellesJahr(){
        
    }
    
    private void zeichneReifeStadien() {
        
    }
    
    
}
