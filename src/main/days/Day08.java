package main.days;

import main.AOCRiddle;

public class Day08 extends AOCRiddle {
    public Day08(String in, int part) {
        super(in,part);
    }

    private final String[] input = getInput().split("\n");

    public String solve1(){
        int codeCharacter = 0;
        int character = 0;

        for(String s : input){
            codeCharacter += s.length();
            while(s.contains("\\")){
                char c = s.charAt(s.indexOf("\\")+1);
                if(c == 'x'){
                    s = s.substring(0,s.indexOf("\\")) + s.substring(s.indexOf("\\")+4);
                }else{
                    s = s.substring(0,s.indexOf("\\")) + s.substring(s.indexOf("\\")+2);
                }
                character += 1;
            }
            s = s.replaceAll("\"","");
            character += s.length();
        }

        return String.valueOf(codeCharacter-character);
    }

    public String solve2(){
        int codeCharacter = 0;
        int newCodeCharacter = 0;
        for(String s : input){
            codeCharacter += s.length();
            newCodeCharacter += s.length() + 2 + countSpecial(s);
        }
        return String.valueOf(newCodeCharacter-codeCharacter);
    }


    private int countSpecial(String s) {
        int sum = 0;
        sum += s.chars().filter(ch->ch == '\\').count();
        sum += s.chars().filter(ch->ch == '"').count();
        return sum;
    }
}
