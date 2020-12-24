package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InternElves {

    private String input;
    private String[] illegalSequences = {"ab","cd","pq","xy"};

    public InternElves(String input){
        this.input = input;
    }


    public int solve1(){

        String[] names = input.replaceAll("\r","").split("\n");
        List<String> good = new ArrayList<>();
        List<String> bad = new ArrayList<>();
        for(String n : names){
            if (countVowels(n) && n.matches("\\S*(\\w)\\1+\\S*") && n.matches("^(?!.*?(?:ab|cd|pq|xy)).*$")){
                System.out.println(n);
                good.add(n);
            }else{
                bad.add(n);
            }
        }
        System.out.println(bad.size());
        return good.size();
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

    public int solve2(){
        String[] names = input.replaceAll("\r","").split("\n");
        List<String> good = new ArrayList<>();
        List<String> bad = new ArrayList<>();
        for(String n : names){
            if (checkPair(n) && checkRepeat(n)){
                //System.out.println(n);
                good.add(n);
            }else{
                bad.add(n);
            }
        }
        System.out.println(bad.size());
        return good.size();
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
                //System.out.println(n);
                //System.out.println(t);
                return true;
            }
        }
        return false;
    }
}
