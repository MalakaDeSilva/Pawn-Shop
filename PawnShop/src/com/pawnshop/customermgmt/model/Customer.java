/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.customermgmt.model;

public class Customer {

    private String nic;
    private String name;
    private String address;
    private int contactNo;
    private String email;
    private boolean deleted;

    public Customer(String nic, String name, String address, int contactNo, String email, boolean deleted) {
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.email = email;
        this.deleted = deleted;
    }

    public Customer() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean deleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
