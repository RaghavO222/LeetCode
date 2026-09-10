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
