package main.days.util;

public class Spell {

    private String name;
    private int cost,damage,armor,duration,health,mana;

    public Spell(String name,int cost, int damage, int armor ,int duration, int health, int mana){
        this.name=name;
        this.armor = armor;
        this.cost = cost;
        this.damage = damage;
        this.duration = duration;
        this.health = health;
        this.mana = mana;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
