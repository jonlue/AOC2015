package main;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HousesInVacuum {

    private String input;
    public HousesInVacuum(String input){
        this.input = input;
    }

    public int solve1(){
        HashMap<Point,Integer> houses = new HashMap<>();
        int x = 0;
        int y = 0;
        houses.put(new Point(x,y),1);
        for(char c : input.toCharArray()){
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
        return houses.size();
    }

    public int solve2(){
        HashMap<Point,Integer> houses = new HashMap<>();
        int x = 0;
        int y = 0;
        int rx = 0;
        int ry = 0;
        int count = 0;
        houses.put(new Point(x,y),2);
        for(char c : input.toCharArray()){
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
        return houses.size();
    }
}
