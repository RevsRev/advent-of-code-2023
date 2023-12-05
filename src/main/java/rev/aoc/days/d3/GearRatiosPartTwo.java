package rev.aoc.days.d3;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GearRatiosPartTwo extends GearRatios
{
    public GearRatiosPartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected long solveProblem(char[][] chars, int height, int width, Map<Pair<Integer, Integer>, Pair<Integer, Integer>> numbersMap)
    {
        long sumOfGearRatios = 0;
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                sumOfGearRatios += checkGear(chars, i, j, height, width, numbersMap);
            }
        }
        return sumOfGearRatios;
    }

    private long checkGear(char[][] chars, int row, int col, int height, int width, Map<Pair<Integer, Integer>, Pair<Integer, Integer>> numbersMap)
    {
        if (chars[row][col] != '*') {
            return 0;
        }

        int iStart = Math.max(0, row-1);
        int iEnd = Math.min(height-1, row+1);
        int jStart = Math.max(0, col-1);
        int jEnd = Math.min(width-1, col+1);

        Set<Pair<Integer,Integer>> numericalNeighbours = new HashSet<>();
        for (int i=iStart; i<=iEnd; i++) {
            for (int j=jStart; j<=jEnd; j++) {
                if (Character.isDigit(chars[i][j])) {
                    int l = j;
                    while (l>0 && Character.isDigit(chars[i][l-1])) {
                        l--;
                    }
                    numericalNeighbours.add(Pair.of(i, l));
                }
            }
        }

        if (numericalNeighbours.size() != 2) {
            return 0;
        }

        long gearRatio = 1;
        Iterator<Pair<Integer,Integer>> it = numericalNeighbours.iterator();
        while (it.hasNext()) {
            Pair<Integer,Integer> numericalNeighbour = it.next();
            Pair<Integer,Integer> numAndLength = numbersMap.get(numericalNeighbour);
            gearRatio *= numAndLength.getLeft();
        }
        return gearRatio;
    }


}
