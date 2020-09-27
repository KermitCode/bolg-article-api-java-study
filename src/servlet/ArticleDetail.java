package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.Help;
import core.Project;
import model.Article;

/**
 * Servlet implementation class ArticleDetail
 */
@WebServlet("/ArticleDetail")
public class ArticleDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Project.init(request, response);
		
		int id = 0;
		if(request.getParameter("id") != null && ! "".equals(request.getParameter("id"))) {
			if(Help.isNumeric(request.getParameter("id"))) {
				id = Integer.valueOf(request.getParameter("id"));
			}
		}
		
		if(id == 0 ) {
			String res = Help.jsonError("无效参数id", 0);
			response.getWriter().write(res);
			response.getWriter().flush();
			response.getWriter().close();
		}
		
		Article ArticleModel = new Article();
		HashMap<String, String> article = ArticleModel.getDetail(id);
		HashMap<String, Object> resp = new HashMap<String, Object>();
		resp.put("article", article);
		
		String res = Help.jsonOK(resp);
		response.getWriter().write(res);
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
