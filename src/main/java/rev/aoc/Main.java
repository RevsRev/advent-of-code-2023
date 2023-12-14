package rev.aoc;

import rev.aoc.days.d1.TrebuchetPartOne;
import rev.aoc.days.d1.TrebuchetPartTwo;
import rev.aoc.days.d10.PipeMaze;
import rev.aoc.days.d10.PipeMazePartOne;
import rev.aoc.days.d10.PipeMazePartTwo;
import rev.aoc.days.d11.CosmicExpansion;
import rev.aoc.days.d12.HotSprings;
import rev.aoc.days.d2.CubeConundrumPartOne;
import rev.aoc.days.d2.CubeConundrumPartTwo;
import rev.aoc.days.d3.GearRatiosPartOne;
import rev.aoc.days.d3.GearRatiosPartTwo;
import rev.aoc.days.d4.AocScratchCardPartOne;
import rev.aoc.days.d4.AocScratchCardPartTwo;
import rev.aoc.days.d5.SeedAlmanacPartOne;
import rev.aoc.days.d6.BoatRacePartOne;
import rev.aoc.days.d6.BoatRacePartTwo;
import rev.aoc.days.d7.CamelCards;
import rev.aoc.days.d8.HauntedWastelandPartOne;
import rev.aoc.days.d8.HauntedWastelandPartTwo;
import rev.aoc.days.d9.MirageMaintenance;
import rev.aoc.days.d9.MirageMaintenancePartOne;
import rev.aoc.days.d9.MirageMaintenancePartTwo;

import java.util.List;

public class Main
{
    public static final void main(String[] args) {
        trySolve(new TrebuchetPartOne(List.of("1.1-trebuchet")));
        trySolve(new TrebuchetPartTwo(List.of("1.1-trebuchet")));
        trySolve(new CubeConundrumPartOne(List.of("2.1-cube-conundrum")));
        trySolve(new CubeConundrumPartTwo(List.of("2.1-cube-conundrum")));
        trySolve(new GearRatiosPartOne(List.of("3.1-gear-ratios")));
        trySolve(new GearRatiosPartTwo(List.of("3.1-gear-ratios")));
        trySolve(new AocScratchCardPartOne(List.of("4.1-scratch-cards")));
        trySolve(new AocScratchCardPartTwo(List.of("4.1-scratch-cards")));
        trySolve(new SeedAlmanacPartOne(List.of("5.1-seed-almanac")));
        //trySolve(new SeedAlmanacPartTwo(List.of("5.1-seed-almanac")));
        trySolve(new BoatRacePartOne(List.of("6.1-boat-race")));
        trySolve(new BoatRacePartTwo(List.of("6.1-boat-race")));
        trySolve(new CamelCards(List.of("7.1-camel-cards"), false));
        trySolve(new CamelCards(List.of("7.1-camel-cards"), true));
        trySolve(new HauntedWastelandPartOne(List.of("8.1-haunted-wasteland")));
        trySolve(new HauntedWastelandPartTwo(List.of("8.1-haunted-wasteland")));
        trySolve(new MirageMaintenancePartOne(List.of("9.1-mirage-maintenance")));
        trySolve(new MirageMaintenancePartTwo(List.of("9.1-mirage-maintenance")));
        trySolve(new PipeMazePartOne(List.of("10.1-pipe-maze")));
        trySolve(new PipeMazePartTwo(List.of("10.1-pipe-maze")));
        trySolve(new CosmicExpansion(List.of("11.1-cosmic-expansion"), 2l));
        trySolve(new CosmicExpansion(List.of("11.1-cosmic-expansion"), 1000000l));
        trySolve(new HotSprings(List.of("12.1-hot-springs")));
    }

    private static void trySolve(AocSolution sol) {
        try {
            Object result = sol.solve();
            System.out.println(String.format("%s:\t\t%s", sol.getClass(), result));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
