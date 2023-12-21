package rev.aoc.days.d19;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.util.ArraysUtil;

public class BetterAplentyEngine implements Function<Integer[][], List<Integer[][]>> {

  private static final int MIN = 1;
  private static final int MAX = 4001;
  private static final int X = 0;
  private static final int M = 1;
  private static final int A = 2;
  private static final int S = 3;

  private static final Comparator<Integer> COMPARATOR = Integer::compareTo;
  private final Map<String, Function<Integer[][], List<Integer[][]>>> rules = new HashMap<>();
  private final Map<String, String> ruleStr = new HashMap<>();

  public BetterAplentyEngine(List<String> rules) {
    parseRules(rules);
  }

  private void parseRules(List<String> strRules) {

    rules.put("A", (a) -> getTrimmedInputRange(a));
    rules.put("R", (a) -> getEmptyRange());

    for (int i = 0; i < strRules.size(); i++) {
      String strRule = strRules.get(i);
      int curlyIndex = strRule.indexOf("{");
      String ruleName = strRule.substring(0, curlyIndex);
      ruleStr.put(ruleName, strRule.substring(curlyIndex));
    }

    Iterator<String> it = ruleStr.keySet().iterator();
    while (it.hasNext()) {
      getRule(it.next());
    }
  }

  private Function<Integer[][], List<Integer[][]>> getRule(String strRule) {
    if (!rules.containsKey(strRule)) {
      addRule(strRule);
    }
    return rules.get(strRule);
  }

  private Function<Integer[][], List<Integer[][]>> addRule(String strRule) {
    String strSteps = ruleStr.get(strRule).replace("{", "").replace("}", "");
    String[] steps = strSteps.split(",");
    List<
            Pair<
                Optional<Pair<Supplier<Integer[][]>, Supplier<Integer[][]>>>,
                Function<Integer[][], List<Integer[][]>>>>
        stepWithPredicate = new ArrayList<>();
    for (int i = 0; i < steps.length; i++) {
      String stepStr = steps[i];
      if (stepStr.contains(":")) {
        String[] predicateAndStep = stepStr.split(":");
        stepWithPredicate.add(
            Pair.of(
                Optional.of(parsePredicate(predicateAndStep[0])), getRule(predicateAndStep[1])));
      } else {
        stepWithPredicate.add(Pair.of(Optional.empty(), getRule(stepStr)));
      }
    }
    rules.put(strRule, createFunctionFromSteps(strRule, stepWithPredicate));
    return rules.get(strRule);
  }

  private Function<Integer[][], List<Integer[][]>> createFunctionFromSteps(
      String ruleName,
      List<
              Pair<
                  Optional<Pair<Supplier<Integer[][]>, Supplier<Integer[][]>>>,
                  Function<Integer[][], List<Integer[][]>>>>
          stepWithPredicate) {
    return new Function<Integer[][], List<Integer[][]>>() {
      private final String name = ruleName;

      @Override
      public List<Integer[][]> apply(Integer[][] input) {
        List<Integer[][]> result = new ArrayList<>();
        for (int i = 0; i < stepWithPredicate.size(); i++) {
          Pair<
                  Optional<Pair<Supplier<Integer[][]>, Supplier<Integer[][]>>>,
                  Function<Integer[][], List<Integer[][]>>>
              step = stepWithPredicate.get(i);
          Optional<Pair<Supplier<Integer[][]>, Supplier<Integer[][]>>> optPredicate =
              step.getLeft();
          if (optPredicate.isPresent()) {
            Supplier<Integer[][]> conditionSupplier = optPredicate.get().getLeft();
            Supplier<Integer[][]> complimentSupplier = optPredicate.get().getRight();
            Integer[][] conditionalInput = intersectRanges(input, conditionSupplier.get());
            result.addAll(step.getRight().apply(conditionalInput));
            input =
                intersectRanges(
                    input, complimentSupplier.get()); // cut down the input for the next step
          } else {
            result.addAll(step.getRight().apply(input));
          }
        }
        return result;
      }
    };
  }

