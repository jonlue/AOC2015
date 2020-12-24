package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSAbacus {

    private String input;

    public JSAbacus(String input){
        this.input = input;
    }


    public int solve1(){
        return sumNumbers(input);
    }

    //TODO filter {..., "red",...} out of input not ["red"]
    public int solve2(){
        while(input.contains(":\"red\"")){
            int middle = input.indexOf(":\"red\"");
            int start = -1;
            int end = -1;
            int found = 0;
            for(int i = middle; i<input.length();++i){
                if(input.charAt(i) == '{'){
                    found++;
                }
                if(input.charAt(i) == '}'){
                    if(found>0){
                        found--;
                    }else {
                        end = i;
                        break;
                    }
                }
            }
            found = 0;
            for(int i = middle; i >= 0;--i){
                if(input.charAt(i) == '}'){
                    found++;
                }
                if(input.charAt(i) == '{'){
                    if(found>0){
                        found--;
                    }else {
                        start = i;
                        break;
                    }
                }
            }
            input = input.substring(0,start) + input.substring(end+1);
            //65402
        }
        System.out.println(input);

        /*
        String withoutRedObjects = Pattern.compile("\\{([a-zA-Z0-9]|\\[|]|-|\"|:|,| )*:\"red\"([a-zA-Z0-9]|\\[|]|-|\"|:|,| )*}").matcher(input).replaceAll("");
        //String test = input.replaceAll("\\{[-a-zA-Z0-9 :\",\\[\\]]*:\"red\"[-a-zA-Z0-9 :\",\\[\\]]*","");
        String withoutRedObjects = input.replaceAll("\\{" +
                "([a-zA-Z0-9]|" +
                "[\\[]|[]]|" +
                "[-]|[\":]|[,])*" +
                ":\"red\"" +
                "([a-zA-Z0-9]|" +
                "[\\[]|[]]|" +
                "[-]|[\":]|[,])*\"}","");
        withoutRedObjects = withoutRedObjects.replaceAll()
        /*
        String withoutRedObjects = input.replaceAll("\\{([-0-9a-zA-Z]|[\":, ]|[\\[\\]])*:\"red\"([-0-9a-zA-Z]|[\":, ]|[\\[\\]])*}","");
        withoutRedObjects = withoutRedObjects.replaceAll("\\{([-0-9a-zA-Z]|[\":, ]|[\\[\\]])*:\"red\"([-0-9a-zA-Z]|[\":, ]|[\\[\\]])*}","");

         */
        //System.out.println(withoutRedObjects);
        return sumNumbers(input);
    }

    private String checkCurlyBrackets(){
        int start = 0;
        int end = 0;
        for(int i = 0; i<input.length(); ++i){
            if(input.charAt(i) == '{'){
                start = i;
                checkCurlyBrackets();
            }
            if(input.charAt(i) == '}'){
                end = i;
                /*if(checkForRed(input.substring(start,end+1))){
                    System.out.println();
                }
                 */
            }
        }
        return "";
    }


    private int sumNumbers(String string){
        int sum = 0;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(string);
        while(m.find()) {
            sum += Integer.parseInt(m.group());
        }
        Pattern p2 = Pattern.compile("-\\d+");
        m = p2.matcher(string);
        while(m.find()){
            sum += Integer.parseInt(m.group());
            sum += Integer.parseInt(m.group());
        }
        return sum;
    }
}
