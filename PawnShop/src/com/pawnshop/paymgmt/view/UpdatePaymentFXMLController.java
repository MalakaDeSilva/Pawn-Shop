/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.paymgmt.view;

import com.pawnshop.loanmgmt.controller.ILoanDAO;
import com.pawnshop.loanmgmt.controller.LoanDAO;
import com.pawnshop.loanmgmt.view.ViewLoansFXMLController;
import com.pawnshop.paymgmt.controller.IPayDAO;
import com.pawnshop.paymgmt.controller.PayDAO;
import com.pawnshop.paymgmt.model.Payment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 */
public class UpdatePaymentFXMLController implements Initializable {

    Payment prevPayment = ViewPaymentsFXMLController.payment;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField amount;

    @FXML
    private TextField customer;

    @FXML
    private TextField empNic;

    @FXML
    private TextField loanId;

    @FXML
    private DatePicker payDate;

    @FXML
    private Text errType;

    @FXML
    private Text errAmount;

    @FXML
    void actionUpdate(ActionEvent event) {
        if (!setErrors()) {
            updatePayment();
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

    private void init() {
        type.setValue(prevPayment.getPaymentType());
        amount.setText(String.valueOf(prevPayment.getAmount()));
        customer.setText(prevPayment.getCustNic());
        empNic.setText(prevPayment.getEmpNic());
        loanId.setText(String.valueOf(prevPayment.getLoanId()));
        payDate.setValue(prevPayment.getPaymentDate().toLocalDate());

    }

    private void updatePayment() {
        Payment payment = new Payment();
        IPayDAO paymentDAO = new PayDAO();

        payment.setPaymentId(prevPayment.getPaymentId());
        payment.setPaymentType(type.getValue());
        payment.setAmount(Double.parseDouble(amount.getText()));
        payment.setEmpNic(empNic.getText());

        paymentDAO.updatePayment(payment);
        recheckLoan();
        ViewPaymentsFXMLController.stage.close();
    }

    private boolean setErrors() {
        boolean errors = false;
        if (amount.getText().isEmpty()) {
            errors = true;
            errAmount.setText("Amount cannot be empty.");
        }

        try {
            if (type.getValue().isEmpty() || type.getValue() == null) {
                errors = true;
                errType.setText("Please choose a payment type.");
            }
            if (type.getValue().equals("Loan Recovery")) {
                if (ViewLoansFXMLController.loan.getRemainder() > Double.parseDouble(amount.getText())) {
                    errors = true;
                    errAmount.setText("To recover the loan, full amount must be paid.");
                }
            }
        } catch (NullPointerException ex) {
            errType.setText("Please choose a payment type.");
        }
        return errors;
    }
    
    private void clearErrors(){
        amount.setOnKeyReleased((event)->{
            errAmount.setText("");
        });
        
        type.setOnAction((event)->{
            errType.setText("");
        });
    }

    private void recheckLoan() {
        ILoanDAO loanDAO = new LoanDAO();
        double remaining = ViewPaymentsFXMLController.loan.getRemainder();
        double prevAmount = prevPayment.getAmount();
        double newAmount = Double.parseDouble(amount.getText());

        double newRemaining = remaining;

        if (newAmount > prevAmount) {
            newRemaining = remaining - (newAmount - prevAmount);
        } else if (newAmount < prevAmount) {
            newRemaining = remaining + (prevAmount - newAmount);
        }

        loanDAO.updateLoanPayment(ViewPaymentsFXMLController.loan.getLoanId(), newRemaining);
    }
}
