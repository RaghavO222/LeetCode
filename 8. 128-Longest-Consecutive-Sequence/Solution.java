class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> nSet = new HashSet<>();

        for(int n: nums){
            nSet.add(n);
        }

        int l = 0;

        for(int n : nSet){
            // Only start counting if 'n' is the beginning of a sequence
            if(!nSet.contains(n - 1)){
                int length = 1;

                while(nSet.contains(n + length)){
                    length++;
                }

                l = Math.max(l, length);
            }
        }

        return l;
    }
}
