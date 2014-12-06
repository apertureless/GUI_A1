/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf5;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author NetGhost03
 */
public class WeinVerwaltung extends javax.swing.JFrame {
    
    private static final String CLOSE_TITEL = "Programm Beenden?";
    private static final String CLOSE_MSG = "Sind Sie sicher, dass Sie das Programm beenden wollen?\nAlle Daten gehen verloren.";
    private static final String ABORT_MSG = "Sind Sie sicher, dass Sie die Weinaufnahme beenden wollen?\nNicht gespeicherte Daten gehen verloren.";
    private static final String ABORT_TITEL = "Weinaufnahme Beenden";
    private int laufnummer = 0;

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

    
    private boolean comboBoxChanged = false;

    private final ArrayList<String[]> REB_LAENDER
            = new ArrayList<>();

    private final HashMap<String, String[]> LAND_UND_REGION
            = new HashMap<>();

    /**
     * Creates new form WeinVerwaltung
     */
    public WeinVerwaltung() {
        initComponents();
        
        fuelleRebLaender();
        fuelleLandUndRegionBeziehung(REB_LAENDER);
        
        FillAnbaugebiet(LAENDER);
        
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
        jLabel1 = new javax.swing.JLabel();
        jComboBoxFarbe = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxLand = new javax.swing.JComboBox();
        jComboBoxRegion = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxAlkoholgehalt = new javax.swing.JComboBox();
        jButtonSpeichern = new javax.swing.JButton();
        jButtonAbbrechen = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuDatei = new javax.swing.JMenu();
        jMenuItemBeenden = new javax.swing.JMenuItem();
        jMenuBearbeiten = new javax.swing.JMenu();
        jMenuWein = new javax.swing.JMenu();
        jMenuItemAufnehmen = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
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
        WeinAufnehmenFrame.setPreferredSize(new java.awt.Dimension(200, 330));
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

        jFTextfieldBestellnummer.setToolTipText("");

        jLBestellnummer.setLabelFor(jFTextfieldBestellnummer);
        jLBestellnummer.setText("Bestellnummer");

        jLabel1.setLabelFor(jTextFieldName);
        jLabel1.setText("Name");

        jComboBoxFarbe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rot", "Weiß", "Rose" }));

        jLabel2.setLabelFor(jComboBoxFarbe);
        jLabel2.setText("Farbe");

        jLabel3.setText("Anbaugebiet");

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

        jLabel4.setText("Region");

        jLabel5.setText("Alkoholgehalt");

        jComboBoxAlkoholgehalt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0 Vol%", "7,5 Vol%", "8 Vol%", "8,5 Vol%", "9 Vol%", "9,5 Vol%", "10 Vol%", "10,5 Vol%", "11 Vol%", "11,5 Vol%", "12 Vol%", "12,5 Vol%", "13 Vol%" }));
        jComboBoxAlkoholgehalt.setSelectedIndex(8);

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

        javax.swing.GroupLayout WeinDatenPanelLayout = new javax.swing.GroupLayout(WeinDatenPanel);
        WeinDatenPanel.setLayout(WeinDatenPanelLayout);
        WeinDatenPanelLayout.setHorizontalGroup(
            WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeinDatenPanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(WeinDatenPanelLayout.createSequentialGroup()
                        .addComponent(jButtonSpeichern)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAbbrechen))
                    .addGroup(WeinDatenPanelLayout.createSequentialGroup()
                        .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLBestellnummer)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(40, 40, 40)
                        .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxRegion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxFarbe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldName)
                            .addComponent(jFTextfieldBestellnummer)
                            .addComponent(jComboBoxLand, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxAlkoholgehalt, 0, 100, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        WeinDatenPanelLayout.setVerticalGroup(
            WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeinDatenPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTextfieldBestellnummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLBestellnummer))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFarbe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxAlkoholgehalt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(WeinDatenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSpeichern)
                    .addComponent(jButtonAbbrechen))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Weindaten", WeinDatenPanel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab2", jPanel2);

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
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jDesktopPane1.add(WeinAufnehmenFrame);
        WeinAufnehmenFrame.setBounds(130, 60, 540, 440);

        jMenuDatei.setMnemonic('D');
        jMenuDatei.setText("Datei");

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

        jMenuWein.setText("Wein");

        jMenuItemAufnehmen.setText("Aufnehmen");
        jMenuItemAufnehmen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAufnehmenActionPerformed(evt);
            }
        });
        jMenuWein.add(jMenuItemAufnehmen);

        jMenuItem1.setText("Ändern");
        jMenuWein.add(jMenuItem1);

        jMenuItem2.setText("Löschen");
        jMenuWein.add(jMenuItem2);

        jMenuBar1.add(jMenuWein);

        jMenuHelp.setText("?");

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
        
      WeinAufnehmenFrame.setVisible(true);
      
    }//GEN-LAST:event_jMenuItemAufnehmenActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItemBeendenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBeendenActionPerformed
        CloseWithPrompt();
        resetFormular();
    }//GEN-LAST:event_jMenuItemBeendenActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        CloseWithPrompt();
        WeinAufnehmenFrame.setVisible(false);
        resetFormular();
    }//GEN-LAST:event_formWindowClosing

    private void WeinAufnehmenFrameInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_WeinAufnehmenFrameInternalFrameClosing
        CleanAndCloseFrame();
    }//GEN-LAST:event_WeinAufnehmenFrameInternalFrameClosing

    private void jComboBoxRegionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRegionActionPerformed
      
    }//GEN-LAST:event_jComboBoxRegionActionPerformed

    private void jComboBoxLandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLandActionPerformed
        if(jComboBoxLand.getSelectedIndex() != 0) {
            jComboBoxRegion.setEnabled(true);
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
        CloseWithPrompt();
        resetFormular();
    }//GEN-LAST:event_jButtonAbbrechenActionPerformed

    private void jButtonSpeichernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSpeichernActionPerformed
        resetFormular();
    }//GEN-LAST:event_jButtonSpeichernActionPerformed

    private void CloseWithPrompt() {
        int close = JOptionPane.showConfirmDialog(null, CLOSE_MSG, CLOSE_TITEL, JOptionPane.YES_NO_OPTION);
        if (close == 0) {
            System.exit(0);
        }
    }
    
    private void CleanAndCloseFrame() {
        int confirm = JOptionPane.showConfirmDialog(null, ABORT_MSG, ABORT_TITEL, JOptionPane.YES_NO_OPTION);
        if (confirm == 0) {
            WeinAufnehmenFrame.setVisible(false);
        }
        
    }
    
    private void FillAnbaugebiet(String[] laeneder) {
          jComboBoxLand.addItem("Bitte Land auswählen");
          
        for (int i = 0; i < laeneder.length; i++) {
            jComboBoxLand.addItem(laeneder[i]);
        }
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
     * Zurücksetzen der Formularfelder
     */
    private void resetFormular() {
        jFTextfieldBestellnummer.setText("");
        jTextFieldName.setText("");
        jComboBoxFarbe.setSelectedIndex(0);
        jComboBoxLand.setSelectedIndex(0);
        jComboBoxRegion.setSelectedIndex(0);
        jComboBoxAlkoholgehalt.setSelectedIndex(8);
   
    }
    
    private void closeFormular() {
        
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WeinVerwaltung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame WeinAufnehmenFrame;
    private javax.swing.JPanel WeinDatenPanel;
    private javax.swing.JButton jButtonAbbrechen;
    private javax.swing.JButton jButtonSpeichern;
    private javax.swing.JComboBox jComboBoxAlkoholgehalt;
    private javax.swing.JComboBox jComboBoxFarbe;
    private javax.swing.JComboBox jComboBoxLand;
    private javax.swing.JComboBox jComboBoxRegion;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFormattedTextField jFTextfieldBestellnummer;
    private javax.swing.JLabel jLBestellnummer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuBearbeiten;
    private javax.swing.JMenu jMenuDatei;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemAufnehmen;
    private javax.swing.JMenuItem jMenuItemBeenden;
    private javax.swing.JMenu jMenuWein;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
}
