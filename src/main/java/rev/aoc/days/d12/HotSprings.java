package rev.aoc.days.d12;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;

import java.util.ArrayList;
import java.util.List;

public abstract class HotSprings extends AocSolution<Long>
{
    public HotSprings(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        List<Pair<char[], int[]>> rowSchematics = parse(lines);

        long totalPossibleArrangements = 0;
        for (int i = 0; i < rowSchematics.size(); i++)
        {
            totalPossibleArrangements += getNumPossibleArrangements(rowSchematics.get(i));
        }
        return totalPossibleArrangements;
    }

    public static long getNumPossibleArrangements(Pair<char[], int[]> rowSchematic)
    {
        char[] chars = rowSchematic.getLeft();
        int[] lengths = rowSchematic.getRight();

        long[][] dp_table = new long[chars.length][lengths.length];

        for (int i = 0; i < chars.length; i++)
        {
            for (int j = 0; j < lengths.length; j++)
            {
                dp_table[i][j] = -1;
            }
        }

        return getNumPossibleArrangements(chars, lengths, dp_table, chars.length - 1, lengths.length - 1);
    }

    private static long getNumPossibleArrangements(char[] chars, int[] lengths, long[][] dp_table, int charIndex, int lengthIndex)
    {
        //we have exhausted the char array
        if (charIndex < 0)
        {
            if (lengthIndex < 0)
            {
                return 1;
            }
            return 0;
        }

        //we have exhausted our lengths
        if (lengthIndex < 0)
        {
            long value = 1;
            for (int i = 0; i <= charIndex; i++)
            {
                if (chars[i] == '#')
                {
                    value = 0;
                    break;
                }
            }
            return value;
        }

        if (dp_table[charIndex][lengthIndex] != -1)
        {
            return dp_table[charIndex][lengthIndex];
        }

        if (charIndex == 0)
        {
            long length = lengths[lengthIndex];
            if (lengthIndex > 0 || length > 1)
            {
                dp_table[charIndex][lengthIndex] = 0;
                return dp_table[charIndex][lengthIndex];
            }
            char c = chars[charIndex];
            if (c == '#' || c == '?')
            {
                dp_table[charIndex][lengthIndex] = 1;
            } else
            {
                dp_table[charIndex][lengthIndex] = 0;
            }
            return dp_table[charIndex][lengthIndex];
        }

        char c = chars[charIndex];

        if (c == '.')
        {
            long value = dealWithPeriod(chars, lengths, dp_table, charIndex, lengthIndex);
            dp_table[charIndex][lengthIndex] = value;
            return value;
        }

        if (c == '#')
        {
            long value = dealWithHashTag(chars, lengths, dp_table, charIndex, lengthIndex);
            dp_table[charIndex][lengthIndex] = value;
            return value;
        }

        long periodValue = dealWithPeriod(chars, lengths, dp_table, charIndex, lengthIndex);
        long hashValue = dealWithHashTag(chars, lengths, dp_table, charIndex, lengthIndex);
        long sum = periodValue + hashValue;
        dp_table[charIndex][lengthIndex] = sum;
        return sum;
    }

    private static long dealWithPeriod(char[] chars, int[] lengths, long[][] dp_table, int charIndex, int lengthIndex)
    {
        return getNumPossibleArrangements(chars, lengths, dp_table, charIndex - 1, lengthIndex);
    }

    private static Long dealWithHashTag(char[] chars, int[] lengths, long[][] dp_table, int charIndex, int lengthIndex)
    {
        int start = charIndex - lengths[lengthIndex] + 1; //TODO prefix chars with a full stop?
        if (start < 0)
        {
            return 0l;
        }
        for (int i = start; i <= charIndex; i++)
        {
            if (chars[i] == '.')
            {
                return 0l;
            }
        }
        if (start > 0)
        {
            start--;
            if (chars[start] == '#')
            {
                return 0l;
            }
        }

        return getNumPossibleArrangements(chars, lengths, dp_table, start - 1, lengthIndex - 1);
    }

    public List<Pair<char[], int[]>> parse(List<String> lines)
    {
        List<Pair<char[], int[]>> rowSchematics = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++)
        {
            String[] rowSchematic = lines.get(i).trim().split("\\s+");
            String[] brokenCountsStr = rowSchematic[1].split(",");
            int[] brokenCounts = new int[brokenCountsStr.length];
            for (int j = 0; j < brokenCountsStr.length; j++)
            {
                brokenCounts[j] = Integer.parseInt(brokenCountsStr[j]);
            }
            Pair<char[], int[]> springsAndBrokenCounts = toSchematic(rowSchematic[0].toCharArray(), brokenCounts);
            rowSchematics.add(springsAndBrokenCounts);
        }
        return rowSchematics;
    }

    protected abstract Pair<char[], int[]> toSchematic(char[] rowSchematic, int[] brokenCounts);

}
