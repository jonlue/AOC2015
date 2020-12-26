package main.days.util;

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
        this.timer = 0;
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
    public boolean isInactive(){
        return !this.isActive;
    }

    public boolean isInstant(){
        return isInstant;
    }

    private void addTimer(){
        timer++;
        if(timer >= duration){
            timer = 0;
            isActive = false;
        }
    }

    public RPGChar[] cast(RPGChar caster, RPGChar receiver){
        if(isActive) {
            caster.changeHealth(health);
            caster.changeMana(mana);
            caster.setArmor(Math.max(armor, caster.getArmor()));
            receiver.changeHealth(-damage);
            addTimer();
        }else if(name.equals("Shield")){
            caster.setArmor(0);
        }
        return new RPGChar[]{caster, receiver};
    }

}
