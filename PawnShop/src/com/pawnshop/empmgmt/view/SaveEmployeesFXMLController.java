/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.empmgmt.view;

import com.pawnshop.empmgmt.controller.EmployeeDAO;
import com.pawnshop.empmgmt.controller.IEmployeeDAO;
import com.pawnshop.empmgmt.model.Employee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 */
public class SaveEmployeesFXMLController implements Initializable {

    IEmployeeDAO employeeDAO = new EmployeeDAO();
    
    @FXML
    private TextField nic;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField password;
    
    @FXML
    void actionSave(ActionEvent event) {
        Employee employee = new Employee();
        
        employee.setNic(nic.getText());
        employee.setName(name.getText());
        employee.setPassword(password.getText());
        
        employeeDAO.saveEmployee(employee);
        ViewEmployeesFXMLController.stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
