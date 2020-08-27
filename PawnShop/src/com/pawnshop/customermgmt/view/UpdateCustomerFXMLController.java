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
public class UpdateCustomerFXMLController implements Initializable {

    Customer customer;

    @FXML
    private TextField nic;

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField contactNo;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextArea address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customer = ViewCustomerFXMLController.customer;
        
        nic.setText(customer.getNic());
        name.setText(customer.getName());
        contactNo.setText(String.valueOf(customer.getContactNo()));
        address.setText(customer.getAddress());
        email.setText(customer.getEmail());
    }

    @FXML
    void actionUpdate(ActionEvent event) {
        ICustomerDAO customerDAO = new CustomerDAO();
        Customer updatedCustomer = new Customer(nic.getText(), name.getText(), address.getText(), Integer.parseInt(contactNo.getText()), email.getText());
        customerDAO.updateCustomer(updatedCustomer);
        ViewCustomerFXMLController.stage.close();
    }

}
