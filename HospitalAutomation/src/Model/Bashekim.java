package Model;


import java.sql.*;
import java.util.ArrayList;



public class Bashekim extends User{
	Connection c=conn.connDB();
	Statement st=null;
    ResultSet rs=null;
    PreparedStatement pSt;
  

	public Bashekim() {}
	
	public Bashekim(int id, String name, String tc_no, String password, String type) {
		super(id, name, tc_no, password, type);
	
	}
	
	public ArrayList<User> getDoctorList() {
		
		ArrayList<User> list=new ArrayList<>();
	    User obj;
		try {
			st=c.createStatement();
			rs=st.executeQuery("SELECT * FROM user WHERE type='doktor'");
			while(rs.next()) {
				obj=new User(rs.getInt("id"),rs.getString("name"),rs.getString("tc_no"),rs.getString("password"),rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	  return list;
	}
	
	public boolean addDoctor(String name,String tc_no,String password) {

		boolean add=false;
		String query="INSERT INTO user"+"(name,tc_no,password,type)"+"VALUES (?,?,?,?)";
		try {
			pSt=c.prepareStatement(query);
			pSt.setString(1,name);
			pSt.setString(2,tc_no);
			pSt.setString(3,password);
			pSt.setString(4,"doktor");
			pSt.executeUpdate();
			add=true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(add)
		return true;
		else 
	    return false;
		
	}
	
	public boolean delDoctor(int id) {

		boolean del=false;
		String query="DELETE FROM user WHERE id=? ";
		try {
			pSt=c.prepareStatement(query);
	        pSt.setInt(1, id);
	        pSt.executeUpdate();
			del=true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(del)
		return true;
		else 
	    return false;
		
	}
	public boolean updateDoctor(int id, String name, String tc_no, String password) {

		boolean upd=false;
		String query="UPDATE user SET name=?, tc_no=?, password=? WHERE id=? ";
		try {
			pSt=c.prepareStatement(query);
	        pSt.setString(1, name);
	        pSt.setString(2, tc_no);
	        pSt.setString(3, password);
	        pSt.setInt(4, id);
	        pSt.executeUpdate();
			upd=true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(upd)
		return true;
		else 
	    return false;
		
	}
	
	
	
	
	


}