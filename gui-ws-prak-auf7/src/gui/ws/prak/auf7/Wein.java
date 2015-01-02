/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf7;

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
    
    private static String trenner = ";";
    private static int anzahlDaten = 10;
     private static final String BESTELLNUMMER = "[0-9]{2}-[A-Z]{3}-[0-9]{42}";
 
    
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
//    
//    @Override
//    public String toString() {
//        String wein;
//
//        wein = "Bestellnr: " + bestellnummer + "\n"
//                + "Name: " + name + "\n"
//                + "Jahrgang: " + jahrgang + "\n"
//                + "Lagerdauer: " + dauer + " Jahre\n"
//                + "Farbe: " + farbe + "\n"
//                + "Anbaugebiet: " + anbaugebiet + "\n"
//                + "Region: " + region + "\n"
//                + "Alkoholgehalt: " + alkoholgehalt + "\n"
//                + "Flaschengröße: " + flaschengroeße + "\n"
//                + "Flaschenpreis: " + preis + " €\n";
//        return wein;
//    }
    
     @Override
    public String toString() {
        String wein;

        wein = bestellnummer + trenner
                + name + trenner
                + jahrgang + trenner
                + dauer + trenner
                + farbe + trenner
                + anbaugebiet + trenner
                + region + trenner
                + alkoholgehalt + trenner
                + flaschengroeße + trenner
                + preis + trenner;
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
    
     public static Wein valueOf(String[] str) throws IllegalArgumentException {
        Wein wein = null;
        
        if (str.length < anzahlDaten) {
            throw new IllegalArgumentException("Datensatz unvollständig.");
        }
        
        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].trim();
        }
        
        if (!str[1].matches(BESTELLNUMMER)) {
            System.out.println(str[1]);
            throw new IllegalArgumentException("Ungueltige Bestellnummer!");
        }
        
        try {
            wein = new Wein(str[1], str[2], Integer.parseInt(str[3]),Integer.parseInt(str[4]), str[5], str[6], 
                     str[7], str[8], str[9], str[10]); 
                    
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ungueltiger Wert!");
        }
        
        return wein;
    }
    
}
