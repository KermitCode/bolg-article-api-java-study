package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import conf.Config;

public class Base {

	static Connection conn = null;
	static Statement stmt = null;
	
	//�������ݿ����ӻ�ȡ
	public Base() {

		//��һ�γ�ʼ�����ݿ�����
		if(conn == null) {
			
			try{
		        // ע�� JDBC ����
		        Class.forName(Config.Database.JDBC_DRIVER);
		    
		        // ������
		        conn = DriverManager.getConnection(Config.Database.MYSQL_DSN, 
		        								   Config.Database.USER, 
		        								   Config.Database.PASS);
		    
		        // ִ�в�ѯ
		        stmt = conn.createStatement();
		        
		    }catch(Exception e){
		        // ���� Class.forName ����
		        e.printStackTrace();
		    }
		}
	    
	}
	
	public ArrayList<HashMap<String,String>> query(String sql) {

		try{
			//��ȡ�����
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			
			//�������ת��Ϊhashmap
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
	        // ���� JDBC ����
	        se.printStackTrace();
	    }
	    
		ArrayList<HashMap<String,String>> arr = new ArrayList<HashMap<String,String>>();
		return arr;

	}
	
	public HashMap<String,String> queryOne(String sql) {

		try{
			//��ȡ�����
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			
			//�������ת��Ϊhashmap
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
	        // ���� JDBC ����
	        se.printStackTrace();
	    }
	    
		HashMap<String,String> hash = new HashMap<String,String>();
		return hash;

	}
	
	
	//�ر����ݿ�����
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
