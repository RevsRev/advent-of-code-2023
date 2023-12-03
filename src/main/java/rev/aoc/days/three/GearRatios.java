package rev.aoc.days.three;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;

import java.io.IOException;
import java.util.*;

public abstract class GearRatios extends AocSolution<Long>
{
    public GearRatios(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        char[][] chars = loadResourceToCharArray();
        int height = chars.length;
        int width = chars[0].length;

        //map of (i,j) coordinate of left most digit to a pair (number, numDigits)
        Map<Pair<Integer, Integer>, Pair<Integer,Integer>> numbersMap = new HashMap<>();
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                char c = chars[i][j];
                if (Character.isDigit(c)) {
                    addToNumbersMap(numbersMap, chars, height, width, i,j);
                }
            }
        }

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

    protected abstract boolean checkNumber(char[][] chars, int height, int width, Pair<Integer,Integer> coordinate, int length);

    private void addToNumbersMap(Map<Pair<Integer, Integer>, Pair<Integer,Integer>> numbersMap, char[][] chars, int height, int width, int i, int j)
    {
        int l = j;
        int r = j;
        while (l>0 && Character.isDigit(chars[i][l-1])) {
            l--;
        }
        while (r<width-1 && Character.isDigit(chars[i][r+1])) {
            r++;
        }

        StringBuilder sb = new StringBuilder();
        for (int k = l; k<=r; k++) {
            sb.append(chars[i][k]);
        }
        numbersMap.put(Pair.of(i,l), Pair.of(Integer.parseInt(sb.toString()), r-l+1));
    }


    private char[][] loadResourceToCharArray() throws IOException
    {
        List<String> lines = getOneAndOnlyResourceLines();
        int height = lines.get(0).length();
        int width = lines.size();
        char[][] chars = new char[width][height];
        for (int i = 0; i< width; i++) {
            for (int j = 0; j< height; j++) {
                chars[i][j] = lines.get(i).charAt(j);
            }
        }
        return chars;
    }
}
