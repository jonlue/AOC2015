package main.days;

import main.AOCRiddle;

public class Day10 extends AOCRiddle {
    public Day10(String in, int part){
        super(in,part);
    }

    public String solve1(){
        return String.valueOf(run(40));
    }

    //TODO optimize
    public String solve2() {
        return String.valueOf(run(50));
    }

    private int run(int times){
        String in = getInput();
        StringBuilder answer = new StringBuilder();
        for(int j = 0; j<times; j++) {
            answer = new StringBuilder();
            for (int i = 0; i < in.length(); i++) {
                char c = in.charAt(i);
                int count = countSame(in.substring(i),c);
                count = Math.max(count,1);
                answer.append(count).append(c);
                i += count-1;
            }
            in = answer.toString();
        }
        return answer.length();
    }

    private int countSame(String s, char c) {
        int sum = 0;
        if(s.startsWith(String.valueOf(c))){
            sum += 1 + countSame(s.substring(1),c);
        }
        return sum;
    }
}
