package Utils;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

public class MapUtil {
	public static <T> T getObject (Map<String, Object> params, String key, Class<T> tClass) {
		Object object = params.getOrDefault(key, null);
		if(object!=null) {
			if(tClass.getTypeName().equals("java.lang.Long")) {
				object = object != ""?Long.valueOf(object.toString()):null;
			}else if(tClass.getTypeName().equals("java.Lang.Integer")) {
				object = object !=""?Integer.valueOf(object.toString()):null;
			}
			else if(tClass.getTypeName().equals("java.Lang.String")) {
				object = object !=""?String.valueOf(object.toString()):null;
			}
			return tClass.cast(object);
		}
	   return null;
	}
}
