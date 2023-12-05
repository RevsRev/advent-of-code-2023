package rev.aoc.days.d5;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import rev.aoc.AocSolution;

import java.util.*;

public class SeedAlmanac extends AocSolution<Long>
{
    private final Set<Integer> seeds = new HashSet<>();
    private final RangeMap<Integer,Integer> seedToSoil =  TreeRangeMap.create();
    private final RangeMap<Integer,Integer> soilToFertilizer =  TreeRangeMap.create();
    private final RangeMap<Integer,Integer> fertilizerToWater =  TreeRangeMap.create();
    private final RangeMap<Integer,Integer> waterToLight =  TreeRangeMap.create();
    private final RangeMap<Integer,Integer> lightToTemp =  TreeRangeMap.create();
    private final RangeMap<Integer,Integer> tempToHumidty =  TreeRangeMap.create();
    private final RangeMap<Integer,Integer> humityToLocation =  TreeRangeMap.create();

    private final LinkedList<RangeMap<Integer,Integer>> mapsOrder = createMapsOrder();

    private LinkedList<RangeMap<Integer, Integer>> createMapsOrder()
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

    public SeedAlmanac(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        parseMaps(lines);

        Iterator<Integer> seedsIt = seeds.iterator();
        int lowestLocation = Integer.MAX_VALUE;
        while (seedsIt.hasNext()) {
            Integer seed = seedsIt.next();
            lowestLocation = Math.min(lowestLocation, getLocation(seed));
        }

        return (long)lowestLocation;
    }

    private Integer getLocation(Integer seed)
    {
        int key = seed;
        Iterator<RangeMap<Integer,Integer>> itMaps = mapsOrder.iterator();
        while (itMaps.hasNext()) {
            RangeMap<Integer,Integer> map = itMaps.next();
            Integer value = map.get(key);
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
            seeds.add(Integer.parseInt(strSeeds[i]));
        }

        RangeMap<Integer,Integer> currentMap = null;
        for (int i=1; i<lines.size(); i++) {
            if (lines.get(i).trim().equals("")) {
                continue; //empty line
            }
            String line = lines.get(i);
            RangeMap<Integer,Integer> newMap = checkMap(line);
            if (newMap != null) {
                currentMap = newMap;
                continue;
            }
            String[] vals = line.trim().split("\\s+");
            Integer nextMapIndex = Integer.parseInt(vals[0]);
            Integer thisMapIndex = Integer.parseInt(vals[1]);
            Integer range = Integer.parseInt(vals[2]);
            currentMap.put(Range.closed(thisMapIndex, thisMapIndex+range-1), nextMapIndex);
        }
    }

    private RangeMap<Integer,Integer> checkMap(String line) {
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
