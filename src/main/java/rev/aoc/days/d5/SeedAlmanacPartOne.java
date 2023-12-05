package rev.aoc.days.d5;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import rev.aoc.AocSolution;

import java.util.*;

public class SeedAlmanacPartOne extends AocSolution<Long>
{
    private final Set<Long> seeds = new HashSet<>();
    private final RangeMap<Long,Long> seedToSoil =  TreeRangeMap.create();
    private final RangeMap<Long,Long> soilToFertilizer =  TreeRangeMap.create();
    private final RangeMap<Long,Long> fertilizerToWater =  TreeRangeMap.create();
    private final RangeMap<Long,Long> waterToLight =  TreeRangeMap.create();
    private final RangeMap<Long,Long> lightToTemp =  TreeRangeMap.create();
    private final RangeMap<Long,Long> tempToHumidty =  TreeRangeMap.create();
    private final RangeMap<Long,Long> humityToLocation =  TreeRangeMap.create();

    private final LinkedList<RangeMap<Long,Long>> mapsOrder = createMapsOrder();

    private LinkedList<RangeMap<Long, Long>> createMapsOrder()
    {
        LinkedList ordering = new LinkedList();
        ordering.add(seedToSoil);
        ordering.add(soilToFertilizer);
        ordering.add(fertilizerToWater);
        ordering.add(waterToLight);
        ordering.add(lightToTemp);
        ordering.add(tempToHumidty);
        ordering.add(humityToLocation);
        return ordering;
    }

    public SeedAlmanacPartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        parseMaps(lines);

        Iterator<Long> seedsIt = seeds.iterator();
        long lowestLocation = Long.MAX_VALUE;
        while (seedsIt.hasNext()) {
            Long seed = seedsIt.next();
            lowestLocation = Math.min(lowestLocation, getLocation(seed));
        }

        return lowestLocation;
    }

    private Long getLocation(Long seed)
    {
        long key = seed;
        Iterator<RangeMap<Long,Long>> itMaps = mapsOrder.iterator();
        while (itMaps.hasNext()) {
            RangeMap<Long,Long> map = itMaps.next();
            Long value = map.get(key);
            if (value == null) {
                continue;
            }
            key = value + (key - map.getEntry(key).getKey().lowerEndpoint());
        }
        return key;
    }

    private final void parseMaps(List<String> lines) {
        String[] strSeeds = lines.get(0).replaceAll("seeds:", "").trim().split("\\s+");
        for (int i=0; i<strSeeds.length; i++) {
            seeds.add(Long.parseLong(strSeeds[i]));
        }

        RangeMap<Long,Long> currentMap = null;
        for (int i=1; i<lines.size(); i++) {
            if (lines.get(i).trim().equals("")) {
                continue; //empty line
            }
            String line = lines.get(i);
            RangeMap<Long,Long> newMap = checkMap(line);
            if (newMap != null) {
                currentMap = newMap;
                continue;
            }
            String[] vals = line.trim().split("\\s+");
            Long nextMapIndex = Long.parseLong(vals[0]);
            Long thisMapIndex = Long.parseLong(vals[1]);
            Long range = Long.parseLong(vals[2]);
            currentMap.put(Range.closed(thisMapIndex, thisMapIndex+range-1), nextMapIndex);
        }
    }

    private RangeMap<Long,Long> checkMap(String line) {
        switch(line.trim()) {
            case "seed-to-soil map:":
                return seedToSoil;
            case "soil-to-fertilizer map:":
                return soilToFertilizer;
            case "fertilizer-to-water map:":
                return fertilizerToWater;
            case "water-to-light map:":
                return waterToLight;
            case "light-to-temperature map:":
                return lightToTemp;
            case "temperature-to-humidity map:":
                return tempToHumidty;
            case "humidity-to-location map:":
                return humityToLocation;
            default:
                return null;
        }
    }
}
