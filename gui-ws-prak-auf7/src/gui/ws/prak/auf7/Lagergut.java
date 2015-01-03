/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf7;

/**
 *
 * @author Jakub
 */
public interface Lagergut {
    
    
    public void setJahr(int erzeugungsjahr);
    public void setDauer(int lagerdauer);
    
    public int getJahr();
    public int getDauer();
    public String getBestellnummer();
    public String getName();
    public String getPreis();
    public String getLand();
    public String getRegion();
    public String getFarbe();
    
}
