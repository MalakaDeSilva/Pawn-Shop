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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Malaka
 */
public class ViewPaymentsFXMLController implements Initializable {

    IPayDAO payDAO = new PayDAO();
    Loan loan = ViewLoansFXMLController.loan;

    @FXML
    private TableView<Payment> table;

    @FXML
    private TableColumn<Payment, Integer> colPaymentId;

    @FXML
    private TableColumn<Payment, String> colPaymentType;

    @FXML
    private TableColumn<Payment, Double> colAmount;

    @FXML
    private TableColumn<Payment, String> colEmployee;

    @FXML
    private TableColumn<Payment, String> colCustomer;

    @FXML
    private TableColumn<Payment, Integer> colLoanId;

    @FXML
    private TableColumn<Payment, Date> colPayDate;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int loan_id;
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colEmployee.setCellValueFactory(new PropertyValueFactory<>("empNic"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("custNic"));
        colLoanId.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        colPayDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        loan_id = (loan == null) ? 0 : loan.getLoanId();
        table.getItems().setAll(payDAO.getAllPayments(loan_id));
    }

}
