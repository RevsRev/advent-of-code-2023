package rev.aoc.days.two;

import rev.aoc.AocSolution;

import java.util.*;

public class CubeConundrum extends AocSolution<Long>
{
    private static final int NUM_REDS = 12;
    private static final int NUM_GREENS = 13;
    private static final int NUM_BLUES = 14;

    public CubeConundrum(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        Map<String, List<String>> resourcesMap = loadResources();
        String resourceFile = resourcesMap.keySet().iterator().next();
        List<String> lines = resourcesMap.get(resourceFile);

        Map<Integer, List<Handful>> gameHandfulMap = parseToGameMap(lines);

        Iterator<Integer> itGameNumbers = gameHandfulMap.keySet().iterator();
        long result = 0;
        while (itGameNumbers.hasNext()) {
            int gameNumber = itGameNumbers.next();
            List<Handful> handfuls = gameHandfulMap.get(gameNumber);
            if (checkHandfuls(handfuls)) {
                result += gameNumber;
            }
        }
        return result;
    }

    private boolean checkHandfuls(List<Handful> handfuls) {
        for (int i=0; i<handfuls.size(); i++) {
            Handful handful = handfuls.get(i);
            if (handful.red > NUM_REDS) {
                return false;
            }
            if (handful.blue > NUM_BLUES) {
                return false;
            }
            if (handful.green > NUM_GREENS) {
                return false;
            }
        }
        return true;
    }

    Map<Integer,List<Handful>> parseToGameMap(List<String> lines) {
        Map<Integer,List<Handful>> retval = new HashMap<>();
        Iterator<String> itLines = lines.iterator();
        while (itLines.hasNext()) {
            String line = itLines.next();
            parseFromLine(retval, line);
        }
        return retval;
    }

    private void parseFromLine(Map<Integer, List<Handful>> retval, String line)
    {
        String[] gameAndValues = line.split(":");
        String game = gameAndValues[0];
        String[] gameHandfuls = gameAndValues[1].split(";");

        int gameNumber = Integer.parseInt(game.replaceFirst("Game ", ""));
        List<Handful> handfuls = new ArrayList<>();
        for (int i=0; i<gameHandfuls.length; i++) {
            String gameHandful = gameHandfuls[i];
            String[] colors = gameHandful.split(",");
            handfuls.add(Handful.from(colors));
        }
        retval.put(gameNumber, handfuls);
    }
}
