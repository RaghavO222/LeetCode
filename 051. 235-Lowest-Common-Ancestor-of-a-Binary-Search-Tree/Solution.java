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
