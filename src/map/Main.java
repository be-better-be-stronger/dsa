package map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Integer a = null;
		System.out.println(a);
	}
	
	/*
	 * Two Sum Đề: Cho mảng nums và số target Tìm 2 index sao cho: nums[i] + nums[j]
	 * = target
	 */
	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int complete;
		for(int i = 0; i < nums.length; i++) {
			complete = target - nums[i];
			if(map.containsKey(complete))
				return new int[] {map.get(complete), i};
			map.put(nums[i], i);
		}
		return new int[] {};
	}
	
	public static int[] twoSumSorted(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		
		while(left < right) {
			int sum = nums[left] + nums[right];
			if(sum == target) return new int[] {left, right};
			else if(sum < target) left++;
			else right--;
		}
		return new int[] {};
	}
	
	public static int[] twoSums(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		while(l < r) {
			int sum = nums[l] + nums[r];
			if(sum == target) return new int[] {l, r};
			else if(sum > target) r--;
			else l++;
		}
		return new int[] {};
	}
	
	/*
	 * Remove Duplicates (Two Pointers) 
	 * Array đã sort: [1,1,2,2,3] 
	 * 👉 remove duplicate in-place 
	 * 👉 trả về length mới
	 */
	public static int removeDuplicates(int[] nums) {
	    if (nums.length == 0) return 0;

	    int slow = 0;

	    for (int fast = 1; fast < nums.length; fast++) {
	        if (nums[fast] != nums[slow]) {
	            slow++;
	            nums[slow] = nums[fast];
	        }
	    }

	    return slow + 1;
	}
	
	public static int[] solution2(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int complete;
		for(int i = 0; i < nums.length; i++) {
			complete = target - nums[i];
			if(map.containsKey(complete)) return new int[] {map.get(complete), i};
			map.put(nums[i], i);
		}
		return new int[] {};
	}
	
	/*
	 * Cho mảng nums, kiểm tra xem có phần tử nào xuất hiện ít nhất 2 lần không
	 * Trả về true nếu có duplicate false nếu tất cả phần tử đều khác nhau
	 */
	public static boolean solution3(int[] nums) {
		Set<Integer> seen = new HashSet<>();
		for(int i = 0; i < nums.length; i++) {
			if(!seen.add(nums[i])) return true;
		}
		return false;
	}
//	Cho mảng nums, tìm tổng lớn nhất của một đoạn con liên tiếp
	public static int solution4 (int[] nums) {
		int current = nums[0];
	    int best = nums[0];

	    for (int i = 1; i < nums.length; i++) {
	        current = Math.max(nums[i], current + nums[i]);
	        best = Math.max(best, current);
	    }

	    return best;
	}
	
	
	//tổng các chữ số của n
	public static int s1(int n) {
		int sum = 0;
		while(n > 0) {
			sum += n%10;
			n /= 10;
		}
		return sum;
	}
	
	//đảo các chữ số của n
	public static int s2(int n) {
		int rev = 0;
		while(n > 0) {
			int digit = n % 10;
			rev = rev * 10 + digit;
			n /= 10;
		}
		return rev;
	}
	
	//số nguyên tố
	public static boolean s3(int n) {
		if(n < 2) return false;
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n % i == 0) return false;
		}
		return true;
	}
	
	/*
	 * GCD and LCM Đề bài
	 * Cho hai số nguyên dương a và b.
	 * Hãy tìm:
	 * GCD(a, b) — ước chung lớn nhất LCM(a, b) — bội chung nhỏ nhất
	 */
	
	
}



