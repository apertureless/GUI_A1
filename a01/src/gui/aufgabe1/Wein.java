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
    
    public double warteAnteil;
    public double steigerungsAnteil;
    public double gutAnteil;
    
    public int jahrgang;
    public int lagerdauer;
    
    

    @Override
    public void setLagergut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setParameter(double warteAnteil, double steierungsAnteil, double gutAnteil) {
       this.warteAnteil = warteAnteil;
       this.steigerungsAnteil = steigerungsAnteil;
       this.gutAnteil = gutAnteil;
    }

    @Override
    public void setJahr(int jahr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDauer(int dauer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
