package put.io.patterns.implement.system;

public class SystemState {
  public double getCpuLoad() {
    return cpuLoad;
  }

  public double getAvailableMemory() {
    return memory;
  }

  public int getUsbDeviceCount() {
    return usbDeviceCount;
  }

  public double getCpuTemp() {
    return cpuTemp;
  }

  public SystemState(double cpuLoad, double cpuTemp, double memory, int usbDeviceCount) {
    this.cpuLoad = cpuLoad;
    this.cpuTemp = cpuTemp;
    this.memory = memory;
    this.usbDeviceCount = usbDeviceCount;

  }

  private final double cpuLoad;
  private final double cpuTemp;
  private final double memory;
  private final int usbDeviceCount;

}
