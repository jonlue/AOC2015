package main.days;

import main.AOCRiddle;

import java.security.MessageDigest;

public class Day04 extends AOCRiddle {

    private final String input = getInput();
    private MessageDigest md;

    public Day04(String in, int part){
        super(in, part);
        init();
    }

    private void init() {
        try {
            md = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String solve1(){
        boolean running = true;
        int counter = 1;
        while(running){
            String message = input.concat(Integer.toString(counter));
            md.update(message.getBytes());
            byte[] digest = md.digest();

            if(digest[0] == 0 && digest[1] == 0 && digest[2] <= 0x0F && digest[2] >=0){
                running = false;
            }

            md.reset();
            counter++;
        }
        return String.valueOf(counter);
    }

    public String solve2(){
        boolean running = true;
        int counter = 1;
        while(running){
            String message = input.concat(Integer.toString(counter));
            md.update(message.getBytes());
            byte[] digest = md.digest();

            if(digest[0] == 0 && digest[1] == 0 && digest[2] ==0){
                running = false;
            }

            md.reset();
            counter++;
        }
        return String.valueOf(counter);
    }

}
