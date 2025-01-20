package Utils;

public class NumberUtil {
	public static boolean isNumber(String value) {
	    try {
	      Long number =Long.parseLong(value);
	      System.out.println("là kiểu nguyên");
	      
	    } catch (NumberFormatException e) { // Không in stack trace ở đây
	        System.out.println("khồng là kiểu nguyen");
	        return false;
		}
	    return true;
	}
}
