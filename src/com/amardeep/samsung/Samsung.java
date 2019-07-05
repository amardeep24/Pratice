package com.amardeep.samsung;

import java.util.concurrent.atomic.AtomicInteger;

public class Samsung {
    public static void main(String[] args) {
        System.out.println(solution(6000, 7000));
    }
    public static int solution(int A, int B) {
        int maxTimesSqrt = 0;
        for(int i = A; i<=B; i++){
            int sqrtCount = countSqrt(i, new AtomicInteger(0));
            if( sqrtCount > maxTimesSqrt){
                maxTimesSqrt = sqrtCount;
            }
        }
        return maxTimesSqrt;
    }
    private static int countSqrt(int val, AtomicInteger count){
        double res = Math.sqrt((double)val);
        if(isInteger(res)){
            count.incrementAndGet();
            countSqrt((int)res, count);
        }
        return count.get();
    }

    private static boolean isInteger(double res){
        if (res == (int)res)
        {
            return true;
        }
        return false;
    }
}
class Static extends StaticSuper{
    static {
        System.out.println("cccccc");
    }
}
class StaticSuper{
    static {
        System.out.println("dddddd");
    }
}
