package put.io.testing.mocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import put.io.students.fancylibrary.database.FancyDatabase;

public class ExpenseRepositoryTest {
    @Test
    void it_should_load_expenses() {
        var database = mock(FancyDatabase.class);
        when(database.queryAll()).thenReturn(Collections.emptyList());

        ExpenseRepository repository = new ExpenseRepository(database);

        repository.loadExpenses();

        InOrder inOrder = inOrder(database);
        inOrder.verify(database).connect();
        inOrder.verify(database).queryAll();
        inOrder.verify(database).close();
        inOrder.verifyNoMoreInteractions();
        assertEquals(Collections.emptyList(), repository.getExpenses());
    }

    @Test
    void it_should_save_expenses() {
        var database = mock(FancyDatabase.class);
        when(database.queryAll()).thenReturn(Collections.emptyList());
        ExpenseRepository repository = new ExpenseRepository(database);

        Collections.nCopies(5, new Expense()).forEach(repository::addExpense);
        repository.saveExpenses();

        InOrder inOrder = inOrder(database);
        inOrder.verify(database).connect();
        inOrder.verify(database, times(5)).persist(any(Expense.class));
        inOrder.verify(database).close();
    }
}
