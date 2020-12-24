package main;

public class LookAndSay {

    private String input;
    public LookAndSay(String input){
        this.input = input;
    }

    public int solve1(){
        return solve1(40);
    }
    private int solve1(int times){
        String in = input;
        String answer = "";
        for(int j = 0; j<times; j++) {
            answer = "";

            for (int i = 0; i < in.length(); i++) {
                char c = in.charAt(i);
                int count = countSame(in.substring(i),c);
                count = Math.max(count,1);
                answer += Integer.toString(count)+ c;
                i += count-1;
            }
            in = answer;
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
    //TODO optimze
    public int solve2() {
        return solve1(50);
    }
}
