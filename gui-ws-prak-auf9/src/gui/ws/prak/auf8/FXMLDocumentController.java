/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.ws.prak.auf8;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Jakub Juszczak
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ComboBox<String> bottleSize;
    
    // Liste mit Flaschengrößen
    ObservableList<String> bottleList = FXCollections.observableArrayList(
        "0,187 l", 
        "0,25 l", 
        "0,375 l", 
        "0,5 l", 
        "0,62 l", 
        "0,7 l", 
        "0,75 l", 
        "0,8 l", 
        "1 l", 
        "1,5 l" 
    );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bottleSize.setItems(bottleList);
    }    
    
}
