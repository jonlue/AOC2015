package main.days;

import main.AOCRiddle;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Day03 extends AOCRiddle {
    public Day03(String in, int part){
        super(in,part);
    }

    private final char[] lines = getInput().toCharArray();

    public String solve1(){
        Map<Point,Integer> houses = new HashMap<>();
        int x = 0;
        int y = 0;
        houses.put(new Point(x,y),1);
        for(char c : lines){
            if(c == '>'){
                x++;
            }else if(c == '<'){
                x--;
            }else if(c == '^'){
                y++;
            }else if (c == 'v'){
                y--;
            }
            Point temp = new Point(x,y);
            houses.putIfAbsent(temp,0);
            houses.putIfAbsent(temp,houses.get(temp) + 1);
        }
        return String.valueOf(houses.size());
    }

    public String solve2(){
        HashMap<Point,Integer> houses = new HashMap<>();
        int x = 0;
        int y = 0;
        int rx = 0;
        int ry = 0;
        int count = 0;
        houses.put(new Point(x,y),2);
        for(char c : lines){
            if(c == '>'){
                if(count%2 == 0) {
                    x++;
                }else{
                    rx++;
                }
            }else if(c == '<'){
                if(count%2 == 0) {
                    x--;
                }else{
                    rx--;
                }
            }else if(c == '^'){
                if(count%2 == 0) {
                    y++;
                }else{
                    ry++;
                }
            }else if (c == 'v'){
                if(count%2 == 0) {
                    y--;
                }else{
                    ry--;
                }
            }
            count++;
            Point temp;
            if(count % 2 == 0){
                temp = new Point(x,y);
            } else{
                temp = new Point(rx,ry);
            }
            houses.putIfAbsent(temp,0);
            houses.putIfAbsent(temp,houses.get(temp) + 1);
        }
        return String.valueOf(houses.size());
    }
}
