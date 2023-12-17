package rev.aoc.days.d16;

import rev.aoc.math.vec.Vec2;

public class FloorLavaPartTwo extends FloorLava
{
    public FloorLavaPartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected long solveProblem(char[][] mirrorContraption)
    {
        int height = mirrorContraption.length;
        int width = mirrorContraption[0].length;

        long maxEnergy = 0;
        for (int j=0; j<width;j++) {
            long topEnergy = getEnergy(mirrorContraption, height, width, new Vec2(j,0), DOWN);
            maxEnergy = Math.max(maxEnergy, topEnergy);
            long bottomEnergy = getEnergy(mirrorContraption, height, width, new Vec2(j, height-1), UP);
            maxEnergy = Math.max(maxEnergy, bottomEnergy);
        }

        for (int i=0; i<height; i++) {
            long leftEnergy = getEnergy(mirrorContraption, height, width, new Vec2(0,i), RIGHT);
            maxEnergy = Math.max(maxEnergy, leftEnergy);
            long rightEnergy = getEnergy(mirrorContraption, height, width, new Vec2(width-1,i), LEFT);
            maxEnergy = Math.max(maxEnergy, rightEnergy);
        }
        return maxEnergy;
    }
}
