
import javax.swing.InputVerifier;
import javax.swing.JComponent;
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
     
    @Override
    public boolean verify(JComponent input) {
        return ((JTextField) input).getText().matches(REGEX_KOMMA);
    }
    
}
