/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.empmgmt.view;

import com.pawnshop.constants.Constants;
import com.pawnshop.customermgmt.view.ViewCustomerFXMLController;
import static com.pawnshop.customermgmt.view.ViewCustomerFXMLController.stage;
import com.pawnshop.empmgmt.controller.EmployeeDAO;
import com.pawnshop.empmgmt.controller.IEmployeeDAO;
import com.pawnshop.empmgmt.model.Employee;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 */
public class ViewEmployeesFXMLController implements Initializable {

    public static Stage stage;
    IEmployeeDAO employeeDAO = new EmployeeDAO();

    @FXML
    private TableView<Employee> empTable;

    @FXML
    private TableColumn<Employee, String> colNIC;

    @FXML
    private TableColumn<Employee, String> colName;

    @FXML
    private TableColumn<Employee, String> colPassword;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        empTable.getItems().setAll(employeeDAO.getAllEmployees());
    }

    @FXML
    void actionNewEmployee(ActionEvent event) {
        newEmployeeWindow();
    }

    private void refresh() {
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        empTable.getItems().setAll(employeeDAO.getAllEmployees());
    }

    private void newEmployeeWindow() {
        try {
            Parent addEmployee = FXMLLoader.load(getClass().getResource("/com/pawnshop/empmgmt/view/saveEmployeesFXML.fxml"));
            Scene scene = new Scene(addEmployee);
            stage = new Stage();
            stage.setTitle(Constants.ADD_EMPLOYEE_TITLE);
            stage.setScene(scene);
            stage.show();
            stage.setOnHiding((WindowEvent we) -> refresh());
        } catch (IOException ex) {
            Logger.getLogger(ViewCustomerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
