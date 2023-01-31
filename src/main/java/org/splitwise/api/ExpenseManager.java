package org.splitwise.api;

import org.splitwise.exception.ExactSplitException;
import org.splitwise.model.Expense;
import org.splitwise.model.SplitExpense;
import org.splitwise.services.ExpenseService;

import java.util.List;

public class ExpenseManager {
    private static volatile ExpenseManager INSTANCE = null;
    private final ExpenseService expenseService;

    private ExpenseManager() {
        expenseService = new ExpenseService();
    }
    public static ExpenseManager getINSTANCE() {
        synchronized (ExpenseManager.class) {
            if (INSTANCE == null) {
                INSTANCE = new ExpenseManager();
            }
            return INSTANCE;
        }
    }
    public void addUser( String userID, String name, String email, int mobileNum ){
        expenseService.addUser( userID, name, email, mobileNum );
    }

    public void addExpense( SplitExpense expenseType, String paidBy, double amount, List<String> involvedUsers, List<Double> values ) throws ExactSplitException {
        if(  !expenseType.validate( amount, values ) )
            throw new ExactSplitException();
        Expense expense = expenseService.createExpense( expenseType, paidBy, amount, involvedUsers, values );
        expenseService.updateBalanceSheet( expense );
    }

    public void showExpense( ){
        expenseService.showBalanceSheet( );
    }
}
