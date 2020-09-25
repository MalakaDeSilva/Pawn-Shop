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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 */
public class ViewPaymentsFXMLController implements Initializable {

    Stage parentStage = ViewLoansFXMLController.stage;
    public static Stage stage; 
    IPayDAO payDAO = new PayDAO();
    public static Loan loan = ViewLoansFXMLController.loan;
    public static Payment payment;

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
        
        table.setContextMenu(createContextMenu());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    private ContextMenu createContextMenu() {
        ContextMenu conMenu = new ContextMenu();

        MenuItem update = new MenuItem("Update");
        update.setOnAction((event) -> {
            updatePaymentWindow();
        });

        MenuItem deleteLoan = new MenuItem("Delete");
        deleteLoan.setOnAction((event) -> {
            deletePaymentWindow();
        });
        conMenu.getItems().addAll(update, deleteLoan);

        return conMenu;
    }

    private void updatePaymentWindow() {
        payment = table.getSelectionModel().getSelectedItem();
        try {
            Parent payments = FXMLLoader.load(getClass().getResource("/com/pawnshop/paymgmt/view/updatePaymentFXML.fxml"));
            Scene scene = new Scene(payments);
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding((WindowEvent we) -> refresh());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void deletePaymentWindow() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete payment");
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            payDAO.deletePayment(table.getSelectionModel().getSelectedItem().getPaymentId());
            recheckLoan();
            refresh();
        }
    }

    private void refresh() {
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
    
    private void recheckLoan(){
        ILoanDAO loanDAO = new LoanDAO();
        double remaining = loan.getRemainder();
        double currPayment =  table.getSelectionModel().getSelectedItem().getAmount();
        
        double newRemaining = remaining + currPayment;
        loanDAO.updateLoanPayment(loan.getLoanId(), newRemaining);
    }
}
