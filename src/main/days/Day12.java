package main.days;

import main.AOCRiddle;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 extends AOCRiddle {

    private String input;

    public Day12(String in, int part) {
        super(in, part);
    }


    public String solve1() {
        return String.valueOf(sumNumbers(input));
    }

    public String solve2() {
        while (input.contains(":\"red\"")) {
            int middle = input.indexOf(":\"red\"");
            int start = -1;
            int end = -1;
            int found = 0;
            for (int i = middle; i < input.length(); ++i) {
                if (input.charAt(i) == '{') {
                    found++;
                }
                if (input.charAt(i) == '}') {
                    if (found > 0) {
                        found--;
                    } else {
                        end = i;
                        break;
                    }
                }
            }
            found = 0;
            for (int i = middle; i >= 0; --i) {
                if (input.charAt(i) == '}') {
                    found++;
                }
                if (input.charAt(i) == '{') {
                    if (found > 0) {
                        found--;
                    } else {
                        start = i;
                        break;
                    }
                }
            }
            input = input.substring(0, start) + input.substring(end + 1);
            //65402
        }
        return String.valueOf(sumNumbers(input));
    }

    private int sumNumbers(String string) {
        int sum = 0;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(string);
        while (m.find()) {
            sum += Integer.parseInt(m.group());
        }
        Pattern p2 = Pattern.compile("-\\d+");
        m = p2.matcher(string);
        while (m.find()) {
            sum += Integer.parseInt(m.group());
            sum += Integer.parseInt(m.group());
        }
        return sum;
    }
}
