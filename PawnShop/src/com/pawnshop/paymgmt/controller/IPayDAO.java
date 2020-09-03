/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.paymgmt.controller;

import com.pawnshop.paymgmt.model.Payment;
import java.util.List;

/**
 *
 */
public interface IPayDAO {

    void recordPayment(Payment payment);

    void deletePayment(int paymentId);

    List<Payment> getAllPayments(int loanId);

    void updatePayment(Payment payment);
}
