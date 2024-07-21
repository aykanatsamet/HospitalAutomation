package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {
DBConnection connection=new DBConnection();

private int id,doctor_id,hasta_id;
private String doctor_name,app_date,hasta_name;

Connection conn=connection.connDB();
Statement st;
ResultSet rs;
PreparedStatement preparedStatement;

public Appointment(int id, int doctor_id, int hasta_id,String hasta_name, String doctor_name, String app_date) {
	super();
	this.id = id;
	this.doctor_id = doctor_id;
	this.hasta_id = hasta_id;
	this.hasta_name=hasta_name;
	this.doctor_name = doctor_name;
	this.app_date = app_date;
}

public Appointment() {
	 
}



public String getHasta_name() {
	return hasta_name;
}

public void setHasta_name(String hasta_name) {
	this.hasta_name = hasta_name;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getDoctor_id() {
	return doctor_id;
}

public void setDoctor_id(int doctor_id) {
	this.doctor_id = doctor_id;
}

public int getHasta_id() {
	return hasta_id;
}

public void setHasta_id(int hasta_id) {
	this.hasta_id = hasta_id;
}

public String getDoctor_name() {
	return doctor_name;
}

public void setDoctor_name(String doctor_name) {
	this.doctor_name = doctor_name;
}

public String getApp_date() {
	return app_date;
}

public void setApp_date(String app_date) {
	this.app_date = app_date;
}

public ArrayList<Appointment> getHastaList(int hasta_id) {
	
	ArrayList<Appointment> list=new ArrayList<>();
    Appointment obj;
    
   try {
	st=conn.createStatement();
    rs=st.executeQuery("SELECT * FROM appointment WHERE hasta_id="+hasta_id);
    while(rs.next()) {
    	obj=new Appointment();
    	obj.setId(rs.getInt("id"));
    	obj.setDoctor_id(rs.getInt("doctor_id"));
    	obj.setDoctor_name(rs.getString("doctor_name"));
    	obj.setHasta_id(rs.getInt("hasta_id"));
    	obj.setHasta_name(rs.getString("hasta_name"));
    	obj.setApp_date(rs.getString("app_date"));
    	list.add(obj);
    }
    
} 
catch (SQLException e) {

	e.printStackTrace();
} 
	


	
  return list;
}
public ArrayList<Appointment> getDoctorList(int doctor_id) {
	
	ArrayList<Appointment> list=new ArrayList<>();
    Appointment obj;
    
   try {
	st=conn.createStatement();
    rs=st.executeQuery("SELECT * FROM appointment WHERE doctor_id="+doctor_id);
    while(rs.next()) {
    	obj=new Appointment();
    	obj.setId(rs.getInt("id"));
    	obj.setDoctor_id(rs.getInt("doctor_id"));
    	obj.setDoctor_name(rs.getString("doctor_name"));
    	obj.setHasta_id(rs.getInt("hasta_id"));
    	obj.setHasta_name(rs.getString("hasta_name"));
    	obj.setApp_date(rs.getString("app_date"));
    	list.add(obj);
    }
    
} 
catch (SQLException e) {

	e.printStackTrace();
} 
	


	
  return list;
}


}
