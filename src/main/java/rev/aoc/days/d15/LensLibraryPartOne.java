package rev.aoc.days.d15;

import rev.aoc.AocSolution;

import java.util.List;

public class LensLibraryPartOne extends AocSolution<Long>
{
    public LensLibraryPartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        char[][] inputs = parse(lines);

        long hashTotal = 0;
        int len = inputs.length;
        for (int i=0; i<len; i++) {
            hashTotal+=calcHash(inputs[i]);
        }
        return hashTotal;
    }

    public static long calcHash(String input) {
        return calcHash(input.toCharArray());
    }
    public static long calcHash(char[] input)
    {
        long val = 0;
        int hashMultiplier = 17;
        int hashMod = 256;
        for (int i=0; i<input.length; i++) {
            val = (hashMultiplier *(val+input[i]))% hashMod;
        }
        return val;
    }

    private char[][] parse(List<String> lines)
    {
        String line = lines.get(0);
        String[] inputsStr = line.split(",");
        char[][] inputs = new char[inputsStr.length][];
        for (int i=0; i<inputsStr.length;i++) {
            inputs[i] = inputsStr[i].toCharArray();
        }
        return inputs;
    }
}
