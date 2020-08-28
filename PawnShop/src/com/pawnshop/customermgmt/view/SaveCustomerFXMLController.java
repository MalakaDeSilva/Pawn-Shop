/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.customermgmt.view;

import com.pawnshop.customermgmt.contoller.CustomerDAO;
import com.pawnshop.customermgmt.contoller.ICustomerDAO;
import com.pawnshop.customermgmt.model.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 */
public class SaveCustomerFXMLController implements Initializable {

    @FXML
    private TextField contactNo;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField nic;

    @FXML
    private TextArea address;

    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void actionSave(ActionEvent event) {
        ICustomerDAO custDAO = new CustomerDAO();
        Customer customer = new Customer(nic.getText(), name.getText(), address.getText(), Integer.parseInt(contactNo.getText()), email.getText());
        custDAO.saveCustomer(customer);
        ViewCustomerFXMLController.stage.close();
    }
}
