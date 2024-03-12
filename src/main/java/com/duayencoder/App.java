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
    public static String makeSomeMeaningfulComputationOnSetOfStrings(ArrayList<Integer> cipherIndex, String text, char c, ArrayList<Integer> replaceIndex){
        String cipheredText = null;

        if( (cipherIndex.size() > 0) && (text.length() > 0) ){
            cipheredText = text;

            for (int i = 0; i < cipherIndex.size() ; i++){
                if(cipherIndex.get(i) <= 26)
                    cipheredText = cipherText(cipheredText, cipherIndex.get(i));            
            }

            for(int i = 0; i < replaceIndex.size(); i++){
                if((replaceIndex.get(i) > 0) && (replaceIndex.get(i) < cipheredText.length())){
                    char[] tempArray = cipheredText.toCharArray();
                    tempArray[replaceIndex.get(i)] = c;

                    cipheredText = new String(tempArray);
                }
            }
        }

        return cipheredText;
    }

    public static String cipherText(String s, int mod){
        StringBuilder sb = new StringBuilder();
        int value = 0;

        for(int i = 0; i < s.length(); i++){
            if (Character.isUpperCase(s.charAt(i))){
                value = ((int) s.charAt(i) + mod - 65) % 26 + 65;
                char ch = (char) value;
                sb.append(ch);
            }else{
                value = ((int)s.charAt(i) + mod - 97) % 26 + 97;
                char ch = (char) value;
                sb.append(ch);
            }
            
            if (Character.isWhitespace(s.charAt(i))){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}