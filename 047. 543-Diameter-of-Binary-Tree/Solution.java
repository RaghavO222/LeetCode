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
