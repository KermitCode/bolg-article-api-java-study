package core;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Project  {
	
	public static HashMap<String, HashMap<String, String>> info = new HashMap<String, HashMap<String, String>>();

	public static void init(HttpServletRequest request, HttpServletResponse response) {
		
		//基础设置
		response.setContentType("text/html;charset=UTF-8");
		
		//基础请求信息
		initReqestInfo(request);
		
		//站点信息
		initSiteConfig();
		
		//初始化默认参数，避免业务中取数据麻烦
		//initParams(request);
	}

	//基础请求信息
	public static void initReqestInfo(HttpServletRequest request) {
		HashMap<String, String> RequInfo = new HashMap<String, String>();
		RequInfo.put("requ_method", request.getMethod());
		RequInfo.put("requ_contentType", request.getContentType());
		RequInfo.put("requ_uri", request.getRequestURI());
		RequInfo.put("requ_query", request.getQueryString());
		RequInfo.put("requ_ip", request.getRemoteAddr());
		RequInfo.put("requ_encoding", request.getCharacterEncoding());
		RequInfo.put("requ_proto", request.getProtocol());
		RequInfo.put("requ_length", (String) Integer.toString(request.getContentLength()));
		RequInfo.put("requ_port", String.valueOf(request.getServerPort()));
		
		info.put("request_info", RequInfo);
	}
	
	//初始化一些基础配置
	public static void initSiteConfig() {
		
		HashMap<String, String> Sites = new HashMap<String, String>();
		
		Sites.put("pro_name", "04007Api项目");
		Sites.put("pro_author", "04007.cn");
		info.put("site_info", Sites);
	}
	
	//初始化请求参数
	public static void initParams(HttpServletRequest request) {
		
		Enumeration<String> params = request.getParameterNames();
		System.out.println(params);
		
	}


}
