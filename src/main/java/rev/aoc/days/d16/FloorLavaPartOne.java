package rev.aoc.days.d16;

import rev.aoc.math.vec.Vec2;

public class FloorLavaPartOne extends FloorLava
{
    public FloorLavaPartOne(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected long solveProblem(char[][] mirrorContraption)
    {
        int height = mirrorContraption.length;
        int width = mirrorContraption[0].length;

        Vec2 start = new Vec2(0, 0);
        Vec2 startDir = RIGHT;
        return getEnergy(mirrorContraption, height, width, start, startDir);
    }
}
