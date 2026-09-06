/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        List<Integer> list = new ArrayList<>();

        // 1. Extract all values from every linked list
        for(ListNode a : lists){
            ListNode curr = a;

            while(curr != null){
                list.add(curr.val);
                curr = curr.next;
            }
        }

        // 2. Handle edge case of empty input
        if(list.size() == 0){
            return null;
        }

        // 3. Sort all the extracted values globally
        Collections.sort(list);

        // 4. Rebuild a brand new linked list from the sorted values
        ListNode ans = new ListNode(list.get(0));
        ListNode curr = ans;

        for(int i = 1; i < list.size(); i++){
            curr.next = new ListNode(list.get(i));
            curr = curr.next;
        }

        return ans;
    }
}
