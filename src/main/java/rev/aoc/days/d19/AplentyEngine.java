package rev.aoc.days.d19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import org.apache.commons.lang3.tuple.Pair;

public class AplentyEngine implements Function<int[],Boolean> {

  private static final int X = 0;
  private static final int M = 1;
  private static final int A = 2;
  private static final int S = 3;

  private final Map<String, Function<int[], Boolean>> rules = new HashMap<>();
  private final Map<String, String> ruleStr = new HashMap<>();

  public AplentyEngine(List<String> rules) {
    parseRules(rules);
  }

  private void parseRules(List<String> strRules) {

    rules.put("A", v -> true);
    rules.put("R", v -> false);

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

  private Function<int[], Boolean> getRule(String strRule) {
    if (!rules.containsKey(strRule)) {
      addRule(strRule);
    }
    return rules.get(strRule);
  }

  private Function<int[], Boolean> addRule(String strRule) {
    String strSteps = ruleStr.get(strRule).replace("{","").replace("}", "");
    String[] steps = strSteps.split(",");
    List<Pair<Optional<Function<int[], Boolean>>, Function<int[], Boolean>>> stepWithPredicate =
        new ArrayList<>();
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
    rules.put(strRule, createFunctionFromSteps(stepWithPredicate));
    return rules.get(strRule);
  }

  private Function<int[], Boolean> createFunctionFromSteps(
      List<Pair<Optional<Function<int[], Boolean>>, Function<int[], Boolean>>> stepWithPredicate) {
    return new Function<int[], Boolean>() {
      @Override
      public Boolean apply(int[] ints) {
        for (int i = 0; i < stepWithPredicate.size(); i++) {
          Pair<Optional<Function<int[], Boolean>>, Function<int[], Boolean>> step =
              stepWithPredicate.get(i);
          Optional<Function<int[], Boolean>> optPredicate = step.getLeft();
          if (optPredicate.isPresent()) {
            if (optPredicate.get().apply(ints)) {
              return step.getRight().apply(ints);
            }
          } else {
            return step.getRight().apply(ints);
          }
        }
        return false;
      }
    };
  }

  private Function<int[], Boolean> parsePredicate(String s) {
    if (s.contains("<")) {
      return parseLessThan(s);
    } else {
      return parseGreaterThan(s);
    }
  }

  private Function<int[], Boolean> parseGreaterThan(String s) {
    String[] symAndVal = s.split(">");
    int sym = parseSymbol(symAndVal[0]);
    int val = Integer.parseInt(symAndVal[1]);
    return ints -> ints[sym] > val;
  }

  private Function<int[], Boolean> parseLessThan(String s) {
    String[] symAndVal = s.split("<");
    int sym = parseSymbol(symAndVal[0]);
    int val = Integer.parseInt(symAndVal[1]);
    return ints -> ints[sym] < val;
  }

  public static int[] parseRating(String ratingStr) {
    ratingStr = ratingStr.replace("{", "").replace("}", "");
    String[] ratings = ratingStr.split(",");
    int[] rating = new int[4];
    for (int i=0;i<ratings.length; i++) {
      String[] symAndVal = ratings[i].split("=");
      int sym = parseSymbol(symAndVal[0]);
      int val = Integer.parseInt(symAndVal[1]);
      rating[sym]=val;
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
  public Boolean apply(int[] ints)
  {
    return rules.get("in").apply(ints);
  }
}
