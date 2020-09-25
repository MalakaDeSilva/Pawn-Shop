/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.paymgmt.controller;

import com.pawnshop.constants.Constants;
import com.pawnshop.paymgmt.model.Payment;
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
public class PayDAO implements IPayDAO {

    Connection connection;
    PreparedStatement prepS;
    ResultSet resS;

    @Override
    public void recordPayment(Payment payment) {
        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.ADD_PAYMENT);

            prepS.setString(1, payment.getPaymentType());
            prepS.setDouble(2, payment.getAmount());
            prepS.setString(3, payment.getEmpNic());
            prepS.setString(4, payment.getCustNic());
            prepS.setInt(5, payment.getLoanId());
            prepS.setDate(6, payment.getPaymentDate());

            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PayDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PayDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deletePayment(int paymentId) {
        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.DELETE_PAYMENT);

            prepS.setInt(1, paymentId);

            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PayDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PayDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Payment> getAllPayments(int loanId) {
        List<Payment> list = new ArrayList<>();
        Payment payment;

        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.GET_ALL_PAYMENTS);

            prepS.setInt(1, loanId);
            resS = prepS.executeQuery();

            while (resS.next()) {
                payment = new Payment();

                payment.setPaymentId(resS.getInt("payment_id"));
                payment.setPaymentType(resS.getString("type"));
                payment.setAmount(resS.getDouble("amount"));
                payment.setEmpNic(resS.getString("emp_nic"));
                payment.setCustNic(resS.getString("nic"));
                payment.setLoanId(resS.getInt("loan_id"));
                payment.setPaymentDate(resS.getDate("payment_date"));

                list.add(payment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PayDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resS.close();
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PayDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public void updatePayment(Payment payment) {
        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.UPDATE_PAYMENT);

            prepS.setDouble(1, payment.getAmount());
            prepS.setString(2, payment.getPaymentType());
            prepS.setString(3, payment.getEmpNic());
            prepS.setInt(4, payment.getPaymentId());

            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PayDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PayDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
