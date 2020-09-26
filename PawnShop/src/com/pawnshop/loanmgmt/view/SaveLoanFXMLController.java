/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.loanmgmt.view;

import com.pawnshop.LoginFXMLController;
import com.pawnshop.itemmgmt.model.Item;
import com.pawnshop.itemmgmt.view.ViewItemsFXMLController;
import com.pawnshop.loanmgmt.controller.ILoanDAO;
import com.pawnshop.loanmgmt.controller.LoanDAO;
import com.pawnshop.loanmgmt.model.Loan;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 */
public class SaveLoanFXMLController implements Initializable {

    Item _item = ViewItemsFXMLController.item;
    ILoanDAO loanDAO = new LoanDAO();
    Loan loan;

    @FXML
    private TextField itemId;

    @FXML
    private TextField value;

    @FXML
    private TextField remainder;

    @FXML
    private DatePicker dueDate;

    @FXML
    private DatePicker billDate;

    @FXML
    private TextField empNic;

    @FXML
    private TextField customerNic;

    @FXML
    private TextField rate;

    @FXML
    private Text errRate;

    @FXML
    private Text errItemId;

    @FXML
    private Text errValue;

    @FXML
    private Text errRemainder;

    @FXML
    private Text errDueDate;

    @FXML
    private Text errCustomerNic;
    @FXML
    private Button btnSave;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billDate.setValue(LocalDate.now());
        empNic.setText(LoginFXMLController.employee);
        value.setText(String.valueOf(_item.getValue()));
        
        if (_item != null) {
            itemId.setText(String.valueOf(_item.getItemId()));
            customerNic.setText(_item.getNic());
        }

        itemId.setOnKeyReleased((event) -> {
            errItemId.setText("");
        });

        value.setOnKeyReleased((event) -> {
            errValue.setText("");
            errRemainder.setText("");
            remainder.setText(value.getText());
        });

        rate.setOnKeyPressed((event) ->{
            errRate.setText("");
        });
        
        remainder.setOnKeyReleased((event) -> {
            errRemainder.setText("");
        });

        dueDate.setOnAction((event) -> {
            errDueDate.setText("");
        });

        customerNic.setOnKeyReleased((event) -> {
            errCustomerNic.setText("");
        });
    }

    @FXML
    void actionSave(ActionEvent event) {
        loan = new Loan();

        if (value.getText().isEmpty()) {
            errValue.setText("Value cannot be empty.");
        }
        if(rate.getText().isEmpty()){
            errRate.setText("Rate cannot be empty.");
        }
        if (remainder.getText().isEmpty()) {
            errRemainder.setText("Remainder cannot be empty.");
        }
        if (dueDate.getValue() == null) {
            errDueDate.setText("Due date cannot be empty.");
        }
        if (customerNic.getText().isEmpty()) {
            errCustomerNic.setText("Customer NIC cannot be empty.");
        }
        if (itemId.getText().isEmpty()) {
            errItemId.setText("Item Id cannot be empty.");
        }
        
        if(rate.getText().isEmpty()){
            errRate.setText("Rate cannot be empty.");
        }
        
        try {
            
            loan.setValue(Double.parseDouble(value.getText()));
            loan.setRate(Float.parseFloat(rate.getText()));
            loan.setRemainder(remaining());
            loan.setDuedate(Date.valueOf(dueDate.getValue()));
            loan.setBilldate(Date.valueOf(LocalDate.now()));
            loan.setEmpNic(empNic.getText());
            loan.setCustomerNic(customerNic.getText());
            loan.setItemId(Integer.parseInt(itemId.getText()));

            loanDAO.saveLoan(loan);
            ViewItemsFXMLController.stage.close();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void actionInput(KeyEvent event) {
        remainder.setText(value.getText());
    }
    
    private Double remaining(){
        double amount = 0;
        
        if(!rate.getText().isEmpty() && !value.getText().isEmpty()){
            amount = Double.parseDouble(value.getText()) * Float.parseFloat(rate.getText()) /100;
            amount = amount + Double.parseDouble(value.getText());
        }
        
        return amount;
    }
}
