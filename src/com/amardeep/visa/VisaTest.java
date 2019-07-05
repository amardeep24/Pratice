package com.amardeep.visa;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VisaTest {
    public static void main(String[] args) {
        System.out.println(findCompletePrefixes(Arrays.asList("steve",
                "stevens",
                "danny",
                "steves",
                "dan",
                "john",
                "johnny",
                "joe",
                "alex",
                "alexander"), Arrays.asList("steve",
                "alex",
                "joe",
                "john",
                "dan")));
        System.out.println(countSubarrays(Arrays.asList(3,4,5), 5));
    }

    public static List<Integer> findCompletePrefixes(List<String> names, List<String> query) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(0, query.size()).mapToObj(idx -> query.get(idx)).forEach(q -> {
            int count = (int)names.stream().filter(name -> !name.equals(q) && name.startsWith(q)).count();
            list.add(count);
        });
        return list;
    }

    public static SinglyLinkedListNode removeNodesGreaterThanX(SinglyLinkedListNode listHead, int x) {
        SinglyLinkedListNode curr = listHead;
        SinglyLinkedListNode dummy = new SinglyLinkedListNode(0);
        SinglyLinkedListNode dummyHead = dummy;
        while(curr != null){
            if(curr.data <= x){
                dummy.next = new SinglyLinkedListNode(curr.data);
                curr = curr.next;
                dummy = dummy.next;
            }else{
                curr =curr.next;
            }
        }
        return dummyHead.next;
    }

    public static long countSubarrays(List<Integer> numbers, int num) {
        List<List<Integer>> myList = myList = new ArrayList<>();
        List<Integer> list = null;
        for(int i=0; i<numbers.size(); i++){
            for(int j=i; j< numbers.size(); j++){
                list = new ArrayList<>();
                for(int k = i; k<=j; k++){
                    list.add(numbers.get(k));
                }
                myList.add(list);
            }
        }
        return myList.stream().filter(lst -> lst.stream().mapToInt(n -> n).sum() <= num).count();
    }
}

class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
    }
}

class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertNode(int nodeData) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

        if (this.head == null) {
            this.head = node;
        } else {
            this.tail.next = node;
        }

        this.tail = node;
    }
}

class SinglyLinkedListPrintHelper {
    public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }
}