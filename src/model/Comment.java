package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Comment extends Base {

	private static int pageSize = 10;
	
	//提取分页数据
	public ArrayList<HashMap<String,String>> getComments(int page){

		int start = ( page - 1 ) * this.pageSize;

		String sql = "SELECT com_arid,com_text,com_time FROM ke_comment order by id desc limit " + start + "," + this.pageSize;
		System.out.println(sql);
		ArrayList<HashMap<String, String>> data = super.query(sql);

		return data;
	}
}
