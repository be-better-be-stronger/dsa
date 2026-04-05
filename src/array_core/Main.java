package array_core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		int i = maxSubArrayLen(new int[] {1, 3, 1, 1, 1, 2, 1}, 6);
		System.out.println(i);
	}
	
	/*
	 * Tìm số nhỏ nhất trong mảng 
	 * Đề: int[] nums = {3, 7, 2, 9, 5};	 * 
	 * Output: 2
	 */
	public static int findMin(int[] nums) {
		int min = nums[0];
		for(int i = 1; i < nums.length; i++) {
			min = Math.min(i, min);
		}
		return min;
	}
	
	/*
	 * TÍNH TỔNG MẢNG 
	 * Đề: int[] nums = {3, 7, 2, 9, 5};	 * 
	 * Output: 26
	 */
	public static int sum(int[] nums) {
		int sum = 0;
		for(int i : nums) {
			sum += i;
		}
		return sum;
	}
	
	/*
	 * ĐẾM SỐ CHẴN 
	 * Đề: int[] nums = {3, 7, 2, 9, 4, 6};
	 * Output: 3 (2, 4, 6)
	 */
	public static int sumEvens(int[] nums) {
		int count = 0, i = 0;
		
		while(i < nums.length) {
			if(nums[i] % 2 == 0) count++;
			i++;
		}
		
		return count;
	}
	
	/*
	 * ĐẾM TẦN SUẤT (QUAN TRỌNG) 
	 * Đề: int[] nums = {1, 2, 2, 3, 1, 2};	 * 
	 * Output:	 * 
	 * 1 → 2 lần 
	 * 2 → 3 lần 
	 * 3 → 1 lần
	 */
	public static Map<Integer, Integer> frequency(int[] nums){
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}
		
		return map;
	}
	
	/*
	 * TWO SUM (CỰC KỲ QUAN TRỌNG) 
	 * Đề: int[] nums = {2, 7, 11, 15}; int target = 9;
	 * Output: [0, 1] (vì 2 + 7 = 9)
	 */
	public static int[] _2sum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int i = 0; i < nums.length; i++) {
			int complete = target - nums[i];
			if(map.containsKey(complete)) 
				return new int[] {map.get(complete), i};
			map.put(nums[i], i);
		}
		
		return new int[] {-1, -1};
	}
	
	/*
	 * CONTAINS DUPLICATE 
	 * Đề: nums = [1,2,3,1]
	 * Output: true (vì có số 1 lặp lại)	 * 
	 * nums = [1,2,3,4] * 
	 * 👉 Output: false
	 */
	public static boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<>();
		
		for(int i : nums) {
			if(!set.add(i)) return true;
		}
		
		return false;
	}
	
	/*
	 * LONGEST SUBSTRING WITHOUT REPEATING 
	 * Đề: Cho 1 string: "abcabcbb"
	 * Output: 3 (vì "abc" là chuỗi không trùng dài nhất)
	 * Ví dụ: "bbbbb" → 1 "pwwkew" → 3 ("wke")
	 */
	
	/*
	 * GỢI Ý (RẤT QUAN TRỌNG)	 * 
	 * Dùng: HashSet 2 pointer (left, right)
	 * Khi bị trùng: → co lại từ bên trái
	 */
	/* 
	 * Tìm substring dài nhất không có ký tự lặp	 * 
	 * "abcabcbb" → 3 ("abc")
	 */
	public static int lengthOfLongestSubstring(String s) {
		Set<Character> set = new HashSet<>();
		int l = 0, maxLen = 0;
		
		for(int r = 0; r < s.length(); r++) {
			char c = s.charAt(r);
			while(set.contains(c)) {
				set.remove(s.charAt(l));
				l++;
			}
			set.add(c);
			maxLen = Math.max(maxLen, r - l + 1);
		}
		
		return maxLen;
	}
	
	
