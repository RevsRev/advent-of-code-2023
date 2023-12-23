package rev.aoc.days.d20.module;

import java.util.Objects;
import rev.aoc.days.d20.module.impl.Conjunction;
import rev.aoc.days.d20.module.impl.FlipFlop;
import rev.aoc.days.d20.module.impl.Relay;
import rev.aoc.days.d20.module.impl.Sink;

public abstract class Module {

  public static final String CONJUNCTION = "&";
  public static final String FLIP_FLOP = "%";
  public static final String RELAY = "r";

  public static final String SINK = "s";

  public final String name;

  protected Module(String name) {
    this.name = name;
  }

  public abstract void compute(int[] inputRegister, int[] outputRegister);

  public abstract int getState();

  @Override
  public int hashCode() {
    return Objects.hash(name, getState());
  }

  public static Module from(String name, String type, int[] inputs, int out) {
    if (SINK.equals(name) || inputs == null || inputs.length == 0) {
      return new Sink(name, out);
    }

    if (CONJUNCTION.equals(type)) {
      return new FlipFlop(name, inputs[0], out);
    } else if (FLIP_FLOP.equals(type)) {
      return new Conjunction(name, inputs, out);
    }
    return new Relay(name, inputs[0], out);
  }
}
