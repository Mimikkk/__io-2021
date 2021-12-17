package put.io.testing.mocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.database.IFancyDatabase;

public class ExpenseRepository implements IExpenseRepository {

    private List<Expense> expenses;
    private final IFancyDatabase fancyDatabase;

    public ExpenseRepository(IFancyDatabase database) {
        this.fancyDatabase = database;
        expenses = new ArrayList<>();
    }

    @Override
    public List<Expense> getExpenses() {
        return Collections.unmodifiableList(expenses);
    }

    @Override
    public List<Expense> getExpensesByCategory(String category) {
        List<Expense> filteredList = new ArrayList<Expense>();

        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category)) {
                filteredList.add(expense);
            }
        }

        return filteredList;
    }

    @Override
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    @Override
    public void deleteExpense(Expense expense) {
        expenses.remove(expense);
    }

    @Override
    public void loadExpenses() {
        fancyDatabase.connect();

        expenses = new ArrayList<Expense>(fancyDatabase.<Expense>queryAll());

        fancyDatabase.close();
    }

    @Override
    public void saveExpenses() {
        fancyDatabase.connect();

        expenses.forEach(fancyDatabase::persist);

        fancyDatabase.close();
    }
}
