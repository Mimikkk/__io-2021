package put.io.patterns.implement.utils;

public class LoopRunner {
  public static void loop(Runnable fn) {
    while (true) fn.run();
  }

}
