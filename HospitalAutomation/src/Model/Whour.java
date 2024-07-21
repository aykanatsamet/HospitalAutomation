package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Whour {

	private int doctor_id,id;
	private String doctor_name,wDate,status;
	DBConnection conn=new DBConnection();
	Connection c=conn.connDB();
	Statement st=null;
    ResultSet rs=null;
    PreparedStatement pSt;
	public Whour(int doctor_id, int id, String doctor_name, String wDate, String status) {
		this.doctor_id = doctor_id;
		this.id = id;
		this.doctor_name = doctor_name;
		this.wDate = wDate;
		this.status = status;
	}
	
	public Whour() {
		
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getwDate() {
		return wDate;
	}
	public void setwDate(String wDate) {
		this.wDate = wDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
	
	public ArrayList<Whour> getWhourList(int doctor_id) {
		
		ArrayList<Whour> list=new ArrayList<>();
	    Whour obj;
		try {
			st=c.createStatement();
			rs=st.executeQuery("SELECT * FROM whour WHERE status='a' AND doctor_id="+doctor_id);
			while(rs.next()) {
				obj=new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_id(rs.getInt("doctor_id"));
				obj.setDoctor_name(rs.getString("doctor_name"));
				obj.setStatus(rs.getString("status"));
				obj.setwDate(rs.getString("w_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	  return list;
	}
    
	
}
