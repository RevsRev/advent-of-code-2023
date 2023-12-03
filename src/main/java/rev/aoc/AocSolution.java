package rev.aoc;

import java.io.*;
import java.util.*;

public abstract class AocSolution
{
    private static final String AOC_RESOURCES_PATH = "/aoc";

    public abstract void solve() throws Exception;

    public abstract List<String> getResources();

    public Map<String,List<String>> loadResources() throws IOException
    {
        Map<String,List<String>> resources = new HashMap<>();
        Iterator<String> it = getResources().iterator();
        while (it.hasNext()) {
            String fileName = it.next();
            String resourcePath = String.format("%s/%s", AOC_RESOURCES_PATH, fileName);
            List<String> lines = readLines(resourcePath);
            resources.put(fileName, lines);
        }
        return resources;
    }

    private List<String> readLines(String resourcePath) throws IOException
    {
        List<String> lines = new ArrayList<>();

        InputStream is = null;
        try {
            is = getClass().getResourceAsStream(resourcePath);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader r = new BufferedReader(isr);
            String line = r.readLine();
            while (line != null) {
                lines.add(line);
                line = r.readLine();
            }
            return lines;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
