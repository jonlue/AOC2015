package main.days;

import main.AOCRiddle;
import main.days.util.Aunt;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//My first Crime Scene Analysis Machine
public class Day16 extends AOCRiddle {
    public Day16(String in, int part){
        super(in,part);
        parse();
    }

    private List<Aunt> aunts;
    private static final String[] TYPES = {"children","cats","samoyeds","pomeranians","akitas","vizslas","goldfish","trees","cars","perfumes"};
    private static final int[] FOUND_VALUES = {3,7,2,3,0,0,5,3,2,1};

    public String solve1(){
        aunts.removeIf(a -> !a.check(FOUND_VALUES));
        return String.valueOf((aunts.get(0).getId()));
    }

    public String solve2(){
        aunts.removeIf(a -> !a.checkExact(FOUND_VALUES));
        return String.valueOf((aunts.get(0).getId()));
    }

    private void parse() {
        aunts = new ArrayList<>();
        String[] temp = getInput().replaceAll(" ","").replaceAll("Sue\\d","").replaceAll(":","").replaceAll("\r","").split("\n");

        Pattern p = Pattern.compile("\\d+");
        int count = 1;

        for(String s : temp){
            int[] values = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            for(int i = 0; i< TYPES.length; i++){
                if(s.contains(TYPES[i])){
                    int start = s.indexOf(TYPES[i]);
                    int end = start + TYPES[i].length() + 3;
                    if ( end >= s.length()) {
                        end = s.length();
                    }
                    String t = s.substring(start,end);

                    Matcher m = p.matcher(t);
                    m.find();
                    values[i] = Integer.parseInt(m.group());
                }

            }
            aunts.add(new Aunt(count,values));
            count++;

        }

    }
}
