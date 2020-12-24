package main;

import java.util.Arrays;

public class YardGIF {

    private String input;

    public YardGIF(String input){
        this.input = input;
    }


    public int solve1() {
        String[] grid = input.replaceAll("\r","").split("\n");
        String[] copy = input.replaceAll("\r","").split("\n");

        //cycles
        for (int i = 0; i < 100; i++) {

            //update lights
            for(int k = 0; k<grid.length; k++){
                for(int j = 0; j<grid[k].length(); j++){

                    int neighboursOn = getNeighbours(grid,k,j);

                    if(grid[k].charAt(j) == '#'){
                         if(! (neighboursOn ==2 || neighboursOn == 3)){
                             copy[k] = copy[k].substring(0,j) + "." + copy[k].substring(Math.min(j+1,grid[k].length()));
                         }

                    }else{
                        if(neighboursOn == 3){
                            copy[k] = copy[k].substring(0,j) + "#" + copy[k].substring(Math.min(j+1,grid[k].length()));
                        }
                    }
                }
            }
            //printLights(copy);
            System.arraycopy(copy,0,grid,0,copy.length);
        }
        return countLightsOn(grid);
    }

    public int solve2(){
        String[] grid = input.replaceAll("\r","").split("\n");
        String[] copy = input.replaceAll("\r","").split("\n");
        grid[0] = "#" + grid[0].substring(1,grid[0].length()-1) + "#";
        copy[0] = "#" + copy[0].substring(1,copy[0].length()-1) + "#";
        grid[grid.length-1] = "#" + grid[grid.length-1].substring(1,grid[grid.length-1].length()-1) + "#";
        copy[copy.length-1] = "#" + copy[grid.length-1].substring(1,copy[grid.length-1].length()-1) + "#";

        //cycles
        for (int i = 0; i < 100; i++) {

            //update lights
            for(int k = 0; k<grid.length; k++){
                for(int j = 0; j<grid[k].length(); j++){
                    if( (j == 0 && k == 0) || (j == 0 && k==grid.length-1) || (j == grid.length-1 && k==0) || (j== grid.length-1 && k == grid.length-1)){
                        continue;
                    }

                    int neighboursOn = getNeighbours(grid,k,j);

                    if(grid[k].charAt(j) == '#'){
                        if(! (neighboursOn ==2 || neighboursOn == 3)){
                            copy[k] = copy[k].substring(0,j) + "." + copy[k].substring(Math.min(j+1,grid[k].length()));
                        }

                    }else{
                        if(neighboursOn == 3){
                            copy[k] = copy[k].substring(0,j) + "#" + copy[k].substring(Math.min(j+1,grid[k].length()));
                        }
                    }
                }
            }
            printLights(copy);
            System.arraycopy(copy,0,grid,0,copy.length);
        }
        return countLightsOn(grid);
    }


    private int countLightsOn(String[] grid) {
        int sum = 0;
        for(String s : grid){
            for(char c : s.toCharArray()){
                sum+= c=='#' ? 1:0;
            }
        }
        return sum;
    }

    private void printLights(String[] grid) {
        for(String s : grid){
            System.out.println(s);
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private int getNeighbours(String[] grid, int k, int j) {
        int count = 0;
        if(k > 0){
            if(j>0){
                //top left
                count += grid[k-1].charAt(j-1)== '#' ? 1:0;
            }
                //top
                count += grid[k-1].charAt(j)== '#' ? 1:0;
            if(j<grid[k].length()-1){
                //top right
                count += grid[k-1].charAt(j+1)== '#' ? 1:0;
            }
        }
        if(j>0) {
            //left
            count += grid[k].charAt(j - 1) == '#' ? 1 : 0;
        }
        if(j< grid[k].length()-1) {
            //right
            count += grid[k].charAt(j + 1) == '#' ? 1 : 0;
        }

        if(k<grid.length-1){

            //bottom right
            if(j<grid[k].length()-1){
                count += grid[k+1].charAt(j+1)== '#' ? 1:0;
            }

            //bottom
            count += grid[k+1].charAt(j)== '#' ? 1:0;

            //bottom left
            if(j>0){
                count += grid[k+1].charAt(j-1)== '#' ? 1:0;
            }
        }
        return count;
    }

}
