package org.splitwise.api;

import org.splitwise.exception.ExactSplitException;
import org.splitwise.model.Expense;
import org.splitwise.model.ExpenseType;
import org.splitwise.services.ExpenseService;

import java.util.List;

public class ExpenseManager {
    private volatile static ExpenseManager INSTANCE = null;
    private static ExpenseService expenseService;

    private ExpenseManager() {
        this.expenseService = new ExpenseService();
    }
    public static ExpenseManager getINSTANCE() {
        if( INSTANCE == null ){
            INSTANCE = new ExpenseManager( );
        }
        return INSTANCE;
    }
    public void addUser( String userID, String name, String email, int mobileNum ){
        expenseService.addUser( userID, name, email, mobileNum );
    }

    public void addExpense(ExpenseType expenseType, String paidBy, double amount, List<String> involvedUsers, List<Double> values ) throws ExactSplitException {
        if( expenseType == ExpenseType.EXACT ){
            double targetAmount = 0;
            for( double i : values ){
                targetAmount = targetAmount + i;
            }
            if( targetAmount !=  amount )
                throw new ExactSplitException();
        }

        Expense expense = expenseService.createExpense( expenseType, paidBy, amount, involvedUsers, values );
        expenseService.updateBalanceSheet( expense );
    }

    public void showExpense( ){
        expenseService.showBalanceSheet( );
    }
}
