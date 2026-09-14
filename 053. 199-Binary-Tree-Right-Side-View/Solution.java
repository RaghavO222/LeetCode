/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            TreeNode r = null;
            int n = q.size();
            
            // Process the entire level
            for(int i = 0; i < n; i++){
                TreeNode node = q.poll();
                if(node != null){
                    r = node; // Constantly update 'r'. By the end, it holds the rightmost node.
                    if(node.left != null) q.add(node.left);
                    if(node.right != null) q.add(node.right);
                }
            }
            // Add the last node of the level to our view
            if(r != null){
                ans.add(r.val);
            }
        }
        return ans;
    }
}