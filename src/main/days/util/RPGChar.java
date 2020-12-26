package main.days.util;

public class RPGChar {

    private int health;
    private int mana;
    private int armor;

    public RPGChar(int health, int mana, int armor){
        this.health = health;
        this.mana = mana;
        this.armor = armor;
    }

    public RPGChar(RPGChar c){
        this.health = c.health;
        this.mana = c.mana;
        this.armor = c. armor;
    }

    public void changeHealth(int health){
        this.health += health;
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
}
