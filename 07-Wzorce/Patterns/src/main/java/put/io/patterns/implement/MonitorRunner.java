package put.io.patterns.implement;

import io.vavr.control.Try;
import put.io.patterns.implement.system.*;
import put.io.patterns.implement.system.observers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;
import static put.io.patterns.implement.utils.LoopRunner.*;

public class MonitorRunner {
  private static final SystemMonitor monitor = new SystemMonitor();

  static void main(String[] ignored) {
    initializeObservers();
    loop(MonitorRunner::watch);
  }

  private static void initializeObservers() {
    monitor.addSystemStateObserver(asList(
        new SystemInfoObserver(),
        new SystemGarbageCollectorObserver(),
        new SystemCoolerObserver(),
        new UsbDeviceObserver()
    ));
  }

  private static void watch() {
    monitor.probe();
    Try.run(() -> Thread.sleep(5000)).onFailure(Throwable::printStackTrace);
  }
}
