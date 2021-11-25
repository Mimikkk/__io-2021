package put.io.patterns.implement;

import io.vavr.control.Try;
import org.jetbrains.annotations.NotNull;

public class MonitorRunner {
  static void main(String[] args) {
    watch(new SystemMonitor());
  }

  private static void watch(@NotNull SystemMonitor monitor) {
    while (true) {
      monitor.probe();
      Try.run(() -> Thread.sleep(5000)).onFailure(Throwable::printStackTrace);
    }
  }
}
