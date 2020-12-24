package main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//My first Crime Scene Analysis Machine
public class MFCSAM {

    private String input;
    private List<Aunt> aunts;
    private String[] types = {"children","cats","samoyeds","pomeranians","akitas","vizslas","goldfish","trees","cars","perfumes"};
    private int[] foundValues = {3,7,2,3,0,0,5,3,2,1};

    public MFCSAM(String input){
        this.input = input;
    }

    public int solve1(){
        parse();
        aunts.removeIf(a -> !a.check(foundValues));
        System.out.println(aunts.size());
        return (aunts.get(0).getId());
    }

    public int solve2(){
        parse();
        aunts.removeIf(a -> !a.checkExact(foundValues));
        System.out.println(aunts.size());
        return (aunts.get(0).getId());
    }

    private void parse() {
        aunts = new ArrayList<>();
        String[] temp = input.replaceAll(" ","").replaceAll("Sue\\d","").replaceAll(":","").replaceAll("\r","").split("\n");

        Pattern p = Pattern.compile("\\d+");
        int count = 1;

        for(String s : temp){
            int[] values = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            for(int i = 0; i< types.length; i++){
                if(s.contains(types[i])){
                    int start = s.indexOf(types[i]);
                    int end = start + types[i].length() + 3;
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
