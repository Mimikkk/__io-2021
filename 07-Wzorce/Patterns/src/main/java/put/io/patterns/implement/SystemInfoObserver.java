package put.io.patterns.implement;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public class SystemInfoObserver implements SystemStateObserver {

  @Override
  public void update(SystemMonitor monitor) {
    var os = monitor.getSystemInfo().getOperatingSystem();
    var hal = monitor.getSystemInfo().getHardware();

    // Get current state of the system resources
    double cpuLoad = hal.getProcessor().getSystemLoadAverage(1)[0];
    double cpuTemp = hal.getSensors().getCpuTemperature();
    double memory = hal.getMemory().getAvailable() / 1000_000f;
    int usbDevices = hal.getUsbDevices(false).size();

    // Print information to the console
    System.out.println("============================================");
    System.out.printf("CPU Load: %2.2f%%%n", monitor.getLastState().getCpuLoad());
    System.out.printf("CPU temperature: %.2f C%n", monitor.getLastState().getCpuTemp());
    System.out.printf("Available memory: %.2f MB%n", monitor.getLastState().getAvailableMemory());
    System.out.printf("USB devices: %d%n", monitor.getLastState().getUsbDeviceCount());

    // Run garbage collector when out of memory
    if (monitor.getLastState().getAvailableMemory() < 200.00) {
      System.out.println("> Running garbage collector...");
    }

    // Increase CPU cooling if the temperature is to high
    if (monitor.getLastState().getCpuTemp() > 60.00) {
      System.out.println("> Increasing cooling of the CPU...");
    }
  }
}
