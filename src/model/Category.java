package model;

import java.util.HashMap;
import java.util.List;

public class Category extends Base {

	//提取所有类目
	public List<HashMap<String, String>> getList(){

		String sql = "SELECT id,class_name,class_fname FROM ke_class order by class_sort asc";
		
		List<HashMap<String,String>> data = super.query(sql);
		
		return data;
	}
		
}
