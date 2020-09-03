/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.paymgmt.view;

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

/**
 * FXML Controller class
 *
 */
public class RecordPaymentFXMLController implements Initializable {
    
    Loan loan = ViewLoansFXMLController.loan;
    IPayDAO payDAO = new PayDAO();
    
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().add("Installments");
        payDate.setValue(LocalDate.now());
        if (loan != null) {
            customer.setText(loan.getCustomerNic());
            empNic.setText(loan.getEmpNic());
            loanId.setText(String.valueOf(loan.getLoanId()));
        }
    }
    
    @FXML
    void actionSave(ActionEvent event) {
        Payment payment = new Payment();
        payment.setPaymentType(type.getValue());
        payment.setAmount(Double.parseDouble(amount.getText()));
        payment.setEmpNic(empNic.getText());
        payment.setCustNic(customer.getText());
        payment.setLoanId(Integer.parseInt(loanId.getText()));
        payment.setPaymentDate(Date.valueOf(LocalDate.now()));
        payDAO.recordPayment(payment);
        ViewLoansFXMLController.stage.close();
    }
}
