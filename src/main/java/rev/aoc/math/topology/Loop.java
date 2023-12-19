package rev.aoc.math.topology;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rev.aoc.math.vec.Vec2;
import rev.aoc.math.vec.Vec3;

public class Loop {

  public static long getWindingNumber(List<Vec2> loop) {
    long windingNumber = 0;

    Vec2 orientation = null;

    int size = loop.size();
    for (int i = 0; i <= size; i++) {
      Vec2 first = loop.get(i % size);
      Vec2 next = loop.get((i + 1) % size);
      Vec2 nextOrientation = next.add(first.mult(-1));
      if (orientation != null) {
        windingNumber += getRightOrLeft(orientation, nextOrientation);
      }
      orientation = nextOrientation;
    }

    return windingNumber / 4;
  }

  public static Set<Vec2> getInterior(List<Vec2> loop) {
    long windingNumber = Loop.getWindingNumber(loop);
    Vec3 planeNorm = new Vec3(0, 0, 1 * windingNumber);

    Set<Vec2> interiorPoints = new HashSet<>();

    Set<Vec2> loopParts = new HashSet<>();
    loopParts.addAll(loop);
    int size = loop.size();
    for (int i = 0; i <= size; i++) {
      Vec2 first = loop.get(i % size);
      Vec2 next = loop.get((i + 1) % size);
      Vec2 orientation = next.add(first.mult(-1));
      Vec3 checkVec3 = planeNorm.cross(Vec3.fromVec2(orientation));
      Vec2 checkDir = new Vec2(checkVec3.x, checkVec3.y);
      Vec2 start = first.add(checkDir);
      while (!loopParts.contains(start)) {
        interiorPoints.add(start);
        start = start.add(checkDir);
      }
      start = next.add(checkDir);
      while (!loopParts.contains(start)) {
        interiorPoints.add(start);
        start = start.add(checkDir);
      }
    }
    return interiorPoints;
  }

  public static long getRightOrLeft(Vec2 orientation, Vec2 nextOrientation) {
    Vec3 o = Vec3.fromVec2(orientation);
    Vec3 n = Vec3.fromVec2(nextOrientation);
    Vec3 norm = o.cross(n);
    return norm.z;
  }
}
