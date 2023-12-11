package rev.aoc.days.d10;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;
import rev.aoc.math.vec.Vec2;

import java.util.*;

public abstract class PipeMaze extends AocSolution<Long>
{
    private static final Vec2 NORTH = new Vec2(0,-1); //opposite to what you would think because arrays indexed from top to bottom
    private static final Vec2 EAST = new Vec2(1,0);
    private static final Vec2 SOUTH = new Vec2(0,1);
    private static final Vec2 WEST = new Vec2(-1,0);

    private static final Map<Character, Pair<Vec2, Vec2>> DIRECTIONS_MAP = createDirectionsMap();

    private static Map<Character, Pair<Vec2, Vec2>> createDirectionsMap()
    {
        HashMap<Character, Pair<Vec2, Vec2>> directionsMap = new HashMap<>();
        directionsMap.put('|', Pair.of(NORTH, SOUTH));
        directionsMap.put('-', Pair.of(EAST, WEST));
        directionsMap.put('L', Pair.of(NORTH, EAST));
        directionsMap.put('J', Pair.of(NORTH, WEST));
        directionsMap.put('7', Pair.of(SOUTH, WEST));
        directionsMap.put('F', Pair.of(SOUTH, EAST));

        return directionsMap;
    }

    public PipeMaze(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        char[][] mazeMap = parse(lines);
        Vec2 start = findStart(mazeMap);

        List<Vec2> loop = findLoop(mazeMap, start);
        return solveProblemForLoop(mazeMap, loop);
    }

    public abstract long solveProblemForLoop(char[][] mazeMap, List<Vec2> loop);

    private List<Vec2> findLoop(char[][] mazeMap, Vec2 start)
    {
        int height = mazeMap.length;
        int width = mazeMap[0].length;
        long maxLength = width*height;

        Iterator<Character> dirIt = DIRECTIONS_MAP.keySet().iterator();
        while (dirIt.hasNext()) {
            List<Vec2> loop = new ArrayList<>();
            long length = 0;
            char startChar = dirIt.next();
            char dirChar = startChar;
            Pair<Vec2, Vec2> startDirections = DIRECTIONS_MAP.get(dirChar);
            Vec2 position = new Vec2(start);
            Vec2 orientation = startDirections.getLeft();
            do {
                loop.add(position);
                position = position.add(orientation);
                length++;
                if (position.x <0 || position.x >= width || position.y<0 || position.y >= height) {
                    break;
                }
                if (position.equals(start)) {
                    orientation = getNextOrientation(orientation, startChar);
                    if (orientation == null) {
                        break;
                    }
                    return loop;
                }

                dirChar = mazeMap[(int)position.y][(int)position.x];
                orientation = getNextOrientation(orientation, dirChar);
                if (orientation == null) {
                    break;
                }
            } while (length < maxLength && !position.equals(start));
        }
        return new ArrayList<>();
    }

    private Vec2 getNextOrientation(Vec2 incoming, char dirChar)
    {
        if (!DIRECTIONS_MAP.containsKey(dirChar)) {
            return null;
        }
        Pair<Vec2, Vec2> directions = DIRECTIONS_MAP.get(dirChar);
        Vec2 outgoing = incoming.mult(-1l);
        if (directions.getLeft().equals(outgoing)) {
            return directions.getRight();
        }
        if (directions.getRight().equals(outgoing)) {
            return directions.getLeft();
        }
        return null;
    }

    private Vec2 findStart(char[][] mazeMap)
    {
        int height = mazeMap.length;
        int width = mazeMap[0].length;
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (mazeMap[i][j] == 'S') {
                    return new Vec2(j,i);
                }
            }
        }
        return null;
    }

    private char[][] parse(List<String> lines)
    {
        int height = lines.size();
        int width = lines.get(0).length();
        char[][] result = new char[height][width];
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                result[i][j] = lines.get(i).charAt(j);
            }
        }
        return result;
    }
}
