package pl.put.cb.api.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.cb.dao.CustomersDAO;
import pl.put.cb.model.Customer;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/customer_cards")
public class CustomerCardsController {
  private static final Logger Logger = LoggerFactory.getLogger(CustomerCardsController.class);
  private Double AverageSpendings = null;
  private Double UpdateTimestamp = null;
  private Double Threshold = null;

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public List<Integer> get(@RequestParam(value = "threshold", defaultValue = "1.0") Double threshold) {
    Logger.info("Requested customer cards with threshold value of '{}'", threshold);
    if (ShouldUpdateAverage()) AverageSpendings = AverageSpendings();
    Threshold = threshold;

    return CustomersDAO.ReadAll().stream()
      .filter(this::IsAboveThreshold)
      .map(Customer::Id).collect(toList());
  }

  private boolean IsAboveThreshold(Customer customer) {
    return AverageSpendings > 0.0 && customer.Spendings() / AverageSpendings >= Threshold;
  }
  private double AverageSpendings() {
    UpdateTimestamp = CustomersDAO.UpdateTimestamp;
    Collection<Customer> customers = CustomersDAO.ReadAll();

    return customers.isEmpty()
      ? 0.0
      : customers.stream().mapToDouble(Customer::Spendings).sum() / customers.size();
  }
  private boolean ShouldUpdateAverage() {
    return AverageSpendings == null || !UpdateTimestamp.equals(CustomersDAO.UpdateTimestamp);
  }
}


