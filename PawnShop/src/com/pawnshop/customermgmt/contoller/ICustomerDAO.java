/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.customermgmt.contoller;

import com.pawnshop.customermgmt.model.Customer;
import java.util.ArrayList;

public interface ICustomerDAO {
    void saveCustomer(Customer customer);
    Customer viewCustomer(String nic);
    void updateCustomer(Customer customer);
    void deleteCustomer(String nic);
    ArrayList<Customer> getAllCustomers();
}
