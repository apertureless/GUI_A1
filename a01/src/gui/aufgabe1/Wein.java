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
public class Wein implements Lagergut {
    private static float ANTEIL_ZU_FRUEH = 8f;
    private static float ANTEIL_OPTIMAL = 2f;
 

    private int jahrgang;
    private int dauer;
 
    

    @Override
    public void setLagergut() {
   
    }

    @Override
    public void setParameter(float warteAnteil, float gutAnteil) {
        ANTEIL_ZU_FRUEH = warteAnteil;
        ANTEIL_OPTIMAL = gutAnteil;
    }

    @Override
    public void setJahr(int erzeugungsjahr) {
        jahrgang = erzeugungsjahr;
    }

    @Override
    public void setDauer(int lagerdauer) {
        dauer = lagerdauer;
    }

    @Override
    public int getJahr() {
        return jahrgang;
    }

    @Override
    public int getDauer() {
       return dauer;
    }
    
    
}
