package conf;

public interface Config {

	
	/**
     * 	数据库配置
     */
    public static class Database {
    	
    	//数据库配置
    	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    	public static final String MYSQL_DSN = "jdbc:mysql://127.0.0.1:3306/you_database";
    	
    	//数据库用户名与密码
    	public static final String USER = "test";
    	public static final String PASS = "123456";
        
    }
    
    public static class Site {
    	public static final String SITE_NAME ="04007.cn Api接口文档";
    	public static final String SITE_AUTHOR ="kermit";
    }
	
    
}
