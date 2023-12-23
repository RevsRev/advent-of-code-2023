package rev.aoc.days.d20.broken.module.impl;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.days.d20.broken.Machine;
import rev.aoc.days.d20.broken.module.Module;

public class Sink extends Module {
  private final int output;

  public Sink(String name, int output) {
    super(name);
    this.output = output;
  }

  @Override
  public Pair<Integer, Integer> compute(int[] inputRegister, int[] outputRegister) {
    int lowCount = inputRegister[output] == Machine.LOW ? 1 : 0;
    int highCount = inputRegister[output] == Machine.HIGH ? 1 : 0;

    // turn off the initialized input bit
    outputRegister[output] = Machine.OFF;
    return Pair.of(lowCount, highCount);
  }

  @Override
  public int getState() {
    return 0;
  }
}
