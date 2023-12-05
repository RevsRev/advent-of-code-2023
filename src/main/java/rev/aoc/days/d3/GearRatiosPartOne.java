package rev.aoc.days.d3;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Iterator;
import java.util.Map;

public class GearRatiosPartOne extends GearRatios
{
    public GearRatiosPartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected long solveProblem(char[][] chars, int height, int width, Map<Pair<Integer, Integer>, Pair<Integer, Integer>> numbersMap)
    {
        long partNumbersSum = 0;
        //iterate through our numbers map and check if they should be included in the sum
        Iterator<Pair<Integer, Integer>> it = numbersMap.keySet().iterator();
        while (it.hasNext()) {
            Pair<Integer,Integer> coordinate = it.next();
            Pair<Integer,Integer> numAndLength = numbersMap.get(coordinate);
            if (checkNumber(chars, height, width, coordinate, numAndLength.getRight())) {
                partNumbersSum += numAndLength.getLeft();
            }
        }
        return partNumbersSum;
    }


    private boolean checkNumber(char[][] chars, int height, int width, Pair<Integer, Integer> coordinate, int length)
    {
        int row = coordinate.getLeft();
        int numColStart = coordinate.getRight();
        int numColEnd = numColStart + length - 1;

        int iStart = Math.max(0, row-1);
        int iEnd = Math.min(height-1, row+1);
        int jStart = Math.max(0, numColStart-1);
        int jEnd = Math.min(width-1, numColEnd+1);

        for (int i=iStart; i<=iEnd; i++) {
            for (int j=jStart; j<=jEnd; j++) {
                if (isSpecial(chars[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSpecial(char c) {
        if (c == '.') {
            return false;
        }
        return !Character.isDigit(c);
    }
}
