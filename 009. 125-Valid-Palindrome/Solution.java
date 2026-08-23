class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while(left < right){

            // Move left pointer to the next valid alphanumeric character
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }

            // Move right pointer to the previous valid alphanumeric character
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }

            // Compare the characters (case-insensitive)
            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }

            // Move both pointers inward for the next comparison
            left++;
            right--;
        }

        return true;
    }
}
