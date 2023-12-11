package rev.aoc;

import rev.aoc.days.d1.TrebuchetPartOne;
import rev.aoc.days.d1.TrebuchetPartTwo;
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
