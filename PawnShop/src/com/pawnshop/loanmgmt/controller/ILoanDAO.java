/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.loanmgmt.controller;

import com.pawnshop.loanmgmt.model.Loan;
import java.util.List;

/**
 *
 */
public interface ILoanDAO {
    void saveLoan(Loan loan);
    void deleteLoan(int loanId);
    List<Loan> getAllLoans();
    void updateLoan(Loan loan);
}
