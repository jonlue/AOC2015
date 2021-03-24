package main;

import java.util.*;

public class Day19 extends AOCRiddle{
    public Day19(String in, int part){
        super(in, part);
        input = getInput().replaceAll(" ","").split("\n");
        getReplacements();
    }

    private String[] input;
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
        input = rename().replace(" ","").split("\n");
        getReplacements();
        int count = 0;
        while(!molecule.equals("e")){
            for(String[] r : replacements){
                while(molecule.contains(r[1])){
                    count++;
                    molecule = molecule.replaceFirst(r[1],r[0]);
                }
            }
        }

        return String.valueOf(count);
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

    private String rename(){
        String in = getInput();
        char newChar = 'A';
        String[] keys = getInput().replaceAll(" => [a-zA-Z]*\n", ",").split(",");
        String before ="";

        for(String s : keys){
            if(s.equals("e")){
                break;
            }
            if(s.equals(before)){
                continue;
            }
            in = in.replaceAll(s , String.valueOf(newChar));
            newChar++;
            before = s;
        }
        HashSet<String> alreadyReplaced = new HashSet<>();
        for(String s : in.split("\n")){
            for(int i = 0; i< s.length()-1; i++){
                String t =""+ s.charAt(i) + s.charAt(i+1);
                if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'
                        && s.charAt(i+1) >='a' && s.charAt(i+1) <= 'z'
                        && !alreadyReplaced.contains(t)){
                    alreadyReplaced.add(t);
                    in = in.replace(t,String.valueOf(newChar));
                    newChar++;
                }
            }
        }

        return in;
    }
}
