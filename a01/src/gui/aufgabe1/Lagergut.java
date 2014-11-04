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
    public void setParameter(float warteAnteil, float gutAnteil);
    
    public void setJahr(int erzeugungsjahr);
    public void setDauer(int lagerdauer);
    
    public int getJahr();
    public int getDauer();
    
}
