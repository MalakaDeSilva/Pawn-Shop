/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.loanmgmt.controller;

import com.pawnshop.constants.Constants;
import com.pawnshop.loanmgmt.model.Loan;
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
public class LoanDAO implements ILoanDAO {

    Connection connection;
    PreparedStatement prepS;
    ResultSet resS;

    @Override
    public void saveLoan(Loan loan) {
        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.ADD_LOAN);

            prepS.setDouble(1, loan.getValue());
            prepS.setFloat(2, loan.getRate());
            prepS.setDouble(3, loan.getRemainder());
            prepS.setDate(4, loan.getDuedate());
            prepS.setDate(5, loan.getBilldate());
            prepS.setString(6, loan.getEmpNic());
            prepS.setString(7, loan.getCustomerNic());
            prepS.setInt(8, loan.getItemId());

            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteLoan(int loanId) {
        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.DELETE_LOAN);
            prepS.setInt(1, loanId);

            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Loan> getAllLoans() {
        List<Loan> list = new ArrayList<>();
        Loan loan;

        connection = DBConnection.getConnection();
        try {
            prepS = connection.prepareStatement(Constants.GET_ALL_LOANS);

            resS = prepS.executeQuery();

            while (resS.next()) {
                loan = new Loan();

                loan.setLoanId(resS.getInt("loan_id"));
                loan.setValue(resS.getDouble("value"));
                loan.setRate(resS.getFloat("rate"));
                loan.setRemainder(resS.getDouble("remainder"));
                loan.setDuedate(resS.getDate("duedate"));
                loan.setBilldate(resS.getDate("billdate"));
                loan.setCustomerNic(resS.getString("nic"));
                loan.setEmpNic(resS.getString("emp_nic"));
                loan.setItemId(resS.getInt("item_id"));

                list.add(loan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resS.close();
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public void updateLoan(Loan loan) {
        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.UPDATE_LOAN);

            prepS.setDouble(1, loan.getValue());
            prepS.setFloat(2, loan.getRate());
            prepS.setDouble(3, loan.getRemainder());
            prepS.setDate(4, loan.getDuedate());
            prepS.setInt(5, loan.getLoanId());

            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateLoanPayment(int loanId, double amount) {
        connection = DBConnection.getConnection();

        try {
            prepS = connection.prepareStatement(Constants.UPDATE_LOAN_PAYMENT);

            prepS.setDouble(1, amount);
            prepS.setInt(2, loanId);

            prepS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                prepS.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoanDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
}
