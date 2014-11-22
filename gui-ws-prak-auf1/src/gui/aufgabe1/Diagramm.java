
package gui.aufgabe1;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Calendar;
import javax.swing.JPanel;

/**
 * Klasse zur Darstellung des Trink-Reife Diagramms.
 * 
 * @author Jakub Juszczak <juszczak.j@googlemail.com>
 */

public class Diagramm extends JPanel {
    
    // Konstanten zur Festlegung der Farben
    private static final Color FARBE_RAHMEN = Color.black;
    private static final Color FARBE_TEXT = Color.black;
    private static final Color FARBE_AKTUELLES_JAHR = Color.red;
    
    public static final Color FARBE_ZU_FRUEH = Color.gray;
    public static final Color FARBE_OPTIMAL = Color.green;
    public static final Color FARBE_UEBERLAGERT = Color.yellow;
    
    private static final Color FARBE_HINTERGRUND = Color.white;
    
    private static final String SCHRIFTART = "Arial";

    // Konstanten zur Festlegung der Berechnung ob ein Wein Reif ist oder nicht.
    private static final float ANTEIL_ZU_FRUEH = 8f;
    private static final float ANTEIL_OPTIMAL = 2f;
    
    // Prozentualer Anteil der Diagramm Höhe und Breite in %
    private static final int DIAGRAMM_BREITE = 80;
    private static final int DIAGRAMM_HOEHE = 40;
    
    private Graphics2D g;
    
    // Fenstergrößen und Schriftgrößen
    private int schriftgroesse;
    private double x, y, b, h;
    private double fensterBreite, fensterHoehe;
    
    // Zeitvariablen 
    private int lagerdauer, jahrgang;
    private int beginnStadium;
    
    private int aktuellesJahr;
    
 
    
    // Objekt mit den Stadien
    public Stadium[] stadien;
   
     // Sammlung der Farben aller Stadien
    public Object[] farben = {
        FARBE_ZU_FRUEH,
        new GradientPaint(0, 0, FARBE_ZU_FRUEH, 0, 0, FARBE_OPTIMAL),
        FARBE_OPTIMAL, 
        FARBE_UEBERLAGERT};
    
    /**
     * Konstruktor mit Testdaten für das Diagramm.
     */
    public Diagramm() {
        Object me = new MausEvents();
        this.addMouseListener((MouseListener) me);
        this.addMouseMotionListener((MouseMotionListener) me);
    }
    
    /**
     * Erzeugt das Diagramm
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
     * @param lg
     */
    public void setJahrgangUndDauer(Lagergut lg){
      jahrgang = lg.getJahr();
      lagerdauer = lg.getDauer();
    }
    
    /**
     * Allgemeine Werte wie Fensterparameter und Schriftparameter werden 
     * gesetzt.
     * 
     * @param d
     * @param g 
     */
    public void setWerte(Dimension d, Graphics2D g){
        
        // Fensterbreite setzen.
        this.fensterBreite = (double) d.width;
        this.fensterHoehe = (double) d.height;
        
        this.g = g;
        
        FontMetrics metrics = g.getFontMetrics();      
        this.schriftgroesse = metrics.getHeight();
        
        // Aktuelles Jahr setzen.
        this.aktuellesJahr = Calendar.getInstance().get(Calendar.YEAR);
        
        // Diagramm Größen setzen.
        this.setDiagramm();
        
        // Reifestadien setzen.
        this.setReifeStatien();
    }
    
    /**
     * Diabgramm Größe setzen.
     */
    private void setDiagramm(){
        // Position des Diagramms
        this.x = (this.fensterBreite * 10) / 100;
        this.y = (this.fensterHoehe * 10) / 100;
        
        // Höhe und Breite des Diagramms
        this.b = (this.fensterBreite * DIAGRAMM_BREITE) / 100;
        this.h = (this.fensterHoehe * DIAGRAMM_HOEHE) / 100;
        
        // Abzug für die Legende
        this.h -= this.schriftgroesse;
    }
    
