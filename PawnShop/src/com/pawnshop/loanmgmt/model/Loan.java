/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.loanmgmt.model;

import java.sql.Date;

/**
 *
 */
public class Loan {

    private int loanId;
    private Double value;
    private float rate;
    private Double remainder;
    private Date duedate;
    private Date billdate;
    private String empNic;
    private int itemId;
    private String customerNic;

    public Loan(int loanId, Double value, float rate, Double remainder, Date duedate, Date billdate, String empNic, int itemId, String customerNic) {
        this.loanId = loanId;
        this.value = value;
        this.rate = rate;
        this.remainder = remainder;
        this.duedate = duedate;
        this.billdate = billdate;
        this.empNic = empNic;
        this.itemId = itemId;
        this.customerNic = customerNic;
    }

    public Loan() {
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getRemainder() {
        return remainder;
    }

    public void setRemainder(Double remainder) {
        this.remainder = remainder;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Date getBilldate() {
        return billdate;
    }

    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    public String getEmpNic() {
        return empNic;
    }

    public void setEmpNic(String empNic) {
        this.empNic = empNic;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

}
