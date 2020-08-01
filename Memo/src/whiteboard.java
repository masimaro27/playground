import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class whiteboard {
	public static void main(String[] args) {
		regex_matches_lookingAt_test();
	}
	
	public static void regex_matches_lookingAt_test() {
	    String text    =
                "????????????????????????a";

        String patternString = "\\?+";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        System.out.println("lookingAt = " + matcher.lookingAt());
        System.out.println("matches   = " + matcher.matches());
	}
}
