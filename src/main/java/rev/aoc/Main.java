package rev.aoc;

import rev.aoc.days.four.AocScratchCard;
import rev.aoc.days.four.AocScratchCardPartOne;
import rev.aoc.days.four.ScratchCard;
import rev.aoc.days.one.TrebuchetPartOne;
import rev.aoc.days.one.TrebuchetPartTwo;
import rev.aoc.days.three.GearRatios;
import rev.aoc.days.three.GearRatiosPartOne;
import rev.aoc.days.three.GearRatiosPartTwo;
import rev.aoc.days.two.CubeConundrumPartOne;
import rev.aoc.days.two.CubeConundrumPartTwo;

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
