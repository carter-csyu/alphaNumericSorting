package com.wmp.assignment.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class AlphaNumericString {
	public static final String ALPHA_ORDER = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
	public static final String NUMBER_ORDER = "0123456789";
	public enum Type { ALPHA, NUMBER };
	
	public AlphaNumericString() {}
	
	private HashMap<Type, String> filter(String str) {
		HashMap<Type, String> result = new HashMap<Type, String>();
		StringBuffer alphaSb = new StringBuffer();
		StringBuffer numberSb = new StringBuffer();
		
		char[] charArr = str.toCharArray();
		for (char c : charArr) {
			int t = (int) c;
			
			if (t >= 48 && t <= 57) {
				numberSb.append(c);
			} else if ((t >= 65 && t <= 90) || (t >= 97 && t <= 122)) {
				alphaSb.append(c);
			}
		}
		
		result.put(Type.ALPHA, alphaSb.toString());
		result.put(Type.NUMBER, numberSb.toString());
		
		return result;
	}
	
	private String sort(String str, Type type) {
		Character[] charArr = new Character[str.length()];
		StringBuffer sb = new StringBuffer();
		
		final String orderType = type == Type.ALPHA ? ALPHA_ORDER : NUMBER_ORDER;
		
		int len = str.length();
		
		for (int i = 0; i < len; i ++) {
			charArr[i] = str.charAt(i);
		}
		
		Arrays.sort(charArr, new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				return orderType.indexOf(o1) - orderType.indexOf(o2);	
			}
		});
		
		for (Character c : charArr) {
			sb.append(c);
		}
		
		return sb.toString();
	}
	
	public String sort(String str) {
		HashMap<Type, String> filtered = filter(str);
		
		String alphaStr = filtered.get(Type.ALPHA);
		String numberStr = filtered.get(Type.NUMBER);
		
		String sortedAlpha = sort(alphaStr, Type.ALPHA);
		String sortedNumber = sort(numberStr, Type.NUMBER);
		
		StringBuffer sb = new StringBuffer();
		
		int alphaLen = alphaStr.length();
		int numberLen = numberStr.length();
		int min = Math.min(alphaLen, numberLen);
		
		if (alphaLen == 0) {
			sb = new StringBuffer(sortedNumber);
		} else if (numberLen == 0) {
			sb = new StringBuffer(sortedAlpha);
		} else {
			for (int i = 0 ; i < min; i++) {
				sb.append(sortedAlpha.charAt(i));
				sb.append(sortedNumber.charAt(i));
			}
			
			if (alphaLen > numberLen) {
				sb.append(sortedAlpha.substring(min, alphaLen));
			} else if (alphaLen < numberLen){
				sb.append(sortedNumber.substring(min, numberLen));
			}
		}
		
		return sb.toString();
	}
}