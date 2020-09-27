package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.Help;
import core.Project;
import model.Comment;

/**
 * Servlet implementation class CommentList
 */
@WebServlet("/CommentList")
public class CommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentList() {
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
		
		Comment CommentModel = new Comment();
		ArrayList<HashMap<String,String>> comments = CommentModel.getComments(page);
		
//		for(int i=0; i<comments.size(); i++) {
//			System.out.println(comments.get(i));
//		}
		
		Map<String, Object> data = new HashMap<>();
        data.put("comments", comments);
        
        String res = Help.jsonOK(data);
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
