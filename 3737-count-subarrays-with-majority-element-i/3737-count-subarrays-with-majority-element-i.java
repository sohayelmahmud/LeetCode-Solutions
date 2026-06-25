public class Solution {
    public int countMajoritySubarrays(int[] a, int target) {
        int n = a.length;
        int pre = n + 1;
        int res = 0;
        int[] count = new int[2 * n + 2];
        int[] acc = new int[2 * n + 2];
        count[pre] = acc[pre] = 1;
        for (int i : a) {
            pre += (i == target ? 1 : -1);
            count[pre]++;
            acc[pre] = acc[pre - 1] + count[pre];
            res += acc[pre - 1];
        }
        return res;
    }
}