/*	pattern: Sliding window
	đề: Tìm độ dài dãy con liên tiếp nhỏ nhất có tổng ≥ target
	nums = [2,3,1,2,4,3], target = 7
	kết quả = 2 ([4,3])*/
	public static int minSubArrayLen(int[] nums, int target) {
		int l = 0, sum = 0, minLen = Integer.MAX_VALUE;
		
		for(int r = 0; r < nums.length; r++) {
			sum += nums[r];
			while(sum >= target) {
				minLen = Math.min(minLen, r - l + 1);
				sum -= nums[l];
				l++;
			}
		}
		
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}
	
	/*
	 * BÀI 2: Tìm độ dài dãy con dài nhất có tổng ≤ k
	 * Ví dụ:
	 * nums = [1,2,1,0,1,1,0] k = 4
	 */
	private static int maxSubArrayLen(int[] nums, int target) {
		int l = 0, sum = 0, maxLen = 0;
		
		for(int r = 0; r < nums.length; r++) {
			sum += nums[r];
			while(sum > target) {				
				sum -= nums[l];
				l++;
			}
			maxLen = Math.max(maxLen, r - l + 1);
		}
		return maxLen;
	}
	
	
//	Tìm độ dài lớn nhất của đoạn có tổng ≤ target
	public static int solution(int[] nums, int target) {
		int l = 0, sum = 0, maxLen = 0;
		for(int r = 0; r < nums.length; r++) {
			sum += nums[r];
			
			while(sum > target) {
				sum -= nums[l];
				l++;
			}
			
			maxLen = Math.max(maxLen, r - l + 1);
		}
		return maxLen;
	}
	
	
	/*
	 * Sliding Window + Duplicate (Set / HashMap) 
	 * TEMPLATE CHUẨN (PHẢI THUỘC)
	 */
	
	/*
	 * Khi nào dùng Sliding Window?	 * 
	 * Nhận diện bằng keyword:	 * 
	 * “subarray / substring” 
	 * “liên tiếp” 
	 * “dài nhất / ngắn nhất” 
	 * “không trùng / unique” 
	 * “tổng ≤ / ≥ / = target”	 * 
	 * 👉 Nếu thấy mấy dấu hiệu này → 90% là Sliding Window
	 */
	
	/*
	 * CASE 1: LONGEST SUBSTRING KHÔNG TRÙNG 
	 * 👉 Bài kinh điển	  
	 * ❓ Đề: Cho string → tìm độ dài substring dài nhất không có ký tự trùng
	 */
	
	/*
	 * Ý tưởng: Dùng HashSet. Nếu gặp duplicate → co window
	 */
	
	public static int longestSubstring(String s) {
		Set<Character> set = new HashSet<>();
		int l = 0, max = 0;
		
		for(int r = 0; r < s.length(); r++) {
			char c = s.charAt(r);
			while(set.contains(c)) {
				set.remove(s.charAt(l));
				l++;
			}
			set.add(c);
			max = Math.max(max, r - l + 1);
		}
		
		return 0;
	}
	
	/*
	 * CASE 2: SUBARRAY SUM ≤ TARGET 
	 * Đề: Tìm độ dài subarray dài nhất sao cho tổng ≤ target
	 */
	public static int maxLen(int[] nums, int target) {
		int l = 0, sum = 0, max = 0;
		
		for(int r = 0; r < nums.length; r++) {
			sum += nums[r];
			while(sum > target) {
				sum -= nums[l];
				l++;
			}
			max = Math.max(max, r - l + 1);
		}
		
		return max;
	}
	
//	Đề: Tìm độ dài subarray ngắn nhất sao cho tổng > target
	public static int minLen(int[] nums, int target) {
		int l = 0, sum = 0, min = Integer.MAX_VALUE;
		for(int r = 0; r < nums.length; r++) {
			sum += nums[r];
			while(sum > target) {
				min = Math.min(target, r - l + 1);
				sum -= nums[l];
				l++;
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}
	
	/*
	 * CASE 3: CHECK DUPLICATE TRONG WINDOW K 
	 * ❓ Đề: Có tồn tại 2 phần tử trùng nhau mà khoảng cách ≤ k không?
	 */
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < nums.length; i++) {
			if(set.contains(nums[i])) return true;
			
			set.add(nums[i]);
			
			if(set.size() > k) 
				set.remove(nums[i - k]);			
		}
		
		return false;
	}
	
