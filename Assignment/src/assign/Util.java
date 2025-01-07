package assign;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class Util {
	public static HashMap<String, String> requestStringToMap(String request) {
	    HashMap<String, String> map = new HashMap<>();
	    String[] pairs = request.split("&");
	    for (int i = 0; i < pairs.length; i++) {
	      String pair = pairs[i];

	      try {
	        String[] kv = pair.split("=");
	        String key = kv[0];
	        key = URLDecoder.decode(key, "UTF-8");

	        String value = kv[1];
	        value = URLDecoder.decode(value, "UTF-8");

	        map.put(key, value);
	      } catch (UnsupportedEncodingException e) {
	        System.err.println(e.getMessage());
	      }

	    }
	    return map;
	  }
}
