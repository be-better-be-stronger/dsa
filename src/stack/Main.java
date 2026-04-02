package stack;

import java.util.Arrays;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		int[] rs = nextGreater(new int[] {1, 0, 2, 2, 4, 3});
		System.out.println(Arrays.toString(rs));

	}
	
/*	VALID PARENTHESES (phiên bản chuẩn)
	Đề:
		Cho chuỗi: s = "()[]{}"
	Hỏi: chuỗi có hợp lệ không?*/
	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		
		for(char c : s.toCharArray()) {
			if(c == '(' || c == '{' || c == '[')
				stack.push(c);
			else {
				if(stack.isEmpty()) return false;
				
				char top = stack.pop();
				
				if((c == ')' && top != '(') ||
						(c == '}' && top != '{') ||
						(c == ']' && top != '['))
					return false;
			}
		}
		
		return stack.isEmpty();
	}
	
	/*
	 * NEXT GREATER ELEMENT 
	 * Bài: [2,1,2,4,3]	 * 
	 * 👉 Với mỗi phần tử: → tìm phần tử lớn hơn gần nhất bên phải
	 * OUTPUT: [4,2,4,-1,-1]
	 */
	public static int[] nextGreater(int[] nums){
		int n = nums.length;
		int[] result = new int[n];
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < n; i++) {
			while(!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
				int index = stack.pop();
				result[index] = nums[i];
			}
			stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			result[stack.pop()] = -1;
		}
		
		return result;
	}

}
