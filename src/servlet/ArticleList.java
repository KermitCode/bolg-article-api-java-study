package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import core.Help;
import core.Project;
import model.Article;

/**
 * Servlet implementation class ArticleList
 */
@WebServlet("/ArticleList")
public class ArticleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Project.init(request, response);

		int page = 1;
		if(request.getParameter("page") != null ){
			if(Help.isNumeric(request.getParameter("page"))) {
				if( ! "".equals(request.getParameter("page"))) {
					page = Integer.valueOf(request.getParameter("page"));
				}
			}
		}
		
		int cid = 0;
		if(request.getParameter("cid") != null ){
			if(Help.isNumeric(request.getParameter("cid"))) {
				cid = Integer.valueOf(request.getParameter("cid"));
			}
		}
		
		
		Article ArticleModel = new Article();
		ArrayList<HashMap<String,String>> articles = ArticleModel.getArticles(cid, page);
		
		/*
		 * for (int i = 0; i < articles.size(); i++) { HashMap<String,String> map =
		 * articles.get(i);
		 * 
		 * for (String j : map.keySet()) { System.out.println("key: " + i + " value: " +
		 * map.get(j)); }
		 * 
		 * System.out.println(); }
		 */
		
		//数据体封装
        Map<String, Object> data = new HashMap<>();
        data.put("articles", articles);
        
        String res = Help.jsonOK(data);
		response.getWriter().write(res);
		response.getWriter().flush();
		response.getWriter().close();
        
        /*响应体封装
        Map<String, Object> map = new HashMap<>();
        map.put("msg","success");
        map.put("code", "0");
        map.put("result", data);

        PrintWriter out = response.getWriter();
        String res = JSON.toJSONString(map);
        out.write(res);
        out.flush();
        out.close();*/

		//response.getWriter().append(json);
        
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