//	BÀI TẬP
	
//	Bài 1: Đếm số lượng số lẻ trong mảng
	public static int countOdd(int[] nums) {		
		if (nums == null || nums.length == 0) return 0;
		int count = 0;		
		for(int i : nums) {
			if(i % 2 != 0) count++;
		}		
		return count;
	}
	
//	Bài 2: Tìm subarray dài nhất toàn số dương
	public static int longestPositiveSubArray(int[] nums) {
		int count = 0, max = 0;
		
		for(int num : nums) {
			if(num > 0) {
				count++;
				max = Math.max(max, count);
			}else count = 0;
		}
		
		return max;
	}
	
	/*
	 * bài 3: 
	 * 👉 Tìm độ dài substring dài nhất KHÔNG có ký tự trùng 
	 * 👉 Nhưng dùng HashMap (lưu index)
	 */
	
	/*
	 * Cách cũ (Set) gặp trùng → while → xóa từ từ ❌ (chậm) 
	 * Cách mới (HashMap) biết luôn vị trí ký tự trùng → nhảy l 1 phát 🚀
	 */
	
	public static int longestSubstringMap(String s) {
	    Map<Character, Integer> map = new HashMap<>();
	    
	    int l = 0, max = 0;
	    
	    for(int r = 0; r < s.length(); r++) {
	    	char c = s.charAt(r);
	    	if(map.containsKey(c))
	    		l = Math.max(l, map.get(c) + 1);
	    	map.put(c, r);
	    	max = Math.max(max, r - l + 1);
	    }
	    
	    return max;
	}
	
	/*
	 * Using an array avoids hashing overhead and provides direct index access, 
	 * so it’s faster in practice. 
	 * However, it only works when the character set is limited.
	 */
	public static int longestSubstringArray(String s) {
		int[] seen = new int[256];
		Arrays.fill(seen, -1);
		
		int l = 0, max = 0;
		
		for(int r = 0; r < s.length(); r++) {
			char c = s.charAt(r);
			
			if(seen[c] != -1)
				l = Math.max(l, seen[c] + 1);
			
			seen[c] = r;
			max = Math.max(max, r - l + 1);
		}
		
		return max;
	}
	
	/*
	 * Longest substring có tối đa K ký tự khác nhau	 * 
	 * 🧠 Ví dụ: s = "eceba", k = 2	 * 
	 * 👉 các substring hợp lệ:	 * 
	 * "ece" ✅ (2 ký tự khác nhau) 
	 * "ceba" ❌ (3 ký tự)	 * 
	 * 👉 kết quả = 3
	 */
	public static int longestK(String s, int k) {
		Map<Character, Integer> map = new HashMap<>();
		int l = 0, max = 0;
		for(int r = 0; r < s.length(); r++) {
			char c = s.charAt(r);
			map.put(c, map.getOrDefault(c, 0) + 1);
			while(map.size() > k) {
				char leftChar = s.charAt(l);
				map.put(leftChar, map.get(leftChar) - 1);
				if(map.get(leftChar) == 0)
					map.remove(leftChar);
				l++;
			}
			max = Math.max(max, r - l + 1);
		}
		return max;
	}
	
	
//	Cho mảng, tìm tổng lớn nhất của subarray có độ dài k
	public static int maxSum(int[] nums, int k) {
		int sum = 0;
		for(int i = 0; i < k; i++) {
			sum += nums[i];
		}
		int max = sum;
		
		for(int r = k; r < nums.length; r++) {
			sum += nums[r];
			sum -= nums[r - k];
			max = Math.max(max, sum);
		}
		
		return max;
	}
	
//	longest subarray sum ≤ target
	public static int maxLen1(int[] nums, int target) {
		int l = 0, sum = 0, maxLen = 0;
		
		for(int r = 0; r < nums.length; r++) {
			sum += nums[r];
			while(sum > target) {
				sum -= nums[l];
				l++;
			}
			maxLen = Math.max(maxLen, r - l + 1);
		}
		
		return maxLen;
	}
	
