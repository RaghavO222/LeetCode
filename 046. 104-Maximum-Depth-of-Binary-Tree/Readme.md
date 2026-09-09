# 104. Maximum Depth of Binary Tree

**Difficulty:** Easy

## Problem Statement
Given the `root` of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

## Intuition & Approach
Finding the depth of a tree is one of the most fundamental recursive patterns. The depth of any given node is `1` (for the node itself) plus the *maximum* depth of its two subtrees.

1. **Base Case:** If the `root` is `null`, it contributes `0` to the depth.
2. **Recursive Step:** We ask the left subtree, "What is your maximum depth?" and we ask the right subtree, "What is your maximum depth?"
3. **Combine:** We take the maximum of those two answers using `Math.max()`, add `1` to account for the current node, and return the result upwards.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes. We traverse every single node in the tree exactly once.
* **Space Complexity:** $O(H)$
  Where $H$ is the height of the tree, due to the recursion call stack. In the worst case (skewed tree), it's $O(N)$. In the best case (balanced tree), it's $O(\log N)$.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        // Base case: an empty tree has a depth of 0
        if(root == null){
            return 0;
        }

        // Add 1 for the current node, and add the max depth of the subtrees
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
