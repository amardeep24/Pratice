package com.amardeep.visa;

import java.util.*;
import java.util.stream.IntStream;

public class VisaTestAmardeep {
    public static void main(String[] args) {
        System.out.println(funWithAnagrams(Arrays.asList("poke", "pkoe", "okpe", "ekop")));
    }

    public static List<String> funWithAnagrams(List<String> s) {
        List<String> nonAnagramList = new ArrayList<>();
        for(int i=0; i<s.size(); i++){
            int j =i;
            boolean flag = s.stream().anyMatch(str -> isAnagram(str,s.get(j+1)));
             if(flag){
                 nonAnagramList.add(s.get(j));
             }
        }
        nonAnagramList.sort(Comparator.naturalOrder());
        return nonAnagramList;
    }

    private static boolean isAnagram(String s1, String s2) {
        int[] charArr = new int[26];
        for(char ch: s1.toCharArray()){
            charArr[ch - 'a']++;
        }
        for(char ch: s2.toCharArray()){
            charArr[ch - 'a']--;
        }
        int sum = Arrays.stream(charArr).map(i -> Math.abs(i)).sum();
        if(sum == 0){
            return true;
        }
        return false;
    }
    public static char slowestKey(List<List<Integer>> keyTimes) {
        // Write your code here
        int longest = 0;
        char ch = 'a';
        for(int i =0; i< keyTimes.size() - 1; i++){
            List<Integer> key = keyTimes.get(i);
            List<Integer> keyNext = keyTimes.get(i+1);
            int t1 = key.get(1);
            int t2 = keyNext.get(1);
            int time = t2 - t1;
            if(time > longest){
                longest = time;
                ch = (char)(keyNext.get(0).intValue() + 97);
            }
        }
        return ch;
    }

}

class Test {

}