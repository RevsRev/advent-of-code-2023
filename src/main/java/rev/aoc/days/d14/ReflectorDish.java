package rev.aoc.days.d14;

import rev.aoc.AocSolution;

import java.util.List;

public abstract class ReflectorDish extends AocSolution<Long>
{
    public enum Direction
    {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }


    public ReflectorDish(Iterable<String> resources)
    {
        super(resources);
    }

    protected Long calcLoad(char[][] dish)
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

    protected void slide(char[][] dish, Direction direction)
    {
        switch (direction) {
            case NORTH:
                slideNorth(dish);
                return;
            case EAST:
                slideEast(dish);
                return;
            case SOUTH:
                slideSouth(dish);
                return;
            case WEST:
                slideWest(dish);
                return;
        }
    }

    private void slideNorth(char[][] dish) {
        int height = dish.length;
        int width = dish[0].length;
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                slideNorth(dish, i, j);
            }
        }
    }
    private void slideEast(char[][] dish) {
        int height = dish.length;
        int width = dish[0].length;
        for (int j=width-1; j>=0; j--) {
            for (int i=0; i<height; i++) {
                slideEast(dish, i, j);
            }
        }
    }
    private void slideSouth(char[][] dish) {
        int height = dish.length;
        int width = dish[0].length;
        for (int i=height-1; i>=0; i--) {
            for (int j=0; j<width; j++) {
                slideSouth(dish, i, j);
            }
        }
    }
    private void slideWest(char[][] dish) {
        int height = dish.length;
        int width = dish[0].length;
        for (int j=0; j<width; j++) {
            for (int i=0; i<height; i++) {
                slideWest(dish, i, j);
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

    private void slideEast(char[][] dish, int i, int j)
    {
        if (dish[i][j] == '.' || dish[i][j] == '#') {
            return;
        }

        int width = dish[0].length;
        int slideAmount = 0;
        while (j+slideAmount+1 < width && dish[i][j+slideAmount+1] == '.') {
            slideAmount++;
        }
        if (slideAmount == 0) {
            return;
        }

        dish[i][j+slideAmount] = dish[i][j];
        dish[i][j] = '.';
    }

    private void slideSouth(char[][] dish, int i, int j)
    {
        if (dish[i][j] == '.' || dish[i][j] == '#') {
            return;
        }

        int height = dish.length;
        int slideAmount = 0;
        while (i+slideAmount+1 < height && dish[i+slideAmount+1][j] == '.') {
            slideAmount++;
        }
        if (slideAmount == 0) {
            return;
        }

        dish[i+slideAmount][j] = dish[i][j];
        dish[i][j] = '.';
    }

    private void slideWest(char[][] dish, int i, int j)
    {
        if (dish[i][j] == '.' || dish[i][j] == '#') {
            return;
        }

        int slideAmount = 0;
        while (j-slideAmount-1 >=0 && dish[i][j-slideAmount-1] == '.') {
            slideAmount++;
        }
        if (slideAmount == 0) {
            return;
        }

        dish[i][j-slideAmount] = dish[i][j];
        dish[i][j] = '.';
    }

    protected char[][] parse(List<String> lines) {
        char[][] dish = new char[lines.size()][lines.get(0).length()];
        for (int i=0; i<lines.size(); i++) {
            String line = lines.get(i);
            dish[i] = line.toCharArray();
        }
        return dish;
    }
}
