package main.days;

import main.AOCRiddle;

import java.util.*;

public class Day17 extends AOCRiddle {
    public Day17(String in, int part) {
        super(in, part);
        String letters = parse();
        getMinMaxContainer();
        combinations(letters);
    }

    private Map<String, Integer> sizes;
    private static final int VOLUME = 150;
    private final List<String[]> combinations = new ArrayList<>();
    private int minLength, maxLength;

    public String solve1() {
        int combinationCount = 0;
        for (String[] s : combinations) {
            int sum = 0;
            for (String t : s) {
                sum += sizes.get(t);
            }
            if (sum == VOLUME) {
                combinationCount++;
            }
        }
        return String.valueOf(combinationCount);
    }

    public String solve2() {
        int combinationCount = 0;
        int shortestSize = maxLength;
        boolean fondShortest = false;
        for (String[] s : combinations) {
            if (fondShortest && shortestSize != s.length) {
                break;
            }
            int sum = 0;
            for (String t : s) {
                sum += sizes.get(t);
            }
            if (sum == VOLUME) {
                fondShortest = true;
                shortestSize = Math.min(shortestSize, s.length);
                combinationCount++;
            }
        }
        return String.valueOf(combinationCount);
    }

    private void getMinMaxContainer() {
        List<Integer> t = new ArrayList<>(sizes.values());
        Collections.sort(t);

        minLength = 0;
        maxLength = sizes.size();
        int sum = 0;
        for (int i = 0; i < t.size(); i++) {
            sum += t.get(i);
            if (sum >= VOLUME) {
                System.out.println(sum);
                maxLength = i + 1;
                break;
            }
        }
        sum = 0;
        for (int i = t.size() - 1; i > 0; i--) {
            sum += t.get(i);
            if (sum >= VOLUME) {
                System.out.println(sum);
                minLength = t.size() - i;
                break;
            }
        }
        System.out.println("min: " + minLength);
        System.out.println("max: " + maxLength);

    }

    private String parse() {
        sizes = new HashMap<>();
        char c = 'A';
        StringBuilder r = new StringBuilder();
        for (String s : getInput().split("\n")) {
            r.append(c);
            sizes.put(Character.toString(c), Integer.parseInt(s));
            c++;
        }
        return r.toString();
    }


    private void combinations(String s) {
        String[] t = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            t[i] = Character.toString(s.charAt(i));
        }

        for (int i = minLength; i < maxLength; i++) {
            combinations(t, i, 0, new String[i]);
        }
    }

    private void combinations(String[] arr, int len, int startPosition, String[] result) {
        if (len == 0) {
            String[] temp = Arrays.copyOf(result, result.length);
            combinations.add(temp);
            return;
        }
        for (int i = startPosition; i <= arr.length - len; i++) {
            result[result.length - len] = arr[i];
            combinations(arr, len - 1, i + 1, result);
        }
    }
}
