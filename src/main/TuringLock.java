package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TuringLock {

    private String input;
    private int a = 0,b = 0;

    private static final String HALF ="hlf";
    private static final String TRIPLE ="tpl";
    private static final String INCREMENT ="inc";
    private static final String JUMP ="jmp";
    private static final String JUMP_EVEN ="jie";
    private static final String JUMP_ONE ="jio";

    public TuringLock(String input){
        this.input = input.replaceAll("\r","");
    }

    public int solve1(){
        return solve1(0);
    }

    private int solve1(int startValue){
        String[] instructions = input.replaceAll(",","").replaceAll(" ",",").split("\n");
        HashMap<String,Integer> registers = new HashMap<>();
        registers.put("a",startValue);
        registers.put("b",0);

        for(int i = 0; i<instructions.length;){
            //System.out.println(instructions[i]);
            String[] temp = instructions[i].split(",");
            if(temp[0].equals(HALF)){
                int t = registers.get(temp[1])/2;
                registers.put(temp[1],t);
                i+=1;
            }else if(temp[0].equals(TRIPLE)){
                int t = registers.get(temp[1])*3;
                registers.put(temp[1],t);
                i+=1;
            }else if(temp[0].equals(INCREMENT)) {
                int t = registers.get(temp[1]) + 1;
                registers.put(temp[1], t);
                i += 1;
            }else if(temp[0].equals(JUMP)) {
                int v = Integer.parseInt(temp[1]);
                i += v;
            }else if(temp[0].equals(JUMP_EVEN)) {
                if(registers.get(temp[1])%2 == 0){
                    int v = Integer.parseInt(temp[2]);
                    i += v;
                }else{
                    i += 1;
                }
            }else if(temp[0].equals(JUMP_ONE)) {
                if(registers.get(temp[1]) == 1){
                    int v = Integer.parseInt(temp[2]);
                    i += v;
                }else{
                    i += 1;
                }
            }
        }
        return registers.get("b");
    }

    public int solve2(){
        return solve1(1);
    }
}
