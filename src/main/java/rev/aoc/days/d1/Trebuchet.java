package rev.aoc.days.d1;

import rev.aoc.AocSolution;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class Trebuchet extends AocSolution<Long>
{
    public Trebuchet(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        Map<String,List<String>> resources = loadResources();

        Iterator<String> iterator = resources.keySet().iterator();
        String trebFile = iterator.next();
        List<String> trebLines = resources.get(trebFile);

        long calibrationSum = 0;
        for (int i=0; i<trebLines.size(); i++) {
            String line = trebLines.get(i);
            calibrationSum += getCalibrationNumber(line);

        }
        return calibrationSum;
    }

    private long getCalibrationNumber(String line)
    {
        line = format(line);
        StringBuilder sbConfigurationNumber = new StringBuilder();
        for (int i=0; i<line.length(); i++) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch)) {
                sbConfigurationNumber.append(ch);
                break;
            }
        }

        for (int i=line.length()-1; i>=0; i--) {
            char ch = line.charAt(i);
            if (Character.isDigit(ch)) {
                sbConfigurationNumber.append(ch);
                break;
            }
        }
        return Long.parseLong(sbConfigurationNumber.toString());
    }

    protected abstract String format(String line);
}
