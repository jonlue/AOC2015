package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Day19 extends AOCRiddle{
    public Day19(String in, int part){
        super(in, part);
        input = getInput().replaceAll(" ","").split("\n");
        getReplacements();
    }

    private final String[] input;
    private String molecule;
    private final List<String[]> replacements = new ArrayList<>();

    public String solve1(){
        HashSet<String> unique = new HashSet<>();

        for(String[] r : replacements){
            if(r[0].equals("e"))continue;
            StringBuilder sb = new StringBuilder(molecule);
            int size = r[0].length();
            boolean found = false;

            for(int i = 0; i<molecule.length();i++){
                for(int j = 0; j<size;j++){
                    if(i+j>=molecule.length()) {
                        found = false;
                        break;
                    }else if (molecule.charAt(i + j) != r[0].charAt(j)) {
                        found = false;
                        break;
                    }
                    found = true;
                }
                if(found){
                    unique.add(sb.replace(i,i+r[0].length(),r[1]).toString());
                    sb = new StringBuilder(molecule);
                }
            }
        }

        return String.valueOf(unique.size());
    }

    public String solve2(){
        int minSteps = Integer.MAX_VALUE;
        for(String[] r : replacements){
            if(!r[0].equals("e")){
                continue;
            }
                int steps = 0;
                StringBuilder sb = new StringBuilder(r[1]);

                steps++;
                if(sb.toString().equals(molecule)) {
                    minSteps = Math.min(steps, minSteps);
                }
        }
        return String.valueOf(minSteps);
    }

    private void getReplacements() {
        for(String s : input){
            if(!s.contains("=>")){
                molecule = s;
            }else{
                replacements.add(s.split("=>"));
            }
        }
    }
}
