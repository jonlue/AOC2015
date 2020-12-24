package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IdealStockingStuffer {

    private String input;

    public IdealStockingStuffer(String input){
        this.input = input.replaceAll("\r","");
    }

    public int solve1(){
        boolean running = true;
        int counter = 1;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        while(running){
            String message = input.concat(Integer.toString(counter));
            md.update(message.getBytes());
            byte[] digest = md.digest();

            if(digest[0] == 0 && digest[1] == 0 && digest[2] <= 0x0F && digest[2] >=0){
                return counter;
            }
            md.reset();
            counter++;
        }
        return -1;
    }

    public int solve2(){
        boolean running = true;
        int counter = 1;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        while(running){
            String message = input.concat(Integer.toString(counter));
            md.update(message.getBytes());
            byte[] digest = md.digest();

            if(digest[0] == 0 && digest[1] == 0 && digest[2] ==0){
                return counter;
            }
            md.reset();
            counter++;
        }
        return -1;
    }

}
