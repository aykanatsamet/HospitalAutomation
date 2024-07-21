package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doctor extends User {

	Connection c = conn.connDB();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pSt;

	public Doctor() {
		super();
	}

	public Doctor(int id, String name, String tc_no, String password, String type) {
		super(id, name, tc_no, password, type);
	}

	public boolean addWhour(int doctor_id, String doctor_name, String wDate) {

		int key = 0;
		int count = 0;

		String query = "INSERT INTO whour" + "(doctor_id,doctor_name,w_date) VALUES" + "(?,?,?)";

		try {
			st = c.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM whour WHERE status='a' AND doctor_id=" + doctor_id + " AND w_date='" + wDate + "'");

			while (rs.next()) {
				count++;
				break;
			}
			if (count == 0) {
				pSt = c.prepareStatement(query);
				pSt.setInt(1, doctor_id);
				pSt.setString(2, doctor_name);
				pSt.setString(3, wDate);
				pSt.executeUpdate();
			}

			key = 1;

		} catch (Exception e) {
			e.printStackTrace();

		}

		if (key == 1)
			return true;
		else
			return false;

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
	
	public boolean delWhour(int id) {

		boolean del=false;
		String query="DELETE FROM whour WHERE id=? ";
		
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
	
	

}
