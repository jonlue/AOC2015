package main.days.util;

import java.util.Objects;

public class Spell {



    private final String name;
    private final int cost;
    private final int damage;
    private final int armor;
    private final int duration;
    private final int health;
    private final int mana;
    private final boolean isInstant;
    private int timer;
    private boolean isActive;

    public Spell(String name,int cost, int damage, int armor ,int duration, int health, int mana){
        this.name=name;
        this.armor = armor;
        this.cost = cost;
        this.damage = damage;
        this.duration = duration;
        this.health = health;
        this.mana = mana;
        this.isInstant = duration == 0;
        this.timer = duration;
        this.isActive = false;
    }

    public Spell(Spell s){
        this.name = s.name;
        this.armor = s.armor;
        this.cost = s.cost;
        this.damage = s.damage;
        this.duration = s.duration;
        this.health = s.health;
        this.mana = s.mana;
        this.timer = s.timer;
        this.isActive = s.isActive;
        this.isInstant = s.isInstant;
    }

    public int getCost() {
        return cost;
    }

    public void setActive(){
        this.isActive = true;
    }

    public boolean isActive(){
        return this.isActive;
    }

    public boolean isInstant(){
        return isInstant;
    }


    private void addTimer(){
        timer--;
        if(timer == 0){
            timer = duration;
            isActive = false;
        }
    }

    public void causeEffect(RPGChar caster, RPGChar receiver){
        if(isInstant) return;
        if(isActive) {
            caster.heal(health);
            caster.changeMana(mana);
            caster.setArmor(Math.max(armor, caster.getArmor()));
            receiver.takeDamage(damage);
            addTimer();
        }
        if(!isActive && name.equals("Shield")){
            caster.setArmor(0);
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spell spell = (Spell) o;
        return Objects.equals(name, spell.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
