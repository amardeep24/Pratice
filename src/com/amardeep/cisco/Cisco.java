package com.amardeep.cisco;

import java.util.*;
import java.util.stream.Collectors;

public class Cisco {
    public static void main(String[] args) {
        System.out.println(maxSubstring("baca"));
    }
    public static String maxSubstring(String s) {
        if(s != null && !s.isEmpty()) {
            List<String> subs = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                for (int j = i+1; j <= s.length(); j++) {
                    subs.add(s.substring(i, j));
                }
            }
            subs.sort(Comparator.naturalOrder());
            return subs.get(subs.size() - 1);
        }
        return s;
    }
    static int triangle(List<Integer> x, List<Integer> y){
        int end = x.size() - 1;
        int area = 0;
        for (int start = 0; start < x.size(); start++)
        {
            //|(Ax(By - Cy) + Bx(Cy - Ay) + Cx(Ay - By))/2|
            area += (x.get(end) + x.get(start)) * (y.get(end) - y.get(start));
            // pointing to last co-ordinate
            end = start;
        }

        return (int)Math.abs(area / 2);
    }
}
class Test{
   /* //AxBxCx AyByCy
    //|(Ax(By - Cy) + Bx(Cy - Ay) + Cx(Ay - By))/2|
    int Ax = x.get(0);
    int Bx = x.get(1);
    int Cx = x.get(2);

    int Ay = y.get(0);
    int By = y.get(1);
    int Cy = y.get(2);

    //using formula
    int area = Math.abs((Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By)))/2;
    return area;*/
}
class TestTwo{

}