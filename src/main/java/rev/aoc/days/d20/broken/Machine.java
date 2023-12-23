package rev.aoc.days.d20.broken;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.days.d20.broken.module.Module;

/**
 * Tried to solve this problem by implementing some sort of "machine" like how a computer works.
 * Unfortunately, the ordering of the circuits matters so this doesn't actually work:(
 */
public class Machine {

  public static final int OFF = -1;
  public static final int LOW = 0;
  public static final int HIGH = 1;

  private final int[] end;

  private final int[] register;
  private final Module[] modules;

  // for counting pulses
  @Getter private int highCount = 0;
  @Getter private int lowCount = 0;

  public Machine(int[] register, Module[] modules) {
    this.register = register;
    this.end = getEmptyRegister(register.length);
    this.modules = modules;
  }

  public void modifyVal(String moduleName, int newValue) {
    for (int i = 0; i < modules.length; i++) {
      if (moduleName.equals(modules[i].name)) {
        register[i] = newValue;
        return;
      }
    }
  }

  public void cycle(String startModule, int startVal, int buttonPresses) {
    int presses = 0;
    do {
      modifyVal(startModule, startVal);
      pulse();
      presses++;
    } while (presses < buttonPresses);
  }

  private void pulse() {
    while (!Arrays.equals(register, end)) {
      pulseStep();
    }
  }

  public void pulseStep() {
    int[] output = getEmptyRegister(register.length);
    for (int i = 0; i < modules.length; i++) {
      Module module = modules[i];
      count(module.compute(register, output));
    }
    copyToRegister(output);
  }

  private void count(Pair<Integer, Integer> val) {
    lowCount += val.getLeft();
    highCount += val.getRight();
  }

  private int getState() {
    return Objects.hash(modules);
  }

  public static int[] getEmptyRegister(int len) {
    return getFilledRegister(len, OFF);
  }

  public static int[] getFilledRegister(int len, int val) {
    int[] reg = new int[len];
    Arrays.fill(reg, val);
    return reg;
  }

  private void copyToRegister(int[] output) {
    for (int i = 0; i < register.length; i++) {
      register[i] = output[i];
    }
  }

  public static Machine fromModules(
      Map<String, String> moduleTypes, Map<String, Set<String>> moduleOutputs) {
    Map<String, Integer> registerAssignment = assignRegisterIndices(moduleTypes.keySet());
    Map<String, int[]> moduleInputs = parseToInputs(registerAssignment, moduleOutputs);
    return fromModules(registerAssignment, moduleTypes, moduleInputs);
  }

  private static Map<String, int[]> parseToInputs(
      Map<String, Integer> registerAssignment, Map<String, Set<String>> outputsToInputs) {
    Map<String, Set<String>> inputsToOutputs = invertOutputsToInputs(outputsToInputs);

    Map<String, int[]> inputs = new HashMap<>();
    Iterator<String> it = inputsToOutputs.keySet().iterator();
    while (it.hasNext()) {
      String moduleName = it.next();
      Set<String> inputsNames = inputsToOutputs.get(moduleName);
      int[] in = toIndices(inputsNames, registerAssignment);
      inputs.put(moduleName, in);
    }
    return inputs;
  }

  private static Map<String, Set<String>> invertOutputsToInputs(
      Map<String, Set<String>> moduleOutputs) {
    Map<String, Set<String>> inputsNameMap = new HashMap<>();
    Iterator<String> itInputs = moduleOutputs.keySet().iterator();
    while (itInputs.hasNext()) {
      String inputName = itInputs.next();
      Set<String> outputs = moduleOutputs.get(inputName);
      Iterator<String> itOutputs = outputs.iterator();
      while (itOutputs.hasNext()) {
        String outputName = itOutputs.next();
        inputsNameMap.computeIfAbsent(outputName, k -> new HashSet<>()).add(inputName);
      }
    }
    return inputsNameMap;
  }

  private static final int[] toIndices(
      Collection<String> inputs, Map<String, Integer> registerAssignment) {
    int[] result = new int[inputs.size()];

    Iterator<String> it = inputs.iterator();
    int i = 0;
    while (it.hasNext()) {
      String inputName = it.next();
      int index = registerAssignment.get(inputName);
      result[i++] = index;
    }
    return result;
  }

  private static Map<String, Integer> assignRegisterIndices(Set<String> moduleNames) {
    Map<String, Integer> assignment = new HashMap<>();

    Iterator<String> itNames = moduleNames.iterator();
    int index = 0;
    while (itNames.hasNext()) {
      String name = itNames.next();
      assignment.put(name, index++);
    }
    return assignment;
  }

  private static Machine fromModules(
      Map<String, Integer> registerAssignment,
      Map<String, String> moduleTypes,
      Map<String, int[]> moduleInputs) {
    int[] register = new int[registerAssignment.size()];
    Module[] modules = new Module[registerAssignment.size()];
    Arrays.fill(register, Machine.OFF);

    Iterator<String> itModuleNames = moduleTypes.keySet().iterator();
    while (itModuleNames.hasNext()) {
      String name = itModuleNames.next();
      int output = registerAssignment.get(name);
      String type = moduleTypes.get(name);
      int[] inputs = moduleInputs.get(name);
      modules[output] = Module.from(name, type, inputs, output);
    }
    return new Machine(register, modules);
  }
}
