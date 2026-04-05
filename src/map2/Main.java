package map2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * phải hiểu: 
	 * 2 biến l, r di chuyển độc lập 
	 * khi nào tăng l 
	 * khi nào tăng r
	 */
	
	/*
	 * 4 dạng bắt buộc: 
	 * 1. Opposite direction: l = 0, r = n - 1 
	 * 2. Same direction: l = 0, r = 0 → r chạy trước -> chính là tiền thân sliding window 
	 * 3. Remove duplicate (sorted array) -> cực kỳ quan trọng 
	 * 4. Merge 2 array
	 */
	
	/*
	 * Đề: Cho mảng đã sort tăng dần, tìm xem có tồn tại 2 số có tổng = target không.
	 * YÊU CẦU: Không dùng HashMap 
	 * 			Dùng 2 pointer 
	 * 			Giải thích từng bước
	 */
	public static boolean s1(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		
		while(l < r) {
			int sum = nums[l] + nums[r];
			if(sum == target) return true;
			else if(sum < target) l++;
			else r--;
		}
		
		return false;
	}
	
	/*
	 * Cho mảng đã sort tăng dần 
	 * 👉 tìm tất cả cặp (i, j) sao cho: nums[i] + nums[j] == target
	 */
	public static List<int[]> s2(int[] nums, int target){
		List<int[]> result = new ArrayList<>();
		
		int l = 0, r = nums.length;
		
		while(l < r) {
			int sum = nums[l] + nums[r];
			if(sum == target) {
				result.add(new int[] {nums[l], nums[r]});
				l++;
				r--;
			}else if(sum < target) l++;
			else r--;
		}
		
		return result;
	}
	
	/*
	 * BÀI TOÁN:
	 * Cho một mảng số nguyên đã được sắp xếp tăng dần.
		👉 Yêu cầu:		
		Xóa các phần tử trùng nhau ngay trong mảng (in-place)
		Sau khi xử lý, các phần tử đầu mảng phải là duy nhất (không trùng)
		Trả về số lượng phần tử duy nhất
	 *  
	 *  [1,1,2,2,3,3,4]	 * 
	 * 👉 biến thành: [1,2,3,4, ...]	 * 
	 * 👉 chỉ quan tâm 4 phần tử đầu tiên	 * 
	 * 🧠 Ý TƯỞNG DUY NHẤT 
	 * 		i: giữ vị trí kết quả 
	 * 		j: chạy để tìm số mới
	 * nếu nums[j] != nums[i] → tìm thấy số mới → i++ → nums[i] = nums[j]
	 */
	
//	dạng: remove duplicate (mảng đã sort)
	public static int s3(int[] nums) {
	    int i = 0;

	    for (int j = 1; j < nums.length; j++) {
	        if (nums[j] != nums[i]) {
	            i++;
	            nums[i] = nums[j];
	        }
	    }

	    return i + 1;
	}
	
	/*
	 * Cho mảng số nguyên → tìm subarray dài nhất có tổng ≤ k
	 */
	
	public static int s4(int[] nums, int k) {
		int l = 0, max = 0, sum = 0;
		
		for(int r = 0; r < nums.length; r++) {
			sum += nums[r];
			while(sum > k) {
				sum -= nums[l];
				l++;
			}
			max = Math.max(max, r - l + 1);
		}
		
		return max;
	}
	
//	Chuỗi ký tự → dài nhất có ≤ k ký tự khác nhau
	public static int s5(String s, int k) {
		Map<Character, Integer> map = new HashMap<>();
		int l= 0, max = 0;
		
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
	
//	Longest Repeating Character Replacement
	
	/*
	 * Đề bài (nói lại cho dễ hiểu)	 * 
	 * Cho chuỗi ký tự viết hoa: s = "AABABBA" k = 1	 * 
	 * 👉 Được phép đổi tối đa k ký tự thành ký tự bất kỳ.	 * 
	 * 👉 Hỏi: độ dài substring dài nhất mà tất cả ký tự giống nhau
	 */
	
	/*
	 * Mục tiêu: 
	 * ✔ hiểu bài hard của sliding window 
	 * ✔ hiểu frequency array 
	 * ✔ hiểu vì sao dùng while 
	 * ✔ hiểu bản chất “giữ majority”
	 */
	public static int s6(String s, int k) {
		int[] count = new int[26];
		int l = 0, maxFreq = 0, maxLen = 0;
		
		for(int r = 0; r < s.length(); r++) {
			char c = s.charAt(r);
			count[c = 'A']++;
			maxFreq = Math.max(maxFreq, count[c - 'A']);
			while((r - l + 1) - maxFreq > k) {
				count[s.charAt(l) - 'A']--;
				l++;
			}
			maxLen = Math.max(maxLen, r - l + 1);
		}
		
		return maxLen;
	}
	
	/*
	 * Longest Substring Without Repeating Characters
	 * (đoạn con dài nhất KHÔNG có ký tự bị lặp)
	 * → cùng pattern 
	 * → nhưng condition là:
	 * không được trùng ký tự
	 */
	public static int s7(String s) {
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
	

}
