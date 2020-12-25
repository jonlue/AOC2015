package main;

import main.days.util.Spell;

import java.util.ArrayList;
import java.util.List;

public class Day22 extends AOCRiddle{
    public Day22(String in, int part) {
        super(in,part);
        parse();
    }

    private String[] boss;
    private static final int START_HEALTH = 50;
    private static final int START_MANA = 500;
    private List<Spell> spells = getSpells();
    private int bossHealth;
    private int bossDamage;

    public String solve1() {
        int minMana = Integer.MAX_VALUE;
        int manaCost = 0;


        minMana = Math.min(manaCost,minMana);
        return "-1";
    }

    public String solve2() {
        return "-1";
    }

    private List<Spell> getSpells() {
        List<Spell> spells = new ArrayList<>();

        spells.add(new Spell("Magic Missle",53,4,0,0,0,0));
        spells.add(new Spell("Drain",73,2,0,0,2,0));
        spells.add(new Spell("Shield",113,0,7,6,0,0));
        spells.add(new Spell("Poison",173,3,0,6,0,0));
        spells.add(new Spell("Recharge",229,0,0,5,0,101));

        return spells;
    }



    private void parse() {
        boss = getInput().replaceAll("Hit Points: ","")
                .replaceAll("Damage: ","")
                .split("\n");
        bossHealth = Integer.parseInt(boss[0]);
        bossDamage = Integer.parseInt(boss[1]);
    }
}
