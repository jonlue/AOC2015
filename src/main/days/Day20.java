package main.days;

import main.AOCRiddle;

public class Day20 extends AOCRiddle {
    public Day20(String in, int part) {
        super(in, part);
    }

    public String solve1() {
        int factorSum = Integer.parseInt(getInput()) / 10;
        int sum;
        int value = factorSum / 5;

        do {
            value += 1;
            sum = calculateSum(value, factorSum);
        } while (sum < factorSum);

        return String.valueOf(value);
    }

    public String solve2() {
        int factorSum = Integer.parseInt(getInput()) / 11;
        int sum;
        int value = factorSum / 4;

        do {
            value += 1;
            sum = calculateSumBorder(value, factorSum);
        } while (sum < factorSum);

        return String.valueOf(value);
    }

    private int calculateSum(int i, int factorSum) {
        int sum = i + 1;
        for (int j = i / 2; j > 1; j--) {
            if (i % j == 0) {
                sum += j;
                if (sum > factorSum) {
                    break;
                }
            }
        }
        return sum;
    }

    private int calculateSumBorder(int i, int factorSum) {
        int sum = i + 1;
        for (int j = i / 2; j > i / 50; j--) {
            if (i % j == 0) {
                sum += j;
                if (sum > factorSum) {
                    break;
                }
            }
        }
        return sum;
    }

}
