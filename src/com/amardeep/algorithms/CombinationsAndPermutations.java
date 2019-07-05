package com.amardeep.algorithms;

import java.util.ArrayList;
import java.util.List;

public class CombinationsAndPermutations {
    public static void main(String[] args) {
        System.out.println(combinaytionOfNumbersFromRange(5));
        //System.out.println(combinaytionOfNumbersFromRangeHavingLengthK(5, 3));
    }

    public static List<List<Integer>> combinaytionOfNumbersFromRangeHavingLengthK(int n, int k){
        List<List<Integer>> acc = new ArrayList<>();
        combinaytionOfNumbersFromRangeHavingLengthKHelper(n, 1, k, new ArrayList<>(), acc);
        return acc;
    }

    public static void combinaytionOfNumbersFromRangeHavingLengthKHelper(int stop, int start, int size, List<Integer> choosen, List<List<Integer>> acc){
        if(choosen.size() == size){
            acc.add(new ArrayList<>(choosen));
            return;
        }
        for(int i = start; i<=stop; i++){
            choosen.add(i);
            combinaytionOfNumbersFromRangeHavingLengthKHelper(stop, start + 1, size, choosen, acc);
            choosen.remove(choosen.size() - 1);
        }
    }

    public static List<List<Integer>> combinaytionOfNumbersFromRange(int n){
        List<List<Integer>> acc = new ArrayList<>();
        combinaytionOfNumbersFromRangeHelper(n, 1, new ArrayList<>(), acc);
        return acc;
    }

    public static void combinaytionOfNumbersFromRangeHelper(int stop, int start, List<Integer> choosen, List<List<Integer>> acc){
        if(choosen.size() > 0) {
            acc.add(new ArrayList<>(choosen));
        }
        if(choosen.size() == 1 && choosen.contains(3)){
            System.out.println(choosen);
        }
        for(int i = start; i<=stop; i++){
            choosen.add(i);
            combinaytionOfNumbersFromRangeHelper(stop, start + 1, choosen, acc);
            choosen.remove(choosen.size() - 1);
        }
    }
}
