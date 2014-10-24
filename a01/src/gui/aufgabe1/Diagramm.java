
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
    private static final Color FARBE_RAHMEN = Color.black;
    private static final Color FARBE_AKTUELLES_JAHR = Color.red;
    
    private static final Color FARBE_ZU_FRUEH = Color.gray;
    private static final Color FARBE_OPTIMAL = Color.green;
    private static final Color FARBE_UEBERLAGERT = Color.yellow;

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
   
     // Sammlung der Farben aller Stadien
    private final Object[] COLORS = {Color.gray,
        new GradientPaint(0, 0, Color.gray, 0, 0, Color.green),
        Color.green, Color.yellow};
    
    /**
     * Konstruktor mit Testdaten für das Diagramm.
     */
    public Diagramm() {}
    
    /**
     * 
     */
    public void erzeugeDiagramm() {
        
        this.g.setPaint(FARBE_RAHMEN);
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
        this.h = (this.fensterHoehe * 40) / 100;
        
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
 
  
    
    /**
     * Zeichnet die einzelnen Stadien der Trinkreife und stellt diese
     * farblich mit einem Verlauf dar.
     */
    private void zeichneReifeStadien() {
        
        // Berechnet die Reifestadien
        this.setzteReifeStatien();
        this.beginnStadium = this.jahrgang;
        
        double aktuellePosition = this.x;
        
        for (int i = 0; i < this.reifeStadien.length; i++) {
            // Berechnung der Breite
            double breite = this.b * this.reifeStadien[i] / (this.lagerdauer + 1);

            // Setze Fuellfarbe
            if (this.COLORS[i] instanceof GradientPaint) {
                this.COLORS[i] = new GradientPaint(
                        (int) aktuellePosition, (int) this.y, Color.gray,
                        (int) (aktuellePosition + breite), (int) this.y, Color.green);
            }
            this.g.setPaint((Paint) this.COLORS[i]);
            this.g.fill(
                    new Rectangle.Double(aktuellePosition, this.y, breite, this.h));

            // Zuruecksetzen der Farbe
            this.g.setPaint(Color.black);

            // Zeichne Rahmen des aktuellen Stadiums und setze die Beschriftung
            this.g.draw(
                    new Rectangle.Double(aktuellePosition, this.y, breite, this.h));
            //System.out.println(stadiumBeginn);
            if(beginnStadium != this.aktuellesJahr) {
                this.g.drawString(
                        Integer.toString(beginnStadium),
                        (int) aktuellePosition,
                        (int) (this.y + this.h + this.schriftgroesse));
            }

            // Berechnet Startjahr des naechsten Stadiums
            beginnStadium += this.reifeStadien[i];

            // Berechne neue Startposition
            aktuellePosition += breite;
        }
        
        
    }
    
      private void zeichneAktuellesJahr(){
        
        double aktuellesJahrBreite = this.b / (this.lagerdauer + 1);
        double aktuellesJahrPosition = (this.aktuellesJahr - this.jahrgang) 
                                        * aktuellesJahrBreite;
        if(this.aktuellesJahr >= this.jahrgang && this.aktuellesJahr < beginnStadium) {
            // Zeichne Rahmen des aktuellen Jahrs und setze Beschriftung
            this.g.setPaint(FARBE_AKTUELLES_JAHR);
            this.g.draw(new Rectangle.Double(
                    this.x + aktuellesJahrPosition,
                    this.y - 1,
                    aktuellesJahrBreite,
                    this.h + 2));
            this.g.drawString(
                    Integer.toString(this.aktuellesJahr),
                    (int) (this.x + aktuellesJahrPosition),
                    (int) (this.y + this.h + this.schriftgroesse));

            // Zuruecksetzen der Farbe
            this.g.setPaint(FARBE_RAHMEN);
        }
    }
    
}
