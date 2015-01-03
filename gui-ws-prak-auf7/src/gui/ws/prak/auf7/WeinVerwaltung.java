/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf7;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.DEFAULT_OPTION;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author NetGhost03
 */
public class WeinVerwaltung extends javax.swing.JFrame {
    
    private static final int MIN_LAGERDAUER = 1;
    private static final int MAX_LAGERDAUER = 25;
    
    private static final int AKTUELLES_JAHR = Calendar.getInstance().get(Calendar.YEAR);
    
    private static final int MIN_JAHRGANG = AKTUELLES_JAHR - MAX_LAGERDAUER;
    private static final int MAX_JAHRGANG = AKTUELLES_JAHR;
    
    private static final String CLOSE_TITEL = "Programm Beenden?";
    private static final String CLOSE_MSG = "Sind Sie sicher, dass Sie das Programm beenden wollen?\nAlle Daten gehen verloren.";
    private static final String ABORT_MSG = "Sind Sie sicher, dass Sie die Weinaufnahme beenden wollen?\nNicht gespeicherte Daten gehen verloren.";
    private static final String ABORT_TITEL = "Weinaufnahme Beenden";
    private static final String SAVE_ERR_MSG ="Wein konnte nicht gespeichert werden.\nEingabe unvollständig.";
    private static final String SAVE_ERR_TITEL ="Fehler beim Speichern.";
    
    private static final String MSG_ERR_FORMAT = "Jahrgang darf nicht leer sein";
    
    private static final String MSG_ERR_BEREICH_1 = "Der Jahrgang muss zwischen dem Jahr " + MIN_JAHRGANG + " und " + MAX_JAHRGANG + " liegen";
    private static final String MSG_ERR_BEREICH_2 = "Der Jahrgang liegt in der Zukunft. Das ist nicht möglich.";
    private static final String MSG_ERR_KURZ = "Lagerdauer zu kurz.";
    
    private static final String MSG_INFO_LAGER = "Lagerdauer (%d - %d): ";
    private static final String MSG_INFO_JAHRGANG = "Jahrgang (%d - %d): ";
    
    private static final String SPEICHERN_ACK_MSG = "Die Speicherung Ihrer Daten war erfolgreich.";
    private static final String SPEICHERN_ACK_TITEL ="Speichern erfolgreich.";
    private static final String SPEICHERN_ERR_MSG = "Leider ist ein Fehler beim Speichern Ihrer Daten aufgetreten.";
    private static final String SPEICHERN_ERR_TITEL = "Speichern nicht erfolgreich!";
    
    double jahrgang;
    int lagerdauer = 0;
    boolean isValid = true;
    private int laufnummer = 0; 
    private int laufnummerDB = 0; 
    private boolean isComboBoxChanged = false;
    
    private static final EingabeCheck ec = new EingabeCheck();
    
    private boolean istButtonFlasche = false;
    private boolean istButtonLiter = false;
    
    private boolean istLiterBerechnung = false;
    private boolean istFlaschenBerechnung = false; 
    
    private boolean isFocusLost = false;
   
    private static final String ERLAUBTE_ZEICHEN ="0123456789,.";
    private static final String REGEX = "(\\d*,?\\d*)|(\\d{0,3}(\\.\\d{3})*,?\\d*)";
    
    private final BestellnummerVerifier bnv;
    
    private int WeinDatenIndex = 0;
    
    Lagergut lg = new Wein();

    private final String[] LAENDER = new String[]{
        "Land_1", "Land_2", "Land_3", "Land_4", "Land_5",
        "Land_6", "Land_7", "Land_8", "Land_9", "Land_10",
        "Land_11", "Land_12", "Land_13", "Land_14", "Land_15",
        "Land_16", "Land_17", "Land_18", "Land_19", "Land_20"};
    
    private final String[] LAND_1 = new String[]{"L1_Region_1", "L1_Region_2", "L1_Region_3",
        "L1_Region_4", "L1_Region_5", "L1_Region_6", "L1_Region_7", "L1_Region_8",
        "L1_Region_9", "L1_Region_10", "L1_Region_11", "L1_Region_12", "L1_Region_13", "L1_Region_14",
        "L1_Region_15", "L1_Region_16"};
        
    private final String[] LAND_2 = new String[]{"L2_Region_1", "L2_Region_2", "L2_Region_3",
        "L2_Region_4", "L2_Region_5", "L2_Region_6", "L2_Region_7", "L2_Region_8",
        "L2_Region_9", "L2_Region_10", "L2_Region_11", "L2_Region_12", "L2_Region_13", "L2_Region_14",
        "L2_Region_15", "L2_Region_16"};

