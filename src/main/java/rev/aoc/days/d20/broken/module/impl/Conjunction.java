package rev.aoc.days.d20.broken.module.impl;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.days.d20.broken.Machine;
import rev.aoc.days.d20.broken.module.Module;

import java.util.Arrays;

public class Conjunction extends Module {
  private final int[] input;
  private final int output;

  private final int[] memory;

  public Conjunction(String name, int[] input, int output) {
    super(name);
    this.input = input;
    this.memory = Machine.getFilledRegister(input.length, Machine.LOW);
    this.output = output;
  }

  @Override
  public Pair<Integer, Integer> compute(int[] inputRegister, int[] outputRegister) {

    int lowCount = 0;
    int highCount = 0;
    for (int i = 0; i < input.length; i++) {
      int val = inputRegister[input[i]];
      if (val == Machine.OFF) {
        continue;
      }

      if (val == Machine.LOW) {
        lowCount++;
      } else if (val == Machine.HIGH) {
        highCount++;
      }

      memory[i] = val;
    }

    if (lowCount == 0 && highCount == 0) {
      outputRegister[output] = Machine.OFF;
      return Pair.of(lowCount, highCount);
    }

    for (int i = 0; i < input.length; i++) {
      if (memory[i] == Machine.LOW) {
        outputRegister[output] = Machine.HIGH;
        return Pair.of(lowCount, highCount);
      }
    }
    outputRegister[output] = Machine.LOW;
    return Pair.of(lowCount, highCount);
  }

  @Override
  public int getState() {
    return Arrays.hashCode(memory);
  }
}
