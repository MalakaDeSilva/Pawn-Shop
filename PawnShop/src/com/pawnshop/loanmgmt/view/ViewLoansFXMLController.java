/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.loanmgmt.view;

import com.pawnshop.PawnShop;
import com.pawnshop.constants.Constants;
import com.pawnshop.customermgmt.view.ViewCustomerFXMLController;
import com.pawnshop.itemmgmt.view.ViewItemsFXMLController;
import com.pawnshop.loanmgmt.controller.ILoanDAO;
import com.pawnshop.loanmgmt.controller.LoanDAO;
import com.pawnshop.loanmgmt.model.Loan;
import com.pawnshop.util.Report;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.exolab.castor.types.DateTime;

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
    private TableColumn<Loan, Float> colRate;

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

    @FXML
    void overdueLoan(ActionEvent event) {
        Date today = Date.valueOf(LocalDate.now());
        String query = "SELECT * FROM loan WHERE duedate > '" + today + "'";
        String filePath = "src/com/pawnshop/loanmgmt/report/overdue_loans.jrxml";

        Report report = new Report();
        report.generateReport(query, filePath);
    }

    @FXML
    void monthlyReport(ActionEvent event) {
        Date startDate = Date.valueOf(LocalDate.of(2020, (Calendar.getInstance().get(Calendar.MONTH)+1), 1));
        Date endDate = Date.valueOf(LocalDate.of(2020, (Calendar.getInstance().get(Calendar.MONTH)+1), 30));
        
        String query = "SELECT * FROM loan WHERE billdate > '" + startDate + "' AND billdate < '"+endDate+"'";
        String filePath = "src/com/pawnshop/loanmgmt/report/current_month_report.jrxml";

        Report report = new Report();
        report.generateReport(query, filePath);
    }

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
        colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("value"));
        colRemain.setCellValueFactory(new PropertyValueFactory<>("remainder"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("duedate"));
        colBillDate.setCellValueFactory(new PropertyValueFactory<>("billdate"));
        colEmpNic.setCellValueFactory(new PropertyValueFactory<>("empNic"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerNic"));

        table.getItems().setAll(loanDAO.getAllLoans());

        table.setContextMenu(createContextMenu());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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

        MenuItem updateLoan = new MenuItem("Update");
        updateLoan.setOnAction((event) -> {
            updateLoanWindow();
        });

        MenuItem deleteLoan = new MenuItem("Delete");
        deleteLoan.setOnAction((event) -> {
            deleteLoanWindow();
        });
        conMenu.getItems().addAll(payments, newPayment, new SeparatorMenuItem(), updateLoan, deleteLoan);

        return conMenu;
    }

    private void viewPaymentsWindow() {
        try {
            Parent payments = FXMLLoader.load(getClass().getResource("/com/pawnshop/paymgmt/view/viewPaymentsFXML.fxml"));
            Scene scene = new Scene(payments);
            stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Payments");
            stage.setResizable(false);
            stage.show();
            stage.setOnHiding((WindowEvent we) -> refresh());
        } catch (IOException ex) {
            Logger.getLogger(ViewLoansFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refresh() {
        colLoanId.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("value"));
        colRemain.setCellValueFactory(new PropertyValueFactory<>("remainder"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("duedate"));
        colBillDate.setCellValueFactory(new PropertyValueFactory<>("billdate"));
        colEmpNic.setCellValueFactory(new PropertyValueFactory<>("empNic"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customerNic"));

        table.getItems().setAll(loanDAO.getAllLoans());

        table.setContextMenu(createContextMenu());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void newPaymentWindow() {
        try {
            Parent payments = FXMLLoader.load(getClass().getResource("/com/pawnshop/paymgmt/view/recordPaymentFXML.fxml"));
            Scene scene = new Scene(payments);
            stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("New Payment");
            stage.setResizable(false);
            stage.show();
            stage.setOnHiding((WindowEvent we) -> refresh());
        } catch (IOException ex) {
            Logger.getLogger(ViewLoansFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateLoanWindow() {
        Parent updateLoan;
        loan = table.getSelectionModel().getSelectedItem();
        try {
            updateLoan = FXMLLoader.load(getClass().getResource("/com/pawnshop/loanmgmt/view/updateLoanFXML.fxml"));

            Scene scene = new Scene(updateLoan);
            stage = new Stage();
            stage.setTitle("Update Loan");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding((WindowEvent we) -> refresh());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteLoanWindow() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete loan");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            loanDAO.deleteLoan(table.getSelectionModel().getSelectedItem().getLoanId());
            refresh();
        }
    }
}
