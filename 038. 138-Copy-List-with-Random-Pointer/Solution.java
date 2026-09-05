/*
// Definition for a Node.
class Node { ... }
*/
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;

        Node curr = head;

        // 1. Create interwoven clone nodes
        while(curr != null){
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = newNode.next;
        }

        curr = head;

        // 2. Assign random pointers to the clones
        while(curr != null){
            if(curr.random != null){
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        curr = head;
        Node newHead = curr.next;
        Node newCurr = newHead;
        
        // 3. Unweave the lists
        while(curr != null){
            curr.next = newCurr.next;
            curr = curr.next;
            if(curr != null){
                newCurr.next = curr.next;
                newCurr = newCurr.next;
            }
        }

        return newHead;
    }
}
