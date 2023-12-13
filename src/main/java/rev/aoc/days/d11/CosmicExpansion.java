package rev.aoc.days.d11;

import rev.aoc.AocSolution;
import rev.aoc.math.vec.Vec2;

import java.util.*;

public class CosmicExpansion extends AocSolution<Long>
{
    private final long expansionRate;
    public CosmicExpansion(Iterable<String> resources, long expansionRate)
    {
        super(resources);
        this.expansionRate = expansionRate;
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        char[][] space = parse(lines);

        List<Integer> expansionCols = new ArrayList(getExpansionCols(space));
        List<Integer> expansionRows = new ArrayList<>(getExpansionRows(space));

        Collections.sort(expansionRows);
        Collections.sort(expansionCols);

        Set<Vec2> galaxyPositions = getGalaxyPositions(space);
        galaxyPositions = expand(galaxyPositions, expansionRows, expansionCols);

        return getSumOfShortestPairwiseDistances(galaxyPositions);
    }

    private Set<Vec2> expand(Set<Vec2> galaxyPositions, List<Integer> expansionRows, List<Integer> expansionCols)
    {
        Set<Vec2> result = new HashSet<>();
        Iterator<Vec2> itGalaxies = galaxyPositions.iterator();
        while (itGalaxies.hasNext()) {
            Vec2 galaxy = itGalaxies.next();
            result.add(expandGalaxy(galaxy, expansionRows, expansionCols));
        }
        return result;
    }

    private Vec2 expandGalaxy(Vec2 galaxy, List<Integer> expansionRows, List<Integer> expansionCols)
    {
        int x = (int)galaxy.x;
        int y = (int)galaxy.y;

        int timesToExpandX = getTimesToExpand(expansionCols, x);
        int timesToExpandY = getTimesToExpand(expansionRows, y);

        long expansionFactor = expansionRate-1;
        return new Vec2(galaxy.x + timesToExpandX* expansionFactor, galaxy.y + timesToExpandY* expansionFactor);
    }

    private int getTimesToExpand(List<Integer> expansionRows, int x)
    {
        int index = Collections.binarySearch(expansionRows, x);
        if (index < 0) {
            index = -index - 1; //insertionPoint;
        }
        return index+1;
    }

    private long getSumOfShortestPairwiseDistances(Set<Vec2> galaxyPositions)
    {
        long sumOfShortestDistances = 0;
        Iterator<Vec2> it = galaxyPositions.iterator();
        while (it.hasNext()) {
            Vec2 galaxy = it.next();
            long distance = getSumOfDistancesToOtherGalaxies(galaxyPositions, galaxy);
            sumOfShortestDistances += distance;
        }
        return sumOfShortestDistances/2;
    }

    private long getSumOfDistancesToOtherGalaxies(Set<Vec2> galaxyPositions, Vec2 galaxy)
    {
        long sumOfDistances = 0;
        Iterator<Vec2> othersIt = galaxyPositions.iterator();
        while (othersIt.hasNext()) {
            Vec2 other = othersIt.next();
            if (galaxy.equals(other)) {
                continue;
            }
            sumOfDistances += shortestDistanceBetweenGalaxies(galaxy, other);
        }
        return sumOfDistances;
    }

    private long shortestDistanceBetweenGalaxies(Vec2 galaxy, Vec2 otherGalaxy)
    {
        return Math.abs(galaxy.x - otherGalaxy.x) + Math.abs(galaxy.y - otherGalaxy.y);
    }

    private Set<Vec2> getGalaxyPositions(char[][] space)
    {
        Set<Vec2> galaxies = new HashSet<>();
        int height = space.length;
        int width = space[0].length;
        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                if (space[i][j] == '#') {
                    galaxies.add(new Vec2(j,i));
                }
            }
        }
        return galaxies;
    }

    private Set<Integer> getExpansionRows(char[][] space)
    {
        int height = space.length;
        int width = space[0].length;

        Set<Integer> expansionRows = new HashSet<>();

        for (int i=0; i<height; i++) {
            boolean noGalaxies = true;
            for (int j=0; j<width; j++) {
                if (space[i][j] == '#') {
                    noGalaxies = false;
                    break;
                }
            }
            if (noGalaxies) {
                expansionRows.add(i);
            }
        }

        return expansionRows;
    }

    private Set<Integer> getExpansionCols(char[][] space)
    {
        int height = space.length;
        int width = space[0].length;

        Set<Integer> expansionCols = new HashSet<>();

        for (int j=0; j<width; j++) {
            boolean noGalaxies = true;
            for (int i=0; i<height; i++) {
                if (space[i][j] == '#') {
                    noGalaxies = false;
                    break;
                }
            }
            if (noGalaxies) {
                expansionCols.add(j);
            }
        }
        return expansionCols;
    }

    private char[][] parse(List<String> lines)
    {
        int height = lines.size();
        int width = lines.get(0).length();
        char[][] galaxy = new char[height][width];
        for (int i=0; i<height; i++) {
            String line = lines.get(i);
            for (int j=0; j<width; j++) {
                galaxy[i][j] = line.charAt(j);
            }
        }
        return galaxy;
    }
}
