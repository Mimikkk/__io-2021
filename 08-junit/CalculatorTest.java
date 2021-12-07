package put.io.testing.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
  private static Calculator calculator;
  private static Random random;

  @Test
  void test_it_should_add() {
    IntStream.range(0, 100).forEach((ignored) -> {
      var x = random.nextInt();
      var y = random.nextInt();

      assertEquals(x + y, calculator.add(x, y));
    });
  }

  @Test
  void test_it_should_multiply() {
    IntStream.range(0, 100).forEach((ignored) -> {
      var x = random.nextInt();
      var y = random.nextInt();

      assertEquals(x * y, calculator.multiply(x, y));
    });
  }

  @Test
  void test_it_should_throw_illegal_argument_exception() {
    var NotPositiveNumber = -1;
    var PositiveNumber = -1;

    assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(NotPositiveNumber, NotPositiveNumber));
    assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(NotPositiveNumber, 0));
    assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(0, NotPositiveNumber));
    assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(NotPositiveNumber, 0));

    assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(0, PositiveNumber));
    assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(PositiveNumber, 0));
    assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(0, PositiveNumber));

    assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(NotPositiveNumber, PositiveNumber));
    assertThrows(IllegalArgumentException.class, () -> calculator.addPositiveNumbers(PositiveNumber, NotPositiveNumber));
  }

  @BeforeAll
  static void setup() {
    calculator = new Calculator();
    random = new Random();
  }
}