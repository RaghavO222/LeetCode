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