package rev.aoc.days.two;

import java.security.InvalidParameterException;

public class Handful {
    public long red = 0;
    public long green = 0;
    public long blue = 0;

    private static final String RED = " red";
    private static final String GREEN = " green";
    private static final String BLUE = " blue";
    public static Handful from(String[] colors) {
        Handful handful = new Handful();
        for (int i=0; i<colors.length; i++) {
            String colorAndAmount = colors[i].trim();
            if (colorAndAmount.contains(RED)) {
                handful.red = Integer.parseInt(colorAndAmount.replaceAll(RED, ""));
            } else if (colorAndAmount.contains(GREEN)) {
                handful.green = Integer.parseInt(colorAndAmount.replaceAll(GREEN, ""));
            } else if (colorAndAmount.contains(BLUE)) {
                handful.blue = Integer.parseInt(colorAndAmount.replaceAll(BLUE, ""));
            } else {
                throw new InvalidParameterException();
            }
        }
        return handful;
    }
}