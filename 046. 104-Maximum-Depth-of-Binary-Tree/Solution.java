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