    private final String[] LAND_3 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_4 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    
    private final String[] LAND_5 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_6 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_7 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_8 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_9 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_10 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_11 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_12 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_13 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_14 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_15 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_16 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_17 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_18 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_19 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};
    
    private final String[] LAND_20 = new String[]{"L3_Region_1", "L3_Region_2", "L3_Region_3",
        "L3_Region_4", "L3_Region_5", "L3_Region_6", "L3_Region_7", "L3_Region_8",
        "L3_Region_9", "L3_Region_10", "L3_Region_11", "L3_Region_12", "L3_Region_13", "L3_Region_14",
        "L3_Region_15", "L3_Region_16"};

    private final ArrayList<String[]> REB_LAENDER
            = new ArrayList<>();

    private final HashMap<String, String[]> LAND_UND_REGION
            = new HashMap<>();
    
    private static final String DATEIENDUNG = "wd";
    private static final String TRENNER = ";";
    private WeinDaten weinDaten = null;
    private File aktuelleDatei = null;
    private static boolean isChangeForm = false;
    
    private final FileNameExtensionFilter filter = new FileNameExtensionFilter("Weindatenbank (.wd)", DATEIENDUNG);
    private JFileChooser dateiAuswahl = new JFileChooser();

    /**
     * Klasse gibt alle gültigen Eingabezeichen JTPreisEingabe Feld vor.
     */
    public class EingabeDokument extends PlainDocument {
       
        /**
         *
         * @param offs
         * @param s
         * @param as
         * @throws BadLocationException
         */
        @Override
        public void insertString(int offs, String s, javax.swing.text.AttributeSet as) throws BadLocationException {
       
            for (int i = 0; i < s.length(); i++) {
                if (!ERLAUBTE_ZEICHEN.contains("" + s.charAt(i)) ){
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
            }
            
            if (!istButtonFlasche) {
                jTPreisAusgabe.setText("");
            }
            
            super.insertString(offs, s, null);
        }
    }
    /**
     * Klasse gibt alle gültigen Eingabezeichen für JTPreisAusgabe Feld vor.
     */
    private class AusgabeDokument extends PlainDocument {
        
         @Override
         public void insertString(int offs, String str, javax.swing.text.AttributeSet as) throws BadLocationException {
            for (int i = 0; i < str.length(); i++) {
                if (!ERLAUBTE_ZEICHEN.contains("" + str.charAt(i)) ){
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
            }
            
            if (!istButtonLiter) {
                jTPreisEingabe.setText("");
            }
            
            super.insertString(offs, str, null);
        }
    }
    
    private class NameDocument extends PlainDocument {
        private static final String BLOCK_CHAR = ";";
         @Override
         public void insertString(int offs, String str, javax.swing.text.AttributeSet as) throws BadLocationException {
            for (int i = 0; i < str.length(); i++) {
                if (BLOCK_CHAR.contains("" + str.charAt(i)) ){
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
            }
            
            super.insertString(offs, str, null);
        }
    }
    
     private class FocusOrder extends FocusTraversalPolicy {

        ArrayList<JComponent> order;

        public FocusOrder(ArrayList<JComponent> order) {
            this.order = order;
        }

        @Override
        public Component getComponentAfter(Container aContainer, Component aComponent) {
            int index = order.indexOf(aComponent);
            if(!order.get((index + 1) % order.size()).isEnabled()) {
                boolean enabled = false;
                while(!enabled) {
                    index = (index + 1) % order.size();
                    enabled = order.get(index).isEnabled();
                }
            } else {
                index = (order.indexOf(aComponent) + 1) % order.size();
            }
            return order.get(index);
        }

        @Override
        public Component getComponentBefore(Container aContainer, Component aComponent) {
            int index = order.indexOf(aComponent);
            if(!order.get((index - 1) % order.size()).isEnabled()) {
                boolean enabled = false;
                while(!enabled) {
                    index = (index - 1) % order.size();
                    enabled = order.get(index).isEnabled();
                }
            } else {
                index = (order.indexOf(aComponent) - 1) % order.size();
            }
            return order.get(index);
        }

        @Override
        public Component getFirstComponent(Container aContainer) {
            return order.get(0);
        }

        @Override
        public Component getLastComponent(Container aContainer) {
            return order.get(order.size() - 1);
        }

        @Override
        public Component getDefaultComponent(Container aContainer) {
            return order.get(0);
        }

         
     }
       
    /**
     * Creates new form WeinVerwaltung
     */
    public WeinVerwaltung() {
        initComponents();
        
        fuelleRebLaender();
        fuelleLandUndRegionBeziehung(REB_LAENDER);
        
        FillAnbaugebiet(LAENDER);
        
        bnv = new BestellnummerVerifier();
        
        MaskFormatter mf = null;
        NumberFormat nf = new DecimalFormat("0000");
        
        jTextFieldName.setDocument(new NameDocument());
        jTPreisEingabe.setDocument(new EingabeDokument());
        jTPreisEingabe.setInputVerifier(ec);
        jTPreisAusgabe.setDocument(new AusgabeDokument());
        jTPreisAusgabe.setInputVerifier(ec);
        
        
        jBFirstItem.setVisible(false);
        jBPrevItem.setVisible(false);
        jBNextItem.setVisible(false);
        jBLastItem.setVisible(false);
  
        // Maskformatter anlegen für das Bestellummer-Feld
        // Im Format 01-ABCD-0001
        try {
            
            mf = new MaskFormatter("##-UUU-" + nf.format(laufnummer + 1));
            mf.setPlaceholderCharacter('_');
            DefaultFormatterFactory dff = new DefaultFormatterFactory(mf);
            jFTextfieldBestellnummer.setFormatterFactory(dff);
            
        } catch (ParseException ex) {
            Logger.getLogger(WeinVerwaltung.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        NumberFormat nf2 = NumberFormat.getInstance();
        
        NumberFormatter nform = new NumberFormatter(nf2);
        DefaultFormatterFactory dff = new DefaultFormatterFactory(nform);
        nf2.setMinimumFractionDigits(0);
        nf2.setMaximumFractionDigits(0);
        nf2.setGroupingUsed(false);
        nform.setAllowsInvalid(false);
        nform.setOverwriteMode(true);
       
        nform.setValueClass(Integer.class);
        nform.setMinimum(0);
        nform.setMaximum(9999);

        jFTJahrgang.setFormatterFactory(dff);
        diagramm1.setVisible(false);
        
        dateiAuswahl.addChoosableFileFilter(filter);
        dateiAuswahl.setAcceptAllFileFilterUsed(true);
        dateiAuswahl.setFileFilter(filter);
        dateiSpeichern.setEnabled(false);
        dateiSpeichernUnter.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        WeinAufnehmenFrame = new javax.swing.JInternalFrame();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        WeinDatenPanel = new javax.swing.JPanel();
        jFTextfieldBestellnummer = new javax.swing.JFormattedTextField();
        jLBestellnummer = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLName = new javax.swing.JLabel();
        jComboBoxFarbe = new javax.swing.JComboBox();
        jLFarbe = new javax.swing.JLabel();
        jLLand = new javax.swing.JLabel();
        jComboBoxLand = new javax.swing.JComboBox();
        jComboBoxRegion = new javax.swing.JComboBox();
        jLRegion = new javax.swing.JLabel();
        jLAlkohol = new javax.swing.JLabel();
        jComboBoxAlkoholgehalt = new javax.swing.JComboBox();
        jButtonSpeichern = new javax.swing.JButton();
        jButtonAbbrechen = new javax.swing.JButton();
        jFTJahrgang = new javax.swing.JFormattedTextField();
        jLabelJahrgang = new javax.swing.JLabel();
        jSLagerdauer = new javax.swing.JSpinner();
        jLabelLagerdauer = new javax.swing.JLabel();
        jCFlaschengroesse = new javax.swing.JComboBox();
        jLFenstergroesse = new javax.swing.JLabel();
        jTPreisEingabe = new javax.swing.JTextField();
        jLFlaschenpreis = new javax.swing.JLabel();
        jLCurrency = new javax.swing.JLabel();
        jBUmrechnenUp = new javax.swing.JButton();
        jBDown = new javax.swing.JButton();
        jTPreisAusgabe = new javax.swing.JTextField();
        jLPreisLiter = new javax.swing.JLabel();
        jLCurrency1 = new javax.swing.JLabel();
        jBFirstItem = new javax.swing.JButton();
        jBLastItem = new javax.swing.JButton();
        jBPrevItem = new javax.swing.JButton();
        jBNextItem = new javax.swing.JButton();
        DiagrammPanel = new javax.swing.JPanel();
        diagramm1 = new gui.ws.prak.auf7.Diagramm();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuDatei = new javax.swing.JMenu();
        dateiOeffnen = new javax.swing.JMenuItem();
        dateiSpeichern = new javax.swing.JMenuItem();
        dateiSpeichernUnter = new javax.swing.JMenuItem();
        jMenuItemBeenden = new javax.swing.JMenuItem();
        jMenuBearbeiten = new javax.swing.JMenu();
        jMenuWein = new javax.swing.JMenu();
        jMenuItemAufnehmen = new javax.swing.JMenuItem();
        jMenuItemAendern = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Wein Verwaltung");
        setMinimumSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        WeinAufnehmenFrame.setClosable(true);
        WeinAufnehmenFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        WeinAufnehmenFrame.setIconifiable(true);
        WeinAufnehmenFrame.setMaximizable(true);
        WeinAufnehmenFrame.setResizable(true);
        WeinAufnehmenFrame.setTitle("Wein aufnehmen");
        WeinAufnehmenFrame.setMinimumSize(new java.awt.Dimension(525, 655));
        WeinAufnehmenFrame.setVisible(false);
        WeinAufnehmenFrame.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                WeinAufnehmenFrameInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        WeinDatenPanel.setFocusTraversalPolicy(new FocusOrder(getOrder()));
        WeinDatenPanel.setFocusTraversalPolicyProvider(true);

        jFTextfieldBestellnummer.setToolTipText("");

        jLBestellnummer.setLabelFor(jFTextfieldBestellnummer);
        jLBestellnummer.setText("Bestellnummer");

        jTextFieldName.setMaximumSize(new java.awt.Dimension(6, 20));
        jTextFieldName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNameFocusGained(evt);
            }
        });

        jLName.setLabelFor(jTextFieldName);
        jLName.setText("Name");

        jComboBoxFarbe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rot", "Weiß", "Rose" }));
        jComboBoxFarbe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFarbeActionPerformed(evt);
            }
        });

        jLFarbe.setLabelFor(jComboBoxFarbe);
        jLFarbe.setText("Farbe");

        jLLand.setText("Anbaugebiet");

        jComboBoxLand.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxLandItemStateChanged(evt);
            }
        });
        jComboBoxLand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLandActionPerformed(evt);
            }
        });

        jComboBoxRegion.setEnabled(false);
        jComboBoxRegion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRegionActionPerformed(evt);
            }
        });

        jLRegion.setText("Region");

        jLAlkohol.setText("Alkoholgehalt");

        jComboBoxAlkoholgehalt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Vol%", "7,5 Vol%", "8 Vol%", "8,5 Vol%", "9 Vol%", "9,5 Vol%", "10 Vol%", "10,5 Vol%", "11 Vol%", "11,5 Vol%", "12 Vol%", "12,5 Vol%", "13 Vol%" }));
        jComboBoxAlkoholgehalt.setSelectedIndex(8);
        jComboBoxAlkoholgehalt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAlkoholgehaltActionPerformed(evt);
            }
        });

        jButtonSpeichern.setText("Speichern");
        jButtonSpeichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSpeichernActionPerformed(evt);
            }
        });

        jButtonAbbrechen.setText("Abbrechen");
        jButtonAbbrechen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbbrechenActionPerformed(evt);
            }
        });

        jFTJahrgang.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFTJahrgang.setToolTipText("");
        jFTJahrgang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTJahrgangFocusLost(evt);
            }
        });
        jFTJahrgang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFTJahrgangActionPerformed(evt);
            }
        });

        jLabelJahrgang.setLabelFor(jFTJahrgang);
        jLabelJahrgang.setText("Jahrgang");

        jSLagerdauer.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSLagerdauerStateChanged(evt);
            }
        });
        jSLagerdauer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSLagerdauerFocusGained(evt);
            }
        });

        jLabelLagerdauer.setText("Lagerdauer");

        jCFlaschengroesse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0,187 l", "0,25 l", "0,375 l", "0,5 l", "0,62 l", "0,7 l", "0,75 l", "0,8 l", "1 l", "1,5 l " }));
        jCFlaschengroesse.setSelectedIndex(6);
        jCFlaschengroesse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCFlaschengroesseActionPerformed(evt);
            }
        });
        jCFlaschengroesse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCFlaschengroesseKeyPressed(evt);
            }
        });

        jLFenstergroesse.setText("Flaschengröße");

        jTPreisEingabe.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTPreisEingabe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTPreisEingabeFocusGained(evt);
            }
        });

        jLFlaschenpreis.setText("Flaschenpreis");

        jLCurrency.setText("€");

        jBUmrechnenUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ws/prak/auf7/arrow-down.png"))); // NOI18N
        jBUmrechnenUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBUmrechnenUpActionPerformed(evt);
            }
        });

        jBDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ws/prak/auf7/arrow-up.png"))); // NOI18N
        jBDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDownActionPerformed(evt);
            }
        });

        jTPreisAusgabe.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTPreisAusgabe.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTPreisAusgabeFocusGained(evt);
            }
        });

        jLPreisLiter.setText("Preis pro Liter");

        jLCurrency1.setText("€");

        jBFirstItem.setText("<<");
        jBFirstItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFirstItemActionPerformed(evt);
            }
        });

        jBLastItem.setText(">>");
        jBLastItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLastItemActionPerformed(evt);
            }
        });

        jBPrevItem.setText("<");
        jBPrevItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPrevItemActionPerformed(evt);
            }
        });

        jBNextItem.setText(">");
        jBNextItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBNextItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout WeinDatenPanelLayout = new javax.swing.GroupLayout(WeinDatenPanel);
        WeinDatenPanel.setLayout(WeinDatenPanelLayout);
        WeinDatenPanelLayout.setHorizontalGroup(
            WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeinDatenPanelLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WeinDatenPanelLayout.createSequentialGroup()
                            .addComponent(jBUmrechnenUp, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jBDown, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(WeinDatenPanelLayout.createSequentialGroup()
                                .addComponent(jButtonSpeichern)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonAbbrechen))
                            .addGroup(WeinDatenPanelLayout.createSequentialGroup()
                                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLBestellnummer)
                                    .addComponent(jLName)
                                    .addComponent(jLFarbe)
                                    .addComponent(jLLand)
                                    .addComponent(jLRegion)
                                    .addComponent(jLAlkohol)
                                    .addComponent(jLabelJahrgang)
                                    .addComponent(jLabelLagerdauer)
                                    .addComponent(jLFenstergroesse)
                                    .addComponent(jLFlaschenpreis)
                                    .addGroup(WeinDatenPanelLayout.createSequentialGroup()
                                        .addComponent(jBFirstItem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBPrevItem)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jCFlaschengroesse, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFTJahrgang, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxRegion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxFarbe, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFTextfieldBestellnummer, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxLand, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxAlkoholgehalt, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSLagerdauer, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTPreisEingabe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addGroup(WeinDatenPanelLayout.createSequentialGroup()
                                        .addComponent(jBNextItem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBLastItem))
                                    .addComponent(jTextFieldName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, WeinDatenPanelLayout.createSequentialGroup()
                        .addComponent(jLPreisLiter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTPreisAusgabe, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLCurrency)
                    .addComponent(jLCurrency1))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        WeinDatenPanelLayout.setVerticalGroup(
            WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeinDatenPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBFirstItem)
                    .addComponent(jBLastItem)
                    .addComponent(jBPrevItem)
                    .addComponent(jBNextItem))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTextfieldBestellnummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLBestellnummer))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLName))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTJahrgang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelJahrgang))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSLagerdauer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLagerdauer))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFarbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLFarbe))
                .addGap(24, 24, 24)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLLand)
                    .addComponent(jComboBoxLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLRegion))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLAlkohol)
                    .addComponent(jComboBoxAlkoholgehalt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCFlaschengroesse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLFenstergroesse))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTPreisEingabe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLCurrency)
                    .addComponent(jLFlaschenpreis))
                .addGap(15, 15, 15)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBUmrechnenUp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBDown, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTPreisAusgabe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLPreisLiter)
                    .addComponent(jLCurrency1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSpeichern)
                    .addComponent(jButtonAbbrechen))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Weindaten", WeinDatenPanel);

        javax.swing.GroupLayout diagramm1Layout = new javax.swing.GroupLayout(diagramm1);
        diagramm1.setLayout(diagramm1Layout);
        diagramm1Layout.setHorizontalGroup(
            diagramm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );
        diagramm1Layout.setVerticalGroup(
            diagramm1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DiagrammPanelLayout = new javax.swing.GroupLayout(DiagrammPanel);
        DiagrammPanel.setLayout(DiagrammPanelLayout);
        DiagrammPanelLayout.setHorizontalGroup(
            DiagrammPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiagrammPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(diagramm1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        DiagrammPanelLayout.setVerticalGroup(
            DiagrammPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiagrammPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(diagramm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(369, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Diagramm", DiagrammPanel);

        javax.swing.GroupLayout WeinAufnehmenFrameLayout = new javax.swing.GroupLayout(WeinAufnehmenFrame.getContentPane());
        WeinAufnehmenFrame.getContentPane().setLayout(WeinAufnehmenFrameLayout);
        WeinAufnehmenFrameLayout.setHorizontalGroup(
            WeinAufnehmenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeinAufnehmenFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        WeinAufnehmenFrameLayout.setVerticalGroup(
            WeinAufnehmenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeinAufnehmenFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDesktopPane1.add(WeinAufnehmenFrame);
        WeinAufnehmenFrame.setBounds(130, 60, 540, 737);

        jMenuDatei.setMnemonic('D');
        jMenuDatei.setText("Datei");

        dateiOeffnen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        dateiOeffnen.setText("Öffnen");
        dateiOeffnen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateiOeffnenActionPerformed(evt);
            }
        });
        jMenuDatei.add(dateiOeffnen);

        dateiSpeichern.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        dateiSpeichern.setText("Speichern");
        dateiSpeichern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateiSpeichernActionPerformed(evt);
            }
        });
        jMenuDatei.add(dateiSpeichern);

        dateiSpeichernUnter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        dateiSpeichernUnter.setText("Speichern Unter");
        dateiSpeichernUnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateiSpeichernUnterActionPerformed(evt);
            }
        });
        jMenuDatei.add(dateiSpeichernUnter);

        jMenuItemBeenden.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemBeenden.setMnemonic('B');
        jMenuItemBeenden.setText("Beenden");
        jMenuItemBeenden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBeendenActionPerformed(evt);
            }
        });
        jMenuDatei.add(jMenuItemBeenden);

        jMenuBar1.add(jMenuDatei);

        jMenuBearbeiten.setText("Bearbeiten");
        jMenuBar1.add(jMenuBearbeiten);

        jMenuWein.setMnemonic('W');
        jMenuWein.setText("Wein");

        jMenuItemAufnehmen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemAufnehmen.setMnemonic('A');
        jMenuItemAufnehmen.setText("Aufnehmen");
        jMenuItemAufnehmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAufnehmenActionPerformed(evt);
            }
        });
        jMenuWein.add(jMenuItemAufnehmen);

        jMenuItemAendern.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemAendern.setText("Ändern");
        jMenuItemAendern.setEnabled(false);
        jMenuItemAendern.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAendernActionPerformed(evt);
            }
        });
        jMenuWein.add(jMenuItemAendern);

        jMenuItem2.setText("Löschen");
        jMenuItem2.setEnabled(false);
        jMenuWein.add(jMenuItem2);

        jMenuBar1.add(jMenuWein);

        jMenuHelp.setMnemonic('?');
        jMenuHelp.setText("?");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem3.setMnemonic('I');
        jMenuItem3.setText("Info");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItem3);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAufnehmenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAufnehmenActionPerformed
        laufnummer = laufnummerDB;
        
        resetFormular();
        jFTextfieldBestellnummer.setEnabled(true);
        WeinAufnehmenFrame.setVisible(true);  
        WeinAufnehmenFrame.setTitle("Wein Aufnehmen");
        
        
    }//GEN-LAST:event_jMenuItemAufnehmenActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       JOptionPane.showInternalConfirmDialog(jDesktopPane1, "Weinverwaltung v.01", "Informationen", DEFAULT_OPTION);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItemBeendenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBeendenActionPerformed
        CloseWithPrompt();
    }//GEN-LAST:event_jMenuItemBeendenActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       CloseWithPrompt();
       
    }//GEN-LAST:event_formWindowClosing

    private void WeinAufnehmenFrameInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_WeinAufnehmenFrameInternalFrameClosing
       closeFormular();
    }//GEN-LAST:event_WeinAufnehmenFrameInternalFrameClosing

    private void jComboBoxRegionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRegionActionPerformed
      
    }//GEN-LAST:event_jComboBoxRegionActionPerformed

    private void jComboBoxLandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLandActionPerformed
        if(jComboBoxLand.getSelectedIndex() != 0) {
            jComboBoxRegion.setEnabled(true);
            isComboBoxChanged = true;
        } else {
            jComboBoxRegion.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBoxLandActionPerformed

    private void jComboBoxLandItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxLandItemStateChanged
          String anbaugebiet = jComboBoxLand.getSelectedItem().toString();
          fillRegion(anbaugebiet);
    }//GEN-LAST:event_jComboBoxLandItemStateChanged

    private void jButtonAbbrechenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbbrechenActionPerformed
        // TODO add your handling code here:
        closeFormular();
    }//GEN-LAST:event_jButtonAbbrechenActionPerformed

    private void jButtonSpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSpeichernActionPerformed
        
        // Variable für Fehlerstatus
        boolean istFehlerGefunden = false;
        resetColors();
  
        // Abfrage ob ein Fehler gefunden wurde.
         if (!bnv.verify(jFTextfieldBestellnummer)) {
            jLBestellnummer.setForeground(Color.RED);
            
            if(!istFehlerGefunden) {
              
               jFTextfieldBestellnummer.requestFocusInWindow();
            }
            istFehlerGefunden = true;
        }
         
        if (jTextFieldName.getText().equals("")) {
            jLName.setForeground(Color.RED);
            if(!istFehlerGefunden) {
               jTextFieldName.requestFocus();
            }
            istFehlerGefunden = true;
        }
                
        if (jFTJahrgang.getText().equals("")) {
            jLabelJahrgang.setForeground(Color.RED);
            if(!istFehlerGefunden) {
               jFTJahrgang.requestFocusInWindow();
            }
            istFehlerGefunden = true;
        }
        
        if (jComboBoxLand.getSelectedIndex() == 0) {
            jLLand.setForeground(Color.RED);
            if(!istFehlerGefunden) {
               jComboBoxLand.requestFocusInWindow();
            }
            istFehlerGefunden = true;
        }
        
        if (jTPreisEingabe.getText().equals("") && jTPreisAusgabe.getText().equals("")) {
            jLFlaschenpreis.setForeground(Color.RED);
            if(!istFehlerGefunden) {
               jTPreisEingabe.requestFocusInWindow();
            }
            istFehlerGefunden = true;
        }
        
        if (jTPreisEingabe.getText().equals("") && !jTPreisAusgabe.getText().equals("")) {
            istButtonFlasche = true;
            berechneFlaschenpreis();
        }
        
        if (jComboBoxRegion.getSelectedIndex() == 0) {
            jLRegion.setForeground(Color.RED);
            if(!istFehlerGefunden) {
               jComboBoxRegion.requestFocusInWindow();
            }
            istFehlerGefunden = true;
        }
        
        // Falls Fehler gefunden wurde
        if (istFehlerGefunden) {
            //JOptionPane.showInternalMessageDialog(jDesktopPane1, SAVE_ERR_MSG, SAVE_ERR_TITEL, JOptionPane.ERROR_MESSAGE);
             JOptionPane.showMessageDialog(null, SAVE_ERR_MSG, SAVE_ERR_TITEL, JOptionPane.ERROR_MESSAGE);
        } else {
            
            // Wein Objekt anlegen
            Wein neuerWein = new Wein(jFTextfieldBestellnummer.getText(),
                                        jTextFieldName.getText(),
                                        (int)jahrgang,
                                        (int)lagerdauer,
                                        jComboBoxFarbe.getSelectedItem().toString(),
                                        jComboBoxLand.getSelectedItem().toString(),
                                        jComboBoxRegion.getSelectedItem().toString(),
                                        jComboBoxAlkoholgehalt.getSelectedItem().toString(),
                                        jCFlaschengroesse.getSelectedItem().toString(),
                                        jTPreisEingabe.getText()
                                    );
            
            // Schauen ob die Arraylist leer ist.
            
            if (weinDaten == null) {
                // Neues Weindaten Objekt anlegen
                weinDaten = new WeinDaten();
            }
            
            if (isChangeForm) {
                weinDaten.setWeinDaten(WeinDatenIndex, neuerWein);
            } else {
                // Aktuellen Wein hinzufügen
            weinDaten.addWeinDaten(neuerWein);
            }
            
            
            System.out.println(neuerWein.toString());
            if (!isChangeForm) {
                laufnummer++;
                resetFormular(); 
            } else {
                //JOptionPane.showMessageDialog(null, SPEICHERN_ACK_MSG, SPEICHERN_ACK_TITEL, JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showInternalMessageDialog(jDesktopPane1, SPEICHERN_ACK_MSG, SPEICHERN_ACK_TITEL, JOptionPane.INFORMATION_MESSAGE);
            }
          
            
            // Aktivieren der Speicher-Menüpunkte
            
            if (weinDaten != null && !dateiSpeichern.isEnabled() && !dateiSpeichernUnter.isEnabled() && !jMenuItemAendern.isEnabled()) {
                dateiSpeichern.setEnabled(true);
                dateiSpeichernUnter.setEnabled(true);
                jMenuItemAendern.setEnabled(true);
            } else if (weinDaten == null && dateiSpeichern.isEnabled() && dateiSpeichernUnter.isEnabled() && jMenuItemAendern.isEnabled()) {
                dateiSpeichern.setEnabled(false);
                dateiSpeichernUnter.setEnabled(false);
                jMenuItemAendern.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jButtonSpeichernActionPerformed

    private void jTextFieldNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNameFocusGained
        jTextFieldName.selectAll();
    }//GEN-LAST:event_jTextFieldNameFocusGained

    private void jComboBoxFarbeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFarbeActionPerformed
       isComboBoxChanged = true;
    }//GEN-LAST:event_jComboBoxFarbeActionPerformed

    private void jComboBoxAlkoholgehaltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAlkoholgehaltActionPerformed
        isComboBoxChanged = true;
    }//GEN-LAST:event_jComboBoxAlkoholgehaltActionPerformed

    private void jFTJahrgangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFTJahrgangActionPerformed
        EingabeCheckJahrgang();
        if(isValid) {
            SetSpinnerValues();
            SetDiagrammValues();
        }
    }//GEN-LAST:event_jFTJahrgangActionPerformed

    private void jFTJahrgangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTJahrgangFocusLost
        EingabeCheckJahrgang();
        if(isValid) {
            SetSpinnerValues(); 
            SetDiagrammValues();
        }
    }//GEN-LAST:event_jFTJahrgangFocusLost

    private void jSLagerdauerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSLagerdauerStateChanged
        lagerdauer = (int)jSLagerdauer.getValue() - (int)jahrgang;
        SetDiagrammValues();
    }//GEN-LAST:event_jSLagerdauerStateChanged

    private void jSLagerdauerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSLagerdauerFocusGained
