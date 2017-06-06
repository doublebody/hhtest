package com.yunjiacloud.nj.httpserver.proxy.schedule.util;

import net.sf.json.JSONObject;

public class ParseJsonStr {
	public static JSONObject createJsonObj(String jsonStr){
		return JSONObject.fromObject(jsonStr);
	}
	
	public static String Obj2Str(Object o){
		JSONObject jsonObj = JSONObject.fromObject(o);
		return jsonObj.toString();
	}
	
}
