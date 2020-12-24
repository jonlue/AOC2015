package main;

public class InfiniteHouses {

    private String input;
    public InfiniteHouses(String in) {
        this.input = in;
    }

    public int solve1(){
        int factorSum = Integer.parseInt(input)/10;
        int sum = 0;
        int value = factorSum/5;

        do {
            value +=1;
            sum = calculateSum(value,factorSum);
        }while(sum < factorSum);

        return value;
    }

    public int solve2(){
        int factorSum = Integer.parseInt(input)/11;
        int sum = 0;
        int value = factorSum/4;

        do {
            value +=1;
            sum = calculateSumBorder(value,factorSum);
        }while(sum < factorSum);

        return value;
    }

    private int calculateSum(int i, int factorSum) {
        int sum = i+1;
        for(int j = i/2; j>1; j--){
            if(i%j == 0) {
                sum += j;
                if(sum >factorSum){
                    break;
                }
            }
        }
        return sum;
    }

    private int calculateSumBorder(int i, int factorSum) {
        int sum = i+1;
        for(int j = i/2; j>i/50; j--){
            if(i%j == 0) {
                sum += j;
                if(sum >factorSum){
                    break;
                }
            }
        }
        return sum;
    }

}
