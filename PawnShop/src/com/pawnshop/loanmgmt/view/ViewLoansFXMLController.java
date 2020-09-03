/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.loanmgmt.view;

import com.pawnshop.PawnShop;
import com.pawnshop.customermgmt.view.ViewCustomerFXMLController;
import static com.pawnshop.customermgmt.view.ViewCustomerFXMLController.parentStage;
import com.pawnshop.itemmgmt.view.ViewItemsFXMLController;
import static com.pawnshop.itemmgmt.view.ViewItemsFXMLController.parentStage;
import com.pawnshop.loanmgmt.controller.ILoanDAO;
import com.pawnshop.loanmgmt.controller.LoanDAO;
import com.pawnshop.loanmgmt.model.Loan;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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

    public static Stage parentStage = PawnShop.stage;
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

    @FXML
    void actionViewCustomers(ActionEvent event) {
        try {
            Parent viewCustomer = FXMLLoader.load(getClass().getResource("/com/pawnshop/customermgmt/view/viewCustomerFXML.fxml"));

            parentStage.setScene(new Scene(viewCustomer));
        } catch (IOException ex) {
            Logger.getLogger(ViewItemsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void actionViewItems(ActionEvent event) {
        try {
            Parent viewItem = FXMLLoader.load(getClass().getResource("/com/pawnshop/itemmgmt/view/viewItemsFXML.fxml"));

            parentStage.setScene(new Scene(viewItem));
        } catch (IOException ex) {
            Logger.getLogger(ViewCustomerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
