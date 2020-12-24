package main;

import java.util.ArrayList;
import java.util.List;

public class WizardSimulator {

    private String input;
    private static final int START_HEALTH = 50;
    private static final int START_MANA = 500;

    public WizardSimulator(String in) {
        this.input = in;
    }

    public int solve1() {
        List<Spell> spells = getSpells();
        String[] boss = input.replaceAll("Hit Points: ","").replaceAll("Damage: ","").split("\n");
        int bHealth = Integer.parseInt(boss[0]);
        int bDamage = Integer.parseInt(boss[1]);

        int minMana = Integer.MAX_VALUE;

        int manaCost = 0;



        minMana = Math.min(manaCost,minMana);
        return 0;
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

    public int solve2() {
        return 0;
    }
}
