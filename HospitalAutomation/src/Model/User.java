package Model;

import Helper.DBConnection;

public class User {
	private int id;
	private String name,tc_no,password,type;
	DBConnection conn=new DBConnection();
	
	public User(int id, String name, String tc_no, String password, String type) {
	
		this.id = id;
		this.name = name;
		this.tc_no = tc_no;
		this.password = password;
		this.type = type;
	}
	
	public User() {}
	
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
	public String getTc_no() {
		return tc_no;
	}
	public void setTc_no(String tc_no) {
		this.tc_no = tc_no;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
