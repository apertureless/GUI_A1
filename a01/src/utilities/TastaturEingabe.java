package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

/**
 * 
 *
 * @author Jakub Juszczak
 */
public class TastaturEingabe {

    private final int MIN_LAGERDAUER = 1;
    private final int MAX_LAGERDAUER = 25;
    
    private final int AKTUELLES_JAHR = Calendar.getInstance().get(Calendar.YEAR);
    
    private final int MIN_JAHRGANG = AKTUELLES_JAHR - MAX_LAGERDAUER;
    private final int MAX_JAHRGANG = AKTUELLES_JAHR;
    

    private int jahrgang;
    private int lagerdauer;
    
    private static final String MSG_ERR_FORMAT = "Fehler\n Falsches Format!";
    private static final String MSG_ERR_BEREICH = "Fehler\n\t Gültigkeitsbereich"
                                                    + "nicht eingehalten.";
    
    

    /**
     * Erzeugt ein neues TastaturEingabe Objekt.
     */
    public TastaturEingabe() {
    }

    /**
     * Liefert den Jahrgang.
     *
     * @return Jahrgang
     */
    public int jahrgang() {
        return this.jahrgang;
    }

    /**
     * Liefert die Lagerdauer.
     *
     * @return Lagerdauer
     */
    public int lagerdauer() {
        return this.lagerdauer;
    }

    /**
     * Prueft die Benutzereingabe und gibt Fehlermeldungen aus.
     *
     * @throws IOException Fehlerbehandlung zur Benutzereingabe
     */
    public void pruefeEingabe() throws IOException {
       
    }

}