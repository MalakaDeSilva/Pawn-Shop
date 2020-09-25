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
    public static String ADD_CUSTOMER_TITLE = "Add Customer";
    public static String ADD_CUSTOMER       = "INSERT INTO Customers (nic, name, address, contact_no, email, deleted) VALUES (?, ?, ?, ?, ?, ?)";
    public static String VIEW_CUSTOMER      = "SELECT * FROM Customers WHERE nic = ?";
    public static String UPDATE_CUSTOMER    = "UPDATE Customers SET name = ?, address = ?, contact_no = ?, email = ? WHERE nic = ?";
    public static String DELETE_CUSTOMER    = "UPDATE Customers SET deleted = ? WHERE nic = ?";
    public static String GET_ALL_CUSTOMERS  = "SELECT * FROM Customers";

    // Item Management 
    public static String ADD_ITEM_TITLE = "Add Item";
    public static String ADD_ITEM       = "INSERT INTO Item (type, weight, karat, value, status, description, nic) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static String VIEW_ITEM      = "SELECT * FROM item WHERE item_id = ?";
    public static String UPDATE_ITEM    = "UPDATE item SET type = ?, weight = ?, karat = ?, value = ?, status = ?, description = ?, nic = ? WHERE item_id = ?";
    public static String DELETE_ITEM    = "DELETE FROM item WHERE item_id = ?";
    public static String GET_ALL_ITEMS  = "SELECT * FROM item";
    
    // Employee Management
    public static String ADD_EMPLOYEE_TITLE = "Add Employee";
    public static String ADD_EMPLOYEE       = "INSERT INTO employee (nic, name, password) VALUES (?, ?, ?)";
    public static String GET_ALL_EMPLOYEES  = "SELECT * FROM employee";
    public static String UPDATE_EMPLOYEE    = "UPDATE employee SET name = ?, password = ? WHERE nic = ?";
    public static String DELETE_EMPLOYEE    = "DELETE FROM employee WHERE nic = ?";
    
    // Loan Management
    public static String ADD_LOAN_TITLE         = "Issue Loan";
    public static String ADD_LOAN               = "INSERT INTO loan (value, rate, remainder, duedate, billdate, emp_nic, nic, item_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static String GET_ALL_LOANS          = "SELECT * FROM loan";
    public static String UPDATE_LOAN_PAYMENT    = "UPDATE loan SET remainder = ? WHERE loan_id = ?";
    public static String UPDATE_LOAN            = "UPDATE loan SET value = ?, rate = ?, remainder = ?, duedate = ? WHERE loan_id = ?";
    public static String DELETE_LOAN            = "DELETE FROM loan WHERE loan_id = ?";
    
    // Payment Management
    public static String ADD_PAYMENT_TITLE  = "Record Payment";
    public static String ADD_PAYMENT        = "INSERT INTO payment (type, amount, emp_nic, nic, loan_id, payment_date) VALUES (?, ?, ?, ?, ?, ?)";
    public static String GET_ALL_PAYMENTS   = "SELECT * FROM payment WHERE loan_id = ?";
    public static String UPDATE_PAYMENT     = "UPDATE payment SET amount = ?, type = ?, emp_nic = ? WHERE payment_id = ?";
    public static String DELETE_PAYMENT     = "DELETE FROM payment WHERE payment_id = ?";
    
}
