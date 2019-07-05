package com.amardeep.oops;

import java.util.*;

public class OopClassDriver {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        OopClass.con(list);
    }

}

enum Color implements Comparator{
     COLOR;

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}

/*
This stops inheritance
class OopClass {
    private OopClass(){}
}
class OopSubClass extends OopClass{

}
*/

/*This stops inheritance
final class OopClass {
    OopClass(){}
}
class OopSubClass extends OopClass{

}
*/
final class OopClass {
    private final Date currDate;
    private final List<Integer> initData;
    private int count;

    public OopClass(Date currDate, List<Integer> initData, int count) {
        this.currDate = currDate;
        this.initData = initData;
        this.count = count;
    }

    public static void con(List<? extends Number> list){

    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OopClass)) return false;
        OopClass oopClass = (OopClass) o;
        return count == oopClass.count &&
                Objects.equals(currDate, oopClass.currDate) &&
                Objects.equals(initData, oopClass.initData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currDate, initData, count);
    }
}
