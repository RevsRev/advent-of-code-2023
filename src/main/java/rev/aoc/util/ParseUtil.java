package rev.aoc.util;

import java.util.List;

public class ParseUtil {

    public static char[][] toCharArray(List<String> lines) {
        int size = lines.size();
        char[][] retval = new char[size][];
        for (int i=0; i<size; i++) {
            retval[i] = lines.get(i).toCharArray();
        }
        return retval;
    }
}
