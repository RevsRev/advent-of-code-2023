package rev.aoc.days.d9;

public class MirageMaintenancePartTwo extends MirageMaintenance
{
    public MirageMaintenancePartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected long incrementResult(long[] sequence, long result)
    {
        return 0;
    }
}
