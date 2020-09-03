/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.loanmgmt.view;

import static com.pawnshop.itemmgmt.view.ViewItemsFXMLController.stage;
import com.pawnshop.loanmgmt.controller.ILoanDAO;
import com.pawnshop.loanmgmt.controller.LoanDAO;
import com.pawnshop.loanmgmt.model.Loan;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class ViewLoansFXMLController implements Initializable {

    public static Stage stage;
    ILoanDAO loanDAO = new LoanDAO();
    public static Loan loan;

    @FXML
    private TableView<Loan> table;

    @FXML
    private TableColumn<Loan, Integer> colLoanId;

    @FXML
    private TableColumn<Loan, Integer> colItemId;

    @FXML
    private TableColumn<Loan, Double> colAmount;

    @FXML
    private TableColumn<Loan, Double> colRemain;

    @FXML
    private TableColumn<Loan, Date> colDueDate;

    @FXML
    private TableColumn<Loan, Date> colBillDate;

    @FXML
    private TableColumn<Loan, String> colEmpNic;

    @FXML
    private TableColumn<Loan, String> colCustomer;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colLoanId.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("value"));
        colRemain.setCellValueFactory(new PropertyValueFactory<>("remainder"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("duedate"));
        colBillDate.setCellValueFactory(new PropertyValueFactory<>("billdate"));
        colEmpNic.setCellValueFactory(new PropertyValueFactory<>("empNic"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerNic"));

        table.getItems().setAll(loanDAO.getAllLoans());

        table.setContextMenu(createContextMenu());
    }

    private ContextMenu createContextMenu() {
        ContextMenu conMenu = new ContextMenu();

        MenuItem payments = new MenuItem("Payments");
        payments.setOnAction((event) -> {
            loan = table.getSelectionModel().getSelectedItem();
            viewPaymentsWindow();
        });

        MenuItem newPayment = new MenuItem("New Payment");
        newPayment.setOnAction((event) -> {
            loan = table.getSelectionModel().getSelectedItem();
            newPaymentWindow();
        });

        conMenu.getItems().addAll(payments, newPayment);

        return conMenu;
    }

    private void viewPaymentsWindow() {
        try {
            Parent payments = FXMLLoader.load(getClass().getResource("/com/pawnshop/paymgmt/view/viewPaymentsFXML.fxml"));
            Scene scene = new Scene(payments);
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewLoansFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void newPaymentWindow() {
        try {
            Parent payments = FXMLLoader.load(getClass().getResource("/com/pawnshop/paymgmt/view/recordPaymentFXML.fxml"));
            Scene scene = new Scene(payments);
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewLoansFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
