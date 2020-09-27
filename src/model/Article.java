package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Article extends Base {
	
	private int pageSize = 10;

	//提取分页数据
	public ArrayList<HashMap<String,String>> getArticles(int cid, int page){

		int start = ( page - 1 ) * this.pageSize;
		
		String extsql = "";
		if( cid != 0) {
			extsql = " where ar_cid = "+ cid;
		}
		
		String sql = "SELECT id,ar_cid,ar_title,ar_tags,ar_time,ar_text,ar_view,ar_comments FROM ke_article "
					+ extsql +" order by id desc limit " + start + "," + this.pageSize;
		
		ArrayList<HashMap<String,String>> data = super.query(sql);

		return data;
	}
	
	//提取文章详情
	public HashMap<String, String> getDetail(int id){

		String sql = "SELECT id,ar_cid,ar_title,ar_tags,ar_time,ar_text,ar_view,ar_comments FROM ke_article where id =" + id;
		
		HashMap<String,String> data = super.queryOne(sql);
		
		return data;
	}
	
	
}
