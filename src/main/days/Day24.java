package main.days;

import main.AOCRiddle;

import java.util.*;

public class Day24 extends AOCRiddle {
    public Day24(String in, int part) {
        super(in, part);
    }

    private final List<String[]> myList = new ArrayList<>();
    private final Map<String, Integer> sizes = new HashMap<>();

    private int compartmentCount;
    private int weightPerCompartment;
    private int minLength, maxLength;

    public String solve1() {
        return String.valueOf(calculate(3));
    }

    public String solve2() {
        return String.valueOf(calculate(4));
    }

    private long calculate(int compartment) {
        compartmentCount = compartment;
        String letters = parse();
        weightPerCompartment = getWeights();
        getMinMaxContainer();
        combinations(letters);
        System.out.println(weightPerCompartment);

        int shortestSize = maxLength;
        boolean foundShortest = false;
        long minQuantumEntanglement = Long.MAX_VALUE;
        System.out.println(minQuantumEntanglement);
        for (String[] s : myList) {
            if (foundShortest && shortestSize != s.length) {
                break;
            }
            int sum = 0;
            long quantumEntanglement = 1;
            for (String t : s) {
                sum += sizes.get(t);
                quantumEntanglement *= sizes.get(t);
            }

            if (sum == weightPerCompartment) {
                minQuantumEntanglement = Math.min(quantumEntanglement, minQuantumEntanglement);
                foundShortest = true;
                shortestSize = Math.min(shortestSize, s.length);
            }
        }
        return minQuantumEntanglement;
    }

    private int getWeights() {
        int sum = 0;
        for (String s : sizes.keySet()) {
            sum += sizes.get(s);
        }
        return sum / compartmentCount;
    }

    private void combinations(String s) {
        String[] t = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            t[i] = Character.toString(s.charAt(i));
        }

        combinations(t, minLength, 0, new String[minLength]);
        combinations(t, minLength + 1, 0, new String[minLength + 1]);
    }

    private void combinations(String[] arr, int len, int startPosition, String[] result) {
        if (len == 0) {
            String[] temp = Arrays.copyOf(result, result.length);
            myList.add(temp);
            return;
        }
        for (int i = startPosition; i <= arr.length - len; i++) {
            result[result.length - len] = arr[i];
            combinations(arr, len - 1, i + 1, result);
        }
    }

    private String parse() {
        char c = 'A';
        StringBuilder r = new StringBuilder();
        for (String s : getInput().split("\n")) {
            r.append(c);
            sizes.put(Character.toString(c), Integer.parseInt(s));
            c++;
        }
        return r.toString();
    }

    private void getMinMaxContainer() {
        List<Integer> t = new ArrayList<>(sizes.values());
        Collections.sort(t);
        minLength = 0;
        maxLength = t.size();
        int sum = 0;
        for (int i = 0; i < t.size(); i++) {
            sum += t.get(i);
            if (sum >= weightPerCompartment) {
                maxLength = i + 1;
                break;
            }
        }
        sum = 0;
        for (int i = t.size() - 1; i > 0; i--) {
            sum += t.get(i);
            if (sum >= weightPerCompartment) {
                minLength = t.size() - i;
                break;
            }
        }
        System.out.println("min: " + minLength);
        System.out.println("max: " + maxLength);
    }
}
