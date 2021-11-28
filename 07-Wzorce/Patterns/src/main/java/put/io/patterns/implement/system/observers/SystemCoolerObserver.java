package put.io.patterns.implement.system.observers;

import put.io.patterns.implement.system.SystemState;

public class SystemCoolerObserver implements SystemStateObserver {
  @Override
  public void update(SystemState state) {
    if (shouldIncreaseCpuCooling(state.getCpuTemp())) increaseCpuCooling();
  }

  private boolean shouldIncreaseCpuCooling(double cpuTemp) {
    return cpuTemp > 60.00;
  }

  private void increaseCpuCooling() {
    System.out.println("> Increasing cooling of the CPU...");
  }
}
