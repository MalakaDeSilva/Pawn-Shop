/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.empmgmt.model;

/**
 *
 */
public class Employee {

    private String nic;
    private String name;
    private String password;

    public Employee(String nic, String name, String password) {
        this.nic = nic;
        this.name = name;
        this.password = password;
    }

    public Employee() {
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
