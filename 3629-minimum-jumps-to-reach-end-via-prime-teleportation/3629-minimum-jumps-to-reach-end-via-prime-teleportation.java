import java.util.*;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int maxVal = 0;
        for (int x : nums) if (x > maxVal) maxVal = x;

        int[] minPrime = new int[maxVal + 1];
        for (int i = 2; i * i <= maxVal; i++) {
            if (minPrime[i] == 0) {
                for (int j = i * i; j <= maxVal; j += i) {
                    if (minPrime[j] == 0) minPrime[j] = i;
                }
            }
        }
        for (int i = 2; i <= maxVal; i++) {
            if (minPrime[i] == 0) minPrime[i] = i;
        }

        int[] head = new int[maxVal + 1];
        Arrays.fill(head, -1);
        int[] nextIdx = new int[n * 8];
        int[] valIdx = new int[n * 8];
        int edgeCount = 0;

        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            while (temp > 1) {
                int p = minPrime[temp];
                valIdx[edgeCount] = i;
                nextIdx[edgeCount] = head[p];
                head[p] = edgeCount++;
                while (temp % p == 0) temp /= p;
            }
        }

        int[] q = new int[n];
        int qHead = 0, qTail = 0;
        q[qTail++] = 0;

        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;

        boolean[] visitedPrime = new boolean[maxVal + 1];

        while (qHead < qTail) {
            int curr = q[qHead++];
            if (curr == n - 1) return dist[curr];

            if (curr + 1 < n && dist[curr + 1] == -1) {
                dist[curr + 1] = dist[curr] + 1;
                q[qTail++] = curr + 1;
            }
            if (curr - 1 >= 0 && dist[curr - 1] == -1) {
                dist[curr - 1] = dist[curr] + 1;
                q[qTail++] = curr - 1;
            }

            int val = nums[curr];
            if (val > 1 && minPrime[val] == val && !visitedPrime[val]) {
                visitedPrime[val] = true;
                for (int e = head[val]; e != -1; e = nextIdx[e]) {
                    int target = valIdx[e];
                    if (dist[target] == -1) {
                        dist[target] = dist[curr] + 1;
                        q[qTail++] = target;
                    }
                }
            }
        }
        return -1;
    }
}