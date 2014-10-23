
package gui.aufgabe1;

import java.awt.*;
import java.util.Calendar;

/**
 * Klasse zur Darstellung des Trink-Reife Diagramms.
 * 
 * @author Jakub Juszczak <juszczak.j@googlemail.com>
 */

public class Diagramm {
    
    
    // Konstanten zur Festlegung der Farben
    private static final Color FARBE_RAHMEN = Color.BLACK;
    private static final Color FARBE_AKTUELLES_JAHR = Color.RED;
    
    private static final Color FARBE_ZU_FRUEH = Color.GRAY;
    private static final Color FARBE_OPTIMAL = Color.GREEN;
    private static final Color FARBE_UEBERLAGERT = Color.YELLOW;

    // Konstanten zur Festlegung der Berechnung ob ein Wein Reif ist oder nicht.
    private static final float ANTEIL_ZU_FRUEH = 8f;
    private static final float ANTEIL_OPTIMAL = 2f;
    
    private Graphics2D g;
    
    // Fenstergrößen und Schriftgrößen
    private int schriftgroesse;
    private double x, y, b, h;
    private double fensterBreite, fensterHoehe;
    
    // Zeitvariablen 
    private int lagerdauer, jahrgang;
    private int beginnStadium;
    
    private int aktuellesJahr;
   
    // Reifestadien 
    private double unreif, optimal, ueberlagert, steigerungsfaehig;
    private double[] reifeStadien;
   
    
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
    
    /**
     * Diabgramm Größe setzen.
     */
    private void setDiagramm(){
        // Position des Diagramms
        this.x = (this.fensterBreite * 10) / 100;
        this.y = (this.fensterHoehe * 10) / 100;
        // Höhe und Breite des Diagramms
        this.b = (this.fensterBreite * 80) / 100;
        this.h = (this.fensterHoehe * 50) / 100;
        
        // Abzug für die Legende
        this.h -= this.schriftgroesse;
    }
    
    /**
     * Berechnet und setzt die einzelnen Stadien der Trinkreife
     */
    private void setzteReifeStatien() {
        
        this.unreif = Math.round(this.lagerdauer / ANTEIL_ZU_FRUEH );
        this.optimal = Math.round(this.lagerdauer / ANTEIL_OPTIMAL);
        this.steigerungsfaehig = Math.round(this.lagerdauer - 
                                (this.optimal + this.unreif));
        this.ueberlagert = 1;
        
        this.reifeStadien = new double[4];
        this.reifeStadien[0] = this.unreif;
        this.reifeStadien[1] = this.steigerungsfaehig;
        this.reifeStadien[2] = this.optimal;
        this.reifeStadien[3] = this.ueberlagert;
  
        
    }
 
    private void zeichneAktuellesJahr(){
        
        
    }
    
    /**
     * Zeichnet die einzelnen Stadien der Trinkreife und stellt diese
     * farblich mit einem Verlauf dar.
     */
    private void zeichneReifeStadien() {
        
        // Berechnet die Reifestadien
        this.setzteReifeStatien();
        this.beginnStadium = this.jahrgang;
    }
    
    
}
