package main;

public class NotQuiteLisp {

    private static final String[] splits = {"\\(","\\)"};

    private String input;
    public NotQuiteLisp(String input) {
        this.input = input;
    }

    public int solve1(){
        int sum = 0;
        for(char c : input.toCharArray()){
            sum+= (c == '(') ? 1 : -1;
        }
        return sum;
    }

    public int solve2(){
        int sum = 0;
        int count = 1;
        for(char c : input.toCharArray()){
            sum+= (c == '(') ? 1 : -1;
            if(sum < 0){
                return count;
            }
            count++;
        }
        return -1;
    }
}
