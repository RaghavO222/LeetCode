# 226. Invert Binary Tree

**Difficulty:** Easy

## Problem Statement
Given the `root` of a binary tree, invert the tree, and return its root. (Every left child becomes a right child, and vice versa).

## Intuition & Approach
This is famously the problem that tripped up the creator of Homebrew in a Google interview! It is actually a very straightforward application of **Depth-First Search (DFS)** using recursion.

1. **Base Case:** If the `root` is `null`, there is nothing to invert, so we simply return `null`.
2. **Swap Children:** For the current node, we temporarily store the right child in a `temp` variable. Then, we overwrite the right child with the left child, and the left child with the `temp` (the old right child).
3. **Recursive Leap:** Now that the current node's immediate children are swapped, we recursively call `invertTree` on both the left and right subtrees to ensure all the grandchildren and lower levels are also inverted.
4. **Return:** Finally, we return the original `root` node, which now sits atop a perfectly mirrored tree.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes in the binary tree. We visit every single node exactly once to perform the swap.
* **Space Complexity:** $O(H)$
  Where $H$ is the height of the tree. This accounts for the memory used by the system call stack during recursion. In the worst-case scenario (a completely unbalanced, list-like tree), this is $O(N)$. In a perfectly balanced tree, it is $O(\log N)$.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }

        // Swap the left and right children
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;

        // Recursively invert the subtrees
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
