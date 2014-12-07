/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf6;

/**
 *
 * @author jakub juszczak
 */
public class Wein implements Lagergut {
    
    private String bestellnummer;
    private String name;
    private String farbe;
    private String anbaugebiet;
    private String region;
    private String alkoholgehalt;
    private int jahrgang;
    private int dauer;
    private String flaschengroeße;
    private String preis;
 
    
    public Wein (String bestellnummer, String name, int jahrgang, int dauer, 
                String farbe, String anbaugebiet, String region, String alkoholgehalt, String flaschengroeße, String preis) {
        this.bestellnummer = bestellnummer;
        this.name = name;
        this.jahrgang = jahrgang;
        this.dauer = dauer;
        this.farbe = farbe;
        this.anbaugebiet = anbaugebiet;
        this.alkoholgehalt = alkoholgehalt;
        this.region = region;
        this.flaschengroeße = flaschengroeße;
        this.preis = preis;
    }
    public Wein() { }
    
    @Override
    public String toString() {
        String wein;

        wein = "Bestellnr: " + bestellnummer + "\n"
                + "Name: " + name + "\n"
                + "Jahrgang: " + jahrgang + "\n"
                + "Lagerdauer: " + dauer + "\n"
                + "Farbe: " + farbe + "\n"
                + "Anbaugebiet: " + anbaugebiet + "\n"
                + "Region: " + region + "\n"
                + "Alkoholgehalt: " + alkoholgehalt + "\n"
                + "Flaschengröße: " + flaschengroeße + "\n"
                + "Flaschenpreis: " + preis + "\n";
        return wein;
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
