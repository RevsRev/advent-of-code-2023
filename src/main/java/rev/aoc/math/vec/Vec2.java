package rev.aoc.math.vec;

import java.util.Objects;

public class Vec2
{
    public long x;
    public long y;

    public Vec2(long x, long y)
    {
        this.x = x;
        this.y = y;
    }

    public Vec2(Vec2 copy)
    {
        this.x = copy.x;
        this.y = copy.y;
    }

    public Vec2 add(Vec2 other)
    {
        return new Vec2(x + other.x, y + other.y);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2 vec2 = (Vec2) o;
        return x == vec2.x && y == vec2.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    public Vec2 mult(long factor)
    {
        return new Vec2(this.x * factor, this.y * factor);
    }
}
