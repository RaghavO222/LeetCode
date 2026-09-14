/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    int count = 0;
    int ans = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        solve(root, k);
        return ans;
    }
    
    public void solve(TreeNode root, int k){
        if(root == null){
            return;
        }

        // 1. Visit Left
        solve(root.left, k);
        
        // 2. Process Current Node
        count++;
        if(count == k){
            ans = root.val;
            return;
        }

        // 3. Visit Right (Pruned: only if we haven't found the answer yet)
        if(count < k){
            solve(root.right, k);
        }
    }
}