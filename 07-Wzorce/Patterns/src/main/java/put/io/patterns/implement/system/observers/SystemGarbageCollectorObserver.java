package put.io.patterns.implement.system.observers;

import put.io.patterns.implement.system.SystemState;

public class SystemGarbageCollectorObserver implements SystemStateObserver {
  @Override
  public void update(SystemState state) {
    if (shouldRunCollection(state.getAvailableMemory())) collectGarbage();
  }

  private boolean shouldRunCollection(double availableMemory) {
    return availableMemory < 200.00;
  }

  private void collectGarbage() {
    System.out.println("> Running garbage collector...");
  }
}
