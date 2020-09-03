/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.itemmgmt.view;

import com.pawnshop.constants.Constants;
import com.pawnshop.itemmgmt.controller.IItemDAO;
import com.pawnshop.itemmgmt.controller.ItemDAO;
import com.pawnshop.itemmgmt.model.Item;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 */
public class ViewItemsFXMLController implements Initializable {

    IItemDAO itemDAO = new ItemDAO();
    public static Stage stage;
    public static Item item;

    @FXML
    private TableView<Item> table;

    @FXML
    private TableColumn<Item, Integer> colItemId;

    @FXML
    private TableColumn<Item, String> colItemType;

    @FXML
    private TableColumn<Item, Double> colWeight;

    @FXML
    private TableColumn<Item, Float> colValue;

    @FXML
    private TableColumn<Item, String> colStatus;

    @FXML
    private TableColumn<Item, String> colCustomer;

    @FXML
    private TableColumn<Item, String> colDescription;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("nic"));

        table.getItems().setAll(getAllItems());

        table.setContextMenu(createContextMenu());
    }

    @FXML
    void actionNewItem(ActionEvent event) {
        newItemWindow();
    }

    private List<Item> getAllItems() {
        List<Item> list = itemDAO.getAllItems();
        return list;
    }

    private void newItemWindow() {
        try {
            Parent addCustomer = FXMLLoader.load(getClass().getResource("/com/pawnshop/itemmgmt/view/saveItemFXML.fxml"));
            Scene scene = new Scene(addCustomer);
            stage = new Stage();
            stage.setTitle(Constants.ADD_ITEM_TITLE);
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding((WindowEvent we) -> refresh());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void refresh() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("nic"));

        table.getItems().setAll(getAllItems());
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    }

    private void issueLoan() {
        try {
            Parent issueLoan = FXMLLoader.load(getClass().getResource("/com/pawnshop/loanmgmt/view/saveLoanFXML.fxml"));
            Scene scene = new Scene(issueLoan);
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewItemsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ContextMenu createContextMenu() {
        ContextMenu conMenu = new ContextMenu();

        MenuItem loanIssue = new MenuItem("Issue Loan");
        loanIssue.setOnAction((event) -> {
            item = table.getSelectionModel().getSelectedItem();
            issueLoan();
        });

        conMenu.getItems().addAll(loanIssue);

        return conMenu;
    }
}
