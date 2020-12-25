package main.days;

import main.AOCRiddle;

import java.util.HashMap;

public class Day23 extends AOCRiddle {
    public Day23(String in, int part) {
        super(in, part);
        parse();
    }

    private String[] instructions;
    private static final String HALF = "hlf";
    private static final String TRIPLE = "tpl";
    private static final String INCREMENT = "inc";
    private static final String JUMP = "jmp";
    private static final String JUMP_EVEN = "jie";
    private static final String JUMP_ONE = "jio";


    public String solve1() {
        return String.valueOf(run(0));
    }

    public String solve2() {
        return String.valueOf(run(1));
    }

    private int run(int startValue) {
        HashMap<String, Integer> registers = new HashMap<>();
        registers.put("a", startValue);
        registers.put("b", 0);

        for (int i = 0; i < instructions.length; ) {
            //System.out.println(instructions[i]);
            String[] temp = instructions[i].split(",");
            int t, v;
            switch (temp[0]) {
                case HALF: {
                    t = registers.get(temp[1]) / 2;
                    registers.put(temp[1], t);
                    i += 1;
                    break;
                }
                case TRIPLE: {
                    t = registers.get(temp[1]) * 3;
                    registers.put(temp[1], t);
                    i += 1;
                    break;
                }
                case INCREMENT: {
                    t = registers.get(temp[1]) + 1;
                    registers.put(temp[1], t);
                    i += 1;
                    break;
                }
                case JUMP:
                    v = Integer.parseInt(temp[1]);
                    i += v;
                    break;
                case JUMP_EVEN:
                    if (registers.get(temp[1]) % 2 == 0) {
                        v = Integer.parseInt(temp[2]);
                        i += v;
                    } else {
                        i += 1;
                    }
                    break;
                case JUMP_ONE:
                    if (registers.get(temp[1]) == 1) {
                        v = Integer.parseInt(temp[2]);
                        i += v;
                    } else {
                        i += 1;
                    }
                    break;
            }
        }
        return registers.get("b");
    }

    private void parse() {
        instructions = getInput().replaceAll(",", "")
                .replaceAll(" ", ",")
                .split("\n");
    }
}
