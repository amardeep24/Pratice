import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Adobe {
    public static void main(String[] args) {
       /* Map map = new HashMap<>();
        final Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("USA", 100);
        wordCounts.put("jobs", 200);
        wordCounts.put("software", 50);
        wordCounts.put("technology", 70);
        wordCounts.put("opportunity", 200);

        Comparator<Map.Entry<String, Integer>> comp  = Map.Entry.comparingByValue(Comparator.reverseOrder());
        comp  = comp.thenComparing(ent -> ent.getKey(), Comparator.reverseOrder());


        final Map<String, Integer> sortedByCount = wordCounts.entrySet()
                .stream()
                .sorted(comp)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        String key = sortedByCount.entrySet().stream().findFirst().get().getKey();
        System.out.println(key);*/

        //IntStream.of(56, 78, 97, 45, 23, 11, 7).peek(n -> {System.out.println("Before filter "+ n);}).filter(n -> n%2 == 0).peek(n -> {System.out.println("In filter "+ n);}).map(n -> n * 2).peek(n -> {System.out.println("In map "+ n);}).boxed().collect(Collectors.toList());
       // System.out.println(Arrays.toString(sumOfKNumbersAddToTarget(new int[]{6, 2, 3, 0}, 5, 3)));
      //  System.out.println(Arrays.toString(sumOfKNumbersWithMaxSum(new int[]{-1, 4, 2, -3, 8, 6}, 3)));
     //   System.out.println(Arrays.toString(maxOfKNumbersWithRanges(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
     //    System.out.println(Arrays.toString(maxOfKNumbersWithRanges(new int[]{1, -1}, 1)));

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        list.stream().forEach(v ->{
            list.add(v);
            System.out.println(v);
        });

        list.forEach(v ->{
            list.add(v);
            System.out.println(v);
        });
    }

    public static int[] sumOfKNumbersAddToTarget(int[] nums, int target, int k){
        int sum =0;
        int windowSize = k;
        int start = 0;
        int end = start + windowSize;
        int prevStart = 0;
        int[] range = new int[windowSize];
        while(start < end && start <= nums.length - 1){
            sum += nums[start++];
            if(sum == target){
                return new int[]{prevStart, end - 1};
            }
            if(start == end){
                start = prevStart + 1;
                end += 1;
                prevStart += 1;
                sum = 0;
            }
        }
        return range;
    }

    public static int[] sumOfKNumbersWithMaxSum(int[] nums, int k){
        int sum =0;
        int windowSize = k;
        int start = 0;
        int end = start + windowSize;
        int prevStart = 0;
        int max = 0;
        int[] range = new int[windowSize];
        while(start < end && start <= nums.length - 1){
            sum += nums[start++];
            if(sum > max && start == prevStart + windowSize){
                max = sum;
                range = new int[]{prevStart, end - 1};
            }
            if(start == end){
                start = prevStart + 1;
                end += 1;
                prevStart += 1;
                sum = 0;
            }
        }
        return range;
    }

    public static int[] maxOfKNumbersWithRanges(int[] nums, int k){
        //https://leetcode.com/problems/sliding-window-maximum/submissions/
        int curr = Integer.MIN_VALUE;
        int windowSize = k;
        int start = 0;
        int end = start + windowSize;
        int prevStart = 0;
        List<Integer> list = new ArrayList<>();
        while(start < end && prevStart <= nums.length - k){
            curr = Math.max(curr, nums[start++]);
            if(start == prevStart + windowSize){
               list.add(curr);
            }
            if(start == end){
                start = prevStart + 1;
                end += 1;
                prevStart += 1;
                curr = Integer.MIN_VALUE;
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
