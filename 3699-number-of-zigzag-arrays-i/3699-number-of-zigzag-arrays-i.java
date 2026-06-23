public class Solution {
    public int zigZagArrays(int n, int l, int r) {
        long MOD = 1000000007L;
        int m = r - l + 1;
        
        long[] dp_up = new long[m];
        long[] dp_down = new long[m];
        
        for (int j = 0; j < m; j++) {
            dp_up[j] = j;
            dp_down[j] = m - 1 - j;
        }
        
        for (int i = 3; i <= n; i++) {
            long[] next_up = new long[m];
            long[] next_down = new long[m];
            
            long sum_down = 0;
            for (int j = 0; j < m; j++) {
                next_up[j] = sum_down;
                sum_down = (sum_down + dp_down[j]) % MOD;
            }
            
            long sum_up = 0;
            for (int j = m - 1; j >= 0; j--) {
                next_down[j] = sum_up;
                sum_up = (sum_up + dp_up[j]) % MOD;
            }
            
            dp_up = next_up;
            dp_down = next_down;
        }
        
        long total = 0;
        for (int j = 0; j < m; j++) {
            total = (total + dp_up[j] + dp_down[j]) % MOD;
        }
        
        return (int) total;
    }
}