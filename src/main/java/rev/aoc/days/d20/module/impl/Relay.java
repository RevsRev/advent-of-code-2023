package rev.aoc.days.d20.module.impl;

import rev.aoc.days.d20.module.Module;

public class Relay extends Module {
  private final int input;
  private final int output;

  public Relay(String name, int input, int output) {
    super(name);
    this.input = input;
    this.output = output;
  }

  @Override
  public void compute(int[] inputRegister, int[] outputRegister) {
    outputRegister[output] = inputRegister[input];
  }

  @Override
  public int getState() {
    return 0;
  }
}
