package main;

import java.util.ArrayList;
import java.util.HashMap;

public class SingleNight {

    private String input;
    private ArrayList<String> mylist = new ArrayList<>();
    public SingleNight(String in){
        this.input = in.replaceAll("\r","");
    }


    public int solve1(){
        input = input.replaceAll(" to ",",").replaceAll(" = ",",").replaceAll(" ","");
        String alias = replaceNames();
        HashMap<String, Integer> places = new HashMap<>();
        for(String s : input.split("\n")){
            String[] t = s.split(",");
            places.put(t[0] + t[1], Integer.parseInt(t[2]));
            places.put(t[1] + t[0], Integer.parseInt(t[2]));
        }

        ArrayList<String> possibilities = permute(alias, 0, alias.length()-1);

        int minDistance = Integer.MAX_VALUE;
        for(String s : possibilities){
            int distance = 0;

            for(int i = 0; i<s.length()-1;i++) {
                for (String p : places.keySet()) {
                    if (p.charAt(0) == s.charAt(i) && p.charAt(1) == s.charAt(i+1)) {
                        distance +=places.get(p);
                        break;
                    }
                }
            }
            minDistance = Math.min(distance,minDistance);
        }

        return minDistance;
    }

    public int solve2(){
        input = input.replaceAll(" to ",",").replaceAll(" = ",",").replaceAll(" ","");
        String alias = replaceNames();
        HashMap<String, Integer> places = new HashMap<>();
        for(String s : input.split("\n")){
            String[] t = s.split(",");
            places.put(t[0] + t[1], Integer.parseInt(t[2]));
            places.put(t[1] + t[0], Integer.parseInt(t[2]));
        }

        ArrayList<String> possibilities = permute(alias, 0, alias.length()-1);

        int maxDistance = Integer.MIN_VALUE;
        for(String s : possibilities){
            int distance = 0;

            for(int i = 0; i<s.length()-1;i++) {
                for (String p : places.keySet()) {
                    if (p.charAt(0) == s.charAt(i) && p.charAt(1) == s.charAt(i+1)) {
                        distance +=places.get(p);
                        break;
                    }
                }
            }
            maxDistance = Math.max(distance,maxDistance);
        }

        return maxDistance;
    }

    private String replaceNames() {
        HashMap<String,String> alias = new HashMap<>();
        char c = 'A';
        for(String s : input.split("\n")){
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
            input = input.replaceAll(s,alias.get(s));
            sb.append(alias.get(s));
        }

        return sb.toString();
    }

    private ArrayList<String> permute(String str, int l, int r) {

        if (l == r)
            mylist.add(str);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
        return  mylist;
    }
    private String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
