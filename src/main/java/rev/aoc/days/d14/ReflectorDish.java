package rev.aoc.days.d14;

import rev.aoc.AocSolution;

import java.util.Iterator;
import java.util.List;

public class ReflectorDish extends AocSolution<Long>
{
    public ReflectorDish(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        char[][] dish = parse(lines);

        slideNorth(dish);
        return calcLoad(dish);
    }

    private Long calcLoad(char[][] dish)
    {
        int height = dish.length;
        int width = dish[0].length;

        long totalLoad = 0;
        for (int i=0; i<height;i++) {
            int load = height-i;
            for (int j=0; j<width; j++) {
                if (dish[i][j] == 'O') {
                    totalLoad += load;
                }
            }
        }
        return totalLoad;
    }

    private void slideNorth(char[][] dish)
    {
        int height = dish.length;
        int width = dish[0].length;
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                slideNorth(dish,i,j);
            }
        }
    }

    private void slideNorth(char[][] dish, int i, int j)
    {
        if (dish[i][j] == '.' || dish[i][j] == '#') {
            return;
        }

        int slideAmount = 0;
        while (i-slideAmount-1 >=0 && dish[i-slideAmount-1][j] == '.') {
            slideAmount++;
        }
        if (slideAmount == 0) {
            return;
        }

        dish[i-slideAmount][j] = dish[i][j];
        dish[i][j] = '.';
    }

    private char[][] parse(List<String> lines) {
        char[][] dish = new char[lines.size()][lines.get(0).length()];
        for (int i=0; i<lines.size(); i++) {
            String line = lines.get(i);
            dish[i] = line.toCharArray();
        }
        return dish;
    }
}
