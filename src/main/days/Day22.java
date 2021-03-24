package main.days;

import main.AOCRiddle;
import main.days.util.RPGChar;
import main.days.util.Spell;

import java.util.*;

public class Day22 extends AOCRiddle {
    public Day22(String in, int part) {
        super(in, part);
        parse();
    }

    private static final int START_HEALTH = 50;
    private static final int START_MANA = 500;
    private final List<Spell> spells = getSpells();
    private static int bossHealth;
    private int bossDamage;

    public String solve1() {
        return String.valueOf(sim(false));
    }

    public String solve2() {
        return String.valueOf(sim(true));
    }

    private int sim(boolean part2){
        int minMana = Integer.MAX_VALUE;

        for (int i = 0; i < spells.size(); i++) {
            minMana = Math.min(
                    sim(new RPGChar(START_HEALTH, START_MANA, 0, getCopy(spells)),
                            new RPGChar(bossHealth, 0, 0, null),
                            0, i, Integer.MAX_VALUE, part2),

                    minMana);
        }

        return minMana;
    }


    private int sim(RPGChar me, RPGChar boss, int sum, int spellId, int currentMinimum,boolean part2) {
        if(part2) me.takeDamage(1);
        if (me.getHealth() <= 0) return Integer.MAX_VALUE;
        // my turn
        if (turn(me, boss, spellId, true)) return Integer.MAX_VALUE;
        sum += spells.get(spellId).getCost();
        // check if boss died
        if (boss.getHealth() <= 0) {
            return sum;
        }
        // enemy turn
        if (causeOnActiveSpell(me, boss)) return sum;

        turn(me, boss, spellId, false);
        // check if i died -> lost -> return max
        if (me.getHealth() <= 0) return Integer.MAX_VALUE;
        // check if boss died -> won -> return cost
        if (boss.getHealth() <= 0) {
            return sum;
        }


        // At start of turn act on all active spells
        if (causeOnActiveSpell(me, boss)) return sum;
        // if nobody Died -> next rounds
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < me.getSpells().size(); i++) {
            if (me.getSpells().get(i).isActive()
                    || me.getSpells().get(i).getCost() + sum > currentMinimum
                    || spells.get(i).getCost() > me.getMana()) continue;

            // get minimum mana used for all sub rounds with all spells
            min = Math.min(
                    sim(new RPGChar(me), new RPGChar(boss), sum, i, currentMinimum, part2),
                    min);
            currentMinimum = Math.min(min, currentMinimum);
        }
        return min;
    }

    private boolean causeOnActiveSpell(RPGChar me, RPGChar boss) {
        for (Spell s : me.getSpells()) {
            s.causeEffect(me, boss);
        }
        return boss.getHealth() <= 0;
    }

    private boolean turn(RPGChar me, RPGChar boss, int spellId, boolean myTurn) {
        // In my Turn Cast Spell
        if (myTurn) {
            // active spells can't be casted
            if (me.isAnActiveSpell(spellId)) return true;
            me.cast(spellId, boss);
        } else {
            // receive Damage
            me.takeDamage(Math.max(bossDamage - me.getArmor(), 1));
        }
        return false;
    }

    private List<Spell> getSpells() {
        List<Spell> spells = new ArrayList<>();

        spells.add(new Spell("Magic Missile", 53, 4, 0, 0, 0, 0));
        spells.add(new Spell("Drain", 73, 2, 0, 0, 2, 0));
        spells.add(new Spell("Shield", 113, 0, 7, 6, 0, 0));
        spells.add(new Spell("Poison", 173, 3, 0, 6, 0, 0));
        spells.add(new Spell("Recharge", 229, 0, 0, 5, 0, 101));

        return spells;
    }

    private List<Spell> getCopy(List<Spell> toCopy){
        List<Spell> copy = new ArrayList<>();
        for(Spell s : toCopy){
            copy.add(new Spell(s));
        }
        return copy;
    }


    private void parse() {
        String[] boss = getInput().replaceAll("Hit Points: ", "")
                .replaceAll("Damage: ", "")
                .split("\n");
        bossHealth = Integer.parseInt(boss[0]);
        bossDamage = Integer.parseInt(boss[1]);
    }
}
