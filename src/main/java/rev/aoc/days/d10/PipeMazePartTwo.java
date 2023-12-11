package rev.aoc.days.d10;

import rev.aoc.math.vec.Vec2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PipeMazePartTwo extends PipeMaze
{
    public PipeMazePartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    public long solveProblemForLoop(char[][] mazeMap, List<Vec2> loop)
    {
        Set<Vec2> loopParts = new HashSet<>();
        loopParts.addAll(loop);

        int height = mazeMap.length;
        int width = mazeMap[0].length;
        boolean[][] truthTable = new boolean[height][width];

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (i == 0 || j ==0 || i == height-1 || j == width -1) {
                    truthTable[i][j] = !loopParts.contains(new Vec2(j,i));
                } else {
                    truthTable[i][j] = false;
                }
            }
        }

        int rectangleMaxDimension = Math.max(width,height);
        int searchRange = (rectangleMaxDimension + 1) / 2;
        for (int k = 1; k <= searchRange; k++)
        {
            int iRange = height-k;
            int jRange = width-k;
            for (int i = k; i<iRange; i++)
            {
                for (int j = k; j < jRange; j++)
                {
                    //could make this faster by just going round the border (n^2 rather than n^3, but I think this will be ok)
                    if (i == k || i == iRange - 1 || j == k || j == jRange - 1)
                    {
                        Vec2 position = new Vec2(j,i);
                        if (loopParts.contains(position))
                        {
                            continue;
                        }
                        Vec2 north = position.add(NORTH);
                        Vec2 east = position.add(EAST);
                        Vec2 south = position.add(SOUTH);
                        Vec2 west = position.add(WEST);

                        propogateOutside(truthTable, position, north);
                        propogateOutside(truthTable, position, east);
                        propogateOutside(truthTable, position, south);
                        propogateOutside(truthTable, position, west);
                    } else
                    {
                        continue;
                    }
                }
            }
        }

        long interiorCount = 0;
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (!truthTable[i][j] && !loopParts.contains(new Vec2(j,i))) {
                    interiorCount++;
                }
            }
        }

        return interiorCount;
    }

    private void propogateOutside(boolean[][] truthTable, Vec2 position, Vec2 neighbour)
    {
        if (truthTable[(int) neighbour.y][(int) neighbour.x]) {
            truthTable[(int) position.y][(int) position.x] = true;
        }
    }
}
