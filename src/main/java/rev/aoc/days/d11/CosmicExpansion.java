package rev.aoc.days.d11;

import rev.aoc.AocSolution;
import rev.aoc.math.vec.Vec2;

import java.util.*;

public class CosmicExpansion extends AocSolution<Long>
{
    public CosmicExpansion(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        char[][] space = parse(lines);

        char[][] expanded = expand(space);

        Set<Vec2> galaxyPositions = getGalaxyPositions(expanded);

        return getSumOfShortestPairwiseDistances(galaxyPositions);
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

    private char[][] expand(char[][] space)
    {
        Set<Integer> expansionCols = getExpansionCols(space);
        Set<Integer> expansionRows = getExpansionRows(space);
        return expand(space, expansionCols, expansionRows);
    }

    private char[][] expand(char[][] space, Set<Integer> expansionCols, Set<Integer> expansionRows)
    {
        int height = space.length;
        int width = space[0].length;
        int expandedHeight = height + expansionRows.size();
        int expandedWidth = width + expansionCols.size();
        char[][] expanded = new char[expandedHeight][expandedWidth];

        int iOffset = 0;
        for (int i=0; i<height; i++) {
            if (expansionRows.contains(i)) {
                char[] expandedRow = expanded[i+iOffset];
                char[] galaxyRow = space[i];
                expandRow(expansionCols, width, expandedRow, galaxyRow);
                iOffset++;
                expanded[i+iOffset] = Arrays.copyOf(expanded[i+iOffset-1], expandedWidth);
            } else {
                char[] expandedRow = expanded[i+iOffset];
                char[] galaxyRow = space[i];
                expandRow(expansionCols, width, expandedRow, galaxyRow);
            }
        }
        return expanded;
    }

    private void expandRow(Set<Integer> expansionCols, int width, char[] expandedRow, char[] spaceRow)
    {
        int jOffset = 0;
        for (int j = 0; j< width; j++) {
            if (expansionCols.contains(j)) {
                expandedRow[j+jOffset] = spaceRow[j];
                jOffset++;
                expandedRow[j+jOffset] = spaceRow[j];
            } else {
                expandedRow[j+jOffset] = spaceRow[j];
            }
        }
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
