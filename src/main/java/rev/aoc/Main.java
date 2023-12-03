package rev.aoc;

import rev.aoc.days.one.Trebuchet;

import java.util.List;

public class Main
{
    public static final void main(String[] args) {
        trySolve(new Trebuchet(List.of("1.1-trebuchet")));
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
