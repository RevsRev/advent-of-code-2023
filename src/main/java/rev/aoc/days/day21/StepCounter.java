package rev.aoc.days.day21;

import lombok.val;
import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;
import rev.aoc.util.ParseUtil;

import java.util.Arrays;
import java.util.List;

public class StepCounter extends AocSolution<Long>
{
    private final int numSteps;

    public StepCounter(Iterable<String> resources, int numSteps)
    {
        super(resources);
        this.numSteps = numSteps;
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        char[][] garden = ParseUtil.toCharArray(lines);

        int height = garden.length;
        int width = garden[0].length;

        Pair<Integer,Integer> start = null;

        int[][][] reachable = new int[height][width][numSteps+1];
        for (int i=0; i<height;i++) {
            for (int j=0;j<width;j++) {
                Arrays.fill(reachable[i][j], -2);

                if (garden[i][j] == 'S') {
                    start = Pair.of(i,j);
                }
            }
        }

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                computeReachable(garden, reachable,i,j,numSteps, start);
            }
        }

        long count = 0;
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (reachable[i][j][numSteps] == 0) {
                    count+=1;
                }
            }
        }
        return count;
    }

    private int computeReachable(char[][] garden, int[][][] reachable, int i, int j, int steps, Pair<Integer,Integer> start)
    {
        int height = garden.length;
        int width = garden[0].length;
        if (i<0 || i>=height || j<0 || j>=width) {
            return -1;
        }


        if (reachable[i][j][steps] != -2) {
            return reachable[i][j][steps];
        }

        if (garden[i][j] == '#') {
            reachable[i][j][steps] = -1;
            return reachable[i][j][steps];
        }

        if (steps == 0) {
            int val = -1;
            if (i==start.getLeft() && j==start.getRight()) {
                val = 0;
            }
            reachable[i][j][steps] = val;
            return val;
        }

        int val = -1;
        val = Math.max(val, computeReachable(garden, reachable, i-1,j,steps-1,start));
        val = Math.max(val, computeReachable(garden, reachable, i+1,j,steps-1,start));
        val = Math.max(val, computeReachable(garden, reachable, i,j-1,steps-1,start));
        val = Math.max(val, computeReachable(garden, reachable, i,j+1,steps-1,start));
        reachable[i][j][steps] = val;
        return val;
    }
}
