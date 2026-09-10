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
