# 199. Binary Tree Right Side View

**Difficulty:** Medium

## Problem Statement
Given the `root` of a binary tree, imagine yourself standing on the **right side** of it, return the values of the nodes you can see ordered from top to bottom.

## Intuition & Approach
To get the right side view, we need to capture the rightmost node at every single level of the tree. This makes **Breadth-First Search (BFS)** (Level Order Traversal) the perfect tool for the job.

1. **Queue Setup:** Initialize a `Queue` and add the `root`.
2. **Process by Level:** While the queue is not empty, determine the number of nodes at the current level (`n = q.size()`).
3. **Capture the Last Node:** Iterate exactly `n` times. Pop the front node and add its children (left, then right) to the queue for the next level. Because we process left-to-right, the *very last node* we process in this `for` loop will be the rightmost node of that level.
4. **Record:** Store that final node's value in our `ans` list.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes in the tree. We enqueue and dequeue every node exactly once.
* **Space Complexity:** $O(W)$
  Where $W$ is the maximum width of the tree. In the worst case (a perfectly balanced tree), the bottom level contains $N/2$ nodes, requiring $O(N)$ space in the queue.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            TreeNode r = null;
            int n = q.size();
            
            // Process the entire level
            for(int i = 0; i < n; i++){
                TreeNode node = q.poll();
                if(node != null){
                    r = node; // Constantly update 'r'. By the end, it holds the rightmost node.
                    if(node.left != null) q.add(node.left);
                    if(node.right != null) q.add(node.right);
                }
            }
            // Add the last node of the level to our view
            if(r != null){
                ans.add(r.val);
            }
        }
        return ans;
    }
}