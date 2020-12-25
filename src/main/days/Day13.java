package main.days;

import main.AOCRiddle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Day13 extends AOCRiddle {
    public Day13(String in, int part) {
        super(in, part);
        parse();
    }

    private final List<String> myList = new ArrayList<>();
    private List<List<String>> relations;

    public String solve1() {
        List<List<String>> as = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            as.add(relations.get(i));
        }

        List<String> possibilities = permute("BCDEFGM", 0, 6);

        int maxSum = Integer.MIN_VALUE;
        for (String s : possibilities) {
            int sum = 0;
            for (List<String> strings : as) {
                if (strings.get(0).charAt(1) == s.charAt(0)) {
                    sum += Integer.parseInt(strings.get(1));
                    for (int j = 0; j < 7; j++) {
                        for (List<String> a : relations) {
                            if (j == 6) {
                                if (s.charAt(j) == a.get(0).charAt(0) && 'A' == a.get(0).charAt(1)) {
                                    sum += Integer.parseInt(a.get(1));
                                    break;
                                }
                            } else if (s.charAt(j) == a.get(0).charAt(0) && s.charAt(j + 1) == a.get(0).charAt(1)) {
                                sum += Integer.parseInt(a.get(1));
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            System.out.println();
            maxSum = Math.max(sum, maxSum);
        }
        return String.valueOf(maxSum);
    }

    public String solve2() {
        relations.addAll(getMyself());
        List<List<String>> as = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            as.add(relations.get(i));
        }
        List<String> temp = new ArrayList<>();
        temp.add("AN");
        temp.add("0");
        as.add(temp);

        List<String> possibilities = permute("BCDEFGMN", 0, 7);

        int maxSum = Integer.MIN_VALUE;
        for (String s : possibilities) {
            int sum = 0;
            for (List<String> strings : as) {
                if (strings.get(0).charAt(1) == s.charAt(0)) {
                    sum += Integer.parseInt(strings.get(1));
                    for (int j = 0; j < 8; j++) {
                        for (List<String> a : relations) {
                            if (j == 7) {
                                if (s.charAt(j) == a.get(0).charAt(0) && 'A' == a.get(0).charAt(1)) {
                                    sum += Integer.parseInt(a.get(1));
                                    break;
                                }
                            } else if (s.charAt(j) == a.get(0).charAt(0) && s.charAt(j + 1) == a.get(0).charAt(1)) {
                                sum += Integer.parseInt(a.get(1));
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            maxSum = Math.max(sum, maxSum);
        }
        return String.valueOf(maxSum);
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

    private List<String> permute(String str, int l, int r) {
        if (l == r)
            myList.add(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
        return myList;
    }

    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }


    private List<List<String>> getRelations(String[] list) {
        List<List<String>> relations = new ArrayList<>();
        List<String[]> pairs = new ArrayList<>();
        for (String s : list) {
            pairs.add(s.split(","));
        }

        for (String[] p1 : pairs) {
            for (String[] p2 : pairs) {

                if (p1[0].equals(p2[2]) && p1[2].equals(p2[0])) {
                    int temp = Integer.parseInt(p1[1]) + Integer.parseInt(p2[1]);
                    String stringTemp = p1[0].charAt(0) + Character.toString(p2[0].charAt(0));
                    ArrayList<String> t = new ArrayList<>();
                    t.add(stringTemp);
                    t.add(Integer.toString(temp));
                    relations.add(t);
                }
            }
        }
        return relations;
    }

    private void parse() {
        String[] in = getInput().replaceAll("would", ",")
                .replaceAll("happiness units by sitting next to", ",")
                .replaceAll(" ", "")
                .replaceAll("gain", "")
                .replaceAll("lose", "-")
                .replaceAll("\r", "")
                .replaceAll("\\.", "")
                .split("\n");

        relations = getRelations(in);
    }
}
