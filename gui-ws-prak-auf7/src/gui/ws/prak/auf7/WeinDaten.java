/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf7;

import java.util.ArrayList;

/**
 *
 * @author Jakub Juszczak
 */
public class WeinDaten {
    private ArrayList<Wein> weinDaten;
    
    public WeinDaten() {
        weinDaten = new ArrayList<>();
    }
    
    public boolean weinDatenHinzufuegn(Wein wein) {
        return weinDaten.add(wein);
    }
    
    public ArrayList<Wein> getWeinDaten() {
        return weinDaten;
    }
    
    @Override
    public String toString() {
        String inhalt = "";
        
        for (int i = 0; i < weinDaten.size(); i++) {
            inhalt += "Wein;" + weinDaten.get(i).toString() + "\n";
        }
        
        return inhalt;
    }
    
    public boolean isEmpty() {        
        return weinDaten.isEmpty();
    }
}
