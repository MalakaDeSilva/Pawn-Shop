/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.empmgmt.controller;

import com.pawnshop.empmgmt.model.Employee;
import java.util.List;

/**
 *
 */
public interface IEmployeeDAO {
    void saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    void deleteEmployee(String nic);
    void updateEmployee(Employee employee);
    boolean login(String username, String password);
}
