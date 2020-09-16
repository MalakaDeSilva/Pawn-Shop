/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pawnshop.validators;

import com.pawnshop.customermgmt.model.Customer;
import com.pawnshop.itemmgmt.model.Item;
import com.pawnshop.loanmgmt.model.Loan;

/**
 *
 */
public class Validator {

    public static boolean validate(Customer customer) {

        return (!customer.getNic().isEmpty() && !customer.getName().isEmpty() && !customer.getEmail().isEmpty() && !customer.getAddress().isEmpty());
    }

    public static boolean validate(Item item) {

        return (!item.getNic().isEmpty() && !item.getItemType().isEmpty() && !item.getStatus().isEmpty() && !item.getDescription().isEmpty());
    }

    public static boolean validate(Loan loan) {

        return (loan.getBilldate() != null && loan.getDuedate() != null);
    }
}
