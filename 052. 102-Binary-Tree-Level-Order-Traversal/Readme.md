# 102. Binary Tree Level Order Traversal

**Difficulty:** Medium

## Problem Statement
Given the `root` of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

## Intuition & Approach
To traverse a tree level by level, we need to use **Breadth-First Search (BFS)**. The standard data structure for BFS is a **Queue**, operating on a First-In-First-Out (FIFO) basis.

The trick to returning the values grouped *by level* (as a list of lists) is keeping track of how many nodes are in the queue at the start of each level's processing.

1. **Initialization:** Handle the edge case of an empty tree. Otherwise, initialize a `Queue` and add the `root` node.
2. **Level-by-Level Processing:** While the queue is not empty, we are looking at a new level.
    * We record the current `size` of the queue. This is crucial! It tells us exactly how many nodes belong to this specific level.
    * We create a temporary list `l` to hold the values for this level.
3. **Process the Level:** We loop exactly `size` times. 
    * We pop the front node (`poll()`), add its value to our level list `l`.
    * If this node has a left child, we push it to the queue. If it has a right child, we push it to the queue. (These children will wait patiently at the back of the queue to be processed during the *next* level's loop).
4. **Finalize Level:** Once the inner loop finishes, the current level is complete. We add `l` to our main `ans` list and repeat until the tree is fully traversed.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes in the tree. We enqueue and dequeue every single node exactly once.
* **Space Complexity:** $O(W)$
  Where $W$ is the maximum width of the tree. This is the maximum number of nodes the queue will ever hold at any given time. In the worst-case scenario (a perfectly balanced tree), the bottom level contains roughly $N/2$ nodes, making the space complexity essentially $O(N)$.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if(root == null){
            return ans;
        } 
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Process until the queue is empty
        while(!queue.isEmpty()){
            // Lock in the number of nodes at the current level
            int size = queue.size();
            List<Integer> l = new ArrayList<>();

            // Process all nodes on this level
            for(int i = 0; i < size; i++){
                TreeNode curr = queue.poll();
                l.add(curr.val);

                // Queue up the children for the NEXT level
                if(curr.left != null){
                    queue.offer(curr.left);
                }
                if(curr.right != null){
                    queue.offer(curr.right);
                }
            }
            
            // Add the completed level to the final answer
            ans.add(l);
        }
        
        return ans;
    }
}
