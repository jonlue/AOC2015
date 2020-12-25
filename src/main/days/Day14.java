package main.days;

import main.AOCRiddle;
import main.days.util.Reindeer;

import java.util.ArrayList;
import java.util.List;

public class Day14 extends AOCRiddle {
    public Day14(String in, int part) {
        super(in, part);
        parse();
    }

    private static final int DURATION = 2503;
    private List<Reindeer> reindeers;

    public String solve1() {
        for (int i = 0; i <= DURATION; i++) {
            for (Reindeer r : reindeers) {
                r.setDistance();
            }
        }
        return String.valueOf(getMaxDistance());
    }

    public String solve2() {
        for (int i = 0; i < DURATION; i++) {
            for (Reindeer r : reindeers) {
                r.setDistance();
            }
            setPoints();
        }
        return String.valueOf(getMaxPoints());
    }

    private int getMaxPoints() {
        int maxPoints = 0;
        for (Reindeer r : reindeers) {
            maxPoints = Math.max(r.getPoints(), maxPoints);
        }
        return maxPoints;
    }

    private void setPoints() {
        int maxDistance = getMaxDistance();

        for (Reindeer r : reindeers) {
            if (r.getDistance() == maxDistance) {
                r.addPoint();
            }
        }
    }

    private int getMaxDistance() {
        int maxDistance = 0;
        for (Reindeer r : reindeers) {
            maxDistance = Math.max(r.getDistance(), maxDistance);
        }
        return maxDistance;
    }

    private void parse() {
        reindeers = new ArrayList<>();
        String[] deers = getInput().replaceAll("can fly", ",")
                .replaceAll("km/s for", ",")
                .replaceAll(", but then must rest for", ",")
                .replaceAll("seconds", "")
                .replaceAll(" ", "")
                .replaceAll("\r", "")
                .replaceAll("\\.", "")
                .split("\n");
        for (String deer : deers) {
            String[] temp = deer.split(",");
            reindeers.add(new Reindeer(temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3])));
        }
    }
}
