package main;

import java.util.ArrayList;
import java.util.List;

public class ReindeerOlympics {

    private static final int DURATION = 2503;

    private String input;
    private List<Reindeer> reindeers;


    public ReindeerOlympics(String input){
        this.input = input;
    }

    public int solve1(){
        parse();
        for(int i = 0; i<= DURATION;i++){
            for(Reindeer r : reindeers){
                r.setDistance();
            }
        }
        return getMaxDistance();
    }

    public int solve2(){
        parse();
        for (int i = 0; i<DURATION; i++){
            for (Reindeer r: reindeers){
                r.setDistance();
            }
            setPoints();
        }
        return getMaxPoints();
    }

    private int getMaxPoints() {
        int maxPoints = 0;
        for(Reindeer r : reindeers){
            maxPoints = Math.max(r.getPoints(),maxPoints);
        }
        return maxPoints;
    }

    private void setPoints() {
        int maxDistance = getMaxDistance();

        for(Reindeer r : reindeers){
            if(r.getDistance() == maxDistance){
                r.addPoint();
            }
        }
    }

    private int getMaxDistance() {
        int maxDistance = 0;
        for(Reindeer r : reindeers){
            maxDistance = Math.max(r.getDistance(),maxDistance);
        }
        return maxDistance;
    }

    private void parse() {
        reindeers = new ArrayList<>();
        String[] deers = input.replaceAll("can fly",",")
                .replaceAll("km/s for",",")
                .replaceAll(", but then must rest for",",")
                .replaceAll("seconds","")
                .replaceAll(" ","")
                .replaceAll("\r","")
                .replaceAll("\\.","")
                .split("\n");
        for(String deer : deers){
            String[] temp = deer.split(",");
            reindeers.add(new Reindeer(temp[0],Integer.parseInt(temp[1]),Integer.parseInt(temp[2]),Integer.parseInt(temp[3])));
        }
    }
}
