package org.splitwise.services;

import org.splitwise.model.Expense;
import org.splitwise.model.ExpenseType;
import org.splitwise.model.SplitExpense;
import org.splitwise.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseService {
    final List<Expense> expenses;
    final HashMap<String,User> userMap;
    final HashMap<String, HashMap<String, Double> > balanceSheet;

    public ExpenseService(){
        this.expenses = new ArrayList<Expense>();
        this.balanceSheet = new HashMap<String, HashMap<String, Double> >();
        this.userMap = new HashMap<String, User >();
    }

    public void addUser( String userID, String name, String email, int mobileNum ){
        User user = new User( userID, name, email, mobileNum );
        userMap.put( userID, user );
        balanceSheet.put( userID, new HashMap< String, Double>() );
        System.out.printf("User %s has been added\n", name );
    }

    public Expense createExpense( SplitExpense expenseType, String paidBy, double amount, List<String> involvedUsers, List<Double> values ){
        Expense expense = expenseType.getSplit(paidBy,amount, involvedUsers, values);
        expenses.add(expense);
        return expense;
    }

    public void updateBalanceSheet( Expense expense ) {
        HashMap<String, Double> splits = expense.getSplits();
        String paidBy = expense.getPaidBy();
        for (Map.Entry<String, Double> split : splits.entrySet()) {
            HashMap<String, Double> balances = balanceSheet.get(paidBy);
            String paidTo = split.getKey();
            double amount = split.getValue();
            if (!balances.containsKey(paidTo))
                balances.put(paidTo, 0.0);
            balances.put(paidTo, balances.get(paidTo) + amount);

            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy))
                balances.put(paidBy, 0.0);
            balances.put(paidBy, balances.get(paidBy) - amount);
        }
    }

    public void showBalanceSheet( ) {
        balanceSheet.forEach( (paidBy, balances) -> {
            balances.forEach( (paidTo, amount) -> {
                if ( amount > 0) {
                    printBalance( paidBy, amount, paidTo );
                }
            });
        });
    }

    public void printBalance( String paidBy, double amount, String paidTo ) {
        String user1 = userMap.get(paidBy).getName();
        String user2 = userMap.get(paidTo).getName();
        System.out.println(user2 + " owes " + user1 + " " + amount );
    }
}