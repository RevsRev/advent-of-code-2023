package rev.aoc.days.three;

import rev.aoc.AocSolution;

import java.io.IOException;
import java.util.List;

public class GearRatios extends AocSolution<Long>
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

        long partNumbersSum = 0;

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                char c = chars[i][j];
                //TODO - implement
            }
        }

        return partNumbersSum;
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
