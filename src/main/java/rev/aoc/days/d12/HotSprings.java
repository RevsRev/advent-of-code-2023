package rev.aoc.days.d12;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotSprings extends AocSolution<Long>
{
    public HotSprings(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        List<Pair<char[],int[]>> rowSchematics = parse(lines);

        long totalPossibleArrangements = 0;
        for (int i=0; i<rowSchematics.size(); i++) {
            totalPossibleArrangements += getNumPossibleArrangements(rowSchematics.get(i));
        }
        return totalPossibleArrangements;
    }

    private long getNumPossibleArrangements(Pair<char[], int[]> rowSchematic)
    {
        rowSchematic = trim(rowSchematic);

        if (rowSchematic == null) {
            return 0;
        }

        //After trimming, the row schematic springs will start and end with ? (or be empty)
        char[] springs = rowSchematic.getLeft();
        int[] brokenLengths = rowSchematic.getRight();

        if (springs.length == 0) {
            return brokenLengths.length == 0 ? 1 : 0; //if we still have some broken lengths at this point then the solution is invalid
        }

        long numPossibleArrangements = 0;
        if (springs.length == 1) {
            springs[0] = '.';
            numPossibleArrangements += getNumPossibleArrangements(Pair.of(springs,brokenLengths));
            springs[0] = '#';
            numPossibleArrangements += getNumPossibleArrangements(Pair.of(springs,brokenLengths));
            return numPossibleArrangements;
        }

        char[] vals = new char[]{'.', '#'};
        for (int i=0; i<vals.length; i++) {
            for (int j=0; j<vals.length; j++) {
                springs[0] = vals[i];
                springs[springs.length-1] = vals[j];
                numPossibleArrangements += getNumPossibleArrangements(Pair.of(springs,brokenLengths));
            }
        }
        return numPossibleArrangements;
    }

    public Pair<char[],int[]> trim(Pair<char[], int[]> rowSchematic)
    {
        int springsSize;
        do {
            springsSize = rowSchematic.getLeft().length;
            char[] springs = trimOperationalSprings(rowSchematic.getLeft());
            int[] brokenCounts = rowSchematic.getRight();

            rowSchematic = trimBrokenSprings(springs, brokenCounts);
        } while (rowSchematic != null && springsSize > rowSchematic.getLeft().length);
        return rowSchematic;
    }

    private Pair<char[],int[]> trimBrokenSprings(char[] springs, int[] brokenCounts)
    {
        if (brokenCounts.length == 0) {
            for (int i=0; i<springs.length; i++) {
                if (springs[i] == '#') {
                    return null;
                }
            }
            return Pair.of(springs,brokenCounts);
        }

        int start = 0;
        if (springs.length > 0 && springs[0] == '#') {
            start = brokenCounts[0];
            if (start>=springs.length) {
                return null;
            }
            for (int j=0; j<start; j++) {
                if (springs[j] == '.') {
                    return null; //This is an invalid solution
                }
            }

            if (start<springs.length-1) {
                start++;
                if (springs[start] == '#') {
                    return null;
                }
            }
            brokenCounts = Arrays.copyOfRange(brokenCounts,1,brokenCounts.length);
        }
        springs = Arrays.copyOfRange(springs,start,springs.length);

        if (brokenCounts.length == 0) {
            for (int i=0; i<springs.length; i++) {
                if (springs[i] == '#') {
                    return null;
                }
            }
            return Pair.of(springs,brokenCounts);
        }

        int end = springs.length;
        if (springs.length > 0 && springs[springs.length-1] == '#') {
            int brokenLength = brokenCounts[brokenCounts.length - 1];
            end -= brokenLength;
            if (end<0) {
                return null;
            }
            for (int j=springs.length-1; j>=end; j--) {
                if (springs[j] == '.') {
                    return null; //This is an invalid solution
                }
            }
            if (end>0) {
                end--;
                if (springs[end] == '#') {
                    return null;
                }
            }
            brokenCounts = Arrays.copyOfRange(brokenCounts, 0, brokenCounts.length-1);
        }
        springs = Arrays.copyOfRange(springs,0,end);
        return Pair.of(springs, brokenCounts);
    }

    private char[] trimOperationalSprings(char[] springs)
    {
        int start = 0;
        while (start<springs.length && springs[start] == '.') {
            start++;
        }
        springs = Arrays.copyOfRange(springs, start, springs.length);

        int end = springs.length;
        while (end>0 && springs[end-1] == '.') {
            end--;
        }
        return Arrays.copyOfRange(springs, 0, end);
    }

    private List<Pair<char[], int[]>> parse(List<String> lines)
    {
        List<Pair<char[],int[]>> rowSchematics = new ArrayList<>();
        for (int i=0; i<lines.size(); i++) {
            String[] rowSchematic = lines.get(i).trim().split("\\s+");
            String[] brokenCountsStr = rowSchematic[1].split(",");
            int[] brokenCounts = new int[brokenCountsStr.length];
            for (int j=0; j<brokenCountsStr.length; j++) {
                brokenCounts[j] = Integer.parseInt(brokenCountsStr[j]);
            }
            rowSchematics.add(Pair.of(rowSchematic[0].toCharArray(), brokenCounts));
        }
        return rowSchematics;
    }
}
