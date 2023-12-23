package rev.aoc.days.d20.broken.module.impl;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.days.d20.broken.Machine;
import rev.aoc.days.d20.broken.module.Module;

public class FlipFlop extends Module {

  private final int[] inputs;
  private final int output;
  private boolean on = false;

  public FlipFlop(String name, int[] inputs, int output) {
    super(name);
    this.inputs = inputs;
    this.output = output;
  }

  @Override
  public Pair<Integer, Integer> compute(int[] inputRegister, int[] outputRegister) {

    int state = Machine.OFF;
    for (int i = 0; i < inputs.length; i++) {
      int pulse = inputRegister[inputs[i]];
      if (pulse == Machine.OFF) {
        continue;
      }
      if (state != Machine.OFF && pulse != state) {
        throw new RuntimeException("Order matters! Relays have different inputs.");
      }
      state = pulse;
    }

    for (int i = 0; i < inputs.length; i++) {
      int pulse = inputRegister[inputs[i]];

      if (pulse == Machine.OFF) {
        continue;
      }

      if (pulse == Machine.HIGH) {
        outputRegister[output] = Machine.OFF;
        return Pair.of(0, 1);
      }

      int writeVal = Machine.HIGH;
      if (on) {
        writeVal = Machine.LOW;
      }

      if (outputRegister[output] != Machine.OFF && outputRegister[output] != writeVal) {
        throw new RuntimeException("Order matters! Output Register has already been written to.");
      }

      outputRegister[output] = writeVal;
      on = !on;
      return Pair.of(1, 0);
    }
    outputRegister[output] = Machine.OFF;
    return Pair.of(0, 0);
  }

  @Override
  public int getState() {
    return on ? 1 : 0;
  }
}
