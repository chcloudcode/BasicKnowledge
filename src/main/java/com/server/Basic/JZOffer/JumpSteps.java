package com.server.Basic.JZOffer;



public class JumpSteps {

	public static void main(String[] args) {
		System.out.println(JumpFloor(3));
		System.out.println(JumpFloorII(3));
	}

	public static int JumpFloor(int target) {
		if (target < 0) {
			return 0;
		} else if (target == 1) {
			return 1;
		} else if (target == 2) {
			return 2;
		} else {
			return JumpFloor(target - 1) + JumpFloor(target - 2);
		}
	}

	public static int JumpFloorII(int target) {
		if (target == 0) {
			return 0;
		} else if (target == 1) {
			return 1;
		} else {
			return 2 * JumpFloorII(target - 1);
		}
	}

}
