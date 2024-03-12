package com.duayencoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppTest {
    private static ArrayList<Integer> cipherList;
    private static ArrayList<Integer> replaceList;
    private static String text;
    private static char c;
    
    @BeforeAll
    public static void setTestEnvironment(){
        cipherList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        replaceList = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        c = 'x';
        text = "mksdfşsdfm?*@dsğkssğdkf";
    }



    @Test
    public void testForNonAlphabeticCharacters(){
        cipherList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        replaceList = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        c = 'x';
        text = "mksdfşsdfm?*@dsğkssğdkf";
        
        assertFalse(App.makeSomeMeaningfulComputationOnSetOfStrings(cipherList, text, c, replaceList));
    }

    @Test
    public void testForOutOfRangeCipherIndex(){
        cipherList = new ArrayList<>(Arrays.asList(1, 2, 3, 38));
        replaceList = new ArrayList<>(Arrays.asList(1, 3, 5, 7));
        c = 'x';
        text = "mks";
        
        assertFalse(App.makeSomeMeaningfulComputationOnSetOfStrings(cipherList, text, c, replaceList));
    }

    @Test
    public void testForOutOfRangeReplaceIndex(){
        cipherList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        replaceList = new ArrayList<>(Arrays.asList(1, 3, 5, 11));
        c = 'x';
        text = "mksdfşsdfm";
        
        assertFalse(App.makeSomeMeaningfulComputationOnSetOfStrings(cipherList, text, c, replaceList));
    }

    @Test
    public void testForCipherTestFunction(){
        text = "he told me i could never teach a llama to drive";
        String result = "kh wrog ph l frxog qhyhu whdfk d oodpd wr gulyh";

        assertEquals(result, App.cipherText(text, 3));
    }

    @Test
    public void testForNegativeIndexForCipherIndex(){
        cipherList = new ArrayList<>(Arrays.asList(1, 2, 3, -7));
        replaceList = new ArrayList<>(Arrays.asList(1, 3, 5, 11));
        c = 'x';
        text = "mksdfşsdfm";

        assertFalse(App.makeSomeMeaningfulComputationOnSetOfStrings(cipherList, text, c, replaceList));
    }
}
