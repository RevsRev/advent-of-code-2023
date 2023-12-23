package rev.aoc.days.d20.module.impl;

import rev.aoc.days.d20.Machine;
import rev.aoc.days.d20.module.Module;

public class FlipFlop extends Module {

  private final int input;
  private final int output;
  private boolean on = false;

  public FlipFlop(String name, int input, int output) {
    super(name);
    this.input = input;
    this.output = output;
  }

  @Override
  public void compute(int[] inputRegister, int[] outputRegister) {
    int pulse = inputRegister[input];
    if (pulse == Machine.HIGH || pulse == Machine.OFF) {
      outputRegister[output] = Machine.OFF;
      return;
    }

    if (on) {
      outputRegister[output] = Machine.LOW;
    } else {
      outputRegister[output] = Machine.HIGH;
    }
    on = !on;
  }

  @Override
  public int getState() {
    return on ? 1 : 0;
  }
}
