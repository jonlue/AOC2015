package main.days.util;

import java.util.ArrayList;
import java.util.List;

public class RPGChar {

    private int health;
    private int mana;
    private int armor;
    private final List<Spell> spells;

    public RPGChar(int health, int mana, int armor, List<Spell> spells){
        this.health = health;
        this.mana = mana;
        this.armor = armor;
        this.spells = spells;
    }

    public RPGChar(RPGChar c){
        this.health = c.health;
        this.mana = c.mana;
        this.armor = c. armor;
        this.spells = getCopyOfSpellList(c.spells);
    }

    public void heal(int lifePoints){
        health += lifePoints;
    }

    public void takeDamage(int damage){
        health -= damage;
    }

    public void cast(int spellId, RPGChar target){
        mana -= spells.get(spellId).getCost();
        if(spells.get(spellId).isInstant()) {
            target.takeDamage(spells.get(spellId).getDamage());
            heal(spells.get(spellId).getHealth());
        }else{
            spells.get(spellId).setActive();
        }
    }

    public int getHealth(){
        return this.health;
    }

    public void changeMana(int mana){
        this.mana += mana;
    }

    public int getMana(){
        return this.mana;
    }

    public void setArmor(int armor){
        this.armor = armor;
    }

    public int getArmor(){
        return this.armor;
    }

    public List<Spell> getSpells(){
        return spells;
    }

    public boolean isAnActiveSpell(int id){
        return spells.get(id).isActive();
    }

    private List<Spell> getCopyOfSpellList(List<Spell> toCopy)
    {
        if(toCopy == null) return null;
        List<Spell> copy = new ArrayList<>();
        for (Spell c : toCopy) {
            copy.add(new Spell(c));
        }
        return copy;
    }
}
