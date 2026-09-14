# 230. Kth Smallest Element in a BST

**Difficulty:** Medium

## Problem Statement
Given the `root` of a binary search tree, and an integer `k`, return the $k^{th}$ smallest value (1-indexed) of all the values of the nodes in the tree.

## Intuition & Approach
Just like checking if a BST is valid, finding the $k^{th}$ smallest element relies on the fact that an **In-order Traversal (Left, Root, Right)** visits BST nodes in perfectly sorted, ascending order. 

1. **Counters:** We maintain a global `count` to track how many nodes we have processed, and an `ans` to store our final result.
2. **Recursive In-order:** We travel as far left as possible. 
3. **Check the Count:** As the recursion unwinds and we "visit" a node, we increment our `count`. If `count == k`, we have found our target! We save the value in `ans` and immediately `return` to stop further left-side processing.
4. **Pruning:** We wrap the right-side recursive call in an `if(count < k)` check. This ensures that once we find our answer, we don't waste time exploring the right subtrees.

## Complexity Analysis

* **Time Complexity:** $O(H + k)$
  Where $H$ is the height of the tree. We traverse down to the smallest element ($O(H)$) and then visit $k$ nodes. We stop early once we find the target.
* **Space Complexity:** $O(H)$
  For the recursive call stack.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    int count = 0;
    int ans = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        solve(root, k);
        return ans;
    }
    
    public void solve(TreeNode root, int k){
        if(root == null){
            return;
        }

        // 1. Visit Left
        solve(root.left, k);
        
        // 2. Process Current Node
        count++;
        if(count == k){
            ans = root.val;
            return;
        }

        // 3. Visit Right (Pruned: only if we haven't found the answer yet)
        if(count < k){
            solve(root.right, k);
        }
    }
}