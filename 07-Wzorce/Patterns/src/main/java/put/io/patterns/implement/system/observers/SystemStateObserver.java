package put.io.patterns.implement.system.observers;

import put.io.patterns.implement.system.SystemState;

public interface SystemStateObserver {
  void update(final SystemState state);
}
