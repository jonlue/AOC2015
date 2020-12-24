package main;

import java.util.HashMap;

public class AssemblyRequired {

    private final String input;
    private static final String KEY = "a";
    private static final String IS_DIGIT = "\\d+";
    private static final int MODULO = 65536;

    public AssemblyRequired(String in) {
        this.input = in.replaceAll("\r","").replaceAll(" ","");
    }

    public int solve1(){
        return solve1(-1);
    }
    private int solve1(int startValue){
        HashMap<String, Integer> circuit = getEmptyMap();
        circuit.put("b",startValue);
        String[] ins = input.split("\n");
        while(circuit.get(KEY) == -1) {
            for (String c : ins) {
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
                        //System.out.println(parts[1] + " " + circuit.get(parts[1]));
                    }


                }
            }
        }
        return circuit.get(KEY);
    }

    private HashMap<String, Integer> getEmptyMap() {
        HashMap<String,Integer> r = new HashMap<>();
        String[] in = input.replaceAll("AND",",").replaceAll("OR",",").replaceAll("LSHIFT",",").replaceAll("RSHIFT",",").replaceAll("NOT","").replaceAll("->",",").split("\n");
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
        if(part.contains("AND")){
            return 1;
        }else if(part.contains("OR")){
            return 2;
        }else if(part.contains("NOT")){
            return 3;
        }else if(part.contains("LSHIFT")){
            return 4;
        }else if(part.contains("RSHIFT")){
            return 5;
        }else{
            return 0;
        }
    }

    public int solve2(){
        return solve1(solve1());
    }

}
