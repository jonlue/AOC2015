package main;

public class Matchsticks {

    private String input;
    public Matchsticks(String input) {
        this.input = input.replaceAll("\r","");
    }

    public int solve1(){
        int codeCharacter = 0;
        int character = 0;

        for(String s : input.split("\n")){
            codeCharacter += s.length();
            while(s.contains("\\")){
                char c = s.charAt(s.indexOf("\\")+1);
                if(c == 'x'){
                    s = s.substring(0,s.indexOf("\\")) + s.substring(s.indexOf("\\")+4);
                    character += 1;
                }else{
                    s = s.substring(0,s.indexOf("\\")) + s.substring(s.indexOf("\\")+2);
                    character += 1;
                }
            }
            s = s.replaceAll("\"","");
            character += s.length();
        }
        System.out.println("character of code: \t" + codeCharacter);
        System.out.println("characters:\t\t" + character);
        return codeCharacter-character;
    }

    public int solve2(){
        int codeCharacter = 0;
        int newCodeCharacter = 0;
        for(String s : input.split("\n")){
            codeCharacter += s.length();
            newCodeCharacter += s.length() + 2 + countSpecial(s);
        }
        return newCodeCharacter-codeCharacter;
    }

    private int countSpecial(String s) {
        int sum = 0;
        sum += s.chars().filter(ch->ch == '\\').count();
        sum += s.chars().filter(ch->ch == '"').count();
        return sum;
    }
}
