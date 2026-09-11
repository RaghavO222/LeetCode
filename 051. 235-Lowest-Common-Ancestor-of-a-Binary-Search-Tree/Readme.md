# 235. Lowest Common Ancestor of a Binary Search Tree

**Difficulty:** Medium

## Problem Statement
Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: "The lowest common ancestor is defined between two nodes `p` and `q` as the lowest node in `T` that has both `p` and `q` as descendants (where we allow a node to be a descendant of itself)."

## Intuition & Approach
The beauty of a Binary Search Tree (BST) is that it inherently organizes its values: everything to the left of a node is smaller, and everything to the right is larger. We can use this property to find the LCA without even needing a recursive call stack!

We traverse down the tree from the root. At each step, we simply ask: "Where are `p` and `q` relative to the current node?"

1. **Both are greater:** If both `p.val` and `q.val` are greater than the `root.val`, it means both nodes are exclusively in the right subtree. We step down to the right (`root = root.right`).
2. **Both are smaller:** If both `p.val` and `q.val` are less than the `root.val`, it means both nodes are exclusively in the left subtree. We step down to the left (`root = root.left`).
3. **The Split Point (LCA):** If neither of the above is true, it means `p` and `q` are on opposite sides of the current node (or one of them *is* the current node). The moment they diverge, we have found our Lowest Common Ancestor! We return the `root`.

## Complexity Analysis

* **Time Complexity:** $O(H)$
  Where $H$ is the height of the tree. In the worst case (a skewed tree), this takes $O(N)$ time. In a balanced BST, it takes $O(\log N)$ time. We only visit one node per level.
* **Space Complexity:** $O(1)$
  Because this solution uses a `while` loop instead of recursion, it completely avoids the memory overhead of the system call stack. The space complexity is strictly constant.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null){
            // If both p and q are greater, the LCA must be in the right subtree
            if(p.val > root.val && q.val > root.val){
                root = root.right;
            } 
            // If both p and q are smaller, the LCA must be in the left subtree
            else if(p.val < root.val && q.val < root.val){
                root = root.left;
            } 
            // We found the split point or one of the targets itself
            else{
                return root;
            }
        }
        return null;
    }
}
