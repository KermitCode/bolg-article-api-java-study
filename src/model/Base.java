package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import conf.Config;

public class Base {

	static Connection conn = null;
	static Statement stmt = null;
	
	//共用数据库连接获取
	public Base() {

		//第一次初始化数据库连接
		if(conn == null) {
			
			try{
		        // 注册 JDBC 驱动
		        Class.forName(Config.Database.JDBC_DRIVER);
		    
		        // 打开链接
		        conn = DriverManager.getConnection(Config.Database.MYSQL_DSN, 
		        								   Config.Database.USER, 
		        								   Config.Database.PASS);
		    
		        // 执行查询
		        stmt = conn.createStatement();
		        
		    }catch(Exception e){
		        // 处理 Class.forName 错误
		        e.printStackTrace();
		    }
		}
	    
	}
	
	public ArrayList<HashMap<String,String>> query(String sql) {

		try{
			//提取结果集
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			
			//将结果集转化为hashmap
			ArrayList<HashMap<String,String>> arr = new ArrayList<HashMap<String,String>>();
		    while (rs.next()) {
		    	HashMap<String, String> map = new HashMap<String, String>();
			    for (int i = 0; i < count; i++) {
			    	String columnName = rsm.getColumnName( (i + 1));
			        map.put(columnName, rs.getString(columnName));
			    }
			    arr.add(map);
		    }
		    
		    rs.close();
			return arr;

		}catch(SQLException se){
	        // 处理 JDBC 错误
	        se.printStackTrace();
	    }
	    
		ArrayList<HashMap<String,String>> arr = new ArrayList<HashMap<String,String>>();
		return arr;

	}
	
	public HashMap<String,String> queryOne(String sql) {

		try{
			//提取结果集
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			
			//将结果集转化为hashmap
			HashMap<String,String> hash = new HashMap<String,String>();
		    while (rs.next()) {
			    for (int i = 0; i < count; i++) {
			    	String columnName = rsm.getColumnName( (i + 1));
			    	hash.put(columnName, rs.getString(columnName));
			    }
			    break;
		    }
		    
		    rs.close();
			return hash;

		}catch(SQLException se){
	        // 处理 JDBC 错误
	        se.printStackTrace();
	    }
	    
		HashMap<String,String> hash = new HashMap<String,String>();
		return hash;

	}
	
	
	//关闭数据库连接
	public void close() {
		
        try{
            if(stmt!=null) stmt.close();
        }catch(SQLException se2){
        	
        }
        
        try{
            if(conn!=null) conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        
	}
    
    
    
    
}
