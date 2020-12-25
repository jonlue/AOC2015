package main.days;

import main.AOCRiddle;

public class Day01 extends AOCRiddle {
    public Day01(String in, int part) {
        super(in, part);
        parse();
    }

    private char[] chars;

    public String solve1(){
        int sum = 0;
        for(char c : chars){
            sum+= (c == '(') ? 1 : -1;
        }
        return String.valueOf(sum);
    }

    public String solve2(){
        int sum = 0;
        int count = 1;
        for(char c : chars){
            sum+= (c == '(') ? 1 : -1;
            if(sum < 0){
                return String.valueOf(count);
            }
            count++;
        }
        return "-1";
    }

    private void parse(){
        chars = getInput().toCharArray();
    }
}
