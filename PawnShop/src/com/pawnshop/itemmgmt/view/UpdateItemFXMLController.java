/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.itemmgmt.view;

import com.pawnshop.itemmgmt.controller.IItemDAO;
import com.pawnshop.itemmgmt.controller.ItemDAO;
import com.pawnshop.itemmgmt.model.Item;
import com.pawnshop.validators.Validator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 */
public class UpdateItemFXMLController implements Initializable {

    Item prevItem;

    @FXML
    private TextField itemType;

    @FXML
    private Text errItemType;

    @FXML
    private TextField weight;

    @FXML
    private Text errWeight;

    @FXML
    private ComboBox<Integer> karat;

    @FXML
    private Text errKarat;

    @FXML
    private TextField value;

    @FXML
    private ComboBox<String> status;

    @FXML
    private Text errStatus;

    @FXML
    private TextArea description;

    @FXML
    private Text errDescription;

    @FXML
    private TextField nic;

    @FXML
    private Text errCustomerNic;

    @FXML
    private Button btnUpdate;

    @FXML
    void actionUpdate(ActionEvent event) {

    }

    @FXML
    void actionAssess(ActionEvent event) {
        if (weight.getText() == null || weight.getText().isEmpty()) {
            errWeight.setText("Enter a weight.");
        } else {
            value.setText(String.valueOf(value()));
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prevItem = ViewItemsFXMLController.item;
        init(); // set previous values to fields

        status.getItems().add("Pawned");
        status.getItems().add("Recovered");
        status.getItems().add("Acquired");
        status.getItems().add("To be sold");

        karat.getItems().add(18);
        karat.getItems().add(22);
        karat.getItems().add(24);

        itemType.setOnKeyReleased((event) -> {
            errItemType.setText("");
        });

        weight.setOnKeyReleased((event) -> {
            errWeight.setText("");
        });

        status.setOnAction((event) -> {
            errStatus.setText("");
        });

        nic.setOnKeyReleased((event) -> {
            errCustomerNic.setText("");
        });

        description.setOnKeyReleased((event) -> {
            errDescription.setText("");
        });

        btnUpdate.setOnAction((event) -> {
            updateItem();
        });
    }

    private void updateItem() {
        Item item = new Item();
        IItemDAO itemDAO = new ItemDAO();

        if (itemType.getText().isEmpty()) {
            errItemType.setText("Item type cannot be empty.");
        }
        if (weight.getText().isEmpty()) {
            errWeight.setText("Weight cannot be empty.");
        }

        if (status.getValue().isEmpty()) {
            errStatus.setText("Status cannot be empty.");
        }
        if (description.getText().isEmpty()) {
            errDescription.setText("Description cannot be empty.");
        }

        if (nic.getText().isEmpty()) {
            errCustomerNic.setText("Customer NIC cannot be empty.");
        }

        try {
            item.setItemId(prevItem.getItemId());
            item.setItemType(itemType.getText());
            item.setWeight(Double.parseDouble(weight.getText()));
            item.setKarat(karat.getValue());
            item.setValue(Float.parseFloat(value.getText()));
            item.setStatus((String) status.getValue());
            item.setDescription(description.getText());
            item.setNic(nic.getText());

            if (Validator.validate(item)) {
                itemDAO.updateItem(item);
                ViewItemsFXMLController.stage.close();
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex);
        }
    }

    private void init() {
        itemType.setText(prevItem.getItemType());
        weight.setText(String.valueOf(prevItem.getWeight()));
        value.setText(String.valueOf(prevItem.getValue()));
        status.setValue(prevItem.getStatus());
        karat.setValue(prevItem.getKarat());
        description.setText(prevItem.getDescription());
        nic.setText(prevItem.getNic());
    }

    private float value() {
        float price = 0;
        if (karat.getValue() == 21 || karat.getValue() == 22) {
            price = 65000 / 8 * Float.parseFloat(weight.getText());
        } else if (karat.getValue() == 18) {
            price = 5000 * Float.parseFloat(weight.getText());
        }
        return price;
    }
}
