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
