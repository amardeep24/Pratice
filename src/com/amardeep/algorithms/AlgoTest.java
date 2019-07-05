package com.amardeep.algorithms;

import java.security.cert.CollectionCertStoreParameters;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AlgoTest {
    public static void main(String[] args) {
        int[] set = { 1, 2, 3, 7, 5, 9 ,3, 8, 10, 9, 10, 1, 2, 2, 1, 5};
        int sum = 10;
        //printDupsInArray(set);
        int A[]= { 6, 4, 12, 10, 7, 0, 32, 42, 3, 11};
        //printPairsInArrayGivenSum(set, 10);
        //System.out.println(makingAnagrams("cde", "abc"));
        //System.out.println(minDeletionsToEqualizeArray(new int[]{1,2, 3, 1, 2, 3, 3, 3}));
        //System.out.println(minimumDistancesBetweenTwoEqualElement(new int[]{9, 2, 34, 7, 1, 3, 4, 1, 7}));
        //System.out.println(minimumDistancesBetweenTwoEqualElementMap(new int[]{9, 2, 34, 7, 1, 3, 4, 1, 7}));
        /*int[] numbers = {1, 2, 5, 3, 7, 9, 10};
        for(int i =0; i<numbers.length; i++){
            sum(numbers, i, numbers[i], 10, String.valueOf(numbers[i]));
        }*/
       /* System.out.println(checkBalancedBracket("{t + b}"));
        System.out.println(checkBalancedBracket("[{t + b}"));
        System.out.println(checkBalancedBracket("{t + (b)}"));
        System.out.println(checkBalancedBracket(")("));*/
        //generatePascalTriangle(5);
        //System.out.println(maximumProductThreeNums(new int[]{1, 2, 3}));
       // permute("1234","");
        //System.out.println(findLongestPalindromicSubsequence("bbbab", 0, 4));
        //System.out.println(findLongestPalindromicSubsequence("geeksforgeeks", 0, 12));
       /* int[] nums = new int[]{1,2,3,4,5,6,7};
        rotateLeft(nums, 3);
        nums = new int[]{1,2,3,4,5,6,7};
        rotateLeftReverse(nums, 3);*/
        //printSubSetSumUnique(new int[]{2, 4, 6, 10, 5, 1}, 6);
        //printSubSetSumNonUnique(new int[]{2, 4, 6, 10, 5, 1}, 6);
        //System.out.println(threeSum(new int[]{1, 0, -1, 2, 1, -2}));
       // printCombinationHavingMaxSum(new int[]{1, 0, -1, 2, 1, -2});
       // buildHeap(new int[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17}); //o/p 17 15 13 9 6 5 10 4 8 3 1
       // System.out.println(getKthMaxElement(new int[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17}, 5));
        //System.out.println(longestPalindrome("abcad"));
        //System.out.println(combination(4, 3));
       // System.out.println(combinationOfNumberProductLessThanK(Arrays.asList(3,4,5),15));
        //System.out.println(combinationSum(Arrays.asList(10, 3, 7, 4, 5, -2, 12, 8, 2), 12));
        //System.out.println(superReducedString("baab"));
        System.out.println(findLongestCommonSubString("abcecdacdef", "cdefr")); //longest
       /*                                                   j     i             cd */
    }

    public static String findLongestCommonSubString(String s1, String s2){
        if(s1 != null && s2 != null && !s1.isEmpty() && !s2.isEmpty()){
           String longer = s1.length() > s2.length() ? s1 : s2;
           String smaller = longer.equals(s2) ? s1 : s2;
           String maxStr = "";
           for(int i = 0; i<longer.length(); i++){
               for(int j = i; j<longer.length(); j++){
                   String sub = longer.substring(i, j+1);
                   if(smaller.contains(sub)) {
                       if(maxStr.length() < sub.length()){
                           maxStr = sub;
                       }
                   }
               }
           }
           return maxStr;
        }
        return s1;
    }
    public static String superReducedString(String s) {
        int freq[] = new int[26];
        for(char c: s.toCharArray()){
            freq[c -'a']++;
        }
        for(int i=0; i<s.length(); i++){
            if(freq[s.charAt(i)- 'a'] % 2 ==0 || (freq[s.charAt(i)- 'a']/2) % 2 == 0){
                s = s.substring(i, i + freq[s.charAt(i)- 'a']);
            }
        }
        return s;

    }
    public static String longestPalindrome(String s) {
        return longestPalindromeHelper(s, 0, s.length() - 1);
    }
    private static String longestPalindromeHelper(String s, int start, int end){
        if(start > end){
            return "";
        }
        if(start == end){
            return s.substring(start, end + 1);
        }
        if(s.charAt(start) == s.charAt(end)){
            return s.charAt(start) + longestPalindromeHelper(s, start + 1, end - 1) + s.charAt(end);
        }
        String left = longestPalindromeHelper(s, start + 1, end);
        String right = longestPalindromeHelper(s, start, end - 1);
        return  left.length() > right.length() ? left :right;
    }
    public static void printCombinationHavingMaxSum(int nums[]){
        AtomicInteger maxVal = new AtomicInteger(Integer.MIN_VALUE);
        printCombinationHavingMaxSumHelper(nums, new int[]{}, maxVal);
        System.out.println(maxVal);
    }
    private static void printCombinationHavingMaxSumHelper(int[] available, int[] choosen, AtomicInteger maxVal){
        if(available.length == 0){
            return;
        }
        int sum = Arrays.stream(choosen).sum();
        if(sum > maxVal.get()){
            maxVal.set(sum);
        }
        for(int i = 0; i < available.length; i++){
            int selected = available[i];
            int[] newAvailable = Arrays.copyOfRange(available, i + 1, available.length);
            int[] newChoosen = IntStream.concat(Arrays.stream(choosen), IntStream.of(selected)).toArray();
            printCombinationHavingMaxSumHelper(newAvailable, newChoosen, maxVal);
        }
    }
    public static void printSubSetSumUnique(int[] nums, int target){
        printSubSetSumUniqueHelper(nums, new int[]{}, 6);
    }

    private static void printSubSetSumUniqueHelper(int[] available, int[] choosen, int target){
        int sum = Arrays.stream(choosen).sum();
        if(target == sum){
            System.out.println(Arrays.toString(choosen));
            return;
        }
        if(target < sum){
            return;
        }
        for(int i =0; i< available.length; i++){
            int selected = available[i];
            int[] newAvailable = Arrays.copyOfRange(available, i + 1, available.length);
            int[] newChoosen = IntStream.concat(Arrays.stream(choosen), IntStream.of(selected)).toArray();
            printSubSetSumUniqueHelper(newAvailable, newChoosen, target);
        }
    }

    public static void printSubSetSumNonUnique(int[] nums, int target){
        printSubSetSumNonUniqueHelper(nums, new int[]{}, 6);
    }

    private static void printSubSetSumNonUniqueHelper(int[] available, int[] choosen, int target){
        int sum = Arrays.stream(choosen).sum();
        if(target == sum){
            System.out.println(Arrays.toString(choosen));
            return;
        }
        if(target < sum){
            return;
        }
        for(int i =0; i< available.length; i++){
            int selected = available[i];
            int[] newAvailable = Arrays.stream(available).filter(n -> n != selected).toArray();
            int[] newChoosen = IntStream.concat(Arrays.stream(choosen), IntStream.of(selected)).toArray();
            printSubSetSumNonUniqueHelper(newAvailable, newChoosen, target);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> acc = new ArrayList<>();
        threeSumHelper(nums, new int[]{}, acc);
        return acc;
    }

    private static void threeSumHelper(int [] available, int[] choosen, List<List<Integer>> acc){
        if(choosen.length == 3 && Arrays.stream(choosen).sum() == 0){
            List<Integer> list = Arrays.stream(choosen).boxed().collect(Collectors.toList());
            acc.add(list);
            return;
        }
        for(int i = 0; i< available.length; i++){
            int selected = available[i];
            int[] newAvailable = Arrays.copyOfRange(available, i + 1, available.length);
            int[] newChoosen = IntStream.concat(Arrays.stream(choosen), IntStream.of(selected)).toArray();
            threeSumHelper(newAvailable, newChoosen, acc);
        }
    }
    public static void rotateLeft(int[] nums, int k) {
        IntStream.range(0, k).forEach(i -> {
            rotateOnce(nums);
        });
        System.out.println(Arrays.toString(nums));
    }
    public static void rotateLeftReverse(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
    public static void rotateOnce(int[] nums){
        int last = nums[nums.length - 1];
        int prev = nums[0];
        int tmp = nums[0];
        for(int i =0; i<nums.length - 1; i++){
            tmp = nums[i + 1];
            nums[i + 1] = prev;
            prev = tmp;
        }
        nums[0] = last;
    }

    public static void reverse(int[] nums, int start, int end){
        //[1,2,3,4,5,6,7]
        //[7,6,5,4,3,2,1]
        int tmp = 0;
        while(start < end){
            tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    public static void findAllSubSetOfArray(int arr[], int sum){

    }

    public static void printDupsInArray(int arr[]){
       Map<Integer, Boolean> map = new HashMap<>();
       for(int i = 0; i < arr.length; i++){
           if(!map.containsKey(arr[i])){
               map.put(arr[i], false);
           }else{
               map.put(arr[i], true);
           }
       }
       //print dups
       map.entrySet().stream().filter(ent -> ent.getValue()).mapToInt(Map.Entry::getKey).forEach(System.out::println);
       //count distinct
       System.out.println(map.entrySet().stream().filter(ent -> !ent.getValue()).count());
    }

    public static void printPairsInArrayGivenSum(int arr[], int sum){
       Map<Integer, Integer> map = new HashMap<>();
       for(Integer i: arr){
           if(map.containsKey(i)) {
               System.out.println( i+ ", " + map.get(i));
           }else{
               map.put(sum - i, i);
           }
       }
    }
    static int findLongestPalindromicSubsequence(String s, int start, int end){
        //no character selected
        if(start > end){
            return 0;
        }
        //one character selected which is always a palindrome
        if(start == end){
            return 1;
        }
        //if starting and ending characters are same, remove them and find the recursive solution for the inner string
        if(s.charAt(start) == s.charAt(end)){
            return 2 + findLongestPalindromicSubsequence(s, start + 1, end - 1);
        }
        //either exclude first character and then find the palindrome of the remaining or exclude the last character and find palindrome
        //for the remaining and take the max out of these as we need to find max palindrome
        else {
            return Math.max(findLongestPalindromicSubsequence(s, start + 1, end), findLongestPalindromicSubsequence(s, start, end - 1));
        }
    }

    static int findMinimumDeletionsToMakeStringPalindrome(String s){
        int maxPalindromeLength = findLongestPalindromicSubsequence(s, 0, s.length() - 1);
        return s.length() - maxPalindromeLength;
    }
    
    static int makingAnagrams(String s1, String s2) {
        int[] freqTable = new int[26];
        for(char c: s1.toCharArray()){
            freqTable[c - 'a']++;
        }
        for(char c: s2.toCharArray()){
            freqTable[c - 'a']--;
        }
        int deletions = Arrays.stream(freqTable).map(Math::abs).sum();
        return deletions;
    }
    static int minDeletionsToEqualizeArray(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxVal = 0;
        for(int i = 0; i<arr.length; i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i], 1);
            }else{
                Integer val = map.get(arr[i]);
                map.put(arr[i], ++val);
                if(val > maxVal){ maxVal = val;}
            }
        }
        return arr.length - maxVal;
    }
    static int minimumDistancesBetweenTwoEqualElement(int[] a) {
        int i = 0;
        int j = a.length - 1;
        int minDist = Integer.MAX_VALUE;
        int dist = 0;
        while(i != j && i != a.length - 1){
            if(a[i] == a[j]){
                dist = j - i;
                j--;
                i++;
                if(dist < minDist){
                    minDist = dist;
                }
            }else if(j == i + 1){
                j = a.length - 1;
                i++;
            }else {
                j--;
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;

    }
    static int minimumDistancesBetweenTwoEqualElementMap(int[] a) {
        int minDist = Integer.MAX_VALUE;
        int dist = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< a.length; i++){
            if(!map.containsKey(a[i])){
                map.put(a[i], i);
            }else{
                int idx = map.get(a[i]);
                dist = i - idx;
                if(dist < minDist){
                    minDist = dist;
                }
            }
        }
        return minDist;
    }
    static void sum(int[] arr, int i, int sum, int target, String s)
    {
        for(int j = i+1; j<arr.length; j++){
            if(sum+arr[j] == target){
                System.out.println(s+" "+String.valueOf(arr[j]));
            }else{
                sum(arr, j, sum+arr[j], target, s+" "+String.valueOf(arr[j]));
            }
        }
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        //https://www.youtube.com/watch?v=siKFOI8PNKM&feature=player_embedded
        List<Integer> output = new ArrayList<>();
        if (matrix.length == 0) return output;
        int dir = 0; // 0-> right; 1-> down; 2->left; 3->up
        int T = 0, R = matrix[0].length - 1, B = matrix.length - 1 , L = 0;
        while(T <= B && L <= R){
            if(dir == 0){
                for(int i = L; i <= R; i++){
                    output.add(matrix[T][i]);
                }
                T++;
                dir = 1;
            }
            else if(dir == 1){
                for(int i = T; i <= B; i++){
                    output.add(matrix[i][R]);
                }
                R--;
                dir = 2;
            }
            else if(dir == 2){
                for(int i = R; i >= L; i--){
                    output.add(matrix[B][i]);
                }
                B--;
                dir = 3;
            }
            else if(dir == 3){
                for(int i = B; i >= T; i--){
                    output.add(matrix[i][L]);
                }
                L++;
                dir = 0;
            }
        }
        return output;

    }

    public static boolean checkBalancedBracket(String expression){
        Deque<Character> stack = new ArrayDeque<>();

        for(int i =0; i< expression.length(); i++){
            if(expression.charAt(i) == '(' ||  expression.charAt(i) == '{' ||  expression.charAt(i) == '[' ){
                stack.push(expression.charAt(i));
            }else if(expression.charAt(i) == ')' || expression.charAt(i) =='}' || expression.charAt(i) == ']'){
                if(!stack.isEmpty())
                    stack.pop();
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }
    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> tri = new ArrayList<>();
        if(numRows == 0){
            return tri;
        }
        else if(numRows == 1){
            tri.add(Arrays.asList(1));
            return tri;
        }
        else if(numRows == 2){
            tri.add(Arrays.asList(1));
            tri.add(Arrays.asList(1, 1));
            return tri;
        }
        List<Integer> fRow = new ArrayList<>(Arrays.asList(1));
        List<Integer> sRow = new ArrayList<>(Arrays.asList(1, 1));
        tri.add(fRow);
        tri.add(sRow);
        List<Integer> prevRow = sRow;
        for(int i = 2; i<numRows; i++){
            List<Integer> nRow = new ArrayList();
            int j = 0;
            int k = j + 1;
            nRow.add(1);
            while(j <= (prevRow.size() - 1) && k <= (prevRow.size() - 1)){
                int sum = prevRow.get(j) + prevRow.get(k);
                nRow.add(sum);
                j++;
                k++;
            }
            nRow.add(1);
            prevRow = nRow;
            tri.add(nRow);
        }
        return tri;
    }
    public static int maximumProductThreeNums(int[] nums) {
        int maxProduct = 1;
        int[] maxNums = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        int max = 0;
        for(int i =0; i < nums.length; i++){
            if(max < nums[i]){
                max = nums[i];
                if(maxNums[0] < max){
                    maxNums[2] = maxNums[1];
                    maxNums[1] = maxNums[0];
                    maxNums[0] = max;
                }
            }
        }
        for(int i = 0; i < maxNums.length; i++){
            maxProduct *= maxNums[i];
        }
        return maxProduct;
    }

    private static void permute(String s, String ans){
       if(s.length() == 0){
           System.out.println(ans);
           return;
       }
       for(int i=0; i<3; i++){
           char ch = s.charAt(i);
           String ros = s.substring(0, i) + s.substring(i+1);
           permute(ros, ans + ch);
       }
    }

    public static void maxHeapify(int[] nums, int i){
        int left = i * 2 + 1;
        int right = i * 2  + 2;
        int largest = i;
        if(left < nums.length && nums[left] > nums[i]){
            largest = left;
        }
        if(right < nums.length && nums[right] > nums[largest]){
            largest = right;
        }
        if(largest != i) {
            int tmp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = tmp;
            maxHeapify(nums, largest);
        }
    }

    public  static int[] buildHeap(int nums[]){
        int lastNonLeafNode = (nums.length / 2) - 1;
        for(int i= lastNonLeafNode; i >= 0 ; i--){
            maxHeapify(nums, i);
        }
       return nums;
    }

    public static int getKthMaxElement(int nums[], int k){
        buildHeap(nums);
        int tmp = nums[0];
        if(k == 1) return tmp;
        while(k != 1){
             tmp = nums[0];
             nums = Arrays.copyOfRange(nums,1, nums.length);
             k--;
        }
        return tmp;
    }

    public static List<List<Integer>> combination(int n, int k){
        List<List<Integer>> acc = new ArrayList<>();
        List<Integer> choosen = new ArrayList<>();
        combinationHelper(n, k, 1, choosen, acc);
        return acc;
    }

    private static void combinationHelper(int n, int k, int start, List<Integer> choosen, List<List<Integer>> acc){
        if(choosen.size() == k){
            acc.add(new ArrayList(choosen));
            return;
        }
        for(int i = start; i <= n; i++){
            choosen.add(i); //choosing the first element
            combinationHelper(n, k, i + 1, choosen, acc); //choosing the next element
            choosen.remove(choosen.size() - 1); //unchoosing
        }
    }

    public static List<List<Integer>> combinationOfNumberProductLessThanK(List<Integer> list, int k){
        List<List<Integer>> acc = new ArrayList<>();
        List<Integer> choosen = new ArrayList<>();
        combinationOfNumberProductLessThanKHelper(list, 0, choosen, acc, k);
        return acc;
    }

    private static void  combinationOfNumberProductLessThanKHelper(List<Integer> list, int start, List<Integer> choosen,
                                                                   List<List<Integer>> acc, int k){
        if(!choosen.isEmpty() && choosen.stream().reduce(1, (i1, i2) -> i1 * i2) <= k){
            acc.add(new ArrayList<>(choosen));
        }
        for(int i = start; i<list.size(); i++){
            choosen.add(list.get(i)); //choose ith item from the given list
            combinationOfNumberProductLessThanKHelper(list, i + 1, choosen, acc, k); //remember last choosen value and choose next one
            choosen.remove(choosen.size() - 1); //unchoose the current item;
        }
    }

    public static List<List<Integer>> combinationSum(List<Integer> list, int k){
        List<List<Integer>> acc = new ArrayList<>();
        List<Integer> choosen = new ArrayList<>();
        combinationSumHelper(list, 0, choosen, acc, k);
        return acc;
    }

    private static void  combinationSumHelper(List<Integer> list, int start, List<Integer> choosen,
                                                                   List<List<Integer>> acc, int k){
        if(!choosen.isEmpty() && choosen.stream().reduce(0, (i1, i2) -> i1 + i2) == k){
            acc.add(new ArrayList<>(choosen));
        }
        for(int i = start; i<list.size(); i++){
            choosen.add(list.get(i)); //choose ith item from the given list
            combinationSumHelper(list, i + 1, choosen, acc, k); //remember last choosen value and choose next one
            choosen.remove(choosen.size() - 1); //unchoose the current item;
        }
    }












}
