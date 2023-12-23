package rev.aoc.days.d20;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;
import rev.aoc.days.d20.module.Module;

public class PulsePropagation extends AocSolution<Long> {
  public PulsePropagation(Iterable<String> resources) {
    super(resources);
  }

  @Override
  protected Long solveImpl() throws Exception {
    List<String> lines = getOneAndOnlyResourceLines();
    Pair<Map<String, String>, Map<String, Set<String>>> moduleTypesAndOutputs = parse(lines);
    Machine m =
        Machine.fromModules(moduleTypesAndOutputs.getLeft(), moduleTypesAndOutputs.getRight());
    m.cycle("broadcaster", Machine.LOW);

    int buttonPresses = 1000;
    long highCount = m.getHighCount() * buttonPresses;
    long lowCount = m.getLowCount() * buttonPresses;
    return highCount * lowCount;
  }

  private Pair<Map<String, String>, Map<String, Set<String>>> parse(List<String> lines) {
    Map<String, String> moduleTypes = new HashMap<>();
    Map<String, Set<String>> moduleOutputs = new HashMap<>();
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i).trim().replaceAll("\\s+", "");
      String[] split = line.split("->");
      Pair<String, String> nameAndType = parseNameAndType(split[0]);
      Set<String> outputs = toSet(split[1].split(","));

      // Add any sinks (i.e. outputs that don't connect to anything)
      Iterator<String> itOuts = outputs.iterator();
      while (itOuts.hasNext()) {
        String outName = itOuts.next();
        moduleTypes.putIfAbsent(outName, Module.SINK);
      }

      String moduleName = nameAndType.getLeft();
      String type = nameAndType.getRight();
      if (outputs.isEmpty()) {
        type = Module.SINK;
      }

      moduleTypes.put(moduleName, type);
      moduleOutputs.put(moduleName, outputs);
    }
    return Pair.of(moduleTypes, moduleOutputs);
  }

  private Set<String> toSet(String[] outputs) {
    Set<String> out = new HashSet();
    out.addAll(Arrays.asList(outputs));
    return out;
  }

  private Pair<String, String> parseNameAndType(String s) {
    if (s.contains(Module.FLIP_FLOP)) {
      return Pair.of(s.replace(Module.FLIP_FLOP, ""), Module.FLIP_FLOP);
    }

    if (s.contains(Module.CONJUNCTION)) {
      return Pair.of(s.replace(Module.CONJUNCTION, ""), Module.CONJUNCTION);
    }
    return Pair.of(s, Module.RELAY);
  }
}
