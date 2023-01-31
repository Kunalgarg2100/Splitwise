package org.splitwise.model;

import java.util.HashMap;
import java.util.List;

public class ExactExpense implements SplitExpense {
    private final ExpenseType expenseType = ExpenseType.EXACT;
    public Expense getSplit(String paidBy, double amount, List<String> involvedUsers, List<Double> values ){
        HashMap<String,Double> splits = new HashMap<String,Double>();
        for (int i = 0; i < involvedUsers.size(); i++) {
            splits.put(involvedUsers.get(i), values.get(i));
        }
        return new Expense( expenseType, paidBy, amount, involvedUsers, splits );
    }

    public boolean validate(double amount, List<Double> values )  {
            double targetAmount = 0;
            for( double i : values ){
                targetAmount = targetAmount + i;
            }
        return targetAmount == amount;
    }
}
