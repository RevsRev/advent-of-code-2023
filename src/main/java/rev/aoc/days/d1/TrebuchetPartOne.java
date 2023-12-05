package rev.aoc.days.d1;

public class TrebuchetPartOne extends Trebuchet
{
    public TrebuchetPartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected String format(String line)
    {
        return line;
    }
}
