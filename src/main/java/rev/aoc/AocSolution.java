package rev.aoc;

import java.util.List;

public abstract class AocSolution
{
    private static final String RESOURCES_PATH = "src/main/resources/aoc";

    public abstract void solve();

    public abstract List<String> getResources();
}
