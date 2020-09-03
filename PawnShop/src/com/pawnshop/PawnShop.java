/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop;

import com.pawnshop.constants.Constants;
import com.pawnshop.util.DBConnection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.ibatis.jdbc.ScriptRunner;

public class PawnShop extends Application {

    public static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent viewEmployees = FXMLLoader.load(getClass().getResource("/com/pawnshop/empmgmt/view/viewEmployeesFXML.fxml"));
        Parent viewCustomer = FXMLLoader.load(getClass().getResource("/com/pawnshop/customermgmt/view/viewCustomerFXML.fxml"));
        Parent viewItem = FXMLLoader.load(getClass().getResource("/com/pawnshop/itemmgmt/view/viewItemsFXML.fxml"));
        Parent viewLoan = FXMLLoader.load(getClass().getResource("/com/pawnshop/loanmgmt/view/viewLoansFXML.fxml"));
        
        scene = new Scene(viewLoan);
        DBConnection.getConnection();
        primaryStage.setTitle(Constants.SHOP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ScriptRunner script = new ScriptRunner(DBConnection.getConnection());
        try {
            script.setLogWriter(null);
            System.out.println("Executing database checks....");
            script.runScript(new BufferedReader(new FileReader("dbscript/pawning_shop_db.sql")));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Database checks completed....");
        }
        launch(args);
    }

}
