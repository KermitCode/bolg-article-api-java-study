package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;

public class Help {

	//�жϴ���Ĳ����Ƿ�������
	public static boolean isNumeric(String str) {
		
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        
        if( !isNum.matches() ){
            return false;
        }
        return true;
        
	}
	
	//��װ��Ӧһ��json����
	public static String jsonError(String msg, int code) {
		
		//��������
        Map<String, Object> data = new HashMap<>();
        
        //��Ӧ���װ
        Map<String, Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("code", code);
        map.put("result", data);

        //����json����
        String res = JSON.toJSONString(map);
        return res;
	}
	
	//��װ��Ӧһ����������json
	public static String jsonOK(Map<String, Object> element) {
		
		//�������װ
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        data.add(element);

        //��Ӧ���װ
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "ok");
        map.put("code", 0);
        map.put("result", data);
        map.put("info", Project.info);

        //����json����
        String res = JSON.toJSONString(map);
        return res;
	}
	
	
}
