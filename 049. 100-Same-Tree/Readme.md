# 100. Same Tree

**Difficulty:** Easy

## Problem Statement
Given the roots of two binary trees `p` and `q`, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

## Intuition & Approach
This is a textbook application of **Recursive Depth-First Search (DFS)**. We can traverse both trees simultaneously and compare their nodes at every step.

1. **Both Null:** If both `p` and `q` reach a `null` node at the exact same time, their structure matches up to this point. We return `true`.
2. **Mismatch Detected:** We return `false` if any of the following are true:
    * One node is `null` but the other is not (structural mismatch).
    * Both nodes exist, but their values are different (`p.val != q.val`).
3. **Recursive Leap:** If the current nodes are identical, we must verify their children. We recursively call `isSameTree` on their left children AND their right children. The tree is only the "same" if both recursive calls return `true`.

## Complexity Analysis

* **Time Complexity:** $O(\min(N, M))$
  Where $N$ and $M$ are the number of nodes in trees `p` and `q`. The algorithm stops as soon as it finds a mismatch, so it only traverses as many nodes as the smaller tree dictates (or all nodes if they are identical).
* **Space Complexity:** $O(\min(H_p, H_q))$
  Where $H$ represents the height of the trees. This accounts for the maximum depth of the system call stack during recursion.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Both nodes are null, we've reached the end of identical branches
        if(p == null && q == null){
            return true;
        }

        // Structural mismatch OR value mismatch
        if(p == null || q == null || p.val != q.val){
            return false;
        }

        // Recursively check the left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
