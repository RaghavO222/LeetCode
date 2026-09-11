/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if(root == null){
            return ans;
        } 
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // Process until the queue is empty
        while(!queue.isEmpty()){
            // Lock in the number of nodes at the current level
            int size = queue.size();
            List<Integer> l = new ArrayList<>();

            // Process all nodes on this level
            for(int i = 0; i < size; i++){
                TreeNode curr = queue.poll();
                l.add(curr.val);

                // Queue up the children for the NEXT level
                if(curr.left != null){
                    queue.offer(curr.left);
                }
                if(curr.right != null){
                    queue.offer(curr.right);
                }
            }
            
            // Add the completed level to the final answer
            ans.add(l);
        }
        
        return ans;
    }
}
