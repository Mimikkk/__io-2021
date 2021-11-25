package put.io.patterns.implement.system;

import io.vavr.control.Try;
import org.jetbrains.annotations.NotNull;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import put.io.patterns.implement.system.observers.SystemStateObserver;

import java.util.ArrayList;
import java.util.List;

public class SystemMonitor {

  public void addSystemStateObserver(SystemStateObserver observer) {
    observers.add(observer);
  }

  public void addSystemStateObserver(List<SystemStateObserver> observers) {
    this.observers.addAll(observers);
  }

  public void removeSystemStateObserver(SystemStateObserver observer) {
    observers.remove(observer);
  }

  public void probe() {
    Try.run(this::updateState).andThen(this::notifyObservers);
  }

  private void updateState() {
    state = getCurrentState();
  }

  private void notifyObservers() {
    observers.forEach(this::observe);
  }

  public SystemMonitor() {
    @NotNull SystemInfo si = new SystemInfo();
    hal = si.getHardware();
  }

  private void observe(SystemStateObserver observer) {observer.update(state);}

  private @NotNull SystemState getCurrentState() {
    double cpuLoad = hal.getProcessor().getSystemLoadAverage(1)[0];
    double cpuTemp = hal.getSensors().getCpuTemperature();
    double memory = hal.getMemory().getAvailable() / 1000000f;
    int usbDevices = hal.getUsbDevices(false).size();

    return new SystemState(cpuLoad, cpuTemp, memory, usbDevices);
  }

  private final @NotNull List<SystemStateObserver> observers = new ArrayList<>();
  private final @NotNull HardwareAbstractionLayer hal;
  private SystemState state;
}