  private static final Integer[][] intersectRanges(Integer[][] r1, Integer[][] r2) {
    Integer[][] result = new Integer[4][];
    for (int i = 0; i < 4; i++) {
      result[i] = ArraysUtil.intersectRanges(r1[i], r2[i], COMPARATOR, Integer.class);
    }
    return result;
  }

  private static final Integer[][] unionRanges(Integer[][] r1, Integer[][] r2) {
    Integer[][] result = new Integer[4][];
    for (int i = 0; i < 4; i++) {
      result[i] = ArraysUtil.unionRanges(r1[i], r2[i], COMPARATOR, Integer.class);
    }
    return result;
  }

  private List<Integer[][]> getTrimmedInputRange(Integer[][] input) {
    Integer[][] range = getInputRange();
    return List.<Integer[][]>of(intersectRanges(input, range));
  }

  public static Integer[][] getInputRange() {
    Integer[][] range = new Integer[4][2];
    for (int i = 0; i < 4; i++) {
      range[i][0] = MIN;
      range[i][1] = MAX;
    }
    return range;
  }

  private List<Integer[][]> getEmptyRange() {
    return List.<Integer[][]>of(new Integer[4][0]);
  }

  private Pair<Supplier<Integer[][]>, Supplier<Integer[][]>> parsePredicate(String s) {
    if (s.contains("<")) {
      return parseLessThan(s);
    } else {
      return parseGreaterThan(s);
    }
  }

  private Pair<Supplier<Integer[][]>, Supplier<Integer[][]>> parseGreaterThan(String s) {
    String[] symAndVal = s.split(">");
    int sym = parseSymbol(symAndVal[0]);
    int val = Integer.parseInt(symAndVal[1]);

    Integer[][] result = new Integer[4][2];
    Integer[][] resultCompliment = new Integer[4][2];
    for (int i = 0; i < 4; i++) {
      if (i != sym) {
        result[i][0] = MIN;
      } else {
        result[i][0] = val + 1;
      }
      result[i][1] = MAX;

      resultCompliment[i][0] = MIN;
      if (i != sym) {
        resultCompliment[i][1] = MAX;
      } else {
        resultCompliment[i][1] = val + 1;
      }
    }
    return Pair.of(() -> result, () -> resultCompliment);
  }

  private Pair<Supplier<Integer[][]>, Supplier<Integer[][]>> parseLessThan(String s) {
    String[] symAndVal = s.split("<");
    int sym = parseSymbol(symAndVal[0]);
    int val = Integer.parseInt(symAndVal[1]);

    Integer[][] result = new Integer[4][2];
    Integer[][] resultCompliment = new Integer[4][2];
    for (int i = 0; i < 4; i++) {
      result[i][0] = MIN;
      if (i != sym) {
        result[i][1] = MAX;
      } else {
        result[i][1] = val;
      }

      if (i != sym) {
        resultCompliment[i][0] = MIN;
      } else {
        resultCompliment[i][0] = val;
      }
      resultCompliment[i][1] = MAX;
    }
    return Pair.of(() -> result, () -> resultCompliment);
  }

  public static int[] parseRating(String ratingStr) {
    ratingStr = ratingStr.replace("{", "").replace("}", "");
    String[] ratings = ratingStr.split(",");
    int[] rating = new int[4];
    for (int i = 0; i < ratings.length; i++) {
      String[] symAndVal = ratings[i].split("=");
      int sym = parseSymbol(symAndVal[0]);
      int val = Integer.parseInt(symAndVal[1]);
      rating[sym] = val;
    }
    return rating;
  }

  private static int parseSymbol(String s) {
    switch (s) {
      case "x":
        return X;
      case "m":
        return M;
      case "a":
        return A;
      default:
        return S;
    }
  }

  @Override
  public List<Integer[][]> apply(Integer[][] integers) {
    return rules.get("in").apply(integers);
  }
}
