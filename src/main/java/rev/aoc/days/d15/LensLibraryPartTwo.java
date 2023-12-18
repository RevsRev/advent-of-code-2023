package rev.aoc.days.d15;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LensLibraryPartTwo extends AocSolution<Long>
{
    public LensLibraryPartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        List<Pair<String, Integer>> labelsAndValues = parse(lines);

        LinkedList<Pair<String, Integer>>[] boxes = createEmptyBoxes();

        for (int i = 0; i < labelsAndValues.size(); i++)
        {
            Pair<String, Integer> labelAndValue = labelsAndValues.get(i);
            String label = labelAndValue.getLeft();
            int boxIndex = (int) LensLibraryPartOne.calcHash(label);
            int lens = labelAndValue.getRight();

            LinkedList<Pair<String, Integer>> box = boxes[boxIndex];
            int labelIndex = indexOf(label, box);

            if (lens < 0)
            {
                if (labelIndex >= 0)
                {
                    box.remove(labelIndex);
                }
            } else
            {
                if (labelIndex < 0)
                {
                    box.addLast(Pair.of(label, lens));
                } else
                {
                    box.remove(labelIndex);
                    box.add(labelIndex, Pair.of(label, lens));
                }
            }
        }

        long focusingTotal = 0;
        for (int i = 0; i < boxes.length; i++)
        {
            int boxFactor = i + 1;
            LinkedList<Pair<String, Integer>> box = boxes[i];
            focusingTotal += boxFactor * focusPower(box);
        }
        return focusingTotal;
    }

    private long focusPower(LinkedList<Pair<String, Integer>> box)
    {
        long focusPower = 0;

        long multiplier = 1;
        Iterator<Pair<String, Integer>> itBox = box.iterator();
        while (itBox.hasNext())
        {
            Pair<String, Integer> node = itBox.next();
            focusPower += multiplier * node.getRight();
            multiplier++;
        }
        return focusPower;
    }

    private static int indexOf(String label, LinkedList<Pair<String, Integer>> box)
    {
        int index = -1;
        Iterator<Pair<String, Integer>> itBox = box.iterator();
        while (itBox.hasNext())
        {
            index++;
            Pair<String, Integer> node = itBox.next();
            if (node.getLeft().equals(label))
            {
                return index;
            }
        }
        return -1;
    }

    private LinkedList<Pair<String, Integer>>[] createEmptyBoxes()
    {
        LinkedList<Pair<String, Integer>>[] boxes = new LinkedList[256];
        for (int i = 0; i < 256; i++)
        {
            boxes[i] = new LinkedList<>();
        }
        return boxes;
    }

    private List<Pair<String, Integer>> parse(List<String> lines)
    {
        String line = lines.get(0);
        String[] labelsAndValuesArr = line.split(",");

        List<Pair<String, Integer>> labelsAndValues = new ArrayList<>();
        for (int i = 0; i < labelsAndValuesArr.length; i++)
        {
            String labelAndValue = labelsAndValuesArr[i];
            if (labelAndValue.contains("="))
            {
                String[] split = labelAndValue.split("=");
                labelsAndValues.add(Pair.of(split[0], Integer.parseInt(split[1])));
            } else
            {
                String[] split = labelAndValue.split("-");
                labelsAndValues.add(Pair.of(split[0], -1));
            }
        }
        return labelsAndValues;
    }
}
