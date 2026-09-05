// Doubly Linked List Node
class Node{
    int key, value;
    Node prev, next;
    Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail; // Dummy head and tail

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        
        // Initialize dummy head and tail
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        // Node was accessed, move it to the front
        remove(node);
        insertAtFront(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value; // Update value
            remove(node);
            insertAtFront(node);
        } else {
            // Evict LRU node if capacity is reached
            if(map.size() == capacity){
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            insertAtFront(newNode);
            map.put(key, newNode);
        }
    }

    // Helper: Removes a node from the doubly linked list
    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper: Inserts a node right behind the dummy head
    private void insertAtFront(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
