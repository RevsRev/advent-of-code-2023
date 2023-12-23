package rev.aoc.days.d20.module.impl;

import rev.aoc.days.d20.Machine;
import rev.aoc.days.d20.module.Module;

public class Conjunction extends Module {
  private final int[] input;
  private final int output;

  private int memory = Machine.LOW;

  public Conjunction(String name, int[] input, int output) {
    super(name);
    this.input = input;
    this.output = output;
  }

  @Override
  public void compute(int[] inputRegister, int[] outputRegister) {
    boolean low = false;
    boolean allInputsOff = true;
    boolean first = true;
    for (int i = 0; i < input.length; i++) {
      if (inputRegister[input[i]] == Machine.OFF) {
        continue;
      }
      allInputsOff = false;

      if (first) {
        memory = inputRegister[input[i]];
        first = false;
      }

      if (inputRegister[input[i]] == Machine.LOW) {
        low = true;
        break;
      }
    }

    if (allInputsOff) {
      outputRegister[output] = Machine.OFF;
      return;
    }

    if (low) {
      outputRegister[output] = Machine.LOW;
      return;
    }
    outputRegister[output] = Machine.HIGH;
  }

  @Override
  public int getState() {
    return memory;
  }
}
