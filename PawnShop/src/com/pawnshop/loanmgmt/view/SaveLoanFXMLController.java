/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.loanmgmt.view;

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
        empNic.setText("963374437V");
        if (_item != null) {
            itemId.setText(String.valueOf(_item.getItemId()));
            customerNic.setText(_item.getNic());
        }
    }

    @FXML
    void actionSave(ActionEvent event) {
        loan = new Loan();

        loan.setValue(Double.parseDouble(value.getText()));
        loan.setRemainder(Double.parseDouble(remainder.getText()));
        loan.setDuedate(Date.valueOf(dueDate.getValue()));
        loan.setBilldate(Date.valueOf(LocalDate.now()));
        loan.setEmpNic(empNic.getText());
        loan.setCustomerNic(customerNic.getText());
        loan.setItemId(Integer.parseInt(itemId.getText()));

        loanDAO.saveLoan(loan);
        ViewItemsFXMLController.stage.close();
    }

    @FXML
    void actionInput(KeyEvent event) {
        remainder.setText(value.getText());
    }
}
