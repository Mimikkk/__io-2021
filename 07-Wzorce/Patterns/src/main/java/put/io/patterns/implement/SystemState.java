package put.io.patterns.implement;

public class SystemState {
  private final double cpuLoad;
  private final double cpuTemp;
  private final double availableMemoryMb;
  private final int usbDeviceCount;

  public SystemState(double cpuLoad, double cpuTemp, double availableMemoryMb, int usbDeviceCount) {
    this.cpuLoad = cpuLoad;
    this.cpuTemp = cpuTemp;
    this.availableMemoryMb = availableMemoryMb;
    this.usbDeviceCount = usbDeviceCount;
  }

  public double getCpuLoad() {
    return cpuLoad;
  }

  public double getAvailableMemory() {
    return availableMemoryMb;
  }

  public int getUsbDeviceCount() {
    return usbDeviceCount;
  }

  public double getCpuTemp() {
    return cpuTemp;
  }
}
