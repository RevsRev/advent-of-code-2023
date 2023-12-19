package rev.aoc.math.topology;

import java.util.ArrayList;
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

  // shoelace formula
  public static long getArea(List<Vec2> loop) {
    List<Vec2> corners = getCorners(loop);
    List<Vec2> cornersExpanded = expandCorners(corners);
    long area = 0;
    for (int i = 0; i < cornersExpanded.size(); i++) {
      Vec2 start = cornersExpanded.get(i);
      Vec2 end = cornersExpanded.get((i + 1) % cornersExpanded.size());

      area += (start.y + end.y) * (end.x - start.x);
    }
    return area / 2;
  }

  /**
   * Expands corners of integer points to be a loop of corners that contains those integer points.
   * Method is assumed to be on a grid of points, i.e. all sides are horizontal or vertical
   */
  private static List<Vec2> expandCorners(List<Vec2> corners) {
    long windingNumber = getWindingNumber(corners);
    if (windingNumber != -1) {
      throw new RuntimeException(
          String.format("Not implemented for winding number %s", windingNumber));
    }

    List<Vec2> expandedCorners = new ArrayList<>();
    for (int i = 0; i < corners.size(); i++) {
      Vec2 start = corners.get(i);
      Vec2 mid = corners.get((i + 1) % corners.size());
      Vec2 end = corners.get((i + 2) % corners.size());

      Vec2 incoming = mid.subtract(start).signum();
      Vec2 outgoing = end.subtract(mid).signum();

      if (incoming.x == 1 && outgoing.y == 1) {
        expandedCorners.add(new Vec2(mid.x, mid.y));
      } else if (incoming.x == 1 && outgoing.y == -1) {
        expandedCorners.add(new Vec2(mid.x + 1, mid.y));
      } else if (incoming.x == -1 && outgoing.y == 1) {
        expandedCorners.add(new Vec2(mid.x, mid.y - 1));
      } else if (incoming.x == -1 && outgoing.y == -1) {
        expandedCorners.add(new Vec2(mid.x + 1, mid.y - 1));
      } else if (incoming.y == 1 && outgoing.x == 1) {
        expandedCorners.add(new Vec2(mid.x, mid.y));
      } else if (incoming.y == 1 && outgoing.x == -1) {
        expandedCorners.add(new Vec2(mid.x, mid.y - 1));
      } else if (incoming.y == -1 && outgoing.x == 1) {
        expandedCorners.add(new Vec2(mid.x + 1, mid.y));
      } else if (incoming.y == -1 && outgoing.x == -1) {
        expandedCorners.add(new Vec2(mid.x + 1, mid.y - 1));
      }
    }
    return expandedCorners;
  }

  private static List<Vec2> getCorners(List<Vec2> loop) {
    List<Vec2> corners = new ArrayList<>();
    for (int i = 0; i < loop.size(); i++) {
      Vec2 start = loop.get(i);
      Vec2 mid = loop.get((i + 1) % loop.size());
      Vec2 end = loop.get((i + 2) % loop.size());

      Vec2 incoming = mid.subtract(start);
      Vec2 outgoing = end.subtract(mid);
      if ((!Vec2.parallel(incoming, outgoing))) {
        corners.add(mid);
      }
    }
    return corners;
  }

  public static long getRightOrLeft(Vec2 orientation, Vec2 nextOrientation) {
    Vec3 o = Vec3.fromVec2(orientation);
    Vec3 n = Vec3.fromVec2(nextOrientation);
    Vec3 norm = o.cross(n);
    return Long.signum(norm.z);
  }
}