    /**
     * Berechnet und setzt die einzelnen Stadien der Trinkreife
     */
    private void setReifeStatien() {
        
        // Berechnet die Dauer der einzelnen Stadien
        double unreifDauer = Math.round(this.lagerdauer / ANTEIL_ZU_FRUEH );
        double optimalDauer = Math.round(this.lagerdauer / ANTEIL_OPTIMAL);
        double steigerungsfaehigDauer = Math.round(this.lagerdauer - 
                                (optimalDauer + unreifDauer));
        double ueberlagertDauer = 1;
        
        double breite = this.b / (this.lagerdauer +1);
         
        Stadium unreif = 
                new Stadium(unreifDauer, this.farben[0],
                        breite * unreifDauer, this.h, this.y, 0);
        
        Stadium steigernd = 
                new Stadium(steigerungsfaehigDauer, this.farben[1],
                breite * steigerungsfaehigDauer, this.h, this.y, 1);
        
        Stadium optimal = 
                new Stadium(optimalDauer, this.farben[2],
                breite * optimalDauer, this.h, this.y, 2);
        
        Stadium ueberlagert = 
                new Stadium(ueberlagertDauer, this.farben[3],
                breite * ueberlagertDauer, this.h, this.y, 3);

        // Ablegen als globales Array
        this.stadien = new Stadium[4];
        this.stadien[0] = unreif;
        this.stadien[1] = steigernd;
        this.stadien[2] = optimal;
        this.stadien[3] = ueberlagert;
      
    }
 
     /**
     * Zeichnet das aktuelle Stadium.
     * 
     * @param s Stadium
     * @param position Position
     */
    public void zeichneAktStadium(Stadium s, double position) {
        // Setze Fuellfarbe
        if (s.farbe instanceof GradientPaint) {
            s.setFarbe(new GradientPaint((int) position, 
                    (int) this.y, ((GradientPaint) s.farbe).getColor1(), 
                    (int) (position + s.breite), 
                    (int) this.y, ((GradientPaint) s.farbe).getColor2()));
        }
        this.g.setPaint((Paint) s.farbe);
        this.g.fill(new Rectangle.Double(
                position, this.y, s.breite, this.h));
        
        // Zuruecksetzen der Farbe
        this.g.setPaint(FARBE_RAHMEN);
        
        // Zeichne Rahmen des aktuellen Stadiums und setze die Beschriftung
        this.g.draw(new Rectangle.Double(
                position, this.y, s.breite, this.h));
        if (beginnStadium != this.aktuellesJahr) {
            this.g.setPaint(FARBE_TEXT);
            this.g.drawString(
                    Integer.toString(beginnStadium),
                    (int) position,
                    (int) (this.y + this.h + this.schriftgroesse));
        }
    }
  
    
    /**
     * Zeichnet die einzelnen Stadien der Trinkreife und stellt diese
     * farblich mit einem Verlauf dar.
     */
    private void zeichneReifeStadien() {
        
        // Setzt den Beginn des ersten Stadiums
        this.beginnStadium = (int) this.jahrgang;

        // Position des aktuellen Stadiums
        double aktPosition = this.x;
        
        for (Stadium aktStadium : this.stadien) {
            // Setze x-Position und Beginn des aktuellen Stadiums
            aktStadium.setXPos(aktPosition);
            aktStadium.setBeginn(beginnStadium);
            
            this.zeichneAktStadium(aktStadium, aktPosition);

            // Berechnet Startjahr des naechsten Stadiums
            beginnStadium += aktStadium.dauer;
            // Berechne neue Startposition
            aktPosition += aktStadium.breite;
        }
    }
    
    /**
     * Zeichnet das aktuelle Jahr farbig in das Diagramm ein.
     */
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
    
      
    /**
     * Methode legt die Schriftart sowie die Schriftgröße fest.
     * @param g 
     */
    public void setTextStil(Graphics2D g) {
        
        // Festlegen von Schriftart und Größe
        int fontSize = (int)Math.round(2 * this.getWidth() / 72.0);
        Font font = new Font(SCHRIFTART, Font.PLAIN, fontSize);
        g.setFont(font);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        
        // Aktiviere Antialiasing (Schriftenglaettung)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Setze weisse Hintergrundfarbe
        this.setBackground(FARBE_HINTERGRUND);
        
        // Setzt die Schriftgröße und Stil.
        this.setTextStil(g2);
        
        // Setzt die Werte des Diagramms
        this.setWerte(getSize(), g2);
        
        super.paintComponent(g2);
        
        // erzeugt das Diagramm.
        this.erzeugeDiagramm();
        
    }
}
