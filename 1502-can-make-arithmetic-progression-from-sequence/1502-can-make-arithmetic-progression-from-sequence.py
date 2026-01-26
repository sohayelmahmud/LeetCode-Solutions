class Solution:
    def canMakeArithmeticProgression(self, arr: List[int]) -> bool:
        arr.sort()      
        common_difference = arr[1] - arr[0]
        from itertools import pairwise
        return all(second - first == common_difference 
                   for first, second in pairwise(arr))