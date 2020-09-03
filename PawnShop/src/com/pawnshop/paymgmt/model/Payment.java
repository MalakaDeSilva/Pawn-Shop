/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.paymgmt.model;

import java.sql.Date;

/**
 *
 */
public class Payment {

    private int paymentId;
    private String paymentType;
    private double amount;
    private String custNic;
    private String empNic;
    private int loanId;
    private Date paymentDate;

    public Payment(int paymentId, String paymentType, double amount, String custNic, String empNic, int loanId, Date paymentDate) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.amount = amount;
        this.custNic = custNic;
        this.empNic = empNic;
        this.loanId = loanId;
        this.paymentDate = paymentDate;
    }

    public Payment() {
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustNic() {
        return custNic;
    }

    public void setCustNic(String custNic) {
        this.custNic = custNic;
    }

    public String getEmpNic() {
        return empNic;
    }

    public void setEmpNic(String empNic) {
        this.empNic = empNic;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

}