//        EingabeCheckJahrgang();
//        if(isValid) {
//            SetSpinnerValues(); 
//            SetDiagrammValues();
//        }
    }//GEN-LAST:event_jSLagerdauerFocusGained

    private void jTPreisEingabeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTPreisEingabeFocusGained
        jTPreisEingabe.selectAll();
    }//GEN-LAST:event_jTPreisEingabeFocusGained

    private void jTPreisAusgabeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTPreisAusgabeFocusGained
        jTPreisAusgabe.selectAll();
    }//GEN-LAST:event_jTPreisAusgabeFocusGained

    private void jBUmrechnenUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBUmrechnenUpActionPerformed
       istButtonLiter = true;
       berechneLiterpreis();
    }//GEN-LAST:event_jBUmrechnenUpActionPerformed

    private void jBDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDownActionPerformed
        istButtonFlasche = true;
        berechneFlaschenpreis();
    }//GEN-LAST:event_jBDownActionPerformed

    private void jCFlaschengroesseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCFlaschengroesseKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           berechneLiterpreis();
       }
    }//GEN-LAST:event_jCFlaschengroesseKeyPressed

    private void jCFlaschengroesseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCFlaschengroesseActionPerformed
        isComboBoxChanged = true;
        if (istLiterBerechnung && !jTPreisEingabe.getText().isEmpty()) {
            istButtonLiter = true;
            berechneLiterpreis();  
            //istLiterBerechnung = false;
        }

        if (istFlaschenBerechnung && !jTPreisAusgabe.getText().isEmpty()) {
           istButtonFlasche = true;
           berechneFlaschenpreis();
           //istFlaschenBerechnung = false;
        }

    }//GEN-LAST:event_jCFlaschengroesseActionPerformed

    private void dateiSpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateiSpeichernActionPerformed
        if (aktuelleDatei != null) {
            speichern(aktuelleDatei);
        } else {
            dateiSpeichernUnterActionPerformed(evt);
        }
    }//GEN-LAST:event_dateiSpeichernActionPerformed

    private void dateiSpeichernUnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateiSpeichernUnterActionPerformed
        int response = dateiAuswahl.showSaveDialog(this);
        if (response == JFileChooser.APPROVE_OPTION) {
            speichern(dateiAuswahl.getSelectedFile());
        }
    }//GEN-LAST:event_dateiSpeichernUnterActionPerformed

    private void dateiOeffnenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateiOeffnenActionPerformed
        boolean dateiAusgewaehlt = true;
        File gewaehlt = null;
        Wein wein = null;

        int antwort = dateiAuswahl.showOpenDialog(this);
        if (antwort == JFileChooser.APPROVE_OPTION) {
            gewaehlt = dateiAuswahl.getSelectedFile();
            System.out.println("Ausgewählt: " + gewaehlt.getName());
        } else {
            dateiAusgewaehlt = false;
            System.out.println("Ausgewählt: nichts");
        }

        if (dateiAusgewaehlt) {
            boolean abbrechen = false;
            if (isFormularChanged()) {
                int speichern = JOptionPane.showConfirmDialog(this, "Wollen Sie Ihren Fortschritt Speichern?", "Speichern", JOptionPane.YES_NO_CANCEL_OPTION);
                if (speichern == JOptionPane.YES_OPTION) {
                    speichern(aktuelleDatei);
                } else {
                    abbrechen = speichern == JOptionPane.CANCEL_OPTION;
                }
            }

            if (!abbrechen) {
                boolean fehler = false;
                aktuelleDatei = null;
                weinDaten = new WeinDaten();
    
                int anzahlWerte = 0;
                int anzahlFehler = 0;
                String zeile;
                try {
                    BufferedReader eingabe = new BufferedReader(new FileReader(gewaehlt));
                    while (eingabe.ready()) {
                        zeile = eingabe.readLine();
                        String[] daten = zeile.split(TRENNER);

                   
                        if (daten[0].equals("Wein")) {
                            try {
                                weinDaten.addWeinDaten(Wein
                                        .valueOf(daten));
                                anzahlWerte++;
                            } catch (IllegalArgumentException we) {
                                anzahlFehler++;
                                System.out.println(we.getMessage());
                            }
                        }
                    }
                    eingabe.close();

                    resetFormular();
                    if (!weinDaten.isEmpty() && !dateiSpeichern.isEnabled() && !dateiSpeichernUnter.isEnabled() && !jMenuItemAendern.isEnabled()) {
                        dateiSpeichern.setEnabled(true);
                        dateiSpeichernUnter.setEnabled(true);
                        jMenuItemAendern.setEnabled(true);
                    } else if (weinDaten.isEmpty() && dateiSpeichern.isEnabled() && dateiSpeichernUnter.isEnabled() && jMenuItemAendern.isEnabled()) {
                        dateiSpeichern.setEnabled(false);
                        dateiSpeichernUnter.setEnabled(false);
                        jMenuItemAendern.setEnabled(false);
                    }
                } catch (FileNotFoundException fe) {
                    JOptionPane.showMessageDialog(this, "Keine Datei gefunden",
                            "Keine Datei", JOptionPane.ERROR_MESSAGE);
                    fehler = true;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Fehler beim Einlesen",
                            "Fehler", JOptionPane.ERROR_MESSAGE);
                    fehler = true;
                }

                if (!fehler) {
                    if (anzahlWerte == 0) {
                        JOptionPane.showMessageDialog(this, "Die Weindatenbank Datei, ist leer und konnte nicht eingelesen werden.",
                                "Datei Leer", JOptionPane.ERROR_MESSAGE);
                        
                    } else if (anzahlFehler == 0 || weinDaten.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Weindatenbank erfolgreich eingelesen.", "Erfolg.",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ihre Weindatenbank ist Fehlerhaft. Es wurden zu viele oder zu wenige Attribute gefunden.\n" +
                                "Bitte wählen Sie eine andere Datei aus.",
                                "Fehler beim lesen", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!weinDaten.isEmpty()) {
                        laufnummer = Integer.parseInt(weinDaten.getWeinDaten()
                                .get(weinDaten.getWeinDaten().size() - 1)
                                .getBestellnummer().substring(8, 11));
                        laufnummerDB = laufnummer;
                         resetFormular();
                    }
                    aktuelleDatei = gewaehlt;
                    System.out.println(weinDaten.toString());
                }
            }
        }
    }//GEN-LAST:event_dateiOeffnenActionPerformed

    private void jMenuItemAendernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAendernActionPerformed
        jBFirstItem.setVisible(true);
        jBFirstItem.setEnabled(false);
        jBPrevItem.setVisible(true);
        jBPrevItem.setEnabled(false);
        jBNextItem.setVisible(true);
        jBLastItem.setVisible(true);
        WeinAufnehmenFrame.setVisible(true); 
        WeinAufnehmenFrame.setTitle("Wein Ändern");
        isChangeForm = true;
  
        jFTextfieldBestellnummer.setEditable(false);
        initChangeValues(0);
    }//GEN-LAST:event_jMenuItemAendernActionPerformed

    private void jBPrevItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPrevItemActionPerformed
            if(WeinDatenIndex == 1) {
            jBPrevItem.setEnabled(false);
            jBFirstItem.setEnabled(false);
            WeinDatenIndex = 0;
            
        } else {
            jBLastItem.setEnabled(true);
            jBNextItem.setEnabled(true);
            WeinDatenIndex--; 
        }
            
        initChangeValues(WeinDatenIndex);
    }//GEN-LAST:event_jBPrevItemActionPerformed

    private void jBFirstItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFirstItemActionPerformed
        WeinDatenIndex = 0;
        jBLastItem.setEnabled(true);
        jBNextItem.setEnabled(true);
        jBFirstItem.setEnabled(false);
        jBPrevItem.setEnabled(false);
        
        initChangeValues(WeinDatenIndex);
    }//GEN-LAST:event_jBFirstItemActionPerformed

    private void jBNextItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBNextItemActionPerformed
        jBFirstItem.setEnabled(true);
        jBPrevItem.setEnabled(true);
        
        if(WeinDatenIndex == weinDaten.getWeinDaten().size()-2 ) {
            jBLastItem.setEnabled(false);
            jBNextItem.setEnabled(false);
            WeinDatenIndex = weinDaten.getWeinDaten().size()-1;
          
        } else {
            WeinDatenIndex++;
        }
        initChangeValues(WeinDatenIndex);
    }//GEN-LAST:event_jBNextItemActionPerformed

    private void jBLastItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLastItemActionPerformed
        WeinDatenIndex = weinDaten.getWeinDaten().size()-1;
        jBFirstItem.setEnabled(true);
        jBPrevItem.setEnabled(true);
        jBNextItem.setEnabled(false);
        jBLastItem.setEnabled(false);
        initChangeValues(WeinDatenIndex);
    }//GEN-LAST:event_jBLastItemActionPerformed

    /**
     * Setzt die Fokusreihenfolge fest.
     *
     * @return ArrayList<JComponent> - Fokusreihenfolge
     */
    private ArrayList<JComponent> getOrder() {
        ArrayList<JComponent> order = new ArrayList<>();
        order.add(jBFirstItem);
        order.add(jBPrevItem);
        order.add(jBNextItem);
        order.add(jBLastItem);
        order.add(jFTextfieldBestellnummer);
        order.add(jTextFieldName);
        order.add(jFTJahrgang);
        order.add(jSLagerdauer);
        order.add(jComboBoxFarbe);
        order.add(jComboBoxLand);
        order.add(jComboBoxRegion);
        order.add(jComboBoxAlkoholgehalt);
        order.add(jCFlaschengroesse);
        order.add(jTPreisEingabe);
        order.add(jBUmrechnenUp);
        order.add(jTPreisAusgabe);
        order.add(jBDown);
        order.add(jButtonSpeichern);
        order.add(jButtonAbbrechen);
        return order;
    }
    
    private void initChangeValues(int i) {
        laufnummer = Integer.parseInt(weinDaten.getWeinDaten().get(i).getBestellnummer().substring(8, 11)) - 1;
        resetFormular();
        lagerdauer = weinDaten.getWeinDaten().get(i).getDauer() ;
        jahrgang = weinDaten.getWeinDaten().get(i).getJahr();
        SetSpinnerValues();
        
        jFTextfieldBestellnummer.setText(weinDaten.getWeinDaten().get(i).getBestellnummer());
        jTextFieldName.setText(weinDaten.getWeinDaten().get(i).getName());
        jFTJahrgang.setValue(weinDaten.getWeinDaten().get(i).getJahr());
        jTPreisEingabe.setText(weinDaten.getWeinDaten().get(i).getPreis());
        jComboBoxFarbe.setSelectedItem(weinDaten.getWeinDaten().get(i).getFarbe());
        jSLagerdauer.setValue(weinDaten.getWeinDaten().get(i).getDauer() + weinDaten.getWeinDaten().get(i).getJahr());
        jComboBoxLand.setSelectedItem(weinDaten.getWeinDaten().get(i).getLand());
        jComboBoxRegion.setSelectedItem(weinDaten.getWeinDaten().get(i).getRegion());
        jComboBoxAlkoholgehalt.setSelectedItem(weinDaten.getWeinDaten().get(i).getAlkohol());
        jCFlaschengroesse.setSelectedItem(weinDaten.getWeinDaten().get(i).getFlasche());
       
    }
    
    private void CloseWithPrompt() {
        int close = JOptionPane.showInternalConfirmDialog(jDesktopPane1, CLOSE_MSG, CLOSE_TITEL, JOptionPane.YES_NO_OPTION);
        if (close == 0) {
            System.exit(0);
        }
    }
    
    private void FillAnbaugebiet(String[] laeneder) {
          jComboBoxLand.addItem("Bitte Land auswählen");
          
        for (int i = 0; i < laeneder.length; i++) {
            jComboBoxLand.addItem(laeneder[i]);
        }
    }
    
    private void resetColors() {
        jLBestellnummer.setForeground(Color.BLACK);
        jLName.setForeground(Color.BLACK);
        jLFarbe.setForeground(Color.BLACK);
        jLLand.setForeground(Color.BLACK);
        jLRegion.setForeground(Color.BLACK);
        jLAlkohol.setForeground(Color.BLACK);
        jLabelJahrgang.setForeground(Color.BLACK);
        jLFlaschenpreis.setForeground(Color.BLACK);
        jLPreisLiter.setForeground(Color.BLACK);
    }
    
    private void fuelleRebLaender() {
        boolean hinzugefuegt = true;
        hinzugefuegt = REB_LAENDER.add(LAND_1);
        hinzugefuegt = REB_LAENDER.add(LAND_2);
        hinzugefuegt = REB_LAENDER.add(LAND_3);
        hinzugefuegt = REB_LAENDER.add(LAND_4);
        hinzugefuegt = REB_LAENDER.add(LAND_5);
        hinzugefuegt = REB_LAENDER.add(LAND_6);
        hinzugefuegt = REB_LAENDER.add(LAND_7);
        hinzugefuegt = REB_LAENDER.add(LAND_8);
        hinzugefuegt = REB_LAENDER.add(LAND_9);
        hinzugefuegt = REB_LAENDER.add(LAND_10);
        hinzugefuegt = REB_LAENDER.add(LAND_11);
        hinzugefuegt = REB_LAENDER.add(LAND_12);
        hinzugefuegt = REB_LAENDER.add(LAND_13);
        hinzugefuegt = REB_LAENDER.add(LAND_14);
        hinzugefuegt = REB_LAENDER.add(LAND_15);
        hinzugefuegt = REB_LAENDER.add(LAND_16);
        hinzugefuegt = REB_LAENDER.add(LAND_17);
        hinzugefuegt = REB_LAENDER.add(LAND_18);
        hinzugefuegt = REB_LAENDER.add(LAND_19);
        hinzugefuegt = REB_LAENDER.add(LAND_20);
    }
    
    private void fillRegion(String land) {
      String[] rebsorten = LAND_UND_REGION.get(land);
      jComboBoxRegion.removeAllItems();
      jComboBoxRegion.addItem("Bitte auswählen");
      if (rebsorten != null) {
          for (int i = 0; i < rebsorten.length; i++) {
              jComboBoxRegion.addItem(rebsorten[i]);
          }
      }
  }
    private void fuelleLandUndRegionBeziehung(ArrayList<String[]> beziehung) {
      for (int i = 0; i < beziehung.size(); i++) {
          LAND_UND_REGION.put(LAENDER[i], beziehung.get(i));
      }
    }
    
    
    
    /**
     * Updaten der Formularfelder
     */
    private void updateFormular() {
        
    }
    
    /**
     * Zurücksetzen der Formularfelder
     */
    private void resetFormular() {
        jFTextfieldBestellnummer.setText("");
        jFTextfieldBestellnummer.setValue("");
        jTextFieldName.setText("");
        jFTJahrgang.setValue(null);
        jComboBoxFarbe.setSelectedIndex(0);
        jComboBoxLand.setSelectedIndex(0);
        jComboBoxRegion.setSelectedIndex(0);
        jComboBoxAlkoholgehalt.setSelectedIndex(8);
        isComboBoxChanged = false;
        jTPreisEingabe.setText("");
        jTPreisAusgabe.setText("");
        
        jSLagerdauer.setValue(0);
        diagramm1.setVisible(false);
        
        
        MaskFormatter mf = null;
        NumberFormat nf = new DecimalFormat("0000");
  
        // Maskformatter anlegen für das Bestellummer-Feld
        // Im Format 01-ABCD-0001
        try {
            
            mf = new MaskFormatter("##-UUU-" + nf.format(laufnummer + 1));
            mf.setPlaceholderCharacter('_');
            DefaultFormatterFactory dff = new DefaultFormatterFactory(mf);
            jFTextfieldBestellnummer.setFormatterFactory(dff);
            
        } catch (ParseException ex) {
            Logger.getLogger(WeinVerwaltung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean isFormularChanged2() {
        boolean isChanged = false;
        for (Component c : WeinDatenPanel.getComponents()){
            if (!isChanged && c instanceof JTextField) {
                isChanged = !((JTextField) c).getText().isEmpty();
            } else if (!isChanged && c instanceof JComboBox) {
                isChanged = isComboBoxChanged;
            }
        }   
        return isChanged;
    }
    
     private boolean isFormularChanged() {
         
        boolean isChanged = false;
        for (Component c : WeinDatenPanel.getComponents()){
            if (!isChanged && c instanceof JComboBox) {
                isChanged = isComboBoxChanged;
            }
        }   
        
        if(!isChanged) {
            isChanged = !jTextFieldName.getText().isEmpty();
        }
        
        if(!isChanged) {
            isChanged = !jFTJahrgang.getText().isEmpty();
        }
        
        if(!isChanged) {
            isChanged = !jTPreisEingabe.getText().isEmpty();
        }
        
         if(!isChanged) {
            isChanged = !jTPreisAusgabe.getText().isEmpty();
        }
       
        if(!isChanged && bnv.verify(jFTextfieldBestellnummer)){
            isChanged = true;
        }
        return isChanged;
    }
   
    private void closeFormular() {
        if (isFormularChanged()) {
            int abbrechen = JOptionPane.showInternalConfirmDialog(jDesktopPane1, ABORT_MSG,
                    ABORT_TITEL, JOptionPane.YES_NO_OPTION);
            if (abbrechen == 0) {
                WeinAufnehmenFrame.setVisible(false);
                isChangeForm = false;
                resetFormular();
                resetColors();
                jBFirstItem.setVisible(false);
                jBPrevItem.setVisible(false);
                jBNextItem.setVisible(false);
                jBLastItem.setVisible(false);
                jFTextfieldBestellnummer.setEditable(true);
            }
        } else {
            WeinAufnehmenFrame.setVisible(false);
            isChangeForm = false;
            resetFormular();
            resetColors();
            jBFirstItem.setVisible(false);
            jBPrevItem.setVisible(false);
            jBNextItem.setVisible(false);
            jBLastItem.setVisible(false);
            jFTextfieldBestellnummer.setEditable(true);
        }
    }
    
    /**
     * Überprüft die Eingabe des Jahrganges.
     */
    private void EingabeCheckJahrgang() {
        NumberFormat nf;
        double jahrgangEingabe = 0;
        isValid = true;
        
        try {
            nf = NumberFormat.getInstance();
            jahrgangEingabe = (nf.parse(jFTJahrgang.getText()).doubleValue());
            
        } catch (ParseException e) {
            
            JOptionPane.showMessageDialog(this, MSG_ERR_FORMAT, "Fehler", JOptionPane.WARNING_MESSAGE);
            jFTJahrgang.requestFocusInWindow();
            jFTJahrgang.selectAll();
            isValid = false;
        }
        
        try {
            if (jahrgangEingabe < MIN_JAHRGANG) {
                JOptionPane.showMessageDialog(this, MSG_ERR_BEREICH_1, "Fehler", JOptionPane.WARNING_MESSAGE);
                jFTJahrgang.requestFocusInWindow();
                jFTJahrgang.selectAll();
                isValid = false;
            }
            
            if (jahrgangEingabe > MAX_JAHRGANG) {
                JOptionPane.showMessageDialog(this, MSG_ERR_BEREICH_2, "Fehler", JOptionPane.WARNING_MESSAGE);
                jFTJahrgang.requestFocusInWindow();
                jFTJahrgang.selectAll();
                isValid = false;
            }
            
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, MSG_ERR_FORMAT, "Fehler", JOptionPane.WARNING_MESSAGE);
            isValid = false;
        }
        
        jahrgang = jahrgangEingabe;
    }
    
    private void SetSpinnerValues() {
        int value = (int)jahrgang + 1;
        int min = value;
        int max = value + MAX_LAGERDAUER;
        int step = 1;

        SpinnerNumberModel lagerModel = new SpinnerNumberModel(value, min, max, step);
        JSpinner.NumberEditor ed = new JSpinner.NumberEditor(jSLagerdauer);
        ed.getFormat().setGroupingUsed(false);


        //jSLagerdauer.setEditor(ed);
        jSLagerdauer.setModel(lagerModel);
        jSLagerdauer.setEditor(new JSpinner.NumberEditor(jSLagerdauer,"####"));

        jSLagerdauer.requestFocusInWindow();

        lagerdauer = value - (int)jahrgang;
    }
    
    private void SetDiagrammValues() {
        diagramm1.setVisible(true);
        
        lg.setJahr((int)jahrgang);
        lg.setDauer((int)lagerdauer);

        diagramm1.setJahrgangUndDauer(lg);
        
        this.repaint();
    }
/***
     * Methode berechnet den Literpreis auf Basis des Flaschenpreises
     */
    private void berechneLiterpreis() {
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        
        String sPreis = jTPreisEingabe.getText();
        
        try {           
            double flaschengroesse = nf.parse(jCFlaschengroesse.getItemAt(jCFlaschengroesse.getSelectedIndex()).toString()).doubleValue();          
            double preis = nf.parse(sPreis).doubleValue();
            double literPreis = preis / flaschengroesse;
            
            jTPreisAusgabe.setText(nf.format(literPreis));
            istLiterBerechnung = true;
            istFlaschenBerechnung = false;
            //jTPreisEingabe.requestFocusInWindow();
            
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Bitte geben Sie zuerst eine Zahl ein", "Eingabefehler", JOptionPane.WARNING_MESSAGE);
            jTPreisEingabe.setText("");
            jTPreisEingabe.requestFocusInWindow();
        }
        istButtonLiter = false;
    } 
    
    /**
     * Methode berechnet den Flaschenpreis auf Basis des Literpreises
     */
     private void berechneFlaschenpreis() {
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        
        String sPreis = jTPreisAusgabe.getText();
        
        try {
            double flaschengroesse = nf.parse(jCFlaschengroesse.getItemAt(jCFlaschengroesse.getSelectedIndex()).toString()).doubleValue();          
            double preis = nf.parse(sPreis).doubleValue();
            double flaschenPreis = preis * flaschengroesse;
            
            jTPreisEingabe.setText(nf.format(flaschenPreis));
            istFlaschenBerechnung = true;
            istLiterBerechnung = false;
            //jTPreisAusgabe.requestFocusInWindow();
            
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Bitte geben Sie zuerst eine Zahl ein", "Eingabefehler", JOptionPane.WARNING_MESSAGE);
            jTPreisAusgabe.setText("");
            jTPreisAusgabe.requestFocusInWindow();
        }
        istButtonFlasche = false;
    } 

      private void speichern(File f) {
        File fileEingabe = f;
        if (!fileEingabe.getName().toLowerCase().endsWith(".wd")) {
            fileEingabe = new File(f.getPath() + ".wd");
        }
        try {
            BufferedWriter ausgabe = new BufferedWriter(new FileWriter(fileEingabe));
            if (weinDaten != null) {
                for (int i = 0; i < weinDaten.getWeinDaten().size(); i++) {
                    System.out.println("Wein;" + weinDaten.getWeinDaten().get(i).toString());
                    ausgabe.write("Wein;" + weinDaten.getWeinDaten().get(i).toString());
                    ausgabe.newLine();
                }
            }
           
            ausgabe.close();
            JOptionPane.showMessageDialog(this, SPEICHERN_ACK_MSG, SPEICHERN_ACK_TITEL, JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, SPEICHERN_ERR_MSG, SPEICHERN_ERR_TITEL, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WeinVerwaltung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WeinVerwaltung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WeinVerwaltung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WeinVerwaltung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WeinVerwaltung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DiagrammPanel;
    private javax.swing.JInternalFrame WeinAufnehmenFrame;
    private javax.swing.JPanel WeinDatenPanel;
    private javax.swing.JMenuItem dateiOeffnen;
    private javax.swing.JMenuItem dateiSpeichern;
    private javax.swing.JMenuItem dateiSpeichernUnter;
    private gui.ws.prak.auf7.Diagramm diagramm1;
    private javax.swing.JButton jBDown;
    private javax.swing.JButton jBFirstItem;
    private javax.swing.JButton jBLastItem;
    private javax.swing.JButton jBNextItem;
    private javax.swing.JButton jBPrevItem;
    private javax.swing.JButton jBUmrechnenUp;
    private javax.swing.JButton jButtonAbbrechen;
    private javax.swing.JButton jButtonSpeichern;
    private javax.swing.JComboBox jCFlaschengroesse;
    private javax.swing.JComboBox jComboBoxAlkoholgehalt;
    private javax.swing.JComboBox jComboBoxFarbe;
    private javax.swing.JComboBox jComboBoxLand;
    private javax.swing.JComboBox jComboBoxRegion;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFormattedTextField jFTJahrgang;
    private javax.swing.JFormattedTextField jFTextfieldBestellnummer;
    private javax.swing.JLabel jLAlkohol;
    private javax.swing.JLabel jLBestellnummer;
    private javax.swing.JLabel jLCurrency;
    private javax.swing.JLabel jLCurrency1;
    private javax.swing.JLabel jLFarbe;
    private javax.swing.JLabel jLFenstergroesse;
    private javax.swing.JLabel jLFlaschenpreis;
    private javax.swing.JLabel jLLand;
    private javax.swing.JLabel jLName;
    private javax.swing.JLabel jLPreisLiter;
    private javax.swing.JLabel jLRegion;
    private javax.swing.JLabel jLabelJahrgang;
    private javax.swing.JLabel jLabelLagerdauer;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuBearbeiten;
    private javax.swing.JMenu jMenuDatei;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemAendern;
    private javax.swing.JMenuItem jMenuItemAufnehmen;
    private javax.swing.JMenuItem jMenuItemBeenden;
    private javax.swing.JMenu jMenuWein;
    private javax.swing.JSpinner jSLagerdauer;
    private javax.swing.JTextField jTPreisAusgabe;
    private javax.swing.JTextField jTPreisEingabe;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
