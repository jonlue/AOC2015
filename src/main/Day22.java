package main;

import main.days.util.RPGChar;
import main.days.util.Spell;

import java.util.*;

public class Day22 extends AOCRiddle{
    public Day22(String in, int part) {
        super(in,part);
        parse();
    }

    private static final int START_HEALTH = 10; //10
    private static final int START_MANA = 250; //250
    private final List<Spell> spells = getSpells();
    private int bossHealth;
    private int bossDamage;

    public String solve1() {
        int minMana = Integer.MAX_VALUE;

        for(Spell s : spells){
            minMana = Math.min(simulate(new Spell(s), new RPGChar(START_HEALTH,START_MANA,0), new RPGChar(bossHealth,0,0), new ArrayList<>(), 0, minMana), minMana);
        }

        return String.valueOf(minMana);
    }

    // test 1: 53 + 173 = 226
    // test 2: 229 + 113 + 73 + 173 + 53 = 641

    public String solve2() {
        return "-1 ";
    }

    private int simulate(Spell spell, RPGChar me, RPGChar boss, List<Spell> activeSpells, int totalMana, int minMana){
        RPGChar[] temp;
        // player turn
        if(me.getMana() < spell.getCost() || totalMana > minMana){
            return Integer.MAX_VALUE;
        }
        for(Spell s : activeSpells){
            temp = s.cast(me, boss);
            me = temp[0];
            boss = temp[1];
        }
        activeSpells.removeIf(Spell::isInactive);
        if(activeSpells.contains(spell)){
            return Integer.MAX_VALUE;
        }
        spell.setActive();
        me.changeMana(-spell.getCost());
        if(spell.isInstant()){
            temp = spell.cast(me, boss);
            me = temp[0];
            boss = temp[1];
            if(boss.getHealth() <=0){
                return totalMana + spell.getCost();
            }
        }else {
            activeSpells.add(spell);
        }

        // boss turn
        for(Spell s : activeSpells){
            temp = s.cast(me, boss);
            me = temp[0];
            boss = temp[1];
        }
        if(boss.getHealth() <=0){
            return totalMana + spell.getCost();
        }
        activeSpells.removeIf(Spell::isInactive);
        me.changeHealth(me.getArmor()-bossDamage);
        if(me.getHealth() <= 0){
            return Integer.MAX_VALUE;
        }

        int mana = Integer.MAX_VALUE;
        for(Spell s : spells){
            List<Spell> copy = new ArrayList<>();
            for(Spell toCopy : activeSpells){
                copy.add(new Spell(toCopy));
            }
            mana = Math.min(simulate(new Spell(s),new RPGChar(me), new RPGChar(boss), copy, totalMana + spell.getCost(), Math.min(minMana, mana)), mana);
        }
        return mana;
    }

    private List<Spell> getSpells() {
        List<Spell> spells = new ArrayList<>();

        spells.add(new Spell("Magic Missile",53,4,0,0,0,0));
        spells.add(new Spell("Drain",73,2,0,0,2,0));
        spells.add(new Spell("Shield",113,0,7,6,0,0));
        spells.add(new Spell("Poison",173,3,0,6,0,0));
        spells.add(new Spell("Recharge",229,0,0,5,0,101));

        return spells;
    }



    private void parse() {
        String[] boss = getInput().replaceAll("Hit Points: ", "")
                .replaceAll("Damage: ", "")
                .split("\n");
        bossHealth = Integer.parseInt(boss[0]);
        bossDamage = Integer.parseInt(boss[1]);
    }
}
