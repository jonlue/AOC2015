package main.days;

import main.AOCRiddle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 extends AOCRiddle {
    public Day05(String in, int part){
        super(in,part);
        parse();
    }

    private List<String> names;

    public String solve1(){
        List<String> good = new ArrayList<>();
        for(String n : names){
            if (countVowels(n) && n.matches("\\S*(\\w)\\1+\\S*") && n.matches("^(?!.*?(?:ab|cd|pq|xy)).*$")){
                System.out.println(n);
                good.add(n);
            }
        }
        return String.valueOf(good.size());
    }
    public String solve2(){
        List<String> good = new ArrayList<>();
        for(String n : names){
            if (checkPair(n) && checkRepeat(n)){
                good.add(n);
            }
        }
        return String.valueOf(good.size());
    }

    private boolean countVowels(String n) {
        int sum = 0;
        sum += n.chars().filter(ch->ch == 'a').count();
        sum += n.chars().filter(ch->ch == 'e').count();
        sum += n.chars().filter(ch->ch == 'i').count();
        sum += n.chars().filter(ch->ch == 'o').count();
        sum += n.chars().filter(ch->ch == 'u').count();
        return sum>=3;
    }

    private boolean checkRepeat(String n) {
        for(int i = 0; i< n.length()-2; i++){
            if(n.charAt(i) == n.charAt(i+2)){
                return true;
            }
        }
        return false;
    }

    private boolean checkPair(String n) {
        for(int i = 0; i< n.length()-2; i++){
            String t = Character.toString(n.charAt(i)) + n.charAt(i+1);
            String t2 = n.substring(0,i) +" "+ n.substring(i+2);
            if(t2.contains(t)){
                return true;
            }
        }
        return false;
    }

    private void parse(){
        names = Arrays.asList(getInput().split("\n").clone());
    }
}
