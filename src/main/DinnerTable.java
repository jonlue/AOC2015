package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class DinnerTable {

    private String input;
    private ArrayList<String> mylist = new ArrayList<>();

    public DinnerTable(String input){
        this.input = input.replaceAll("would",",").replaceAll("happiness units by sitting next to",",").replaceAll(" ","").replaceAll("gain","").replaceAll("lose","-").replaceAll("\r","").replaceAll("\\.","");
    }

    public int solve1(){
        String[]list = input.split("\n");
        ArrayList<ArrayList<String>> relations =  getRealtions(list);
        ArrayList<ArrayList<String>> as = new ArrayList<>();

        for(int i = 0; i<7;i++){
            as.add(relations.get(i));
        }

        ArrayList<String> possibilities = permute("BCDEFGM", 0, 6);

        ArrayList<Integer> sums = new ArrayList<>();
        int maxSum = Integer.MIN_VALUE;
        for(String s : possibilities) {
            int sum = 0;
            for (int i = 0; i < as.size(); i++) {
                if(as.get(i).get(0).charAt(1) == s.charAt(0)){
                    sum += Integer.parseInt(as.get(i).get(1));
                    for(int j = 0; j<7; j++){
                        for(ArrayList<String> a : relations){
                            if(j == 6){
                                if(s.charAt(j) == a.get(0).charAt(0) && 'A' == a.get(0).charAt(1)){
                                    sum += Integer.parseInt(a.get(1));
                                    break;
                                }
                            }else if(s.charAt(j) == a.get(0).charAt(0) && s.charAt(j+1) == a.get(0).charAt(1)){
                                sum += Integer.parseInt(a.get(1));
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            System.out.println();
            maxSum = Math.max(sum,maxSum);
            sums.add(sum);
        }
        return maxSum;
    }

    public int solve2(){
        String[]list = input.split("\n");
        ArrayList<ArrayList<String>> relations =  getRealtions(list);
        relations.addAll(getMyself());

        ArrayList<ArrayList<String>> as = new ArrayList<>();

        for(int i = 0; i<7;i++){
            as.add(relations.get(i));
        }
        ArrayList<String> temp = new ArrayList<>();
        temp.add("AN");
        temp.add("0");
        as.add(temp);

        ArrayList<String> possibilities = permute("BCDEFGMN", 0, 7);

        int maxSum = Integer.MIN_VALUE;
        for(String s : possibilities) {
            int sum = 0;
            for (int i = 0; i < as.size(); i++) {
                if(as.get(i).get(0).charAt(1) == s.charAt(0)){
                    sum += Integer.parseInt(as.get(i).get(1));
                    for(int j = 0; j<8; j++){
                        for(ArrayList<String> a : relations){
                            if(j == 7){
                                if(s.charAt(j) == a.get(0).charAt(0) && 'A' == a.get(0).charAt(1)){
                                    sum += Integer.parseInt(a.get(1));
                                    break;
                                }
                            }else if(s.charAt(j) == a.get(0).charAt(0) && s.charAt(j+1) == a.get(0).charAt(1)){
                                sum += Integer.parseInt(a.get(1));
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            maxSum = Math.max(sum,maxSum);
        }
        return maxSum;
    }

    private Collection<? extends ArrayList<String>> getMyself() {
        ArrayList<ArrayList<String>> t = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        temp.add("AN");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("BN");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("CN");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("DN");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("EN");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("FN");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("GN");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("MN");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("NA");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("NB");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("NC");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("ND");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("NE");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("NF");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("NG");
        temp.add("0");
        t.add(temp);

        temp = new ArrayList<>();
        temp.add("NM");
        temp.add("0");
        t.add(temp);

        return t;
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
    public String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }



    private ArrayList<ArrayList<String>> getRealtions(String[] list){
        ArrayList<ArrayList<String>> relations = new ArrayList<>();
        ArrayList<String[]> pairs = new ArrayList<>();
        for(String s : list){
            pairs.add(s.split(","));
        }

        for(int i = 0; i< pairs.size();i++) {
            for (int j = 0; j<pairs.size();j++) {
                String[] p = pairs.get(i);
                String[] p2 = pairs.get(j);

                if (p[0].equals(p2[2]) && p[2].equals(p2[0])) {
                    int temp = Integer.parseInt(p[1]) + Integer.parseInt(p2[1]);
                    String stringTemp = p[0].charAt(0) + Character.toString(p2[0].charAt(0));
                    ArrayList<String> t = new ArrayList<>();
                    t.add(stringTemp);
                    t.add(Integer.toString(temp));
                    relations.add(t);
                }
            }
        }
        return relations;
    }
}
