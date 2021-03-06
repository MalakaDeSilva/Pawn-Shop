/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.paymgmt.view;

import com.pawnshop.loanmgmt.controller.ILoanDAO;
import com.pawnshop.loanmgmt.controller.LoanDAO;
import com.pawnshop.loanmgmt.model.Loan;
import com.pawnshop.loanmgmt.view.ViewLoansFXMLController;
import com.pawnshop.paymgmt.controller.IPayDAO;
import com.pawnshop.paymgmt.controller.PayDAO;
import com.pawnshop.paymgmt.model.Payment;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
public class RecordPaymentFXMLController implements Initializable {

    Loan loan = ViewLoansFXMLController.loan;
    IPayDAO payDAO = new PayDAO();
    ILoanDAO loanDAO = new LoanDAO();

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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().add("Installments");
        type.getItems().add("Loan Recovery");

        payDate.setValue(LocalDate.now());
        if (loan != null) {
            customer.setText(loan.getCustomerNic());
            empNic.setText(loan.getEmpNic());
            loanId.setText(String.valueOf(loan.getLoanId()));
        }

        clearErrors();
    }

    @FXML
    void actionSave(ActionEvent event) {
        if (!setErrors()) {
            Payment payment = new Payment();
            payment.setPaymentType(type.getValue());
            payment.setAmount(Double.parseDouble(amount.getText()));
            payment.setEmpNic(empNic.getText());
            payment.setCustNic(customer.getText());
            payment.setLoanId(Integer.parseInt(loanId.getText()));
            payment.setPaymentDate(Date.valueOf(LocalDate.now()));
            payDAO.recordPayment(payment);

            double remainder = loan.getRemainder() - Double.parseDouble(amount.getText());
            loanDAO.updateLoanPayment(Integer.parseInt(loanId.getText()), remainder);
            ViewLoansFXMLController.stage.close();
        }
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
                if (loan.getRemainder() > Double.parseDouble(amount.getText())) {
                    errors = true;
                    errAmount.setText("To recover the loan, full amount must be paid.");
                }
            }
        } catch (NullPointerException ex) {
            errType.setText("Please choose a payment type.");
        }
        return errors;
    }

    private void clearErrors() {
        amount.setOnKeyReleased((event) -> {
            errAmount.setText("");
        });

        type.setOnAction((event) -> {
            errType.setText("");
        });
    }
}
