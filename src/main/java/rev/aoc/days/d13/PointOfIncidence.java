package rev.aoc.days.d13;

import org.apache.commons.lang3.tuple.Pair;
import rev.aoc.AocSolution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PointOfIncidence extends AocSolution<Long>
{
    public PointOfIncidence(Iterable<String> resources)
    {
        super(resources);
    }

    @Override
    protected Long solveImpl() throws Exception
    {
        List<String> lines = getOneAndOnlyResourceLines();
        List<Pair<char[][],char[][]>> mirrors = parse(lines);

        long score = 0;
        for (int i=0; i<mirrors.size(); i++) {
            Pair<char[][],char[][]> mirror = mirrors.get(i);
            char[][] m = mirror.getLeft();
            char[][] mTranspose = mirror.getRight();
            int colOfReflection = getReflectionIndex(m);
            int rowOfReflection = getReflectionIndex(mTranspose);
            score += getScore(colOfReflection, rowOfReflection);
        }
        return score;
    }

    private long getScore(int colOfReflection, int rowOfReflection)
    {
        if (colOfReflection>=0) {
            return colOfReflection+1;
        }
        return 100 * (rowOfReflection+1);
    }

    private int getReflectionIndex(char[][] m)
    {
        int height = m.length;
        int width = m[0].length;

        Set<Integer> reflectionIndices = getReflectionIndices(m[0]);

        for (int i=1; i<height; i++) {
            char[] row = m[i];
            Set<Integer> newReflectionIndices = getReflectionIndices(m[i]);
            reflectionIndices.retainAll(newReflectionIndices);
            if (reflectionIndices.isEmpty()) {
                return -1;
            }
        }

        if (reflectionIndices.size() > 1) {
            throw new RuntimeException("Problem solution is ambiguous");
        }
        return reflectionIndices.iterator().next();
    }

    private Set<Integer> getReflectionIndices(char[] arr)
    {
        int[][] palindromeTable = getPalindromeTable(arr);
        Set<Integer> result = new HashSet<>();
        for (int i=1; i<arr.length; i+=2) {
            if (palindromeTable[0][i] == 1) {
                result.add(i/2);
            }
            if (palindromeTable[arr.length-1-i][arr.length-1] == 1) {
                result.add(arr.length-1-(i+1)/2);
            }
        }
        return result;
    }

    private int[][] getPalindromeTable(char[] arr)
    {
        int len = arr.length;
        int[][] palindromeTable = new int[len][len];

        for (int i=0; i<len; i++) {
            for (int j=0; j<len; j++) {
                if (i>j) {
                    palindromeTable[i][j]=0;
                } else {
                    palindromeTable[i][j] = -1;
                }
            }
        }

        for (int i=0; i<len; i++) {
            for (int j=i; j<len; j++) {
                calcPalindrome(arr, palindromeTable, i,j);
            }
        }

        return palindromeTable;
    }

    private int calcPalindrome(char[] arr, int[][] palindromeTable, int i, int j)
    {
        if (palindromeTable[i][j] != -1) {
            return palindromeTable[i][j];
        }

        if (i == j) {
            palindromeTable[i][j] = 1;
            return 1;
        }

        if (arr[i] != arr[j]) {
            palindromeTable[i][j] = 0;
            return 0;
        }

        if (i+1 == j) {
            palindromeTable[i][j]=1;
            return 1;
        }

        int val = calcPalindrome(arr, palindromeTable, i+1,j-1);
        palindromeTable[i][j] = val;
        return val;
    }

    private List<Pair<char[][], char[][]>> parse(List<String> lines)
    {
        List<Pair<char[][],char[][]>> retval = new ArrayList<>();
        int lineIndex = 0;
        while (lineIndex<lines.size()) {
            String line = lines.get(lineIndex);
            if (line.trim().equals("")) {
                lineIndex++;
            } else {
                int width = line.length();
                int height=0;
                while (!line.equals("")) {
                    height++;
                    line=lines.get(height);
                }
                char[][] mirror = new char[height][width];
                char[][] mirrorTranspose = new char[width][height];
                for (int i=0; i<height;i++) {
                    mirror[i] = lines.get(lineIndex+i).toCharArray();
                }
                for (int i=0; i<height; i++) {
                    for (int j=0; j<width; j++) {
                        mirrorTranspose[j][i]=mirror[i][j];
                    }
                }
                retval.add(Pair.of(mirror, mirrorTranspose));
                lineIndex+=height;
            }
        }
        return retval;
    }
}
