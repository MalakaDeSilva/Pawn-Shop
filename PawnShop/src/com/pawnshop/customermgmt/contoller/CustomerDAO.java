/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.customermgmt.contoller;

import com.pawnshop.constants.Constants;
import com.pawnshop.customermgmt.model.Customer;
import com.pawnshop.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO implements ICustomerDAO {

    Connection connection;
    PreparedStatement prepS;
    ResultSet resS;

    @Override
    public void saveCustomer(Customer customer) {
        try {
            connection = DBConnection.getConnection();
            prepS = connection.prepareStatement(Constants.ADD_CUSTOMER);

            prepS.setString(1, customer.getNic());
            prepS.setString(2, customer.getName());
            prepS.setString(3, customer.getAddress());
            prepS.setInt(4, customer.getContactNo());
            prepS.setString(5, customer.getEmail());

            prepS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
    public Customer viewCustomer(String nic) {
        Customer customer = new Customer();
        try {
            connection = DBConnection.getConnection();

            prepS = connection.prepareStatement(Constants.VIEW_CUSTOMER);
            prepS.setString(1, nic);

            resS = prepS.executeQuery();

            while (resS.next()) {
                customer.setNic(resS.getString("nic"));
                customer.setAddress(resS.getString("address"));
                customer.setContactNo(resS.getInt("contact_no"));
                customer.setName(resS.getString("name"));
                customer.setEmail(resS.getString("email"));
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
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            connection = DBConnection.getConnection();

            prepS = connection.prepareStatement(Constants.UPDATE_CUSTOMER);

            prepS.setString(1, customer.getName());
            prepS.setString(2, customer.getAddress());
            prepS.setInt(3, customer.getContactNo());
            prepS.setString(4, customer.getEmail());
            prepS.setString(5, customer.getNic());
            
            prepS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     *
     * @param nic
     */
    @Override
    public void deleteCustomer(String nic) {
        try {
            connection = DBConnection.getConnection();

            prepS = connection.prepareStatement(Constants.DELETE_CUSTOMER);

            prepS.setString(1, nic);

            prepS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> list = new ArrayList<>();
        Customer customer = null;

        try {
            connection = DBConnection.getConnection();
            prepS = connection.prepareStatement(Constants.GET_ALL_CUSTOMERS);
            resS = prepS.executeQuery();

            while (resS.next()) {
                customer = new Customer();

                customer.setNic(resS.getString("nic"));
                customer.setAddress(resS.getString("address"));
                customer.setContactNo(resS.getInt("contact_no"));
                customer.setName(resS.getString("name"));
                customer.setEmail(resS.getString("email"));

                list.add(customer);
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
        return list;
    }

}
