/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    int ans;
    
    public void solve(TreeNode root, int max){
        if(root == null){
            return;
        }
        
        // If the current node is greater than or equal to the path's maximum, it's good
        if(root.val >= max){
            ans++;
        }
        
        // Pass the updated maximum down to the children
        solve(root.left, Math.max(root.val, max));
        solve(root.right, Math.max(root.val, max));
    }
    
    public int goodNodes(TreeNode root) {
        ans = 0;
        solve(root, Integer.MIN_VALUE);
        return ans;
    }
}