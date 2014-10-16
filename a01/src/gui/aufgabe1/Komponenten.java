/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package gui.aufgabe1;

import javax.swing.JPanel;

/**
 *
 * @author Jakub Juszczak <juszczak.j@googlemail.com>
 */
public class Komponenten extends JPanel {
    
    final Diagramm diagramm;
    final Legende legende;
    
    public Komponenten() {
        
        this.diagramm = new Diagramm();
        this.legende = new Legende();
    }
    
}
