package put.io.testing.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FailureOrErrorTest {
  @Test
  public void test1() {
    assertEquals("haha", "hihi");
  }

  @Test
  public void test2() {
    throw new RuntimeException("haha");
  }

  @Test
  public void test3() {
    try {
      test1();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
