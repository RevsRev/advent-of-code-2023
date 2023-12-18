package rev.aoc.days.d17;

public class ClumsyCruciblePartOne extends ClumsyCrucible
{
    public ClumsyCruciblePartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected int getMaximumRunLength()
    {
        return 3;
    }

    @Override
    protected int getMinimumRunLength()
    {
        return 1;
    }
}
