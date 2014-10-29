/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.aufgabe1;

/**
 *
 * @author Jakub
 */
public interface Lagergut {
    
    public void setLagergut();
    public void setParameter(double warteAnteil, double steierungsAnteil, 
                            double gutAnteil);
    public void setJahr(int jahr);
    public void setDauer(int dauer);
    
}
