package org.splitwise.services;

import org.splitwise.model.Expense;
import org.splitwise.model.ExpenseType;
import org.splitwise.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseService {
    List<Expense> expenses;
    HashMap<String,User> userMap;
    HashMap<String, HashMap<String, Double> > balanceSheet;

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

    public Expense createExpense( ExpenseType expenseType, String paidBy, double amount, List<String> involvedUsers, List<Double> values ){
        HashMap<String,Double> splits = new HashMap<String,Double>();
        Expense expense = null;
        switch (expenseType) {
            case EQUAL:
                double splitAmount = amount / involvedUsers.size();
                double remAmount = 0;
                for (String u : involvedUsers) {
                    if (u != paidBy) {
                        remAmount = remAmount + splitAmount;
                        splits.put(u, splitAmount);
                    }
                }
                if (amount - remAmount > 0)
                    splits.put(paidBy, amount - remAmount);
                expense = new Expense(expenseType, paidBy, amount, involvedUsers, splits);
                expenses.add(expense);
                break;
            case EXACT:
                for (int i = 0; i < involvedUsers.size(); i++) {
                    splits.put(involvedUsers.get(i), values.get(i));
                }
                ;
                expense = new Expense(expenseType, paidBy, amount, involvedUsers, splits);
                expenses.add(expense);
                break;
            case PERCENT:
                for (int i = 0; i < involvedUsers.size(); i++) {
                    splits.put(involvedUsers.get(i), amount * values.get(i) * 0.01 );
                }
                ;
                expense = new Expense(expenseType, paidBy, amount, involvedUsers, splits);
                expenses.add(expense);
                break;
            default:
                break;
        };
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
    };

    public void showBalanceSheet( ) {
        for(Map.Entry<String, HashMap<String, Double>> balances: balanceSheet.entrySet() ) {
            String paidBy = balances.getKey();
            for (Map.Entry<String, Double> userBalance : balances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    printBalance( paidBy, userBalance.getValue(), userBalance.getKey() );
                }
            }
        }
    }

    public void printBalance( String paidBy, double amount, String paidTo ) {
        String user1 = userMap.get(paidBy).getName();
        String user2 = userMap.get(paidTo).getName();
        System.out.println(user2 + " owes " + user1 + " " + amount );
    }
}