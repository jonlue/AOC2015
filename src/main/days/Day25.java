package main.days;

import main.AOCRiddle;

import java.util.HashMap;

public class Day25 extends AOCRiddle {
    public Day25(String in, int part) {
        super(in, part);
        parse();


    }

    private int row;
    private int column;
    private static final int START_VALUE = 20151125;
    private static final int MULTIPLIER = 252533;
    private static final int MODULO = 33554393;
    private HashMap<Long, Integer> numbers;

    public String solve1() {
        int countRep = countToRepetition();
        System.out.println(countRep);

        int rowColNumber = getRowColNumber();
        System.out.println(rowColNumber);
        rowColNumber = rowColNumber % countRep + 1;

        long result = -1;
        for (long v : numbers.keySet()) {
            if (numbers.get(v) == rowColNumber) {
                result = v;
                break;
            }
        }
        return String.valueOf(result);
    }

    public String solve2() {
        return "Well done!";
    }

    private int getRowColNumber() {
        row -= 1;
        int rowNumber = 1 + (row * row + row) / 2;
        column = row + column;
        row++;
        return rowNumber + (column * column + column) / 2 - ((row * row + row) / 2);
    }

    private int countToRepetition() {
        numbers = new HashMap<>();
        int i = 1;
        long value = START_VALUE;
        numbers.put(value, i);
        boolean running = true;

        while (running) {
            value = value * MULTIPLIER % MODULO;
            i++;
            if (numbers.containsKey(value)) {
                running = false;
            } else {
                numbers.put(value, i);
            }
        }
        return i;
    }


    private void parse() {
        String[] temp = getInput()
                .replaceAll("To continue, please consult the code grid in the manual.  Enter the code at row ", "")
                .replaceAll("column", "")
                .replaceAll(" ", "")
                .replaceAll("\\.", "")
                .split(",");
        row = Integer.parseInt(temp[0]);
        column = Integer.parseInt(temp[1]);
    }
}
