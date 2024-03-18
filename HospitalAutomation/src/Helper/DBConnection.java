package Helper;
import java.sql.*;

public class DBConnection {
	
	
	Connection conn=null;
	private String kullaniciAdi="root";
	private String password="1234567890";
	private String url="jdbc:mysql://localhost:3306/hospital";
    private String driver="com.mysql.cj.jdbc.Driver";
	     
    
    
    		
	public DBConnection() {
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			
		System.out.println("MySQL Driver bulunamadı!"+e);
			e.printStackTrace();
		}
	}
	    
	
	
	public Connection connDB() {
		
		
	try {
		this.conn=DriverManager.getConnection(url, kullaniciAdi, password);
		return conn;
	} catch (SQLException e) {
		System.out.println("Bağlantı Sağlanamadı!!"+e);
		e.printStackTrace();
	}
	
		return conn; 
	}

}
 