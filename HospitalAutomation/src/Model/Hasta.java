package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Helper.Helper;

public class Hasta extends User {
	
	Connection c = conn.connDB();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pSt; 

	public Hasta() {
	
	
	}

	public Hasta(int id, String name, String tc_no, String password, String type) {
		super(id, name, tc_no, password, type);
		
	}
	
	
	public boolean register(String name, String tcNo, String pass) {

		int key = 0;
		int count = 0;

		String query = "INSERT INTO user " + "(name,tc_no,password,type) VALUES" + "(?,?,?,?)";

		try {
			st = c.createStatement();
			rs = st.executeQuery(
					"SELECT * FROM user WHERE tc_no='"+tcNo+"'");
			while (rs.next()) {
				count++;
				Helper.showMsg("Bu TC numarasına ait bir kayıt bulunmaktadır.");
				break;
			}
			if (count == 0) { 
				pSt = c.prepareStatement(query);
				pSt.setString(1, name);
				pSt.setString(2, tcNo);
				pSt.setString(3, pass);
				pSt.setString(4, "hasta");
				pSt.executeUpdate();
				key = 1;
			}

			

		} catch (Exception e) {
			e.printStackTrace();

		}

		if (key == 1)
			return true;
		else
			return false;

	}
	public boolean addAppointment(int doctor_id,int hasta_id,String doctor_name,String hasta_name,String app_date) {

		int key = 0;
		

		String query = "INSERT INTO appointment" + "(doctor_id,doctor_name,hasta_id,hasta_name,app_date) VALUES" + "(?,?,?,?,?)";

		try {
			
			
				pSt = c.prepareStatement(query);
				pSt.setInt(1, doctor_id);
				pSt.setString(2, doctor_name);
				pSt.setInt(3, hasta_id);
				pSt.setString(4, hasta_name);
				pSt.setString(5, app_date);
				pSt.executeUpdate();
				key = 1;
	

		}catch (Exception e) {
			e.printStackTrace();

		}

		if (key == 1)
			return true;
		else
			return false;

	}
	public boolean updateWhourStatus(int doctor_id,String wdate) {

		int key = 0;
		

		String query = "UPDATE whour SET status= ? WHERE doctor_id= ? AND w_date= ?"; 

		try {
			
			
				pSt = c.prepareStatement(query);
				pSt.setString(1,"p");
				pSt.setInt(2, doctor_id);
				pSt.setString(3,wdate);
				pSt.executeUpdate();
				key = 1;
	
			

		}catch (Exception e) {
			e.printStackTrace();

		}

		if (key == 1)
			return true;
		else
			return false;

	}
	
	

}
