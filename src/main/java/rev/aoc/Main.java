package rev.aoc;

import rev.aoc.days.one.TrebuchetPartOne;
import rev.aoc.days.one.TrebuchetPartTwo;
import rev.aoc.days.two.CubeConundrum;
import rev.aoc.days.two.CubeConundrumPartOne;

import java.util.List;

public class Main
{
    public static final void main(String[] args) {
        trySolve(new TrebuchetPartOne(List.of("1.1-trebuchet")));
        trySolve(new TrebuchetPartTwo(List.of("1.1-trebuchet")));
        trySolve(new CubeConundrumPartOne(List.of("2.1-cube-conundrum")));
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
