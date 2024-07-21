package Model;

import java.sql.*;
import java.util.ArrayList;
import Helper.DBConnection;


public class Clinic {
	DBConnection connection=new DBConnection();
	private int id;
	private String name;
	Connection conn=connection.connDB();
	Statement st;
    ResultSet rs;
    PreparedStatement preparedStatement;
    
	
	
	
	public Clinic() {
		
	}
	
	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
public ArrayList<Clinic> getList() {
		
		ArrayList<Clinic> list=new ArrayList<>();
	    Clinic obj;
	    
	   try {
		st=conn.createStatement();
        rs=st.executeQuery("SELECT * FROM clinic");
        while(rs.next()) {
        	obj=new Clinic();
        	obj.setId(rs.getInt("idclinic"));
        	obj.setName(rs.getString("name"));
        	list.add(obj);
        }
        
	} 
	catch (SQLException e) {
	
		e.printStackTrace();
	} 
		
	

		
	  return list;
	}



public boolean addClinic(String name) {

	boolean add=false;
	String query="INSERT INTO clinic"+"(name)"+"VALUES (?)";
	try {
		preparedStatement=conn.prepareStatement(query);
		preparedStatement.setString(1,name);
		preparedStatement.executeUpdate();
		add=true;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	if(add)
	return true;
	else 
    return false;
	
}

public boolean delClinic(int id) {

	boolean del=false;
	String query="DELETE FROM clinic WHERE idclinic=? ";
	try {
		preparedStatement=conn.prepareStatement(query);
       preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
		del=true;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	if(del)
	return true;
	else 
    return false;
	
}


public boolean updateClinic(int id,String name) {

	boolean upd=false;
	String query="UPDATE clinic SET name=? WHERE idclinic=? ";
	try {
		preparedStatement=conn.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
		upd=true;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	if(upd)
	return true;
	else 
    return false;
	
}

public Clinic getFetch(int id) {
	Clinic c=new Clinic();
	
	try {
		st=conn.createStatement();
		rs=st.executeQuery("SELECT * FROM clinic WHERE idclinic="+id);
		while(rs.next()) {
			c.setId(rs.getInt("idclinic"));
			c.setName(rs.getString("name"));
			break;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return c;
	
}

public ArrayList<User> getClinicDoctorList(int clinicID) {
	
	ArrayList<User> list=new ArrayList<>();
    User obj;
	try {
		Connection c=connection.connDB();
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
