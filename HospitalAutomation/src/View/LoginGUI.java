package View;

import java.awt.EventQueue;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.DBConnection;
import Helper.Helper;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

public class LoginGUI extends JFrame {


	private JPanel w_pane;
	private JTextField fld_hasta_Tc;
	private JTextField fld_Doctor_Tc;
	private JPasswordField fld_hasta_pass;
	private JPasswordField fld_Doctor_pass;
	private DBConnection conn=new DBConnection();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hospital Automation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("icon.png")));
		lbl_logo.setBounds(193, 23, 106, 69);
		
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setBounds(105, 104, 282, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("YuGothic", Font.BOLD, 13));
		w_pane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(33, 148, 418, 201);
		w_pane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Hasta Girişi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaranız:");
		lblTcNumaranz.setHorizontalAlignment(SwingConstants.CENTER);
		lblTcNumaranz.setFont(new Font("YuGothic", Font.BOLD, 13));
		lblTcNumaranz.setBounds(-42, 30, 282, 16);
		panel.add(lblTcNumaranz);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setHorizontalAlignment(SwingConstants.CENTER);
		lblifre.setFont(new Font("YuGothic", Font.BOLD, 13));
		lblifre.setBounds(-11, 58, 282, 16);
		panel.add(lblifre);
		
		fld_hasta_Tc = new JTextField();
		fld_hasta_Tc.setBounds(159, 22, 130, 26);
		panel.add(fld_hasta_Tc);
		fld_hasta_Tc.setColumns(10);
		
		JButton btn_hasta_Login = new JButton("Giriş Yap");
		btn_hasta_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hasta_Tc.getText().length()==0|| fld_hasta_pass.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					boolean key=true;
					try {
						Connection c=conn.connDB();
						Statement st=c.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM user"); 
						
					while(rs.next()) {
						if(fld_hasta_Tc.getText().equals(rs.getString("tc_no"))&&fld_hasta_pass.getText().equals(rs.getString("password"))) {
	            			
							  if(rs.getString("type").equals("hasta")) {
	    					    Hasta hasta=new Hasta();
	    						hasta.setId(rs.getInt("id"));
	    						hasta.setName(rs.getString("name"));
	    						hasta.setPassword(rs.getString("password"));
	    						hasta.setTc_no(rs.getString("tc_no"));
	    						hasta.setType(rs.getString("type"));
	    						HastaGUI hastaGUI=new HastaGUI(hasta);
	    						hastaGUI.setVisible(true);
	    						dispose();
	    						key=false;
							  }
						}
					}
					
					} catch (SQLException e1) {
		
						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("Hasta bulunamadı, kayıt olunuz");
					}
					
				}
			}
		});
		btn_hasta_Login.setBounds(185, 86, 154, 37);
		panel.add(btn_hasta_Login);
		
		fld_hasta_pass = new JPasswordField();
		fld_hasta_pass.setBounds(159, 53, 130, 26);
		panel.add(fld_hasta_pass);
		
		JButton btn_register = new JButton("Kayıt ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI registerGUI=new RegisterGUI();
				registerGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setBounds(6, 107, 130, 26);
		panel.add(btn_register);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Doktor Girişi", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaranız:");
		lblTcNumaranz_1.setBounds(-42, 30, 282, 16);
		lblTcNumaranz_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTcNumaranz_1.setFont(new Font("Dialog", Font.BOLD, 13));
		panel_1.add(lblTcNumaranz_1);
		
		fld_Doctor_Tc = new JTextField();
		fld_Doctor_Tc.setBounds(159, 22, 130, 26);
		fld_Doctor_Tc.setColumns(10);
		panel_1.add(fld_Doctor_Tc);
		
		JLabel lblifre_1 = new JLabel("Şifre:");
		lblifre_1.setBounds(-11, 58, 282, 16);
		lblifre_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblifre_1.setFont(new Font("Dialog", Font.BOLD, 13));
		panel_1.add(lblifre_1);
		
		JButton btn_doctor_Login = new JButton("Giriş Yap");
		btn_doctor_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_Doctor_Tc.getText().length()==0 || fld_Doctor_pass.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					
				 try {
					Connection c=conn.connDB();
					Statement st=c.createStatement();
					ResultSet rs=st.executeQuery("SELECT * FROM user"); 
				while(rs.next()) {
					if(fld_Doctor_Tc.getText().equals(rs.getString("tc_no"))&&fld_Doctor_pass.getText().equals(rs.getString("password"))) {
            			if(rs.getString("type").equals("başhekim")) {

    						Bashekim bashekim=new Bashekim();
    						bashekim.setId(rs.getInt("id"));
    						bashekim.setName(rs.getString("name"));
    						bashekim.setPassword(rs.getString("password"));
    						bashekim.setTc_no(rs.getString("tc_no"));
    						bashekim.setType(rs.getString("type"));
    						BashekimGUI bashekimGUI=new BashekimGUI(bashekim);
    						bashekimGUI.setVisible(true);
    						dispose();
            			}
            			
            			if(rs.getString("type").equals("doktor")) {
            				Doctor doctor =new Doctor();
            				doctor.setId(rs.getInt("id"));
    						doctor.setName(rs.getString("name"));
    						doctor.setPassword(rs.getString("password"));
    						doctor.setTc_no(rs.getString("tc_no"));
    						doctor.setType(rs.getString("type"));
    						DoctorGUI doctorGUI=new DoctorGUI(doctor);
    						doctorGUI.setVisible(true);
    						dispose();
            				
            			}
						
					}
				}
				
				} catch (SQLException e1) {
	
					e1.printStackTrace();
				}
					
				}
				 
			}
		});
		btn_doctor_Login.setBounds(110, 95, 154, 37);
		panel_1.add(btn_doctor_Login);
		
		fld_Doctor_pass = new JPasswordField();
		fld_Doctor_pass.setBounds(159, 53, 130, 26);
		panel_1.add(fld_Doctor_pass);
	}
}
