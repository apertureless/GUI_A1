package gui.ws.prak.auf3;


import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/**
 *
 * @author Jakub Juszczak <juszczak.j@googlemail.com>
 */
public class EingabeCheck extends InputVerifier {

    private static final String REGEX_KOMMA = "(\\d*,?\\d{0,2})|(\\d{0,3}(\\.\\d{3})*,?\\d{0,2})";
    private static final String FEHLER_TITEL = "Keine gültige Zahl - Verify";
    private static final String FEHLER_MSG = "Bitte geben Sie eine Zahl im deutschen Format ein. (12,34 oder 1.234,56) ";
    
    
    @Override
    public boolean verify(JComponent input) {
        return ((JTextField) input).getText().matches(REGEX_KOMMA);
    }
    
    @Override
    public boolean shouldYieldFocus(JComponent input) {
        boolean istGueltig = verify(input); 
        if (!istGueltig) {
            JOptionPane.showMessageDialog(null, FEHLER_MSG, FEHLER_TITEL, JOptionPane.WARNING_MESSAGE);
        }
        return istGueltig;
    }
}
