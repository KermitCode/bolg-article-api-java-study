package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;

public class Help {

	//判断传入的参数是否是数字
	public static boolean isNumeric(String str) {
		
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        
        if( !isNum.matches() ){
            return false;
        }
        return true;
        
	}
	
	//封装响应一个json错误
	public static String jsonError(String msg, int code) {
		
		//空数据体
        Map<String, Object> data = new HashMap<>();
        
        //响应体封装
        Map<String, Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("code", code);
        map.put("result", data);

        //返回json数据
        String res = JSON.toJSONString(map);
        return res;
	}
	
	//封装响应一个正常返回json
	public static String jsonOK(Map<String, Object> element) {
		
		//数据体封装
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        data.add(element);

        //响应体封装
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "ok");
        map.put("code", 0);
        map.put("result", data);
        map.put("info", Project.info);

        //返回json数据
        String res = JSON.toJSONString(map);
        return res;
	}
	
	
}
