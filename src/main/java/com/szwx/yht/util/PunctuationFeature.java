package com.szwx.yht.util;


import java.util.HashSet;

/**
 * 
 * @author zhangyj
 * @date Mar 20, 2012
 * return the punctuation feature of a char
 */
public class PunctuationFeature {
	
	private static HashSet<Character> punctuation = new HashSet<Character>();
	private static final String englishPunc = ".,;:'\"[]{}|\\-!<>?";
	private static final String chinesePunc = "。，；’［］《》？”：｛｝|、（）！";
	
	private PunctuationFeature(){
		
	}
	static {
		//English punctuation
		for(char c:englishPunc.toCharArray()){
			punctuation.add(c);
		}
		//Chinese punctuation
		for(char c:chinesePunc.toCharArray()){
			punctuation.add(c);
		}
	}
	
	public static boolean isPunctuation(Character symbol){
		if (punctuation.contains(symbol)) {
			return true;
		} else
			return false;
	}
	
	public static String getFeature(Character ch){
		if(isPunctuation(ch)){
			return "T";
		}
		else return "F";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(PunctuationFeature.getFeature('?'));
     }

}
