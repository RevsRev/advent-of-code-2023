package rev.aoc;

import java.io.*;
import java.util.*;

public abstract class AocSolution<R>
{
    private static final String AOC_RESOURCES_PATH = "/aoc";

    private final Iterable<String> resources;

    public AocSolution(Iterable<String> resources) {
        this.resources = resources;
    }

    public final R solve() throws Exception {
        try {
            return solveImpl();
        } catch (Exception e) {
            System.out.println(String.format("Could not solve aoc problem ", getClass().getName()));
            throw e;
        }
    }

    protected abstract R solveImpl() throws Exception;

    public List<String> getOneAndOnlyResourceLines() throws IOException
    {
        Map<String,List<String>> resources = loadResources();

        Iterator<String> iterator = resources.keySet().iterator();
        String oneAndOnlyFile = iterator.next();
        return resources.get(oneAndOnlyFile);
    }

    public Map<String,List<String>> loadResources() throws IOException
    {
        Map<String,List<String>> retval = new HashMap<>();
        Iterator<String> it = resources.iterator();
        while (it.hasNext()) {
            String fileName = it.next();
            String resourcePath = String.format("%s/%s", AOC_RESOURCES_PATH, fileName);
            List<String> lines = readLines(resourcePath);
            retval.put(fileName, lines);
        }
        return retval;
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
