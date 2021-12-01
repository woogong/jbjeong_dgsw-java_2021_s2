package kr.hs.dgsw.javaClass.server.chatting.server;

import java.util.HashMap;

public class ClientData {

	private HashMap<String, Object> map = new HashMap<String, Object>();
	
	public Object getData(String key) {
		return map.get(key);
	}
	
	public void setData(String key, Object value) {
		map.put(key, value);
	}
}
