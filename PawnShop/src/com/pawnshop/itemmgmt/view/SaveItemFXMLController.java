/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.itemmgmt.view;

import com.pawnshop.itemmgmt.controller.IItemDAO;
import com.pawnshop.itemmgmt.controller.ItemDAO;
import com.pawnshop.itemmgmt.model.Item;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 */
public class SaveItemFXMLController implements Initializable {

    @FXML
    private TextField itemType;

    @FXML
    private TextField weight;

    @FXML
    private TextField value;

    @FXML
    private ComboBox<String> status;

    @FXML
    private TextArea description;

    @FXML
    private TextField nic;

    @FXML
    private Button btnSave;

    @FXML
    void actionSave(ActionEvent event) {
        saveItem();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        status.getItems().add("Pawned");
        status.getItems().add("Acquired");
    }
    
    private void saveItem(){
        Item item = new Item();
        IItemDAO itemDAO = new ItemDAO();
        
        item.setItemType(itemType.getText());
        item.setWeight(Double.parseDouble(weight.getText()));
        item.setValue(Float.parseFloat(value.getText()));
        item.setStatus((String) status.getValue());
        item.setDescription(description.getText());
        item.setNic(nic.getText());
        
        itemDAO.saveItem(item);
        ViewItemsFXMLController.stage.close();
    }

}
