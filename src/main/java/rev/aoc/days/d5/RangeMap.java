package rev.aoc.days.d5;

import com.google.common.collect.Range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RangeMap
{
    private final List<Long> ranges = new ArrayList<>();

    public final List<Range<Long>> resolve(Range<Long> input)
    {
        long l = input.lowerEndpoint();
        long r = input.upperEndpoint();

        int lIndex = Collections.binarySearch(ranges, l);
        int rIndex = Collections.binarySearch(ranges, r);

        if (lIndex < 0)
        {
            lIndex = -lIndex - 1;
        }
        if (rIndex < 0)
        {
            rIndex = -rIndex - 1;
        }

        if (lIndex == rIndex)
        {
            if (lIndex % 2 == 0)
            { //trivial mapping
                return List.of(Range.closed(l, r));
            }
            return List.of(Range.closed(map(ranges.get(lIndex), l), map(ranges.get(lIndex), r)));
        }

        List<Range<Long>> retval = new ArrayList<>();
        for (int i = lIndex; i < rIndex; i++)
        {
            int rangeStart = lIndex;
            int rangeEnd = rIndex;
            if (rangeStart % 2 == 0)
            { //trivial mapping up to (but not including) the next index
                retval.add(Range.closed(l, ranges.get(rIndex) - 1));
            } else
            {
                retval.add(Range.closed(map(ranges.get(lIndex), l), map(ranges.get(rIndex), ranges.get(rIndex) - 1)));
            }
        }
        return null; //TODO - Finish implementation
    }

    private final long map(long rangeRoot, long boundary)
    {
        return boundary; //TOOD - Implement
    }
}
