package put.io.patterns.implement;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.util.ArrayList;
import java.util.List;

public class SystemMonitor {
  private final List<SystemStateObserver> observers = new ArrayList<>();

  public void addSystemStateObserver(SystemStateObserver observer) {
    observers.add(observer);
  }

  public void removeSystemStateObserver(SystemStateObserver observer) {
    observers.remove(observer);
  }

  private final SystemInfo systemInfo;

  public SystemMonitor() {
    systemInfo = new SystemInfo();
  }

  public void probe() {
    var os = systemInfo.getOperatingSystem();
    var hal = systemInfo.getHardware();

    double cpuLoad = hal.getProcessor().getSystemLoadAverage(1)[0];
    double cpuTemp = hal.getSensors().getCpuTemperature();
    double memory = hal.getMemory().getAvailable() / 1000_000f;
    int usbDevices = hal.getUsbDevices(false).size();
    lastState = new SystemState(cpuLoad, cpuTemp, memory, usbDevices);
  }

  private SystemState lastState = null;

  public void notifyObservers() {
    for (SystemStateObserver observer : observers) {
      observer.update(this);
    }
  }

  public SystemInfo getSystemInfo() {
    return systemInfo;
  }

  public SystemState getLastState() {
    return lastState;
  }
}
