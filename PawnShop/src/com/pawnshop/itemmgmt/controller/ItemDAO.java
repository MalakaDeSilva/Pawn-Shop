/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.itemmgmt.controller;

import com.pawnshop.constants.Constants;
import com.pawnshop.itemmgmt.model.Item;
import com.pawnshop.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ItemDAO implements IItemDAO {
    
    Connection connection;
    PreparedStatement prepS;
    ResultSet resS;
    
    @Override
    public void saveItem(Item item) {
        connection = DBConnection.getConnection();
        try {
            prepS = connection.prepareStatement(Constants.ADD_ITEM);
            
            prepS.setString(1, item.getItemType());
            prepS.setDouble(2, item.getWeight());
            prepS.setInt(3, item.getKarat());
            prepS.setFloat(4, item.getValue());
            prepS.setString(5, item.getStatus());
            prepS.setString(6, item.getDescription());
            prepS.setString(7, item.getNic());
            
            prepS.executeUpdate();
        } catch (SQLException ex) {
            if (Integer.parseInt(ex.getSQLState()) == 23000) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Occured!");
                alert.setHeaderText("Item is not stored.");
                alert.setContentText("Customer " + item.getNic() + ", does not exist.");
                
                alert.showAndWait();
            }
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public Item viewItem(int itemId) {
        connection = DBConnection.getConnection();
        Item item = null;
        
        try {
            prepS = connection.prepareStatement(Constants.VIEW_ITEM);
            prepS.setInt(1, itemId);
            
            resS = prepS.executeQuery();
            while (resS.next()) {
                item = new Item();
                
                item.setItemId(resS.getInt("item_id"));
                item.setItemType(resS.getString("type"));
                item.setKarat(resS.getInt("karat"));
                item.setWeight(resS.getDouble("weight"));
                item.setValue(resS.getFloat("value"));
                item.setStatus(resS.getString("status"));
                item.setDescription(resS.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resS.close();
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return item;
    }
    
    @Override
    public void updateItem(Item item) {
        connection = DBConnection.getConnection();
        
        try {
            prepS = connection.prepareStatement(Constants.UPDATE_ITEM);
            
            prepS.setString(1, item.getItemType());
            prepS.setDouble(2, item.getWeight());
            prepS.setInt(3, item.getKarat());
            prepS.setFloat(4, item.getValue());
            prepS.setString(5, item.getStatus());
            prepS.setString(6, item.getDescription());
            prepS.setString(7, item.getNic());
            prepS.setInt(8, item.getItemId());
            
            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void deleteItem(int itemId) {
        connection = DBConnection.getConnection();
        
        try {
            prepS = connection.prepareStatement(Constants.DELETE_ITEM);
            
            prepS.setInt(1, itemId);
            
            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    @Override
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> itemList = new ArrayList<>();
        Item item;
        
        connection = DBConnection.getConnection();
        try {
            prepS = connection.prepareStatement(Constants.GET_ALL_ITEMS);
            resS = prepS.executeQuery();
            
            while (resS.next()) {
                item = new Item();
                
                item.setItemId(resS.getInt("item_id"));
                item.setItemType(resS.getString("type"));
                item.setWeight(resS.getDouble("weight"));
                item.setKarat(resS.getInt("karat"));
                item.setValue(resS.getFloat("value"));
                item.setStatus(resS.getString("status"));
                item.setDescription(resS.getString("description"));
                item.setNic(resS.getString("nic"));
                itemList.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resS.close();
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return itemList;
    }
    
}
