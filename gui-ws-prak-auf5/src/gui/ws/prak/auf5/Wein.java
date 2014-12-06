/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf5;

/**
 *
 * @author jakub juszczak
 */
public class Wein {
    
    private String bestellnummer;
    private String name;
    private String farbe;
    private String anbaugebiet;
    private String region;
    private String alkoholgehalt;
    
    public Wein (String bestellnummer, String name, String farbe, String anbaugebiet, String region, String alkoholgehalt) {
        this.bestellnummer = bestellnummer;
        this.name = name;
        this.farbe = farbe;
        this.anbaugebiet = anbaugebiet;
        this.alkoholgehalt = alkoholgehalt;
        this.region = region;
    }
    
        @Override
    public String toString() {
        String wein;

        wein = "Bestellnr: " + bestellnummer + "\n"
                + "Name: " + name + "\n"
                + "Farbe: " + farbe + "\n"
                + "Anbaugebiet: " + anbaugebiet + "\n"
                + "Region: " + region + "\n"
                + "Alkoholgehalt: " + alkoholgehalt + "\n";
        return wein;
    }
}
