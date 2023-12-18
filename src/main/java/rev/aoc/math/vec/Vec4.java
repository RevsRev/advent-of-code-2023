package rev.aoc.math.vec;

import java.util.Objects;

public class Vec4
{
    public long w;
    public long x;
    public long y;
    public long z;

    public Vec4(long w, long x, long y, long z)
    {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec4 vec4 = (Vec4) o;
        return w == vec4.w && x == vec4.x && y == vec4.y && z == vec4.z;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(w, x, y, z);
    }
}
