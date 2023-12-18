package rev.aoc.days.d4;

import com.google.common.math.IntMath;
import lombok.Getter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ScratchCard
{
    @Getter
    private final int id;
    private final Set<Integer> numbers;
    private final Set<Integer> winningNumbers;

    public ScratchCard(int id, Set<Integer> numbers, Set<Integer> winningNumbers)
    {
        this.id = id;
        this.numbers = numbers;
        this.winningNumbers = winningNumbers;
    }

    public int getScore()
    {
        int count = getNumbersThatWin().size();
        if (count == 0)
        {
            return count;
        }
        return IntMath.pow(2, count - 1);
    }

    public Set<Integer> getNumbersThatWin()
    {
        Set<Integer> retval = new HashSet<>();
        Iterator<Integer> itNumbers = numbers.iterator();
        while (itNumbers.hasNext())
        {
            int number = itNumbers.next();
            if (winningNumbers.contains(number))
            {
                retval.add(number);
            }
        }
        return retval;
    }

    public static final ScratchCard fromGameLine(String line)
    {
        String[] gameNumbersSplit = line.split(":");
        int id = Integer.parseInt(gameNumbersSplit[0].replaceAll("Card ", "").trim());
        String[] numbersWinnersSplit = gameNumbersSplit[1].split("\\|");
        String allNumbers = numbersWinnersSplit[0].trim();
        String allWinners = numbersWinnersSplit[1].trim();
        String[] numbers = allNumbers.split("\\s+");
        String[] winners = allWinners.split("\\s+");

        Set<Integer> setNumbers = putAllInSet(numbers);
        Set<Integer> setWinners = putAllInSet(winners);
        return new ScratchCard(id, setNumbers, setWinners);
    }

    private static Set<Integer> putAllInSet(String[] nums)
    {
        Set<Integer> retval = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
        {
            retval.add(Integer.parseInt(nums[i].trim()));
        }
        return retval;
    }
}
