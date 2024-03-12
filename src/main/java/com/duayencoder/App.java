package com.duayencoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class App {
    static String endResult;
    static String cipherResult;
    public static void main(String[] args) {


        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, User");

        post("/calculation", (req, res) -> {
            boolean isItCalculated;


            String input1 = req.queryParams("input1");
            Scanner sc1 = new Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");

            ArrayList<Integer> cipherList = new ArrayList<>();
            while (sc1.hasNext()){
                int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
                cipherList.add(value);
            }
            System.out.println(cipherList);


            String text = req.queryParams("input2");
            String textDump = req.queryParams("input2");

            String input3 = req.queryParams("input3");
            char[] input3AsCharArray = input3.toCharArray();

            String input4 = req.queryParams("input4");
            Scanner sc2 = new Scanner(input4);
            sc2.useDelimiter("[;\r\n]+");

            ArrayList<Integer> replaceList = new ArrayList<>();
            while (sc2.hasNext()){
                int value = Integer.parseInt(sc2.next().replaceAll("\\s",""));
                replaceList.add(value);
            }
            System.out.println(replaceList);


            isItCalculated = App.makeSomeMeaningfulComputationOnSetOfStrings(cipherList, textDump, input3AsCharArray[0], replaceList);
            String result;
            
            if(isItCalculated){
                result = "<br><br>Text: " + text + "<br>";
                result += "After Ciphering and before replacing: " + cipherResult + "<br>";
                result += "End Result: " + endResult + "<br>";
                result += "Cipher Values: " + cipherList.toString() + "<br>";
                result += "Inserted Char: " + input3AsCharArray[0] + "<br>";
                result += "Replaced Indices: " + replaceList.toString();
            }else {
                result = "An Error Occured. Check Your Inputs Again!";
            }

            Map map = new HashMap();
            map.put("result", result);
            return new ModelAndView(map, "calculation.mustache");
        }, new MustacheTemplateEngine());

        get("/calculation", (rq, rs) -> {
            Map map = new HashMap();
            map.put("result", "Not Calculated Yet!");
            return new ModelAndView(map, "calculation.mustache");
        }, new MustacheTemplateEngine());

    }

    public static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }


    //Takes a String and applies ceaser cipher with mod cipherIndex cipherIndex.size() times
    //then changes the value at replaceIndex with char c (replaceIndex.get(i) > 0 && replaceIndex.get(i) < text.length())
    public static boolean makeSomeMeaningfulComputationOnSetOfStrings(ArrayList<Integer> cipherIndex, String text, char c, ArrayList<Integer> replaceIndex){
        String cipheredText = null;
        boolean status = true;

        for (int i = 0; i < text.length(); i++){
            if( !(Character.isAlphabetic(text.charAt(i)) || Character.isWhitespace(text.charAt(i)))){   
                status = false;
                return status;
            }
        }

        if( (cipherIndex.size() > 0) && (text.length() > 0)){
            cipheredText = text;

            for (int i = 0; i < cipherIndex.size() ; i++){
                if((cipherIndex.get(i) > 0))
                    cipheredText = cipherText(cipheredText, cipherIndex.get(i));
                else
                    return false;            
            }
            cipherResult = cipheredText;

            for(int i = 0; i < replaceIndex.size(); i++){
                if((replaceIndex.get(i) > 0) && (replaceIndex.get(i) < cipheredText.length())){
                    char[] tempArray = cipheredText.toCharArray();
                    tempArray[replaceIndex.get(i)] = c;

                    cipheredText = new String(tempArray);
                }else{
                    return false;
                }
            }
        }

        endResult = cipheredText;
        return status;
    }

    public static String cipherText(String s, int mod){
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        int value = 0;

        for(int i = 0; i < s.length(); i++){
            if (Character.isWhitespace(s.charAt(i))){
                sb.append(s.charAt(i));
            }else{
                value = s.charAt(i) - 'a';
                value = (value + mod) % 26;
                char ch = (char) ('a' + value);
                sb.append(ch);    
            }
        }
        return sb.toString();
    }

}