package put.io.patterns.implement.system.observers;

import org.jetbrains.annotations.Nullable;
import put.io.patterns.implement.system.SystemState;

public class UsbDeviceObserver implements SystemStateObserver {
  private @Nullable Integer previousUsbCount = null;

  @Override
  public void update(SystemState state) {
    if (previousUsbCount == null) previousUsbCount = state.getUsbDeviceCount();
    else if (isCountChanged(state.getUsbDeviceCount())) {
      System.out.printf("USB device count changed from %d to %d\n", previousUsbCount, state.getUsbDeviceCount());
      previousUsbCount = state.getUsbDeviceCount();
    }
  }

  private boolean isCountChanged(int usbDeviceCount) {
    assert previousUsbCount != null;
    return usbDeviceCount != previousUsbCount;
  }
}
