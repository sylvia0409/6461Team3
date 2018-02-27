package part2;


public class Cache {
    private class Node{
        int address;
        String value;
        Node next;
        Node(int address, String value){
            this.address = address;
            this.value = value;
        }
    }
    private Memory mainMemory;
    private Node tail = new Node(0, mainMemory.getValue(0));
    private Node head = tail;

    Cache(Memory mainMemory){
        this.mainMemory = mainMemory;
        initCache();
    }

    //we use linkedlist to store 16 cache lines in one cache
    public void initCache(){
        for (int i = 1; i < 16; i++) {
            Node newNode = new Node(i, mainMemory.getValue(i));
            newNode.next = head;
            head = newNode;
        }
    }

    public String findOne(int address){
        Node current = head;
        while(current != null){
            //cache hit
            if(current.address == address){
                return current.value;
            }
            current = current.next;
        }
        //cache miss
        addOneCacheLine(address);
        return head.value;
    }

    public void addOneCacheLine(int address){
        //delete the last node
        Node find = head;
        while(find != null){
            if(find.next.next == null){
                find.next = null;
            }
            find = find.next;
        }
        //add new node to head
        Node newNode = new Node(address, mainMemory.getValue(address));
        newNode.next = head;
        head = newNode;
    }
}
