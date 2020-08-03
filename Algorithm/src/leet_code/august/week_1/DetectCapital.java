package leet_code.august.week_1;

public class DetectCapital {
	public static void main(String[] args) {
		DetectCapital dc = new DetectCapital();
		System.out.println(dc.detectCapitalUse("Flag"));
	}
	
	public boolean detectCapitalUse(String word) {
		
		if (isAllUpperCase(word) || isAllLowerCase(word) || isCapitalOnlyFirstChar(word)) {
			return true;
		}
		
		return false;
		
	}
	public boolean isCapitalOnlyFirstChar (String word) {
		char[] sWord = word.toCharArray();
		int i;
		
		if (!isUpperCase(sWord[0])) {
			return false;
		}
		
		if (!isAllLowerCase(word.substring(1))) {
			return false;
		}
		
		return true;
	}
	
	public boolean isAllUpperCase(String word) {
		char[] sWord = word.toCharArray();
		int i;
		for (i=0; i < sWord.length; i++) {
			if (!isUpperCase(sWord[i])) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isAllLowerCase(String word) {
		char[] sWord = word.toCharArray();
		int i;
		for (i=0; i < sWord.length; i++) {
			if (!isLowerCase(sWord[i])) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isUpperCase(char c) {
		int code = (int) c;
		if (code > 64 && code < 91) {
			return true;
		}
		return false;
	}
	
	public boolean isLowerCase(char c) {
		int code = (int) c;
		if (code > 96 && code < 123) {
			return true;
		}
		return false;
	}
}
