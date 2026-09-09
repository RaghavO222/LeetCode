# 543. Diameter of Binary Tree

**Difficulty:** Easy

## Problem Statement
Given the `root` of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root. The length of a path between two nodes is represented by the number of edges between them.

## Intuition & Approach
The diameter at any specific node is simply the **height of its left subtree + the height of its right subtree**. 

Since the longest path in the whole tree might not actually go through the top `root` node, we need to check the diameter at *every single node* while we calculate the heights.

1. **Global Tracker:** We need a way to keep track of the maximum diameter we've seen so far across all recursive calls. Since Java passes primitives by value, we use a single-element array `int[] ans` to act as a mutable reference.
2. **Height Function:** We create a helper function `height(root, ans)` that does two things simultaneously:
    * It recursively calculates the `leftHeight` and `rightHeight` of the current node.
    * It calculates the diameter passing through the current node (`leftHeight + rightHeight`) and updates `ans[0]` if this local diameter is the largest we've seen.
3. **Return Height:** The function must still return the actual height of the current node to its parent, which is `Math.max(leftHeight, rightHeight) + 1`.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Where $N$ is the number of nodes. The height function calculates the diameter as it visits each node bottom-up, ensuring we only traverse the tree exactly once.
* **Space Complexity:** $O(H)$
  Where $H$ is the height of the tree. This is the space used by the recursion call stack.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        // Use an array of size 1 to pass the maximum diameter by reference
        int[] ans = new int[1];
        height(root, ans);
        return ans[0];
    }

    public int height(TreeNode root, int[] ans) {
        if (root == null) {
            return 0;
        }

        // Recursively find the height of left and right subtrees
        int leftHeight = height(root.left, ans);
        int rightHeight = height(root.right, ans);

        // Update the maximum diameter found so far
        ans[0] = Math.max(ans[0], leftHeight + rightHeight);

        // Return the height of the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
