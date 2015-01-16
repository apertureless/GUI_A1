/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf8;

import javafx.scene.control.TextField;

/**
 *
 * @author Jakub Juszczak
 */



public class NumberTextField extends TextField {
    
    private static final String ERLAUBTE_ZEICHEN2 ="0123456789,.";
    private static final String ERLAUBTE_ZEICHEN ="[0-9,]";
    private static final String REGEX = "(\\d*,?\\d*)|(\\d{0,3}(\\.\\d{3})*,?\\d*)";
    
    public NumberTextField() {
        this.setPromptText("im Format x,xx");
    }
    
    @Override
    public void replaceText(int i, int i1, String string){
        if(string.matches(ERLAUBTE_ZEICHEN) || string.isEmpty()) {
            super.replaceText(i, i1, string);
        }
    }
    
    @Override
    public void replaceSelection(String string){ 
        super.replaceSelection(string);
    }
    
}
