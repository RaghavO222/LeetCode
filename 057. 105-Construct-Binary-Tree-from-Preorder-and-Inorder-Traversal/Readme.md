# 105. Construct Binary Tree from Preorder and Inorder Traversal

**Difficulty:** Medium

## Problem Statement
Given two integer arrays `preorder` and `inorder` where `preorder` is the preorder traversal of a binary tree and `inorder` is the inorder traversal of the same tree, construct and return the binary tree.

## Intuition & Approach
To reconstruct a tree, we need to know what the root is, and what belongs in its left and right subtrees.
* **Preorder (Root, Left, Right)** always gives us the Root first.
* **Inorder (Left, Root, Right)** gives us the boundary. Once we locate the Root in the inorder array, everything to its left belongs to the left subtree, and everything to its right belongs to the right subtree.

1. **HashMap for Speed:** Finding the root in the `inorder` array via a linear scan takes $O(N)$ time per step. By pre-computing a `HashMap` that maps the node value to its index in the `inorder` array, we drop the lookup time to $O(1)$.
2. **Recursive Build:** 
    * The current root is always located at `preorder[rtIdx]`.
    * We fetch the index of this root from our `inorder` map (`mid`).
    * If there are elements to the left of `mid` (`mid > left`), we recursively build the `root.left` subtree. The next root will be immediately after the current one (`rtIdx + 1`).
    * If there are elements to the right of `mid` (`mid < right`), we recursively build the `root.right` subtree. The tricky part is calculating the preorder index of the right child's root: it is exactly `rtIdx + (size of left subtree) + 1`, which translates to `rtIdx + (mid - left) + 1`.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  Building the HashMap takes $O(N)$ time. The recursive function processes each node exactly once, taking $O(1)$ time per node thanks to the HashMap.
* **Space Complexity:** $O(N)$
  The HashMap requires $O(N)$ space, and the recursive call stack takes $O(H)$ space (which is bounded by $O(N)$).

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Map to quickly find the index of elements in the inorder array
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            mp.put(inorder[i], i);
        }
        return solve(preorder, mp, 0, 0, inorder.length - 1);
    }

    public TreeNode solve(int[] preorder, Map<Integer, Integer> mp, int rtIdx, int left, int right){
        if (left > right) {
            return null;
        }
        
        // The current root is dictated by the preorder array
        TreeNode root = new TreeNode(preorder[rtIdx]);

        // Find where this root splits the inorder array
        int mid = mp.get(preorder[rtIdx]);
        
        // Recursively build the left subtree
        if(mid > left){
            root.left = solve(preorder, mp, rtIdx + 1, left, mid - 1);
        }
        
        // Recursively build the right subtree
        // Skip over the entire left subtree in the preorder array: rtIdx + (mid - left) + 1
        if(mid < right){
            root.right = solve(preorder, mp, rtIdx + mid - left + 1, mid + 1, right);
        }
        
        return root;
    }
}