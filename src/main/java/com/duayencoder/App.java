package com.duayencoder;

import java.util.ArrayList;
import java.util.List;

public class App {
    ArrayList<Integer> cipherIndex = new ArrayList<>();
    ArrayList<Integer> replaceIndex = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello world!");
        cipherText("he told me i could never teach a llama to drive", 3);
    }

    //Takes a String and applies ceaser cipher with mod cipherIndex cipherIndex.size() times
    //then changes the value at replaceIndex with char c (replaceIndex.get(i) > 0 && replaceIndex.get(i) < text.length())
    public static boolean makeSomeMeaningfulComputationOnSetOfStrings(ArrayList<Integer> cipherIndex, String text, char c, ArrayList<Integer> replaceIndex){
        String cipheredText = null;
        boolean status = true;

        for (int i = 0; i < text.length(); i++){
            if(Character.isAlphabetic(text.charAt(i))){
                return false;    
            }
        }


        if( (cipherIndex.size() > 0) && (text.length() > 0) ){
            cipheredText = text.toLowerCase();

            for (int i = 0; i < cipherIndex.size() ; i++){
                if((cipherIndex.get(i) <= 26) && (cipherIndex.get(i) > 0))
                    cipheredText = cipherText(cipheredText, cipherIndex.get(i));
                else
                    return false;            
            }

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