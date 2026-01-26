class Solution:
    def reverse(self, x: int) -> int:
        MAX_INT = 2147483647  # 2^31 - 1
        MIN_INT = -2147483648 # -2^31

        res = 0
        while x != 0:
            digit = int(x % 10) if x > 0 else int(x % -10)
            x = int(x / 10)
            if res > MAX_INT // 10 or (res == MAX_INT // 10 and digit > 7):
                return 0
            if res < int(MIN_INT / 10) or (res == int(MIN_INT / 10) and digit < -8):
                return 0
            res = res * 10 + digit
        return res