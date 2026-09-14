/**
 * Definition for a binary tree node.
 * public class TreeNode { ... }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Map to quickly find the index of elements in the inorder array
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            mp.put(inorder[i], i);
        }
        return solve(preorder, mp, 0, 0, inorder.length - 1);
    }

    public TreeNode solve(int[] preorder, Map<Integer, Integer> mp, int rtIdx, int left, int right){
        if (left > right) {
            return null;
        }
        
        // The current root is dictated by the preorder array
        TreeNode root = new TreeNode(preorder[rtIdx]);

        // Find where this root splits the inorder array
        int mid = mp.get(preorder[rtIdx]);
        
        // Recursively build the left subtree
        if(mid > left){
            root.left = solve(preorder, mp, rtIdx + 1, left, mid - 1);
        }
        
        // Recursively build the right subtree
        // Skip over the entire left subtree in the preorder array: rtIdx + (mid - left) + 1
        if(mid < right){
            root.right = solve(preorder, mp, rtIdx + mid - left + 1, mid + 1, right);
        }
        
        return root;
    }
}