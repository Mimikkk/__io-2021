package put.io.patterns.implement.system.observers;

import put.io.patterns.implement.system.SystemState;

public class SystemInfoObserver implements SystemStateObserver {
  @Override
  public void update(SystemState state) {
    System.out.println("==============<| Resources |>==============");
    System.out.printf("CPU Load: %2.2f%%%n", state.getCpuLoad());
    System.out.printf("CPU temperature: %.2f C%n", state.getCpuTemp());
    System.out.printf("Available memory: %.2f MB%n", state.getAvailableMemory());
    System.out.printf("USB devices: %d%n", state.getUsbDeviceCount());
    System.out.println("============================================");
  }
}
