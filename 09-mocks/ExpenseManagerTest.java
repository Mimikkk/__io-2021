package put.io.testing.mocks;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import put.io.students.fancylibrary.database.FancyDatabase;
import put.io.students.fancylibrary.service.FancyService;

public class ExpenseManagerTest {
    @Test
    void it_should_calculate_total() {
        var service = mock(FancyService.class);
        var repository = mock(ExpenseRepository.class);
        var manager = new ExpenseManager(repository, service);

        when(repository.getExpenses()).thenReturn(Stream.of(10, 20, 30).map(Expense::of).collect(toList()));

        assertEquals(60, manager.calculateTotal());
    }

    @Test
    void it_should_calculate_total_for_category() {
        var service = mock(FancyService.class);
        var repository = mock(ExpenseRepository.class);
        var manager = new ExpenseManager(repository, service);

        when(repository.getExpensesByCategory(anyString())).thenReturn(List.of());

        var homeExpenses = Stream.of(10, 20, 30).map((i) -> new Expense(i, "Home")).collect(toList());
        var carExpenses = Stream.of(10, 20).map((i) -> new Expense(i, "Car")).collect(toList());
        when(repository.getExpensesByCategory("Home")).thenReturn(homeExpenses);
        when(repository.getExpensesByCategory("Car")).thenReturn(carExpenses);

        assertEquals(60, manager.calculateTotalForCategory("Home"));
        assertEquals(30, manager.calculateTotalForCategory("Car"));
        assertEquals(0, manager.calculateTotalForCategory("Sport"));
        assertEquals(0, manager.calculateTotalForCategory("Food"));
        assertEquals(0, manager.calculateTotalForCategory(anyString()));
    }

    @Test
    void it_should_calculate_total_in_dollars() throws ConnectException {
        var service = mock(FancyService.class);
        var repository = mock(ExpenseRepository.class);
        var manager = new ExpenseManager(repository, service);
        var conversionRate = 4.0;

        when(repository.getExpenses()).thenReturn(Stream.of(10, 20, 30).map(Expense::of).collect(toList()));
        when(service.convert(anyDouble(), eq("PLN"), eq("USD"))).thenAnswer(
            mock -> mock.getArgument(0, Double.class) * conversionRate
        );

        assertEquals(240, manager.calculateTotalInDollars());
    }

    @Test
    void it_should_handle_connection_exception_for_calculate_total_in_dollars() throws ConnectException {
        var service = mock(FancyService.class);
        var repository = mock(ExpenseRepository.class);
        var manager = new ExpenseManager(repository, service);
        when(service.convert(anyDouble(), eq("PLN"), eq("USD"))).thenThrow(new ConnectException());

        assertEquals(-1, manager.calculateTotalInDollars());
    }
}
