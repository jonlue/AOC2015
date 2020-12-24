package main;

public class NoMath {

    private String[] input;
    private int[] sides = new int[3];
    private int[] dimensions = new int[3];
    public  NoMath(String input){
        this.input = input.replaceAll("\r","").split("\n");
    }

    public int solve1(){
        int sum = 0;
        for(String s : input){
            parse(s);
            sum += getSurface() + getExtra();
        }
        return sum;
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

    public int solve2(){
        int sum = 0;
        for (String s : input){
            parse(s);
            sum += getSmallSides() + getBow();
        }
        return sum;
    }

    private int getBow() {
        return dimensions[0] * dimensions[1] * dimensions[2];
    }

    private int getSmallSides() {
        return 2 * (dimensions[0] + dimensions[1] + dimensions[2] - Math.max(dimensions[0],Math.max(dimensions[1],dimensions[2])));
    }
}
