/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.customermgmt.view;

import com.pawnshop.PawnShop;
import com.pawnshop.constants.Constants;
import com.pawnshop.customermgmt.contoller.CustomerDAO;
import com.pawnshop.customermgmt.contoller.ICustomerDAO;
import com.pawnshop.customermgmt.model.Customer;
import com.pawnshop.itemmgmt.view.ViewItemsFXMLController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 */
public class ViewCustomerFXMLController implements Initializable {

    public static Stage parentStage = PawnShop.stage;
    ICustomerDAO customerDAO = new CustomerDAO();
    public static Stage stage;
    public static Customer customer;
    @FXML
    private TableView<Customer> table;

    @FXML
    private TableColumn<Customer, String> colNIC;

    @FXML
    private TableColumn<Customer, String> colName;

    @FXML
    private TableColumn<Customer, Integer> colContactNo;

    @FXML
    private TableColumn<Customer, String> colEmail;

    @FXML
    private TableColumn<Customer, String> colAddress;
    @FXML
    private Button btnNewCust;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ContextMenu conMenu = new ContextMenu();

        MenuItem itemDelete = new MenuItem("Delete");
        itemDelete.setOnAction((event) -> {
            deleteCustomerWindow(table.getSelectionModel().getSelectedItem().getName());
        });

        MenuItem itemUpdate = new MenuItem("Update");
        itemUpdate.setOnAction((event) -> {
            updateCustomerWindow();
        });

        conMenu.getItems().addAll(itemDelete, itemUpdate);

        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        table.getItems().setAll(getCustomers());
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.setOnMousePressed((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                updateCustomerWindow();
            }

            if (event.isSecondaryButtonDown() && event.getClickCount() == 1 && table.getSelectionModel().getSelectedItem() != null) {
                table.setContextMenu(conMenu);
            }
        });
    }

    @FXML
    void actionNewCustomer(ActionEvent event) {
        newCustomerWindow();
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

    @FXML
    void actionViewLoans(ActionEvent event) {
        try {
            Parent viewLoan = FXMLLoader.load(getClass().getResource("/com/pawnshop/loanmgmt/view/viewLoansFXML.fxml"));

            parentStage.setScene(new Scene(viewLoan));
        } catch (IOException ex) {
            Logger.getLogger(ViewItemsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Customer> getCustomers() {
        List<Customer> list = customerDAO.getAllCustomers();

        return list;
    }

    private void refresh() {
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        table.getItems().setAll(getCustomers());
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    }

    private void updateCustomerWindow() {
        Parent updateCustomer;
        customer = table.getSelectionModel().getSelectedItem();
        try {
            updateCustomer = FXMLLoader.load(getClass().getResource("/com/pawnshop/customermgmt/view/updateCustomerFXML.fxml"));

            Scene scene = new Scene(updateCustomer);
            stage = new Stage();
            stage.setTitle(Constants.ADD_CUSTOMER_TITLE);
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding((WindowEvent we) -> refresh());
        } catch (IOException ex) {
            Logger.getLogger(ViewCustomerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void newCustomerWindow() {
        try {
            Parent addCustomer = FXMLLoader.load(getClass().getResource("/com/pawnshop/customermgmt/view/saveCustomerFXML.fxml"));
            Scene scene = new Scene(addCustomer);
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding((WindowEvent we) -> refresh());
        } catch (IOException ex) {
            Logger.getLogger(ViewCustomerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteCustomerWindow(String custName) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Customer: " + custName);
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            customerDAO.deleteCustomer(table.getSelectionModel().getSelectedItem().getNic());
            refresh();
        }
    }
}
