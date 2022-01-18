package pl.put.cb.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.cb.model.Customer;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.Collections.*;

/**
 * This is a naive implementation of a "database".
 */
public class CustomersDAO {
  public static Double UpdateTimestamp;
  public static Collection<Customer> ReadAll() {
    return unmodifiableMap(customers).values();
  }
  public Customer ReadById(int id) {
    return customers.getOrDefault(id, null);
  }

  public static final CustomersDAO Instance = new CustomersDAO();
  private static final Logger Logger = LoggerFactory.getLogger(CustomersDAO.class);
  private static final Map<Integer, Customer> customers = new HashMap<>();
  static {
    IntStream.range(0, 10000).forEach(i -> customers.put(i, new Customer(i, Math.random() * 10000)));
    UpdateTimestamp = (double) System.currentTimeMillis();
    Logger.debug("Database created");
  }
}
