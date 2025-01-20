package Utils;

import java.util.List;

import org.springframework.aop.AfterReturningAdvice;

public class StringUtil {
	public static boolean checkString(String data) {
		if(data!=null && !data.equals("")){
			return true;
		}
		return false;
	}
	public static boolean checkArrayString(List<String> typecode) {
		if (typecode!=null && typecode.size()!=0) {
			return true;
		}
		return false;
	}
}
