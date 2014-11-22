/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf4;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author NetGhost03
 */
public class Komponenten extends javax.swing.JPanel {
    
    private final int MIN_LAGERDAUER = 1;
    private final int MAX_LAGERDAUER = 25;
    
    private final int AKTUELLES_JAHR = Calendar.getInstance().get(Calendar.YEAR);
    
    private final int MIN_JAHRGANG = AKTUELLES_JAHR - MAX_LAGERDAUER;
    private final int MAX_JAHRGANG = AKTUELLES_JAHR;

    private static final String MSG_ERR_FORMAT = "Fehler\n Falsches Format!";
    private static final String MSG_ERR_BEREICH = "Fehler\n\t Gültigkeitsbereich"
                                                    + " nicht eingehalten.";
    private static final String MSG_ERR_KURZ = "Lagerdauer zu kurz.";
    
    private static final String MSG_INFO_LAGER = "Lagerdauer (%d - %d): ";
    private static final String MSG_INFO_JAHRGANG = "Jahrgang (%d - %d): ";
    
    /**
     * Creates new form Komponenten
     */
    public Komponenten() {
        initComponents();
        
        NumberFormat nf = NumberFormat.getInstance();
        
        NumberFormatter nform = new NumberFormatter(nf);
        DefaultFormatterFactory dff = new DefaultFormatterFactory(nform);
        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(0);
        nf.setGroupingUsed(false);
        nform.setAllowsInvalid(false);
        nform.setOverwriteMode(true);
       
        nform.setValueClass(Integer.class);
        nform.setMinimum(0);
        nform.setMaximum(9999);

        jFTJahrgang.setFormatterFactory(dff);
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFTJahrgang = new javax.swing.JFormattedTextField();
        jLJahrgang = new javax.swing.JLabel();
        jLLagerdauer = new javax.swing.JLabel();
        jSLagerdauer = new javax.swing.JSpinner();

        jFTJahrgang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFTJahrgangActionPerformed(evt);
            }
        });

        jLJahrgang.setLabelFor(jFTJahrgang);
        jLJahrgang.setText("Jahrgang");

        jLLagerdauer.setText("Lagerdauer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLJahrgang)
                    .addComponent(jLLagerdauer))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSLagerdauer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTJahrgang, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTJahrgang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLJahrgang))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLLagerdauer)
                    .addComponent(jSLagerdauer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Methode die Aufgerufen wird, wenn Werte im Jahrgang Feld geändert werden.
     * 
     * @param evt 
     */
    private void jFTJahrgangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFTJahrgangActionPerformed
        
        NumberFormat nf;
        double jahrgangEingabe = 0;
        
        try {
            nf = NumberFormat.getInstance();

            
            jahrgangEingabe = (nf.parse(jFTJahrgang.getText()).doubleValue());
            
        } catch (ParseException e) {
            
            JOptionPane.showMessageDialog(this, "Fehler", "Fehler", JOptionPane.WARNING_MESSAGE);
            jFTJahrgang.requestFocusInWindow();
            jFTJahrgang.selectAll();
        }
        
        try {
             if (jahrgangEingabe > MAX_JAHRGANG || jahrgangEingabe < MIN_JAHRGANG) {
                JOptionPane.showMessageDialog(this, MSG_ERR_BEREICH, "Fehler", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, MSG_ERR_FORMAT, "Fehler", JOptionPane.WARNING_MESSAGE);
        }
       
    }//GEN-LAST:event_jFTJahrgangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField jFTJahrgang;
    private javax.swing.JLabel jLJahrgang;
    private javax.swing.JLabel jLLagerdauer;
    private javax.swing.JSpinner jSLagerdauer;
    // End of variables declaration//GEN-END:variables
}
