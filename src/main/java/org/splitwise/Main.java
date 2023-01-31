package org.splitwise;

import org.splitwise.api.ExpenseManager;
import org.splitwise.exception.ExactSplitException;
import org.splitwise.exception.ExpenseTypeNotDefined;
import org.splitwise.model.ExpenseFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ExactSplitException, ExpenseTypeNotDefined {
        ExpenseManager expenseManager = ExpenseManager.getINSTANCE();
        expenseManager.addUser( "u1", "Kunal", "KG@email.com", 82734982 );
        expenseManager.addUser( "u2", "Muskan", "MJ@email.com", 82734932 );

        List<String> list = new ArrayList<>(Arrays.asList("u1", "u2"));
        List<Double> list1 = new ArrayList<>(Arrays.asList(25.0, 75.0));
        //Adding elements in the List

        expenseManager.addExpense( ExpenseFactory.getExpenseClass("PERCENT"), "u1", 300, list, list1 );
        expenseManager.showExpense();
        System.out.println("Hello world!");
    }
}