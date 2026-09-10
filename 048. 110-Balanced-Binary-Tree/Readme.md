# 110. Balanced Binary Tree

**Difficulty:** Easy

## Problem Statement
Given a binary tree, determine if it is height-balanced.

A height-balanced binary tree is defined as a binary tree in which the left and right subtrees of *every* node differ in height by no more than `1`.

## Intuition & Approach
To check if a tree is balanced, we must verify that the height of the left and right subtrees for every single node differ by at most `1`. This solution uses a **Top-Down Depth-First Search (DFS)** approach.

1. **Height Helper:** We create a helper function `checkHeightDiff(root)` that calculates the maximum depth (height) of any given node.
2. **Base Case:** In our main `isBalanced` function, an empty tree (`root == null`) is inherently balanced, so we return `true`.
3. **Check Current Node:** We calculate the height of the current node's left subtree and right subtree. If the absolute difference between them is strictly greater than `1`, the tree is imbalanced, and we return `false`.
4. **Recursive Leap:** If the current node is balanced, we must still ensure that *all* of its children are balanced. We recursively call `isBalanced` on both the left and right children and return `true` only if both subtrees are also perfectly balanced.

## Complexity Analysis

* **Time Complexity:** $O(N^2)$ worst-case. 
  Where $N$ is the number of nodes. For every node, we call `checkHeightDiff`, which iterates through its children. This means upper-level nodes are repeatedly visited by the height function. *(Note: While correct, this can be optimized to $O(N)$ by calculating the balance simultaneously with the height from the bottom up!)*
* **Space Complexity:** $O(N)$ worst-case.
  In a completely skewed tree, the recursion stack will go $N$ levels deep. In a perfectly balanced tree, it will be $O(\log N)$.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    // Helper function to calculate the height of a subtree
    public int checkHeightDiff(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max(checkHeightDiff(root.left), checkHeightDiff(root.right));
    }

    public boolean isBalanced(TreeNode root) {
        // Base case: an empty tree is perfectly balanced
        if(root == null){
            return true;
        }
        
        int left = checkHeightDiff(root.left);
        int right = checkHeightDiff(root.right);

        // If the current node is imbalanced, the whole tree is imbalanced
        if(Math.abs(left - right) > 1){
            return false;
        }
        
        // Ensure both subtrees are also completely balanced
        return isBalanced(root.left) && isBalanced(root.right);
    }
}
