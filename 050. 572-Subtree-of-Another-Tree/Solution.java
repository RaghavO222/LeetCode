/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    // Helper function to serialize the tree into a String
    public String preOrder(TreeNode root){
        if(root == null){
            return "null"; // Explicitly record nulls to maintain structure
        }

        StringBuilder sb = new StringBuilder("^"); // Delimiter prevents value bleeding (e.g., 12 vs 2)
        sb.append(root.val);
        sb.append(preOrder(root.left));
        sb.append(preOrder(root.right));

        return sb.toString();
    }
    
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String left = preOrder(root);
        String right = preOrder(subRoot);

        // If the subRoot string is inside the root string, it's a valid subtree!
        return (left.contains(right));
    }
}
