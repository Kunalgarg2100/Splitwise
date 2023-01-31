package org.splitwise.model;

import org.splitwise.exception.ExpenseTypeNotDefined;

public class ExpenseFactory {
    public ExpenseFactory() {
    }

    public static SplitExpense getExpenseClass(String s) throws ExpenseTypeNotDefined {
        if( s.equals( ExpenseType.EXACT.name() ) ){
            return new ExactExpense();
        }
        else if( s.equals( ExpenseType.EQUAL.name() ) ){
            return new EqualExpense();
        }
        else if( s.equals( ExpenseType.PERCENT.name() ) ){
            return new PercentExpense();
        }
        else {
            throw new ExpenseTypeNotDefined();
        }
    }
}
