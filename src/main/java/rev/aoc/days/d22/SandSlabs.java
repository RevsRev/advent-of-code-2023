package rev.aoc.days.d22;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;
import rev.aoc.math.vec.Vec3;

import java.util.*;

public class SandSlabs extends AocSolution<Long> {
    private static final int EMPTY = 0;

    public SandSlabs(Iterable<String> resources) {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception {
        List<String> lines = getOneAndOnlyResourceLines();
        Pair<int[][][],Map<Integer,Set<Vec3>>> slabsAndCoords = parse(lines);
        int[][][] slabs = slabsAndCoords.getLeft();
        Map<Integer,Set<Vec3>> slabCoords = slabsAndCoords.getRight();

        drop(slabs,slabCoords);
        return calculateDisintegratable(slabs,slabCoords);
    }

    private long calculateDisintegratable(int[][][] slabs, Map<Integer, Set<Vec3>> slabCoords) {
        Set<Integer> considered = new HashSet<>();

        int width = slabs.length;
        int length = slabs[0].length;
        int height = slabs[0][0].length;

        long count = 0;
        for (int z=1;z<height;z++) {
            for (int x=0;x<width;x++) {
                for (int y=0;y<length;y++) {
                    int slabIndex = slabs[x][y][z];
                    if (slabIndex == EMPTY || considered.contains(slabIndex)) {
                        continue;
                    }
                    if (isDisintegratable(slabs, slabCoords, slabIndex)) {
                        count++;
                    }
                    considered.add(slabIndex);
                }
            }
        }
        return count;
    }

    private boolean isDisintegratable(int[][][] slabs, Map<Integer, Set<Vec3>> slabCoords, int slabIndex) {
        Set<Integer> topNeighbours = getTopNeighbours(slabs, slabCoords.get(slabIndex), slabIndex);
        Iterator<Integer> itTopNeighbours = topNeighbours.iterator();

        while (itTopNeighbours.hasNext()) {
            Integer topSlabIndex = itTopNeighbours.next();
            Set<Integer> supports = getBottomNeighbours(slabs, slabCoords.get(topSlabIndex), topSlabIndex);
            if (supports.size()==1) {
                return false;
            }
        }
        return true;
    }

    private Set<Integer> getTopNeighbours(int[][][] slabs, Set<Vec3> slabCoords, int slabIndex) {
        return getZNeighbours(slabs,slabCoords,slabIndex, true);
    }
    private Set<Integer> getBottomNeighbours(int[][][] slabs, Set<Vec3> slabCoords, int slabIndex) {
        return getZNeighbours(slabs,slabCoords,slabIndex, false);
    }
    private Set<Integer> getZNeighbours(int[][][] slabs, Set<Vec3> slabCoords, int slabIndex, boolean above)
    {
        int step = above == true ? 1 : -1;
        Set<Integer> neighbours = new HashSet<>();

        Iterator<Vec3> itCoords = slabCoords.iterator();
        while (itCoords.hasNext()) {
            Vec3 coord = itCoords.next();
            int x = (int)coord.x;
            int y = (int)coord.y;
            int z = (int)coord.z;
            int val = slabs[x][y][z+step];
            if (val != EMPTY && val != slabIndex) {
                neighbours.add(val);
            }
        }
        return neighbours;
    }

    private void drop(int[][][] slabs, Map<Integer, Set<Vec3>> slabCoords) {
        Set<Integer> considered = new HashSet<>();

        int width = slabs.length;
        int length = slabs[0].length;
        int height = slabs[0][0].length;

        for (int z=1;z<height;z++) {
            for (int x=0;x<width;x++) {
                for (int y=0;y<length;y++) {
                    int slabIndex = slabs[x][y][z];
                    if (slabIndex == EMPTY || considered.contains(slabIndex)) {
                        continue;
                    }
                    drop(slabs,slabCoords.get(slabIndex),slabIndex);
                    considered.add(slabIndex);
                }
            }
        }
    }

    private void drop(int[][][] slabs, Set<Vec3> slabCoords, int slabIndex) {
        int dropAmount = 0;
        boolean incrementDropAmount = true;
        while (incrementDropAmount) {
            Iterator<Vec3> itCoords = slabCoords.iterator();
            int nextDropAmount = dropAmount + 1;
            while (itCoords.hasNext()) {
                Vec3 coord = itCoords.next();
                if (coord.z - nextDropAmount == 0) {
                    incrementDropAmount = false;
                    break;
                }
                int x = (int)coord.x;
                int y = (int)coord.y;
                int z = (int)coord.z;
                int val = slabs[x][y][z-nextDropAmount];
                if (val != EMPTY && val != slabIndex) {
                    incrementDropAmount = false;
                    break;
                }
            }
            if (incrementDropAmount) {
                dropAmount++;
            }
        }

        if (dropAmount == 0) {
            return;
        }

        List<Vec3> sorted = sortByZCoord(slabCoords);
        for (int i=0; i<sorted.size();i++) {
            Vec3 coords = sorted.get(i);
            int x = (int)coords.x;
            int y = (int)coords.y;
            int z = (int)coords.z;
            slabs[x][y][z] = EMPTY;
            slabs[x][y][z-dropAmount] = slabIndex;
            coords.z = coords.z-dropAmount;
        }
    }

    List<Vec3> sortByZCoord(Set<Vec3> coords) {
        List<Vec3> sorted = new ArrayList<>(coords);
        Collections.sort(sorted, Comparator.comparingLong(o -> o.z));
        return sorted;
    }

    private Pair<int[][][],Map<Integer,Set<Vec3>>> parse(List<String> lines) {
        int[][][] slabs = initialiseEmpty(lines);
        Map<Integer, Set<Vec3>> slabCoordinates = new HashMap<>();

        int slabIndex = 1;
        for (int i=0; i<lines.size(); i++) {
            Pair<Vec3,Vec3> startAndEnd = extractCoordinates(lines.get(i));
            populateSlab(slabs,slabCoordinates,slabIndex,startAndEnd.getLeft(),startAndEnd.getRight());
            slabIndex++;
        }
        return Pair.of(slabs,slabCoordinates);
    }

    private void populateSlab(int[][][] slabs,Map<Integer,Set<Vec3>> slabCoordinates, int slabIndex, Vec3 left, Vec3 right) {
        int xMin = (int)Math.min(left.x,right.x);
        int xMax = (int)Math.max(left.x,right.x);
        int yMin = (int)Math.min(left.y,right.y);
        int yMax = (int)Math.max(left.y,right.y);
        int zMin = (int)Math.min(left.z,right.z);
        int zMax = (int)Math.max(left.z,right.z);

        for (int i=xMin;i<=xMax;i++) {
            for (int j=yMin;j<=yMax;j++) {
                for (int k=zMin;k<=zMax;k++) {
                    slabCoordinates.computeIfAbsent(slabIndex, key -> new HashSet<>()).add(new Vec3(i,j,k));
                    slabs[i][j][k] = slabIndex;
                }
            }
        }
    }

    private int[][][] initialiseEmpty(List<String> lines) {
        int minX = 0;
        int minY = 0;
        int minZ = 0;
        int maxX = 0;
        int maxY = 0;
        int maxZ = 0;

        for (int i=0; i<lines.size(); i++) {
            Pair<Vec3,Vec3> startAndEnd = extractCoordinates(lines.get(i));
            Vec3 start = startAndEnd.getLeft();
            Vec3 end = startAndEnd.getRight();
            maxX = Math.max(maxX, (int)Math.max(start.x,end.x));
            maxY = Math.max(maxY, (int)Math.max(start.y,end.y));
            maxZ = Math.max(maxZ, (int)Math.max(start.z,end.z));
        }

        int[][][] slabs = new int[maxX+1][maxY+1][maxZ+1];
        for (int i=0;i<maxX+1;i++) {
            for (int j=0;j<maxY+1;j++) {
                Arrays.fill(slabs[i][j],EMPTY);
            }
        }
        return slabs;
    }

    private Pair<Vec3,Vec3> extractCoordinates(String line) {
        String[] startAndEnd = line.split("~");
        String[] startStr = startAndEnd[0].split(",");
        String[] endStr = startAndEnd[1].split(",");

        Vec3 start = new Vec3(0,0,0);
        Vec3 end = new Vec3(0,0,0);

        start.x = Integer.parseInt(startStr[0]);
        start.y = Integer.parseInt(startStr[1]);
        start.z = Integer.parseInt(startStr[2]);

        end.x = Integer.parseInt(endStr[0]);
        end.y = Integer.parseInt(endStr[1]);
        end.z = Integer.parseInt(endStr[2]);

        return Pair.of(start, end);
    }
}
