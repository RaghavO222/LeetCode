# 98. Validate Binary Search Tree

**Difficulty:** Medium

## Problem Statement
Given the `root` of a binary tree, determine if it is a valid binary search tree (BST).

## Intuition & Approach
The defining property of a valid BST is that an **In-order Traversal (Left, Root, Right)** will always yield nodes in strictly ascending order. 

Instead of doing a complex recursive range-check, we can just do an Iterative In-order traversal using a Stack and ensure that every node we pop is strictly greater than the previous node we popped.

1. **Stack Setup:** We use a `Stack` to simulate the recursive call stack, diving as far left as possible.
2. **Track Previous:** We maintain a `pre` pointer to track the last node we successfully processed.
3. **Validate:** When we pop a node from the stack, we compare it to `pre`. If `root.val <= pre.val`, the ascending order is broken, and it's not a valid BST.
4. **Traverse Right:** Once the current node is validated, we update `pre = root` and move to explore `root.right`.

## Complexity Analysis

* **Time Complexity:** $O(N)$
  In the worst case, we process every node in the tree once.
* **Space Complexity:** $O(H)$
  Where $H$ is the height of the tree, representing the maximum number of nodes pushed onto the stack at one time.

## Java Solution

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        
        Stack<TreeNode> st = new Stack<>();
        TreeNode pre = null;
        
        while(root != null || !st.isEmpty()){
            // Dive all the way to the bottom left
            while(root != null){
                st.push(root);
                root = root.left;
            }
            
            root = st.pop();
            
            // Check if the strictly increasing order is violated
            if(pre != null && root.val <= pre.val){
                return false;
            }
            
            pre = root;
            root = root.right;
        }
        return true;
    }
}