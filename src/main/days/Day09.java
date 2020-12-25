package main.days;

import main.AOCRiddle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day09 extends AOCRiddle {
    public Day09(String in, int part){
        super(in, part);
        parse();
        initMap();
        initList();
    }

    private String[] input;
    private String alias;
    private final ArrayList<String> myList = new ArrayList<>();
    private Map<String, Integer> places;
    private List<String> possibilities;



    public String solve1(){
        int minDistance = Integer.MAX_VALUE;
        for(String s : possibilities){
            minDistance = Math.min(getDistance(s), minDistance);
        }

        return String.valueOf(minDistance);
    }

    public String solve2(){
        int maxDistance = Integer.MIN_VALUE;
        for(String s : possibilities){
            maxDistance = Math.max(getDistance(s),maxDistance);
        }
        return String.valueOf(maxDistance);
    }

    private int getDistance(String s) {
        int distance = 0;

        for(int i = 0; i<s.length()-1;i++) {
            for (String p : places.keySet()) {
                if (p.charAt(0) == s.charAt(i) && p.charAt(1) == s.charAt(i+1)) {
                    distance +=places.get(p);
                    break;
                }
            }
        }
        return distance;
    }

    private String replaceNames() {
        HashMap<String,String> alias = new HashMap<>();
        char c = 'A';
        for(String s : input){
            String[]temp = s.split(",");
            if(!alias.containsKey(temp[0])){
                alias.put(temp[0],Character.toString(c));
                c++;
            }
            if(!alias.containsKey(temp[1])){
                alias.put(temp[1],Character.toString(c));
                c++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String s : alias.keySet()){
            setInput(getInput().replaceAll(s,alias.get(s)));
            sb.append(alias.get(s));
        }

        return sb.toString();
    }

    private ArrayList<String> permute(String str, int l, int r) {
        if (l == r)
            myList.add(str);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
        return myList;
    }

    private String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    private void initMap(){
        places = new HashMap<>();
        for(String s : input){
            String[] t = s.split(",");
            places.put(t[0] + t[1], Integer.parseInt(t[2]));
            places.put(t[1] + t[0], Integer.parseInt(t[2]));
        }
    }

    private void initList() {
        possibilities = permute(alias,0,alias.length()-1);
    }

    private void parse(){
        alias = replaceNames();
        input = getInput().replaceAll(" to ",",").replaceAll(" = ",",").replaceAll(" ","").split("\n");
    }
}
