/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.customermgmt.view;

import com.pawnshop.customermgmt.contoller.CustomerDAO;
import com.pawnshop.customermgmt.contoller.ICustomerDAO;
import com.pawnshop.customermgmt.model.Customer;
import com.pawnshop.validators.Validator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.apache.commons.validator.routines.EmailValidator;

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

    @FXML
    private Text nicError;

    @FXML
    private Text nameError;

    @FXML
    private Text emailError;

    @FXML
    private Text contactNoError;

    @FXML
    private Text addressError;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nic.setOnKeyReleased((event) -> {
            nicError.setText("");
        });
        contactNo.setOnKeyReleased((event) -> {
            contactNoError.setText("");
        });
        email.setOnKeyReleased((event) -> {
            EmailValidator validator = EmailValidator.getInstance();
            if (!validator.isValid(email.getText())) {
                emailError.setText("Please enter a valid email address.");
            } else {
                emailError.setText("");
            }
        });
        name.setOnKeyReleased((event) -> {
            nameError.setText("");
        });
        address.setOnKeyReleased((event) -> {
            addressError.setText("");
        });
    }

    @FXML
    void actionSave(ActionEvent event) {
        ICustomerDAO custDAO = new CustomerDAO();
        if (nic.getText().isEmpty()) {
            nicError.setText("NIC cannot be empty");
        }
        if (name.getText().isEmpty()) {
            nameError.setText("Name cannot be empty");
        }
        if (address.getText().isEmpty()) {
            addressError.setText("Address cannot be empty");
        }
        if (contactNo.getText().isEmpty()) {
            contactNoError.setText("Contact number cannot be empty.");
        }
        if (email.getText().isEmpty()) {
            emailError.setText("Email cannot be empty");
        }

        try {
            Customer customer = new Customer(nic.getText(), name.getText(), address.getText(), Integer.parseInt(contactNo.getText()), email.getText(), false);
            if (Validator.validate(customer)) {
                custDAO.saveCustomer(customer);
                ViewCustomerFXMLController.stage.close();
            } else {
                System.out.println("failed" + Validator.validate(customer));
            }
        } catch (NumberFormatException ex) {

        }

    }
}
