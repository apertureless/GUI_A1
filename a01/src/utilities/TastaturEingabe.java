package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

/**
 * Tastatur Eingabe und Werte Überprüfung für die Berechnung 
 * der Trinkreife eines Weines.
 *
 * @author Jakub Juszczak
 */
public class TastaturEingabe {

    // Konstante Eigenschaften zur berechnung der Trinkreife.
    
    private final int MIN_LAGERDAUER = 1;
    private final int MAX_LAGERDAUER = 25;
    
    private final int AKTUELLES_JAHR = Calendar.getInstance().get(Calendar.YEAR);
    
    private final int MIN_JAHRGANG = AKTUELLES_JAHR - MAX_LAGERDAUER;
    private final int MAX_JAHRGANG = AKTUELLES_JAHR;
    

    private int jahrgang;
    private int lagerdauer;
    
    // Fehler und Informations Nachrichten
    private static final String MSG_ERR_FORMAT = "Fehler\n Falsches Format!";
    private static final String MSG_ERR_BEREICH = "Fehler\n\t Gültigkeitsbereich"
                                                    + " nicht eingehalten.";
    private static final String MSG_ERR_KURZ = "Lagerdauer zu kurz.";
    
    private static final String MSG_INFO_LAGER = "Lagerdauer (%d - %d): ";
    private static final String MSG_INFO_JAHRGANG = "Jahrgang (%d - %d): ";

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
        String sJahrgang;
        String sLagerdauer;

        this.jahrgang = 0;
        this.lagerdauer = 0;

        InputStreamReader rdr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(rdr);

        do {
            System.out.printf(MSG_INFO_JAHRGANG,
                    this.MIN_JAHRGANG, this.MAX_JAHRGANG);
            sJahrgang = reader.readLine();
            try {
                this.jahrgang = Integer.parseInt(sJahrgang);

                if (this.jahrgang > this.MAX_JAHRGANG
                        || this.jahrgang < this.MIN_JAHRGANG) {
                    this.jahrgang = 0;
                    System.out.println(MSG_ERR_BEREICH);
                }
            } catch (NumberFormatException nfe) {
                System.out.println(MSG_ERR_FORMAT);
            }
        } while (this.jahrgang == 0);

        do {
            System.out.printf(MSG_INFO_LAGER,
                    this.MIN_LAGERDAUER, this.MAX_LAGERDAUER);
            sLagerdauer = reader.readLine();
            try {
                this.lagerdauer = Integer.parseInt(sLagerdauer);
                if (this.lagerdauer == 0) {
                    System.out.println(MSG_ERR_KURZ);
                }
                if (this.lagerdauer > this.MAX_LAGERDAUER
                        || this.lagerdauer < this.MIN_LAGERDAUER) {
                    this.lagerdauer = 0;
                    System.out.println(MSG_ERR_BEREICH);
                }
            } catch (NumberFormatException nfe) {
                System.out.println(MSG_ERR_FORMAT);
            }
        } while (this.lagerdauer == 0);
    }

}