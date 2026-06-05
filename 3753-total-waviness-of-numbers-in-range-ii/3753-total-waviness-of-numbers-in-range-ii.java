class Solution {
    static class State {
        long count;
        long sum;
        State(long count, long sum) {
            this.count = count;
            this.sum = sum;
        }
    }

    private State[][][][][] dp = new State[18][11][11][2][2];
    private boolean[][][][][] visited = new boolean[18][11][11][2][2];
    private String s;

    private State solveDp(int idx, int prev, int prev2, int isLess, int isStarted) {
        if (idx == s.length()) {
            return new State(1, 0);
        }

        if (visited[idx][prev][prev2][isLess][isStarted]) {
            return dp[idx][prev][prev2][isLess][isStarted];
        }

        long count = 0;
        long sum = 0;
        int limit = (isLess == 1) ? 9 : (s.charAt(idx) - '0');

        for (int d = 0; d <= limit; d++) {
            int nextLess = (isLess == 1 || d < (s.charAt(idx) - '0')) ? 1 : 0;

            if (isStarted == 0 && d == 0) {
                State nextState = solveDp(idx + 1, 10, 10, nextLess, 0);
                count += nextState.count;
                sum += nextState.sum;
            } else {
                long wave = 0;
                if (isStarted == 1 && prev != 10 && prev2 != 10) {
                    if ((prev > prev2 && prev > d) || (prev < prev2 && prev < d)) {
                        wave = 1;
                    }
                }

                State nextState = solveDp(idx + 1, d, prev, nextLess, 1);
                count += nextState.count;
                sum += nextState.sum + wave * nextState.count;
            }
        }

        visited[idx][prev][prev2][isLess][isStarted] = true;
        return dp[idx][prev][prev2][isLess][isStarted] = new State(count, sum);
    }

    private long getWaviness(long val) {
        if (val < 100) return 0;
        s = String.valueOf(val);
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 11; k++) {
                    for (int l = 0; l < 2; l++) {
                        visited[i][j][k][l][0] = false;
                        visited[i][j][k][l][1] = false;
                    }
                }
            }
        }
        return solveDp(0, 10, 10, 0, 0).sum;
    }

    public long totalWaviness(long num1, long num2) {
        return getWaviness(num2) - getWaviness(num1 - 1);
    }
}