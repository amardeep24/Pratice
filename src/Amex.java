import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

class A{}
class B extends A{
    private void test(int a){

    }
    private void test(float a){

    }
    final void test(int a, int b){

    }
}
class C extends B{}

public class Amex {
    public static void main(String[] args) {
        B b = new C();
        System.out.println(b instanceof C);





        int[] A = new int[]{6, 1, 4, 6, 3, 2, 7, 4};
        //int[] A = new int[]{10, 19, 15};
        List<List<Integer>> setOne = getSubSet(A, 3);
        List<List<Integer>> setTwo = getSubSet(A, 2);
        int maxOne = Integer.MIN_VALUE;
        int maxTwo = Integer.MIN_VALUE;
        List<Integer> maxList = null;
        for(List<Integer> list : setOne){
            int sum = list.stream().mapToInt(n -> A[n]).sum();
            if(maxOne < sum){
                maxOne = sum;
                maxList = list;
            }
        }
        for(List<Integer> list : setTwo){
            int sum = list.stream().mapToInt(n -> A[n]).sum();
            if(maxTwo < sum && !containsSome(maxList, list)){
                maxTwo = sum;
            }

        }
        int firstMax = Math.max((maxOne + maxTwo), -1);

        maxOne = Integer.MIN_VALUE;
        maxTwo = Integer.MIN_VALUE;

        for(List<Integer> list : setTwo){
            int sum = list.stream().mapToInt(n -> A[n]).sum();
            if(maxTwo < sum ){
                maxTwo = sum;
                maxList = list;
            }
        }
        for(List<Integer> list : setOne){
            int sum = list.stream().mapToInt(n -> A[n]).sum();
            if(maxOne < sum && !containsSome(list, maxList)){
                maxOne = sum;
            }
        }
        int secondMax = Math.max(firstMax, maxOne + maxTwo);

        System.out.println(Math.max(firstMax, secondMax));

    }
    private static List<List<Integer>> getSubSet(int[] A, int k){
        List<List<Integer>> subList = new ArrayList<>();
        for(int i =0; i<=A.length - k; i++){
            List<Integer> list = new ArrayList<>();
            for(int j = i; j< i + k; j++){
                list.add(j);
            }
            subList.add(list);
        }
        return subList;
    }

    private static boolean containsSome(List<Integer> a, List<Integer> b){
        class Flag{
            boolean flag = false;
        }
        Flag flag = new Flag();
        a.forEach(e1 -> {
            if(b.stream().anyMatch(e2 -> e1 == e2)){
                flag.flag = true ;
            }
        });
        return flag.flag;
    }
}
