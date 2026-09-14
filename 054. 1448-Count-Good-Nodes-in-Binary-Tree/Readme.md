# 1448. Count Good Nodes in Binary Tree

**Difficulty:** Medium

## Problem Statement
Given a binary tree `root`, a node $X$ in the tree is named **good** if in the path from root to $X$ there are no nodes with a value greater than $X$.

Return the number of good nodes in the binary tree.

## Intuition & Approach
This is a path-dependent problem, which makes **Depth-First Search (DFS)** the natural choice. As we travel down a path from the root to the leaves, we just need to carry a "record" of the highest value we've seen so far.

1. **State Tracking:** We use a global `ans` variable to count the good nodes. 
2. **Recursive Traversal:** We pass the current node and the `max` value seen so far on this specific path into our `solve` function.
3. **Evaluate:** If the current `root.val` is greater than or equal to `max`, it qualifies as a "good" node! We increment `ans`.
4. **Propagate:** We continue the DFS to the left and right children. Crucially, the new `max` we pass down is `Math.max(root.val, max)` to ensure children know about the largest hurdle above them.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes. We visit each node exactly once.
* **Space Complexity:** $O(H)$
  Where $H$ is the height of the tree. This accounts for the maximum depth of the system call stack.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    int ans;
    
    public void solve(TreeNode root, int max){
        if(root == null){
            return;
        }
        
        // If the current node is greater than or equal to the path's maximum, it's good
        if(root.val >= max){
            ans++;
        }
        
        // Pass the updated maximum down to the children
        solve(root.left, Math.max(root.val, max));
        solve(root.right, Math.max(root.val, max));
    }
    
    public int goodNodes(TreeNode root) {
        ans = 0;
        solve(root, Integer.MIN_VALUE);
        return ans;
    }
}