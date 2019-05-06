package com.software.JZOffer;



public class ReplaceSpace {

	public static void main(String[] args) {
		StringBuffer str = new StringBuffer("We are family");
		System.out.println(replaceSpace(str));
	}

	public static String replaceSpace(StringBuffer str) {
		return str.toString().replace(" ", "%20");

	}

}
