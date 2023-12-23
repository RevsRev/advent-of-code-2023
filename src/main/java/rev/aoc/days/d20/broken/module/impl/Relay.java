package rev.aoc.days.d20.broken.module.impl;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.days.d20.broken.Machine;
import rev.aoc.days.d20.broken.module.Module;

public class Relay extends Module {
  private final int input;
  private final int output;

  public Relay(String name, int input, int output) {
    super(name);
    this.input = input;
    this.output = output;
  }

  @Override
  public Pair<Integer, Integer> compute(int[] inputRegister, int[] outputRegister) {
    int pulse = inputRegister[input];
    int lowCount = pulse == Machine.LOW ? 1 : 0;
    int highCount = pulse == Machine.HIGH ? 1 : 0;

    outputRegister[output] = pulse;
    return Pair.of(lowCount, highCount);
  }

  @Override
  public int getState() {
    return 0;
  }
}
