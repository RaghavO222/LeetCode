# 146. LRU Cache

**Difficulty:** Medium

## Problem Statement
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
Implement the `LRUCache` class:
* `LRUCache(int capacity)` Initialize the LRU cache with positive size capacity.
* `int get(int key)` Return the value of the `key` if the key exists, otherwise return `-1`.
* `void put(int key, int value)` Update the value of the `key` if the `key` exists. Otherwise, add the `key-value` pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.

The functions `get` and `put` must each run in $O(1)$ average time complexity.

## Intuition & Approach
To achieve $O(1)$ lookups, we absolutely need a **HashMap**. However, a HashMap cannot keep track of "order" (i.e., what was used most recently vs. least recently). To achieve $O(1)$ insertion, deletion, and order management, we pair the HashMap with a **Doubly Linked List**.

1. **The Architecture:** 
    * The `HashMap` maps integer keys to actual `Node` objects.
    * The `Doubly Linked List` orders the nodes. We use two dummy nodes, `head` (most recently used end) and `tail` (least recently used end), to make adding and removing nodes painless.
2. **`get(key)`:** If the key exists in the map, we have accessed it! We must remove it from its current position in the linked list and re-insert it right behind `head` (marking it as the Most Recently Used).
3. **`put(key, value)`:** 
    * If the key exists, we update its value, remove it from the list, and insert it behind `head`.
    * If it's a new key, we first check the capacity. If we are full, we delete the node right in front of `tail` (the Least Recently Used node) from both the linked list and the map. Finally, we create the new node, insert it behind `head`, and add it to the map.

## Complexity Analysis

* **Time Complexity:** $O(1)$ for both `get` and `put`. Hash map operations take constant time. Removing a node and inserting a node at the front of a Doubly Linked List only requires rewiring 4 pointers, which is strictly $O(1)$.
* **Space Complexity:** $O(\text{capacity})$
  The `HashMap` and the `Doubly Linked List` will both store at most `capacity` number of nodes simultaneously.

## Java Solution

```java
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
