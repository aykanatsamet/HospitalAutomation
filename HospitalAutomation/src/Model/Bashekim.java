package Model;


import java.sql.*;
import java.util.ArrayList;



public class Bashekim extends User{
	Connection c=conn.connDB();
	Statement st=null;
    ResultSet rs=null;
    PreparedStatement pSt;
  

	public Bashekim() {}
	
	
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
		String query2="DELETE FROM worker WHERE user_id=?";
		try {
			pSt=c.prepareStatement(query);
	        pSt.setInt(1, id);
	        pSt.executeUpdate();
	        
	        pSt=c.prepareStatement(query2);
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
	
	public boolean addWorker(int user_id,int clinic_id) {

		boolean add=false;
		String query="INSERT INTO worker"+"(user_id,clinic_id)"+"VALUES (?,?)";
		int count=0;
		
		try {
			st=c.createStatement();
			rs=st.executeQuery("SELECT * FROM worker WHERE clinic_id="+clinic_id+" AND user_id="+user_id);
			while(rs.next()) {
				count++;
			}
			
			if(count==0) {
				pSt=c.prepareStatement(query);
				pSt.setInt(1,user_id);
				pSt.setInt(2,clinic_id);
				pSt.executeUpdate();
			
			}
			add=true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(add)
		return true;
		else 
	    return false;
		
	}
	
	
	public ArrayList<User> getClinicDoctorList(int clinicID) {
		
		ArrayList<User> list=new ArrayList<>();
	    User obj;
		try {
			st=c.createStatement();
			rs=st.executeQuery("SELECT u.id,u.tc_no,u.type,u.name,u.password FROM worker w LEFT JOIN user u ON user_id=u.id WHERE clinic_id="+clinicID);
			while(rs.next()) {
				obj=new User(rs.getInt("u.id"),rs.getString("u.name"),rs.getString("u.tc_no"),rs.getString("u.password"),rs.getString("u.type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	  return list;
	}
	
	
	


}