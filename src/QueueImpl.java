import java.util.Arrays;
import java.util.stream.IntStream;

public class QueueImpl {
    public static void main(String[] args) {
        //test for empty queue
        ArrayQueue queue = new ArrayQueue();
        try { queue.poll(); }catch(IllegalStateException ie){
            System.out.println("Queue is empty");
        }
        IntStream.rangeClosed(1, 30).forEach(queue::offer);
        IntStream.rangeClosed(1, 30).forEach(e -> System.out.println(queue.poll()));
        IntStream.rangeClosed(1, 30).forEach(queue::offer);
        IntStream.rangeClosed(1, 30).forEach(e -> System.out.println(queue.poll()));

        LinkedQueue linkedQueue = new LinkedQueue(0);
        IntStream.rangeClosed(1, 30).forEach(linkedQueue::offer);
        IntStream.rangeClosed(1, 30).forEach(e -> System.out.println(linkedQueue.poll()));
        IntStream.rangeClosed(1, 30).forEach(linkedQueue::offer);
        IntStream.rangeClosed(1, 30).forEach(e -> System.out.println(linkedQueue.poll()));


    }
}
class ArrayQueue{

    private int[] queue;
    private int size = 0;
    private int head = -1;
    private int tail = -1;
    private static final int DEFAULT_CAPACITY = 16;

    ArrayQueue(int initialCapacity){
        queue = new int[initialCapacity];
    }

    ArrayQueue(){
        queue = new int[DEFAULT_CAPACITY];
    }

    public void offer(int element){
        ensureCapacity();
        queue[++tail] = element;
        if(head == -1){head++;}
        size++;
    }

    public int poll(){
        if(head == -1){
            throw new IllegalStateException("Queue is empty");
        }
        int element = queue[head];
        queue[head] = 0;
        head++;
        return element;
    }

    private void ensureCapacity(){
        if(queue.length == size){
            queue = Arrays.copyOf(queue, queue.length * 2);
        }
    }
}
class LinkedQueue{

    private Node head;
    private int size = 0;
    private Node tail;

    class Node{
        public Node(int val){
            this.val = val;
        }
        private int val;
        private Node next;
    }

    public LinkedQueue(int val){
        if(head == null){
            head = new Node(val);
        }
        tail = head;
    }

    public void offer(int val){
        tail.next = new Node(val);
        tail = tail.next;
    }

    public int poll(){
        int element = head.val;
        head = head.next;
        return element;
    }

}
