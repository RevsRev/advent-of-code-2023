package rev.aoc.days.d1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TrebuchetPartTwo extends Trebuchet
{
    private static final Map<String,String> NUMS_AS_WORDS = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9");

    private static final Map<String,String> NUMS_AS_WORDS_REVERSED = getNumsAsWordsReversed();

    private static final Map<String,String> getNumsAsWordsReversed() {
        Map<String,String> retval = new HashMap<>();
        Iterator<String> it = NUMS_AS_WORDS.keySet().iterator();
        while (it.hasNext()) {
            String word = it.next();
            String numeral = NUMS_AS_WORDS.get(word);
            String wordReversed = new StringBuilder().append(word).reverse().toString();
            retval.put(wordReversed,numeral);
        }
        return retval;
    }

    public TrebuchetPartTwo(Iterable<String> resources)
    {
        super(resources);
    }

    /**
     * Not the nicest solution but I'm already getting bored by this problem
     */
    @Override
    protected String format(String line)
    {
        String forwardLine = formatForwards(line, NUMS_AS_WORDS);
        String backwardLine = formatForwards(new StringBuilder().append(line).reverse().toString(), NUMS_AS_WORDS_REVERSED);

        return forwardLine + (new StringBuilder().append(backwardLine).reverse().toString());
    }

    private String formatForwards(String line, Map<String,String> words)
    {
        String earliestReplacement = "";
        do {
            earliestReplacement = "";
            long earliestIndex = line.length();
            Iterator<String> it = words.keySet().iterator();
            while (it.hasNext()) {
                String word = it.next();
                int index = line.indexOf(word);
                if (index >=0 && index<earliestIndex) {
                    earliestIndex = index;
                    earliestReplacement = word;
                }
            }
            if (!earliestReplacement.equals("")) {
                line = line.replaceFirst(earliestReplacement, words.get(earliestReplacement));
            }
        } while (!earliestReplacement.equals(""));
        return line;
    }
}
