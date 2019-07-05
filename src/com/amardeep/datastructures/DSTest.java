package com.amardeep.datastructures;

import java.net.Inet4Address;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DSTest {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public static class DoubleNode {
        int val;
        DoubleNode next;
        DoubleNode prev;
        DoubleNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        /*ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        rotateRight(head, 4);*/
      /*TreeNode tree = new TreeNode(10);
        tree.left = new TreeNode(5);
        tree.right = new TreeNode(12);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(7);
        tree.right.left = new TreeNode(11);
        tree.right.right = new TreeNode(14);
        findNthMaxBst(tree, 3);*/
        //System.out.println(findMaxHeightBST(tree));
        //System.out.println(levelOrderTraversalBST(tree));
        //System.out.println(inOrderTraversalBST(tree));
        //System.out.println(isValidBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        //System.out.println(haspathSumBST(tree, 34));
        //ListNode l1 = new ListNode(5);
        //l1.next = new ListNode(2);
        /*l1.next.next = new ListNode(1);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(3);*/
       // ListNode s = mergeSortList(l1);
      //  printList(s);
     //   removeNthNodeFromEnd(l1, 2);
        //System.out.println(mergeTwoLists(l1, l2));
        //ListNode l2 = reverseList(l1);
        //printList(l2);
        //ListNode l3 = reverseListRecur(l1);
        //printList(l3);
       /* ListNode l1 = new ListNode(2);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(6);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(14);
        l1 = insertNodeSortedList(l1, 19);
        l1 = insertNodeSortedList(l1, 7);
        l1 = insertNodeSortedList(l1, 0);
        printList(l1);
        TreeNode rootMaxMin = insertNode(null, 15);
        insertNode(rootMaxMin, 10);
        insertNode(rootMaxMin, 17);
        insertNode(rootMaxMin, 16);
        insertNode(rootMaxMin, 11);*/
        //findNthMaxBst(rootMaxMin, 3);
        //findNthMinBst(rootMaxMin, 1);
        //System.out.println(hasPathSum(rootMaxMin, 36));
        //System.out.println(hasPathSum(rootMaxMin, 37));
      /*  int[] nums = new int[]{203, 45, 78, 12, -98, 4, 0, 1, -45, 234, -876, 44};
        buildMinHeap(nums);
        System.out.println(extractKthSmallestElement(nums, 4));*/
       // System.out.println(isIsomorphic("add", "egg"));
       // ListNode l1 = new ListNode(1);
       // l1.next = new ListNode(2);
        //l1.next.next = new ListNode(1);
       // l1.next.next.next = new ListNode(1);
       // System.out.println(isPalindrome(l1));
       /* TreeNode rootMaxMin = insertNode(null, 15);
        insertNode(rootMaxMin, 10);
        insertNode(rootMaxMin, 17);
        insertNode(rootMaxMin, 16);
        insertNode(rootMaxMin, 11);
        DoubleNode dNode = convertToDLL(rootMaxMin);
        System.out.println(dNode);*/
        /*ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        rotateLinkedListKth(l1, 1);*/
        SortedMap<Integer, List<Integer>> map = new TreeMap<>();
        TreeNode rootMaxMin = insertNode(null, 15);
        insertNode(rootMaxMin, 10);
        insertNode(rootMaxMin, 17);
        insertNode(rootMaxMin, 16);
        insertNode(rootMaxMin, 11);
        verticalTraversal(rootMaxMin, 0, map);
        System.out.println(map);
        map = new TreeMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(rootMaxMin);
        verticalTraversalLevelOrder(0, queue, map);
        System.out.println(map);
    }

    public static boolean isSubTree(TreeNode root, TreeNode sub){
        if(root == null && sub == null){ return true; }
        if(root == null || sub == null){ return false;}

        if(root.val == sub.val) {
            return isSubTree(root.left, sub.left) || isSubTree(root.right, sub.right);
        }else{
           boolean subInLeft =  isSubTree(root.left, sub);
           boolean subInRight = isSubTree(root.right, sub);
           return subInLeft || subInRight;
        }
    }

    public static DoubleNode convertToDLL(TreeNode node){
        if(node == null){
            return null;
        }
        DoubleNode prev = convertToDLL(node.left);
        DoubleNode curr = new DoubleNode(node.val);
        curr.prev = prev;
        if(prev != null) {
            prev.next = curr;
        }
        DoubleNode next = convertToDLL(node.right);
        curr.next = next;
        if(next != null){
            next.prev = curr;
        }
        return curr;
    }
    //Wrong
    public static boolean isIsomorphic(String s, String t) {
        Map<String, Long> firstMap = s.chars()
                .mapToObj(String::valueOf)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> secondMap = t.chars()
                .mapToObj(String::valueOf)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long count = firstMap.values().stream().filter(ele -> !secondMap.containsValue(ele)).count();
        return count == 0;
    }

    public static TreeNode insertNode(TreeNode root, int val){
        if(root == null){
            return new TreeNode(val);
        }
        if(val > root.val){
            root.right = insertNode(root.right, val);
            return root;
        }
        else if(val < root.val){
            root.left = insertNode(root.left, val);
            return root;
        }
        return root;
    }
    public static int findMaxHeightBST(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftChild = 1 + findMaxHeightBST(root.left);
        int rightChild = 1 + findMaxHeightBST(root.right);
        return Math.max(leftChild, rightChild);
    }
    public static List<Integer> levelOrderTraversalBST(TreeNode root){
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if(node.left != null) {
                queue.offer(node.left);
            }if(node.right != null){
                queue.offer(node.right);
            }
        }
        return list;
    }
    public static List<Integer> inOrderTraversalBST(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null){return list;}
        list.addAll(inOrderTraversalBST(root.left));
        list.add(root.val);
        list.addAll(inOrderTraversalBST(root.right));
        return list;
    }
    public static boolean isValidBST(TreeNode root, int min, int max){
        //base case
        if(root == null){
            return true;
        }
        if(min > root.val || max < root.val){ //any node of left sub tree is greater than the root of parent (max)
            return false;
        }
        //all values in left subtree should be less than the value of the root node
        return (isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max));
    }
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null){
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        int count = 0;
        int maxCount = 0;
        queue.offer(root);
        count = queue.size();
        while(!queue.isEmpty()){
            if(count > maxCount){
                maxCount = count;
            }
            TreeNode node = queue.poll();
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
            count = queue.size();
        }
        return maxCount;
    }

    public static void findNthMaxBst(TreeNode root, int N){
        AtomicInteger idx = new AtomicInteger(0);
        findNthMaxBst(root, N, idx);
    }

    private static void findNthMaxBst(TreeNode root, int N, AtomicInteger c){
        if(root == null || c.get() >= N){
            return;
        }
        //since max go to the right sub tree first
        findNthMaxBst(root.right, N, c);
        if(c.addAndGet(1) == N){
            System.out.println(root.val);
            return;
        }
        //go to left sub tree
        findNthMaxBst(root.left, N, c);
    }

    public static void findNthMinBst(TreeNode root, int N){
        AtomicInteger count = new AtomicInteger(0);
        findNthMinBst(root, N, count);
    }

    private static void findNthMinBst(TreeNode root, int N, AtomicInteger count){
        if(root == null){
            return;
        }
        //traverse left child
        findNthMinBst(root.left, N, count);
        //check root if left is empty
        if(count.incrementAndGet() == N){
            System.out.println(root.val);
        }
        //lastly check right child
        findNthMinBst(root.right, N, count);
    }

    public static boolean hasPathSum(TreeNode root, int sum){
        if(root == null){
            return false;
        }
        int sumSoFar = sum - root.val;
        if(root.left == null && root.right == null && sumSoFar == 0){
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        if(root == p || root == q){ return root; }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null){
            return root;
        }else if(left != null){
            return left;
        }else{
            return right;
        }
    }
    public static void findKthAncestorNode(TreeNode root, TreeNode node, int k){
        if(root == null){
            return;
        }
        if(root.val == node.val){
            return;
        }
        if(root.val < node.val){
            findKthAncestorNode(root.right, node, k);
        }
    }

    //LinkedList Starts

    public static ListNode insertNodeSortedList(ListNode node, int val){
        ListNode curr = node;
        if(curr == null){
            return new ListNode(val);
        }
        if(curr.next == null){
            curr.next = new ListNode(val);
            return node;
        }
        while(curr != null){
            if(curr.val < val && curr.next != null && val < curr.next.val){
                ListNode tmp = curr.next;
                curr.next = new ListNode(val);
                curr.next.next = tmp;
                break;
            }else if(curr.val > val){
                ListNode tmp = new ListNode(val);
                tmp.next = curr;
                node = tmp;
                break;
            }else if(curr.val < val && curr.next == null){
                curr.next = new ListNode(val);
                break;
            }
            curr = curr.next;
        }
        return node;
    }
    public static ListNode rotateRight(ListNode head, int k) {
        if(k == 0 || head == null || head.next == null){return head;}
        ListNode newTail = head;
        while(newTail.next.next != null){
            newTail = newTail.next;
        }
        ListNode temp = newTail.next;
        newTail.next = null;
        temp.next = head;
        k--;
        return rotateRight(temp, k);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newLinkedList = new ListNode(0);
        ListNode p = l1;
        ListNode q = l2;
        ListNode ptr = newLinkedList;
        while(p != null || q != null){
            if(p != null && q != null){
                if(p.val < q.val){
                    ptr.next = new ListNode(p.val);
                    ptr = ptr.next;
                    p = p.next;
                }else{
                    ptr.next = new ListNode(q.val);
                    ptr = ptr.next;
                    q = q.next;
                }
            }
            else if(p != null){
                ptr.next = new ListNode(p.val);
                ptr = ptr.next;
                p = p.next;
            }else if(q != null){
                ptr.next = new ListNode(q.val);
                ptr = ptr.next;
                q = q.next;
            }
        }
        return newLinkedList.next;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode reversed = head;
        if(head != null){
            ListNode toBeReversed = head.next;
            while(toBeReversed != null){
                ListNode tmp = toBeReversed;
                toBeReversed = toBeReversed.next;
                tmp.next = reversed;
                if(reversed == head) {
                    reversed.next = null;
                }
                reversed = tmp;
            }
        }
        return reversed;
    }

    public static ListNode reverseListRecur(ListNode head) {
        if(head != null && head.next != null){
            ListNode newHead = reverseListRecur(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }
        return head;
    }

    public static void printList(ListNode head){
        ListNode curr = head;
        while(curr != null){
            System.out.println(curr.val);
            curr = curr.next;
        }
    }

    public static ListNode mergeSortList(ListNode list){
        if(list == null || list.next == null){
            return list;
        }
        ListNode next = list.next;
        list.next = null;
        return mergeTwoLists(list, mergeSortList(next));
    }

    public static ListNode removeNthNodeFromEnd(ListNode head, int n){
        if(head == null){
            return head;
        }
        if(head.next == null){
            return null;
        }
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode prev = dummyNode;
        ListNode slow = head;
        ListNode fast = head;
        int count = n;
        while(fast != null && count != 0){
            fast = fast.next;
            count--;
        }
        while(fast != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = slow.next;
        return dummyNode.next;
    }
    //Min Heap
    public static void minHeapify(int[] nums, int idx){
        int leftChild = (2 * idx) + 1;
        int rightChild = (2  * idx) + 2;
        int smallest = idx;
        if(leftChild < nums.length && nums[leftChild] < nums[idx]){
            smallest = leftChild;
        }
        if(rightChild < nums.length && nums[rightChild] < nums[smallest]){
            smallest = rightChild;
        }
        if(smallest != idx){
            swap(nums, idx, smallest);
            minHeapify(nums, smallest);
        }
    }

    public static void buildMinHeap(int[] nums){
        //starting with the last non-leaf nodes
        for(int i = nums.length/2; i >= 0 ; i--){
            minHeapify(nums, i);
        }
    }

    public static int extractKthSmallestElement(int[] nums, int k){
        if(k > nums.length){
            throw new IllegalArgumentException();
        }
        int smallest = nums[0];
        if(k == 1){
            return smallest;
        }
        int newNums[] = Arrays.copyOf(nums, nums.length);
        while(k != 0){
            smallest = newNums[0];
            newNums = Arrays.copyOfRange(newNums, 1, nums.length);
            buildMinHeap(newNums);
            k--;
        }
        return smallest;
    }

    public static void swap(int[] nums, int first, int second){
        int tmp = nums[first];
        nums[first] = nums[second];
        nums[second] = tmp;
    }

    public static ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return head;
        }
        while(head != null && head.val == val){
            head = head.next;
        }
        ListNode current = head;
        while(current != null && current.next != null){
            if(current.next.val == val){
                current.next = current.next.next;
            }else{
                current = current.next;
            }
        }
        return head;
    }

    public static boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while(fastPtr != null && fastPtr.next != null){
            stack.push(slowPtr.val);
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
        }
        if(fastPtr != null){
            slowPtr= slowPtr.next;
        }
        while(slowPtr != null){
            if(slowPtr.val != stack.pop()){
                return false;
            }
            slowPtr = slowPtr.next;
        }
        return true;
    }

    public static void rotateLinkedListKth(ListNode head, int k){
        //1-2-3-4-5
        //5-1-2-3-4
        ListNode current = head;

        while(current.next.next != null){
            current = current.next;
        }
        ListNode newLastNode = current;
        ListNode newHead = current.next;
        newHead.next = head;
        head = newHead;
        newLastNode.next = null;
        printList(head);
    }

    public static void verticalTraversal(TreeNode root, int start, SortedMap<Integer, List<Integer>> map) {
        if(root == null){ return; }
       //Using pre-order traversal root, left, right
       map.compute(start, (key, oldValue) ->{
           if(oldValue == null){
               oldValue = new ArrayList<>();
           }
           oldValue.add(root.val);
           return oldValue;
       });
       verticalTraversal(root.left, start - 1, map);
       verticalTraversal(root.right, start + 1, map);
    }

    public static void verticalTraversalLevelOrder(int start, Queue<TreeNode> queue, SortedMap<Integer, List<Integer>> map) {
        if(queue.isEmpty()){ return; }
        TreeNode node = queue.poll();
        if(node != null) {
            map.compute(start, (key, oldValue) -> {
                if (oldValue == null) {
                    oldValue = new ArrayList<>();
                }
                oldValue.add(node.val);
                return oldValue;
            });
            queue.offer(node.left);
            verticalTraversalLevelOrder(start - 1, queue, map);
            queue.offer(node.right);
            verticalTraversalLevelOrder(start + 1, queue, map);
        }
    }
}
