package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxCrud.ViewCheckOption;

import Helper.Helper;
import Model.Bashekim;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BashekimGUI extends JFrame {

	private JPanel w_pane;
	static Bashekim bashekim=new Bashekim();
	private JTextField fld_newDoctor_name;
	private JTextField fld_newDoctor_Tc;
	private JTextField fld_newDoctor_pass;
	private JTextField fld_deleteDoctor;
	private JTable table_Doctor;
	private DefaultTableModel doctorModel=null;
	private Object[] doctorData=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		doctorModel=new DefaultTableModel();
		Object[] colDoctorName=new Object[4];
		colDoctorName[0]="ID";
		colDoctorName[1]="AD SOYAD";
		colDoctorName[2]="TC NO";
		colDoctorName[3]="ŞİFRE";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData=new Object[4];
		
		for(int i=0;i<bashekim.getDoctorList().size();i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTc_no();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
		
		setTitle("Hospital Automation");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		w_pane.setBackground(new Color(255, 255, 255));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın "+bashekim.getName());
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setBounds(21, 18, 231, 25);
		w_pane.add(lblNewLabel);
		
		JButton doctor_logOut = new JButton("Çıkış Yap");
		doctor_logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		doctor_logOut.setBounds(365, 17, 117, 29);
		w_pane.add(doctor_logOut);
		
		JTabbedPane tabbedPane_bashekim = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_bashekim.setBounds(6, 55, 488, 311);
		w_pane.add(tabbedPane_bashekim);
		
		JPanel pnl_doktor_yönetimi = new JPanel();
		tabbedPane_bashekim.addTab("Doktor Yönetimi", null, pnl_doktor_yönetimi, null);
		pnl_doktor_yönetimi.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_1.setBounds(18, 6, 100, 16);
		pnl_doktor_yönetimi.add(lblNewLabel_1);
		
		fld_newDoctor_name = new JTextField();
		fld_newDoctor_name.setBounds(18, 23, 130, 26);
		pnl_doktor_yönetimi.add(fld_newDoctor_name);
		fld_newDoctor_name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("T.C. No");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_2.setBounds(18, 46, 100, 16);
		pnl_doktor_yönetimi.add(lblNewLabel_2);
		
		fld_newDoctor_Tc = new JTextField();
		fld_newDoctor_Tc.setBounds(18, 61, 130, 26);
		pnl_doktor_yönetimi.add(fld_newDoctor_Tc);
		fld_newDoctor_Tc.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Şifre");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_3.setBounds(18, 85, 100, 16);
		pnl_doktor_yönetimi.add(lblNewLabel_3);
		
		fld_newDoctor_pass = new JTextField();
		fld_newDoctor_pass.setBounds(18, 99, 130, 26);
		pnl_doktor_yönetimi.add(fld_newDoctor_pass);
		fld_newDoctor_pass.setColumns(10);
		
		JButton btn_addDoctor = new JButton("Ekle");
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_newDoctor_name.getText().length()==0||fld_newDoctor_pass.getText().length()==0||fld_newDoctor_Tc.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					boolean control=bashekim.addDoctor(fld_newDoctor_name.getText(),fld_newDoctor_Tc.getText(),fld_newDoctor_pass.getText());
					if(control) {
					Helper.showMsg("success");
					fld_newDoctor_name.setText(null);
					fld_newDoctor_Tc.setText(null);
					fld_newDoctor_pass.setText(null);
					UpdateDoctorModel();
					}
					else 
					Helper.showMsg("error");
					
					
				}
			}
		});
		btn_addDoctor.setBounds(18, 124, 117, 29);
		pnl_doktor_yönetimi.add(btn_addDoctor);
		
		JLabel lblNewLabel_4 = new JLabel("Kullanıcı ID");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_4.setBounds(18, 165, 100, 16);
		pnl_doktor_yönetimi.add(lblNewLabel_4);
		
		fld_deleteDoctor = new JTextField();
		fld_deleteDoctor.setBounds(18, 182, 130, 26);
		pnl_doktor_yönetimi.add(fld_deleteDoctor);
		fld_deleteDoctor.setColumns(10);
		
		JButton btn_deleteDoctor = new JButton("Sil");
		btn_deleteDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(fld_deleteDoctor.getText().length()==0)
			Helper.showMsg("Lütfen geçerli bir doktor seçiniz!");
			else {
			    if(Helper.confirm("sure")) {
				
			     int selectId=Integer.parseInt(fld_deleteDoctor.getText());
				 boolean control=bashekim.delDoctor(selectId);	
				 if(control) {
					 Helper.showMsg("success");
					 fld_deleteDoctor.setText(null);
					 UpdateDoctorModel();
				 }
				
			      }
			  }
			
			}
		});
		btn_deleteDoctor.setBounds(18, 209, 117, 29);
		pnl_doktor_yönetimi.add(btn_deleteDoctor);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(166, 18, 295, 241);
		pnl_doktor_yönetimi.add(w_scrollDoctor);
		
		table_Doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_Doctor);
		
		table_Doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
		
				try {
					fld_deleteDoctor.setText(table_Doctor.getValueAt(table_Doctor.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
				
				}
			}
		});
		
		table_Doctor.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
			  if(e.getType()==TableModelEvent.UPDATE) {
				  int selectID=Integer.parseInt(table_Doctor.getValueAt(table_Doctor.getSelectedRow(), 0).toString());
				  String selectName=table_Doctor.getValueAt(table_Doctor.getSelectedRow(), 1).toString();
				  String selectTcNO=table_Doctor.getValueAt(table_Doctor.getSelectedRow(), 2).toString();
				  String selectPass=table_Doctor.getValueAt(table_Doctor.getSelectedRow(), 3).toString();
				  
			 bashekim.updateDoctor(selectID, selectName, selectTcNO, selectPass);
				
			  }
				
			}
		});
		 
	}
	
	public void UpdateDoctorModel() {
		DefaultTableModel clearModel= (DefaultTableModel) table_Doctor.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<bashekim.getDoctorList().size();i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTc_no();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
				
		
	}
}
