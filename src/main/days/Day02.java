package main.days;

import main.AOCRiddle;

import java.util.Arrays;
import java.util.List;

public class Day02 extends AOCRiddle {
    public Day02(String in, int part){
        super(in, part);
        parse();
    }

    private List<String> eq;
    private final int[] sides = new int[3];
    private final int[] dimensions = new int[3];

    public String solve1(){
        int sum = 0;
        for(String s : eq){
            parse(s);
            sum += getSurface() + getExtra();
        }
        return String.valueOf(sum);
    }

    public String solve2(){
        int sum = 0;
        for (String s : eq){
            parse(s);
            sum += getSmallSides() + getBow();
        }
        return String.valueOf(sum);
    }

    private void parse(String s) {

        String[] dim = s.split("x");
        dimensions[0] = Integer.parseInt(dim[0]);
        dimensions[1] = Integer.parseInt(dim[1]);
        dimensions[2] = Integer.parseInt(dim[2]);
        getSides();
    }

    private void getSides(){
        this.sides[0] = dimensions[0] * dimensions[1];
        this.sides[1] = dimensions[0] * dimensions[2];
        this.sides[2] = dimensions[1] * dimensions[2];
    }

    private int getSurface() {
        return 2*(sides[0]+sides[1] + sides[2]);
    }

    private int getExtra() {
        return Math.min(sides[0], Math.min(sides[1],sides[2]));
    }



    private int getBow() {
        return dimensions[0] * dimensions[1] * dimensions[2];
    }

    private int getSmallSides() {
        return 2 * (dimensions[0] + dimensions[1] + dimensions[2] - Math.max(dimensions[0],Math.max(dimensions[1],dimensions[2])));
    }

    private void parse(){
        eq = Arrays.asList(getInput().split("\n").clone());
    }
}
