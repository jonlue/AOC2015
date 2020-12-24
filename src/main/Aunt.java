package main;

public class Aunt {

    private int id;
    private int[] values;
    //,children, cats, samoyeds,pomeranians,akitas,vizlas,goldfish,trees,cars,perfumes;


    public Aunt(int id, int[] values) {
        this.id = id;
        this.values = values;
    }

    public boolean check(int[] foundValues) {
        for(int i = 0; i<values.length; i++){
            if(values[i] == -1){
                continue;
            }else if(values[i] != foundValues[i]){
                return false;
            }
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public boolean checkExact(int[] foundValues) {
        for(int i = 0; i<values.length; i++){
            if(values[i] == -1){
                continue;
            }else if(i == 1 || i == 7){
                if(values[i] <= foundValues[i]){
                    return false;
                }
            }else if(i == 3 || i == 6){
                if(values[i] >= foundValues[i]) {
                    return false;
                }
            }else if(values[i] != foundValues[i]){
                return false;
            }
        }
        return true;
    }
}
