/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pawnshop.constants;

public class Constants {
    // General 
    public static String SHOP_TITLE = "Supipi Pawning Shop";
    
    // Customer Management
    public static String ADD_CUSTOMER       = "INSERT INTO Customers (nic, name, address, contact_no, email) VALUES (?, ?, ?, ?, ?)";
    public static String VIEW_CUSTOMER      = "SELECT * FROM Customers WHERE nic = ?";
    public static String UPDATE_CUSTOMER    = "UPDATE Customers SET name = ?, address = ?, contact_no = ?, email = ? WHERE nic = ?";
    public static String DELETE_CUSTOMER    = "DELETE FROM Customers WHERE nic = ?";
    public static String GET_ALL_CUSTOMERS  = "SELECT * FROM Customers";
}
