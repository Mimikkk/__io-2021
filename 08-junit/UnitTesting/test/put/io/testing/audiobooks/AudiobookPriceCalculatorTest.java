package put.io.testing.audiobooks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AudiobookPriceCalculatorTest {
  private static final AudiobookPriceCalculator calculator = new AudiobookPriceCalculator();
  private static final double audiobookPrice = 100.0;
  private static final Audiobook audiobook = new Audiobook("mockAudiobook", audiobookPrice);

  @Test
  void test_it_should_handle_any_subscriber() {
    var standardSubscriber = new Customer("mockCustomer", Customer.LoyaltyLevel.STANDARD, true);
    var silverSubscriber = new Customer("mockCustomer", Customer.LoyaltyLevel.SILVER, true);
    var goldSubscriber = new Customer("mockCustomer", Customer.LoyaltyLevel.GOLD, true);
    var customers = List.of(standardSubscriber, silverSubscriber, goldSubscriber);

    customers.forEach(customer -> assertEquals(calculator.calculate(customer, audiobook), 0));
  }

  @Test
  void test_it_should_handle_standard_customer() {
    var standardCustomer = new Customer("mockCustomer", Customer.LoyaltyLevel.STANDARD, false);

    assertEquals(calculator.calculate(standardCustomer, audiobook), audiobookPrice);
  }

  @Test
  void test_it_should_handle_silver_customer() {
    var standardSubscriber = new Customer("mockCustomer", Customer.LoyaltyLevel.SILVER, false);

    assertEquals(calculator.calculate(standardSubscriber, audiobook), audiobookPrice * 0.9);
  }

  @Test
  void test_it_should_handle_gold_customer() {
    var standardSubscriber = new Customer("mockCustomer", Customer.LoyaltyLevel.GOLD, false);

    assertEquals(calculator.calculate(standardSubscriber, audiobook), audiobookPrice * 0.8);
  }
}