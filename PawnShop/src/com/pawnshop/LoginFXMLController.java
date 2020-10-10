/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop;

import static com.pawnshop.PawnShop.stage;
import com.pawnshop.constants.Constants;
import com.pawnshop.empmgmt.controller.EmployeeDAO;
import com.pawnshop.util.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Malaka
 */
public class LoginFXMLController implements Initializable {
    public static String employee;
    
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Text errUsername;

    @FXML
    private Text errPassword;

    @FXML
    void login(ActionEvent event) {
        boolean validated = true;
        if (username.getText().isEmpty()) {
            validated = false;
            errUsername.setText("Enter a username.");
        }
        if (password.getText().isEmpty()) {
            validated = false;
            errPassword.setText("Enter a password.");
        }

        if (validated) {
            if (new EmployeeDAO().login(username.getText(), password.getText())) {
                employee = username.getText();
                stage.close();
                stage = new Stage();
                try {
                    Parent viewItem = FXMLLoader.load(getClass().getResource("/com/pawnshop/itemmgmt/view/viewItemsFXML.fxml"));

                    Scene scene = new Scene(viewItem);
                    DBConnection.getConnection();
                    stage.setTitle(Constants.SHOP_TITLE);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {

                }
            } else {
                errPassword.setText("Username/Password error.");
            }
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setOnKeyReleased((event) -> {
            errUsername.setText("");
        });

        password.setOnKeyReleased((event) -> {
            errPassword.setText("");
        });
    }

}
