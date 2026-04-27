class Solution {
    public boolean validDigit(int n, int x) {
        String s = String.valueOf(n);
        char target = (char) (x + '0');

        if (s.charAt(0) == target) {
            return false;
        }

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == target) {
                return true;
            }
        }

        return false;
    }
}