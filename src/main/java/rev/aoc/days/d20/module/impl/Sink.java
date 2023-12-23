package rev.aoc.days.d20.module.impl;

import rev.aoc.days.d20.Machine;
import rev.aoc.days.d20.module.Module;

public class Sink extends Module {
  private final int output;

  public Sink(String name, int output) {
    super(name);
    this.output = output;
  }

  @Override
  public void compute(int[] inputRegister, int[] outputRegister) {
    // turn off the initialized input bit
    outputRegister[output] = Machine.OFF;
  }

  @Override
  public int getState() {
    return 0;
  }
}
