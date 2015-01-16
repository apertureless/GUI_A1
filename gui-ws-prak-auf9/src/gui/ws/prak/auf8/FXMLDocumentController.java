/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf8;

import java.awt.Toolkit;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Jakub Juszczak
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ComboBox<String> bottleSize;
    
    @FXML 
    private Button buttonUp;
    
    @FXML 
    private Button buttonDown;
    
    @FXML
    private TextField txfBottlePrice;
    
    @FXML
    private TextField txfPriceLiter;
    
  
    private boolean isBottleCalcLast = false;
    private boolean isLiterCalcLast = false;
    private static final String REGEX = "(\\d*,?\\d*)|(\\d{0,3}(\\.\\d{3})*,?\\d*)";
    
    
    
    @FXML
    private void buttonDownAction(ActionEvent event) {
        calculateLiterPrice();   
    }
    
    @FXML
    private void buttonUpAction(ActionEvent event) {
        calculateBottlePrice();
    }
    
    @FXML
    private void comboboxChangeAction(ActionEvent event) {
        if (isBottleCalcLast) {
            calculateLiterPrice();
        }
        
        if (isLiterCalcLast) {
            calculateBottlePrice();
        }
    }
    
    // Liste mit Flaschengrößen
    ObservableList<String> bottleList = FXCollections.observableArrayList(
        "0,187", 
        "0,25", 
        "0,375", 
        "0,5", 
        "0,62", 
        "0,7", 
        "0,75", 
        "0,8", 
        "1", 
        "1,5" 
    );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image imgDown = new Image(getClass().getResourceAsStream("circle-down.png"));
        Image imgUp = new Image(getClass().getResourceAsStream("circle-up.png"));

        bottleSize.setItems(bottleList);
        bottleSize.setValue("0,75");
        buttonUp.setGraphic(new ImageView(imgUp));
        buttonDown.setGraphic(new ImageView(imgDown));
    }    
    
    private void calculateLiterPrice() {

        NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        
        if(!txfBottlePrice.getText().matches(REGEX)) {
           Dialogs.create()
            .title("Eingabefehler")
            .masthead("Falsches Format")
            .message("Bitte geben Sie eine Zahl im Format x,xx ein.")
            .showError();
           txfBottlePrice.selectAll();
           txfBottlePrice.requestFocus();
        } else {
            try {
                double dSize = nf.parse(bottleSize.getValue()).doubleValue();
                double dPrice = nf.parse(txfBottlePrice.getText()).doubleValue();
                double newPrice = dPrice / dSize;
                txfPriceLiter.setText(nf.format(newPrice));
                isBottleCalcLast = true;
                isLiterCalcLast = false;
            
            } catch (java.text.ParseException ex) {
              Dialogs.create()
                .title("Eingabefehler")
                .masthead("Feld leer")
                .message("Bitte geben Sie eine Zahl im Format x,xx ein.")
                .showError();
               txfBottlePrice.selectAll();
               txfBottlePrice.requestFocus();
            }
        }
   
    }
    
    private void calculateBottlePrice() {
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        
        if(!txfPriceLiter.getText().matches(REGEX)) {
            Dialogs.create()
             .title("Eingabefehler")
             .masthead("Falsches Format")
             .message("Bitte geben Sie eine Zahl im Format x,xx ein.")
             .showError();
            txfPriceLiter.selectAll();
            txfPriceLiter.requestFocus();
        } else {
            try {
                double dSize = nf.parse(bottleSize.getValue()).doubleValue();
                double dPrice = nf.parse(txfPriceLiter.getText()).doubleValue();
                double newPrice = dPrice * dSize;
                txfBottlePrice.setText(nf.format(newPrice));
                isBottleCalcLast = false;
                isLiterCalcLast = true;

            } catch (java.text.ParseException ex) {
               Dialogs.create()
                .title("Eingabefehler")
                .masthead("Feld leer")
                .message("Bitte geben Sie eine Zahl im Format x,xx ein.")
                .showError();
               txfPriceLiter.selectAll();
               txfPriceLiter.requestFocus();
            }
        }
    }
    
}
