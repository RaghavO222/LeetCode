# 572. Subtree of Another Tree

**Difficulty:** Easy

## Problem Statement
Given the roots of two binary trees `root` and `subRoot`, return `true` if there is a subtree of `root` with the same structure and node values of `subRoot` and `false` otherwise.

A subtree of a binary tree `tree` is a tree that consists of a node in `tree` and all of this node's descendants. The tree `tree` could also be considered as a subtree of itself.

## Intuition & Approach
While you could use the `isSameTree` logic and check every single node in `root` against `subRoot`, there is a brilliant alternative: **Tree Serialization**.

If we convert both trees into a string representation using a Preorder Traversal (Root, Left, Right), a subtree will literally just be a substring of the main tree's string! 

1. **Serialize with Delimiters:** We write a `preOrder` helper function that converts a tree to a `String`. 
    * We *must* use a delimiter (like `^`) before every value. If we don't, a tree with node `12` might accidentally trigger a false positive when checking for a subtree with node `2`.
    * We also record `null` nodes explicitly, which ensures the structural shape of the tree is perfectly preserved in the string.
2. **String Matching:** We serialize `root` into the string `left`, and `subRoot` into the string `right`. Finally, we just check if `left.contains(right)`.

## Complexity Analysis

* **Time Complexity:** $O(N + M)$
  Where $N$ is the number of nodes in `root` and $M$ is the number of nodes in `subRoot`. Serializing both trees takes linear time relative to their sizes. The `String.contains()` method in Java uses string matching which typically runs in $O(N)$ time.
* **Space Complexity:** $O(N + M)$
  We allocate memory to store the `StringBuilder` and final `String` representations of both entire trees.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    // Helper function to serialize the tree into a String
    public String preOrder(TreeNode root){
        if(root == null){
            return "null"; // Explicitly record nulls to maintain structure
        }

        StringBuilder sb = new StringBuilder("^"); // Delimiter prevents value bleeding (e.g., 12 vs 2)
        sb.append(root.val);
        sb.append(preOrder(root.left));
        sb.append(preOrder(root.right));

        return sb.toString();
    }
    
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String left = preOrder(root);
        String right = preOrder(subRoot);

        // If the subRoot string is inside the root string, it's a valid subtree!
        return (left.contains(right));
    }
}