//	longest substring without repeating
	public static int longestUnique(String s) {
		Set<Character> set = new HashSet<>();
		int l = 0, max = 0;
		
		for(int r = 0; r < s.length(); r++) {
			char c = s.charAt(r);
			
			while(set.contains(c)) {
				set.remove(s.charAt(l));
				l++;
			}
			
			set.add(c);
			max = Math.max(max, r - l + 1);
		}
		
		return max;
	}
	
	/*
	 * Cho mảng số nguyên nums và số k	 * 
	 * 👉 Tìm độ dài lớn nhất của subarray liên tiếp sao cho:	 * 
	 * sum ≤ k
	 */
	public static int s1(int[] nums, int k) {
		if(nums == null || nums.length == 0) return 0;
		int l = 0, sum = 0, max = 0;
		
		for(int r = 0; r < nums.length; r++) {
			sum += nums[r];
			while(sum > k){
				sum -= nums[l];
				l++;
			}
			max = Math.max(max, r - l + 1);
		}
		
		return max;
	}
	
//	tìm độ dài nhỏ nhất của subarray có sum ≥ k
	public static int s2(int[] nums, int k) {
		if(nums == null ||  nums.length == 0) return 0;
		int l = 0, sum = 0, min = Integer.MAX_VALUE;
		
		for(int r = 0; r < nums.length; r++) {
			sum += nums[r];
			while(sum >= k) {
				min = Math.min(min, r - l + 1);
				sum -= nums[l];
				l++;
			}
		}
		
		return min == Integer.MAX_VALUE? 0 : min;
	}
	
	/*
	 * Cho string s, tìm độ dài substring dài nhất chứa không quá k ký tự khác nhau
	 * Ví dụ: s = "eceba", k = 2 → output = 3 ("ece")
	 */
	public static int s3(String s, int k) {
		Map<Character, Integer> map = new HashMap<>();
		int l = 0, max = 0;
		
		for(int r = 0; r < s.length(); r++) {
			char c = s.charAt(r);
			map.put(c, map.getOrDefault(c, 0) + 1);
			while(map.size() > k) {
				char leftchar = s.charAt(l);
				map.put(leftchar, map.get(leftchar) - 1);
				if(map.get(leftchar) == 0) 
					map.remove(leftchar);
				l++;
			}
			max = Math.max(max, r - l + 1);
		}
		
		return max;
	}
	
//	longest substring có đúng k ký tự khác nhau
	public static int s4(String s, int k) {
		Map<Character, Integer> map = new HashMap<>();
		int l = 0, max = 0;
		
		for(int r = 0; r < s.length(); r++) {
			char c = s.charAt(r);
			map.put(c, map.getOrDefault(c, 0) + 1);
			
			if(map.size() == k)
				max = Math.max(max, r - l + 1);
			
			while(map.size() > k) {
				char leftChar = s.charAt(l);
				map.put(leftChar, map.get(leftChar) - 1);
				if(map.get(leftChar) == 0)
					map.remove(leftChar);
				l++;
			}
		}
		
		return max;
	}
	
//	Đếm số substring có at most k ký tự khác nhau
	/*
	 * Goi y: mỗi lần window hợp lệ:
	 * số substring kết thúc tại r = r - l + 1
	 */
	
	
	/*
	 * Cho mảng chỉ gồm số 0 và 1	 * 
	 * 👉 Tìm độ dài dài nhất của subarray chứa tối đa k số 0	 * 
	 * 📌 Ví dụ: nums = [1,1,1,0,0,1,1,0] k = 2 → output = 7
	 */
	public static int s6(int[] nums, int k) {
		int l = 0, max = 0, count = 0;
		
		for(int r = 0; r < nums.length; r++) {
			if(nums[r] == 0) count++;
			while(count > k) {
				if(nums[l] == 0) count--;
				l++;
			}
			max = Math.max(max, r - l + 1);
		}
		
		return max;
	}
}
