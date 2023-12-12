package rev.aoc.math.vec;

public class Vec3
{
    public long x;
    public long y;
    public long z;

    public Vec3(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vec3 fromVec2(Vec2 v) {
        return new Vec3(v.x,v.y, 0);
    }

    public Vec3 cross(Vec3 other) {
        long cX = y*other.z - other.y*z;
        long cY = -(x*other.z - other.x*z);
        long cZ = x*other.y - other.x*y;
        return new Vec3(cX, cY, cZ);
    }

}
