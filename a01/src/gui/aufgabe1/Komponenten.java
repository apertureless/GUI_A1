/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.aufgabe1;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author Jakub Juszczak <juszczak.j@googlemail.com>
 */
public class Komponenten extends JPanel {
    
    final Diagramm diagramm;
    final Legende legende;
    
    private static final Color FARBE_HINTERGRUND = Color.white;
    
    /**
     * Erzeugt die Komponenten des Fensters
     */
    public Komponenten() {
        
        // Das Diagramm wird erzeugt.
        this.diagramm = new Diagramm();
        
        // Die Legende wird erzeugt.
        this.legende = new Legende();
    }
    
    /**
     * Methode legt die Schriftart sowie die Schriftgröße fest.
     * @param g 
     */
    public void setTextStil(Graphics2D g) {
        // Festlegen von Schriftart und Größe
        int fontSize = (int)Math.round(1.5 * this.getWidth() / 72.0);
        Font font = new Font("Arial", Font.PLAIN, fontSize);
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
        this.diagramm.setWerte(this.getSize(), g2);
        
        
        super.paintComponent(g2);
        
        this.diagramm.erzeugeDiagramm();
    }
    
}
