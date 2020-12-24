package main;

import java.util.ArrayList;
import java.util.List;

public class RPGSimulator {

    private String input;
    private int hitpoint = 100;

    public RPGSimulator(String in){
        this.input = in.replaceAll("\r","").replaceAll(" ","").replaceAll(":","").replaceAll("HitPoints","").replaceAll("Damage","").replaceAll("Armor","");
    }
    public int solve1() {
        String[] boss = input.split("\n");
        List<Equipment> weapons = getWeapons();
        List<Equipment> armor = getArmor();
        List<Equipment> rings = getRings();

        int minCost = Integer.MAX_VALUE;

        int health = Integer.parseInt(boss[0]);
        int attack = Integer.parseInt(boss[1]);
        int defense = Integer.parseInt(boss[2]);

        for(Equipment w : weapons){
            for (Equipment a : armor){
                for (Equipment r : rings){

                    if(fightWon(w,a,r,health,defense,attack)){
                        minCost = Math.min(w.getCost()+a.getCost()+r.getCost(),minCost);
                    }
                    hitpoint = 100;
                }
            }
        }
        return minCost;
    }

    public int solve2() {
        String[] boss = input.split("\n");
        List<Equipment> weapons = getWeapons();
        List<Equipment> armor = getArmor();
        List<Equipment> rings = getRings();

        int maxCost = Integer.MIN_VALUE;

        int health = Integer.parseInt(boss[0]);
        int attack = Integer.parseInt(boss[1]);
        int defense = Integer.parseInt(boss[2]);

        for(Equipment w : weapons){
            for (Equipment a : armor){
                for (Equipment r : rings){

                    if(!fightWon(w,a,r,health,defense,attack)){
                        maxCost = Math.max(w.getCost()+a.getCost()+r.getCost(),maxCost);
                    }
                    hitpoint = 100;
                }
            }
        }
        return maxCost;
    }

    private boolean fightWon(Equipment w, Equipment a, Equipment r, int health, int defense, int attack) {
        int damage = w.getDamage() + a.getDamage() + r.getDamage();
        int armor = w.getArmor() + a. getArmor() + r.getArmor();

        return Math.max(damage-defense,1) >= Math.max(attack-armor,1);
        /*
        while(hitpoint > 0 && health > 0){
            health -= Math.max(damage-defense,1);
            if(health<0) break;
            hitpoint -= Math.max(attack-armor,1);
        }
        return hitpoint>0;

         */
    }


    private List<Equipment> getWeapons(){
        List<Equipment> w = new ArrayList<>();
        w.add(new Equipment("Dagger",8,4,0));
        w.add(new Equipment("Shortsword",10,5,0));
        w.add(new Equipment("Warhammer",25,6,0));
        w.add(new Equipment("Longsword",40,7,0));
        w.add(new Equipment("Greataxe",74,8,0));
        return w;
    }

    private List<Equipment> getArmor(){
        List<Equipment> w = new ArrayList<>();
        w.add(new Equipment("-",0,0,0));
        w.add(new Equipment("Leather",13,0,1));
        w.add(new Equipment("Chainmail",31,0,2));
        w.add(new Equipment("Splintmail",53,0,3));
        w.add(new Equipment("Bandedmail",75,0,4));
        w.add(new Equipment("Platemail",102,0,5));
        return w;
    }

    private List<Equipment> getRings(){
        List<Equipment> w = new ArrayList<>();
        w.add(new Equipment("-",0,0,0));
        w.add(new Equipment("D1",25,1,0));
        w.add(new Equipment("D2",50,2,0));
        w.add(new Equipment("D3",100,3,0));
        w.add(new Equipment("A1",20,0,1));
        w.add(new Equipment("A2",40,0,2));
        w.add(new Equipment("A3",80,0,3));

        w.add(new Equipment("D1D2",75,3,0));
        w.add(new Equipment("D1D3",125,4,0));
        w.add(new Equipment("D2D3",150,5,0));

        w.add(new Equipment("D1A1",45,1,1));
        w.add(new Equipment("D1A2",65,1,2));
        w.add(new Equipment("D1A3",105,1,3));

        w.add(new Equipment("D2A1",70,2,1));
        w.add(new Equipment("D2A2",90,2,2));
        w.add(new Equipment("D2A3",130,2,3));

        w.add(new Equipment("D3A1",120,3,1));
        w.add(new Equipment("D3A2",140,3,2));
        w.add(new Equipment("D3A3",180,3,3));

        w.add(new Equipment("A1A2",60,0,3));
        w.add(new Equipment("A1A3",100,0,4));
        w.add(new Equipment("A2A3",120,0,5));

        return w;
    }
}
