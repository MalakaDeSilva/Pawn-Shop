/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.empmgmt.controller;

import com.pawnshop.constants.Constants;
import com.pawnshop.empmgmt.model.Employee;
import com.pawnshop.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class EmployeeDAO implements IEmployeeDAO {

    Connection connection;
    PreparedStatement prepS;
    ResultSet resS;

    @Override
    public void saveEmployee(Employee employee) {
        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.ADD_EMPLOYEE);

            prepS.setString(1, employee.getNic());
            prepS.setString(2, employee.getName());
            prepS.setString(3, employee.getPassword());

            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        connection = DBConnection.getConnection();
        Employee employee;
        List<Employee> list = new ArrayList<>();

        try {
            prepS = connection.prepareStatement(Constants.GET_ALL_EMPLOYEES);

            resS = prepS.executeQuery();

            while (resS.next()) {
                employee = new Employee();

                employee.setNic(resS.getString("nic"));
                employee.setName(resS.getString("name"));
                employee.setPassword(resS.getString("password"));

                list.add(employee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resS.close();
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public void deleteEmployee(String nic) {
        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.DELETE_ITEM);

            prepS.setString(1, nic);

            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void updateEmployee(Employee employee) {
        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.UPDATE_EMPLOYEE);

            prepS.setString(1, employee.getName());
            prepS.setString(2, employee.getPassword());
            prepS.setString(3, employee.getNic());

            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
