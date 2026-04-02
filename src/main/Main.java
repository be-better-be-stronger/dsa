package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		System.out.println(max(new int[] {1, 5, 3, 6, 1}));
	}
	
	/*
	 * Đề: Cho mảng số và target. Tìm 2 số có tổng = target 
	 * Input: nums = [2, 7, 11, 15], target = 9 Output:[0, 1]
	 */
	public static int[] _2Sum (int[] nums, int target) {
		if(nums.length < 2) return new int[] {};
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i ++) {
			if(map.containsKey(target - nums[i])) 
				return new int[] {map.get(target - nums[i]), i};
			map.put(nums[i], i);
		}
		return new int[] {};
	}
	
	/*
	 * Đề: Tìm 3 số có tổng = 0	 * 
	 * Input: [-1, 0, 1, 2, -1, -4]	 * 
	 * Output: [[-1, -1, 2], [-1, 0, 1]]
	 */
	public static List<List<Integer>> _3Sum(int[] nums) {
		//1.1. create list result
		List<List<Integer>> result = new ArrayList<>();
		//1.2 sort
		Arrays.sort(nums);
		
		//2. for loop
		for(int i = 0; i < nums.length - 2; i++) {
			//2.1. skip duplicate at i
			if(i > 0 && nums[i] == nums[i - 1]) continue;
			// 2.2 set left = i + 1, right = last index
			int left = i + 1, right = nums.length - 1;
			while(left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if(sum == 0) {
					result.add(Arrays.asList(nums[i], nums[left], nums[right]));
					while(left < right && nums[left] == nums[left + 1]) left++;
					while(left < right && nums[right] == nums[right - 1]) right--;
					left++;
					right--;
				}
				else if(sum < 0) left++;
				else right--;
			}				
		}
		//3. return list
		return result;
	}
	
	public static int max(int[] nums) {
		int rs = nums[0];
		for(int i = 1; i < nums.length; i++) {
			rs = Math.max(rs, nums[i]);
		}
		return rs;
	}
	

}
