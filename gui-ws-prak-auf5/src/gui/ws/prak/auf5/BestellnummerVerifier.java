/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf5;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Jakub Juszczak
 */
public class BestellnummerVerifier extends InputVerifier{
    
    private final String REGEX = "[0-9]{2}-[A-Z]{3}-[0-9]{4}";

    @Override
    public boolean verify(JComponent component) {
        return ((JTextField) component).getText().matches(REGEX);
    }
  
    
}
