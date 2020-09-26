/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.loanmgmt.view;

import com.pawnshop.LoginFXMLController;
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
public class UpdateLoanFXMLController implements Initializable {

    Loan prevLoan = ViewLoansFXMLController.loan;
    ILoanDAO loanDAO = new LoanDAO();

    @FXML
    private TextField itemId;

    @FXML
    private Text errItemId;

    @FXML
    private TextField value;

    @FXML
    private Text errValue;

    @FXML
    private TextField remainder;

    @FXML
    private Text errRemainder;

    @FXML
    private DatePicker dueDate;

    @FXML
    private Text errDueDate;

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
    private Text errCustomerNic;

    @FXML
    private Button btnSave;

    @FXML
    void actionInput(KeyEvent event) {
        remainder.setText(value.getText());
    }

    @FXML
    void actionUpdate(ActionEvent event) {
        Loan loan = new Loan();
        if(!setErrors()){
            try {
                loan.setLoanId(prevLoan.getLoanId());
                loan.setValue(Double.parseDouble(value.getText()));
                loan.setRate(Float.parseFloat(rate.getText()));
                loan.setRemainder(remaining());
                loan.setDuedate(Date.valueOf(dueDate.getValue()));
                loan.setBilldate(Date.valueOf(LocalDate.now()));
                loan.setEmpNic(empNic.getText());
                loan.setCustomerNic(customerNic.getText());
                loan.setItemId(Integer.parseInt(itemId.getText()));

                loanDAO.updateLoan(loan);
                ViewLoansFXMLController.stage.close();
            } catch (NumberFormatException ex) {
                errValue.setText("Please enter a number.");
            }
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
        init();
        clearErrors();
    }

    private boolean setErrors() {
        boolean errors = false;
        if (remainder.getText().isEmpty()) {
            errors = true;
            errRemainder.setText("Remainder cannot be empty.");
        }
        if (dueDate.getValue() == null) {
            errors = true;
            errDueDate.setText("Due date cannot be empty.");
        }
        if (customerNic.getText().isEmpty()) {
            errors = true;
            errCustomerNic.setText("Customer NIC cannot be empty.");
        }
        if (itemId.getText().isEmpty()) {
            errors = true;
            errItemId.setText("Item Id cannot be empty.");
        }
        if (rate.getText().isEmpty()) {
            errors = true;
            errRate.setText("Rate cannot be empty.");
        }
        
        return errors;
    }

    private void clearErrors() {
        billDate.setValue(LocalDate.now());
        empNic.setText(LoginFXMLController.employee);

        itemId.setOnKeyReleased((event) -> {
            errItemId.setText("");
        });

        value.setOnKeyReleased((event) -> {
            errValue.setText("");
            errRemainder.setText("");
            remainder.setText(value.getText());
        });

        remainder.setOnKeyReleased((event) -> {
            errRemainder.setText("");

            if (Double.parseDouble(remainder.getText()) > Double.parseDouble(value.getText())) {
                errRemainder.setText("Remainder cannot exceed loan amount.");
            }

        });

        dueDate.setOnAction((event) -> {
            errDueDate.setText("");
        });

        customerNic.setOnKeyReleased((event) -> {
            errCustomerNic.setText("");
        });
        rate.setOnKeyPressed((event) -> {
            errRate.setText("");
        });

    }

    private void init() {
        itemId.setText(String.valueOf(prevLoan.getItemId()));
        value.setText(String.valueOf(prevLoan.getValue()));
        rate.setText(String.valueOf(prevLoan.getRate()));
        remainder.setText(String.valueOf(prevLoan.getRemainder()));
        dueDate.setValue(prevLoan.getDuedate().toLocalDate());
        billDate.setValue(prevLoan.getBilldate().toLocalDate());
        empNic.setText(prevLoan.getEmpNic());
        customerNic.setText(prevLoan.getCustomerNic());
    }

    private Double remaining() {
        double amount = 0;

        if (!rate.getText().isEmpty() && !value.getText().isEmpty()) {
            amount = Double.parseDouble(value.getText()) * Float.parseFloat(rate.getText()) / 100;
            amount = amount + Double.parseDouble(value.getText());
        }

        return amount;
    }
}
