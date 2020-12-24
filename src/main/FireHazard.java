package main;

import java.awt.*;
import java.util.HashMap;

public class FireHazard {

    private String input;

    private String[] modes = {"turn on ", "turn off ","toggle "};

    public FireHazard(String input){
        this.input = parse(input);
    }



    public int solve1(){
        String[] coordinates = input.split("\n");
        HashMap<Point, Boolean> lights = new HashMap<>();

        for(String c : coordinates) {
            int mode;
            if (c.contains(modes[0])) {
                mode = 0;
            } else if (c.contains(modes[1])) {
                mode = 1;
            } else {
                mode = 2;
            }
            c = c.replaceAll(modes[0], "").replaceAll(modes[1], "").replaceAll(modes[2], "");

            String[] ps = c.split(",");
            int x1s = Integer.parseInt(ps[0]);
            int y1s = Integer.parseInt(ps[1]);
            int x2 = Integer.parseInt(ps[2]);
            int y2 = Integer.parseInt(ps[3]);

            for (int x = x1s; x <= x2; x++) {
                for (int y = y1s; y <= y2; y++) {
                    Point temp = new Point(x, y);
                    lights.putIfAbsent(temp, false);
                    switch (mode) {
                        case (0):
                            lights.put(temp, true);
                            break;
                        case (1):
                            lights.put(temp, false);
                            break;
                        case (2):
                            lights.put(temp, !lights.get(temp));
                            break;
                    }
                }
            }
        }
        return countLights(lights);
    }

    private int countLights(HashMap<Point, Boolean> lights) {
        int sum = 0;
        for(Point p : lights.keySet()){
            sum += lights.get(p)? 1:0;
        }
        return sum;
    }

    public int solve2(){
        String[] coordinates = input.split("\n");
        HashMap<Point, Integer> lights = new HashMap<>();

        for(String c : coordinates) {
            int mode;
            if (c.contains(modes[0])) {
                mode = 0;
            } else if (c.contains(modes[1])) {
                mode = 1;
            } else {
                mode = 2;
            }
            c = c.replaceAll(modes[0], "").replaceAll(modes[1], "").replaceAll(modes[2], "");

            String[] ps = c.split(",");
            int x1s = Integer.parseInt(ps[0]);
            int y1s = Integer.parseInt(ps[1]);
            int x2 = Integer.parseInt(ps[2]);
            int y2 = Integer.parseInt(ps[3]);

            for (int x = x1s; x <= x2; x++) {
                for (int y = y1s; y <= y2; y++) {
                    Point temp = new Point(x, y);
                    lights.putIfAbsent(temp, 0);
                    switch (mode) {
                        case (0):
                            lights.put(temp, lights.get(temp)+1);
                            break;
                        case (1):
                            lights.put(temp, Math.max(lights.get(temp)-1,0));
                            break;
                        case (2):
                            lights.put(temp, lights.get(temp)+2);
                            break;
                    }
                }
            }
        }
        return countBrightness(lights);
    }

    private int countBrightness(HashMap<Point, Integer> lights) {
        int sum = 0;
        for(Point p : lights.keySet()){
            sum += lights.get(p);
        }
        return sum;
    }

    private String parse(String input){
        return input.replaceAll(" through ",",").replaceAll("\r","");
    }
}
