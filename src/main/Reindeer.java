package main;

public class Reindeer {

    private String name;
    private int speed, duration,restDuration;
    private int distance;
    private boolean resting;
    private int points;

    private int restTime, flightTime;
    public Reindeer(String name, int speed, int duration, int restDuration){
        this.name = name;
        this.speed=speed;
        this.duration = duration;
        this.restDuration = restDuration;
        this.distance = 0;
        this.resting = false;
        this.flightTime = 0;
        this.restTime = restDuration;
        this.points = 0;
    }

    public int getDistance() {
        return distance;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint(){
        this.points++;
    }

    public void setDistance(){
        if(!resting){
            distance +=speed;
            flightTime++;
            if(flightTime == duration){
                resting = true;
                restTime = 0;
            }
        }else{
            restTime++;
            if(restTime==restDuration){
                flightTime = 0;
                resting = false;
            }
        }
    }

    public String getName() {
        return name;
    }
}
