package propositional_logic_convert;

import java.util.regex.Pattern;

public class integer_detection {
	static String str;
	integer_detection(String str){
		this.str=str;
	}
	
	public static boolean isInteger() {  
        if(str.indexOf("-") != -1) {
        	return true;
        }
		Pattern pattern = Pattern.compile("[0-9]*");  
        return pattern.matcher(str).matches();
	}
}
