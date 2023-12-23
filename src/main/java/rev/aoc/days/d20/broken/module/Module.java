package rev.aoc.days.d20.broken.module;

import java.util.Objects;
import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.days.d20.broken.module.impl.Conjunction;
import rev.aoc.days.d20.broken.module.impl.FlipFlop;
import rev.aoc.days.d20.broken.module.impl.Relay;
import rev.aoc.days.d20.broken.module.impl.Sink;

public abstract class Module {

  public static final String CONJUNCTION = "&";
  public static final String FLIP_FLOP = "%";
  public static final String RELAY = "r";

  public static final String SINK = "s";

  public final String name;

  protected Module(String name) {
    this.name = name;
  }

  public abstract Pair<Integer, Integer> compute(int[] inputRegister, int[] outputRegister);

  public abstract int getState();

  @Override
  public int hashCode() {
    return Objects.hash(name, getState());
  }

  public static Module from(String name, String type, int[] inputs, int out) {
    if (SINK.equals(type) || inputs == null || inputs.length == 0) {
      return new Sink(name, out);
    }

    if (FLIP_FLOP.equals(type)) {
      return new FlipFlop(name, inputs, out);
    } else if (CONJUNCTION.equals(type)) {
      return new Conjunction(name, inputs, out);
    }
    return new Relay(name, inputs[0], out);
  }
}
