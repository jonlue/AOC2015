package main.days;

import main.AOCRiddle;

import java.util.HashMap;

public class Day07 extends AOCRiddle {
    public Day07(String in,int part) {
        super(in,part);
    }

    private final String[] input = getInput().replaceAll(" ","").split("\n");
    private static final String KEY = "a";
    private static final String IS_DIGIT = "\\d+";
    private static final int MODULO = 65536;



    public String solve1(){
        return String.valueOf(run(-1));
    }

    public String solve2(){
        return String.valueOf(run(run(-1)));
    }

    private int run(int startValue){
        HashMap<String, Integer> circuit = getEmptyMap();
        circuit.put("b",startValue);
        while(circuit.get(KEY) == -1) {
            for (String c : input) {
                String[] parts = c.split("->");
                int mode = getMode(parts[0]);
                parts[0] = parts[0].replaceAll("AND",",").replaceAll("OR",",").replaceAll("NOT","").replaceAll("RSHIFT",",").replaceAll("LSHIFT",",");
                String[] t = parts[0].split(",");
                int result =-1;
                if(circuit.get(parts[1]) == -1){
                    switch (mode) {
                        case (0):
                            if(t[0].matches(IS_DIGIT)) {
                                result = Integer.parseInt(parts[0]);
                            }else if (circuit.get(parts[0]) != -1){
                                result = circuit.get(parts[0]);
                            }

                            break;
                        case (1):
                            if(t[0].matches(IS_DIGIT)){
                                if (circuit.get(t[1]) != -1) {
                                    //number AND var
                                    int a1 = Integer.parseInt(t[0]);
                                    int a2 = circuit.get(t[1]);
                                    result = a1 & a2;
                                }
                            } else if (circuit.get(t[0]) != -1 && circuit.get(t[1]) != -1) {
                                //var AND var
                                int a1 = circuit.get(t[0]);
                                int a2 = circuit.get(t[1]);
                                result = a1 & a2;
                            }
                            break;
                        case (2):
                            if (circuit.get(t[0]) != -1 && circuit.get(t[1]) != -1) {
                                //var OR var
                                int a1 = circuit.get(t[0]);
                                int a2 = circuit.get(t[1]);
                                result =  a1 | a2;
                            }
                            break;
                        case (3):
                            if (circuit.get(t[0]) != -1) {
                                result = ~circuit.get(t[0]);
                                if(result<0){
                                    result+= MODULO;
                                }
                            }
                            break;
                        case (4):
                            if(t[1].matches(IS_DIGIT)){
                             if (circuit.get(t[0]) != -1) {
                                 //var OR number
                                 int a1 = circuit.get(t[0]);
                                 int a2 = Integer.parseInt(t[1]);
                                 result = a1 << a2;
                             }
                            } else if (circuit.get(t[0]) != -1 && circuit.get(t[1]) != -1) {
                                //var OR var
                                int a1 = circuit.get(t[0]);
                                int a2 = circuit.get(t[1]);
                                result =  a1 << a2;
                            }
                            break;
                        case (5):
                            if(t[1].matches(IS_DIGIT)){
                                if (circuit.get(t[0]) != -1) {
                                    //var OR number
                                    int a1 = circuit.get(t[0]);
                                    int a2 = Integer.parseInt(t[1]);
                                    result = a1 >>> a2;
                                }
                            } else if (circuit.get(t[0]) != -1 && circuit.get(t[1]) != -1) {
                                //var OR var
                                int a1 = circuit.get(t[0]);
                                int a2 = circuit.get(t[1]);
                                result = a1 >>> a2;
                            }
                            break;
                    }
                    if(result != -1) {
                        result = result % MODULO;
                        circuit.put(parts[1], result);
                    }
                }
            }
        }
        return circuit.get(KEY);
    }

    private HashMap<String, Integer> getEmptyMap() {
        HashMap<String,Integer> r = new HashMap<>();
        String[] in = getInput().replaceAll("AND",",").replaceAll("OR",",").replaceAll("LSHIFT",",").replaceAll("RSHIFT",",").replaceAll("NOT","").replaceAll("->",",").split("\n");
        for(String command : in){
            String[] parts = command.split(",");
            for(String p : parts){
                if(!p.matches(IS_DIGIT)){
                    r.putIfAbsent(p,-1);
                }
            }
        }
        return r;
    }

    private int getMode(String part) {
        if (part.contains("AND")) {
            return 1;
        } else if (part.contains("OR")) {
            return 2;
        } else if (part.contains("NOT")) {
            return 3;
        } else if (part.contains("LSHIFT")) {
            return 4;
        } else if (part.contains("RSHIFT")) {
            return 5;
        } else {
            return 0;
        }
    }
}
