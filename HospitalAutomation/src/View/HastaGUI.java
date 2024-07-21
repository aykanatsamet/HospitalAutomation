package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private static Hasta hasta=new Hasta();
	private Clinic clinic=new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData=null;
	private Whour whour=new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData=null;
	private JTable table_whour;
	private int selectDoctorID=0;
	private String selectDoctorName=null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData=null;
	Appointment appointment=new Appointment();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
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
	public HastaGUI(Hasta hasta) {
		
		// DoctorModel
		doctorModel=new DefaultTableModel();
		Object[] colDoctor=new Object[2];
		colDoctor[0]="ID";
		colDoctor [1]="AD SOYAD";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData=new Object[2];
		
		//WhourModel
		whourModel=new DefaultTableModel();
		Object[] colWhour=new Object[2];
		colWhour[0]="ID";
		colWhour [1]="TARİH";
		whourModel.setColumnIdentifiers(colWhour);
		whourData=new Object[2];
		
		// Appoint Model
		appointModel=new DefaultTableModel();
		Object[] colAppoint=new Object[3];
		colAppoint[0]="ID";
		colAppoint[1]="DOKTOR";
		colAppoint[2]="TARİH";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData=new Object[3];
		
		for(int i=0;i<appointment.getHastaList(hasta.getId()).size();i++) {
			appointData[0]=appointment.getHastaList(hasta.getId()).get(i).getId();
			appointData[1]=appointment.getHastaList(hasta.getId()).get(i).getDoctor_name();
			appointData[2]=appointment.getHastaList(hasta.getId()).get(i).getApp_date();
			appointModel.addRow(appointData);
		}
		
		setTitle("Hospital Automation");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,600, 400);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_hasta = new JLabel("Hoşgeldiniz, Sayın "+hasta.getName());
		lbl_hasta.setFont(new Font("Dialog", Font.BOLD, 13));
		lbl_hasta.setBounds(17, 7, 231, 25);
		w_pane.add(lbl_hasta);
		
		JButton register_logOut = new JButton("Çıkış Yap");
		register_logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI=new LoginGUI();
				loginGUI.setVisible(true);
				dispose(); 
			}
		});
		register_logOut.setBounds(477, 6, 117, 29);
		w_pane.add(register_logOut);
		
		JTabbedPane tab_hasta = new JTabbedPane(JTabbedPane.TOP);
		tab_hasta.setBounds(6, 44, 588, 311);
		w_pane.add(tab_hasta);
		
		JPanel pane_rezerv = new JPanel();
		tab_hasta.addTab("Rezervasyon sistemi", null, pane_rezerv, null);
		pane_rezerv.setLayout(null);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(6, 37, 170, 222);
		pane_rezerv.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		
		JLabel lbl_doctorList = new JLabel("Doktor Listesi");
		lbl_doctorList.setBounds(6, 9, 119, 16);
		pane_rezerv.add(lbl_doctorList);
		
		JComboBox sel_clinic = new JComboBox();
		sel_clinic.setBounds(177, 54, 100, 27);
		sel_clinic.addItem("--Poliklinik Seç");
		for(int i=0;i<clinic.getList().size();i++) {
			sel_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}
		
		sel_clinic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(sel_clinic.getSelectedIndex()!=0) {
					JComboBox comboBox=(JComboBox) e.getSource();
					Item item=(Item) comboBox.getSelectedItem();
					DefaultTableModel clearModel=(DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					
					for(int i=0;i<clinic.getClinicDoctorList(item.getKey()).size();i++) {
						doctorData[0]=clinic.getClinicDoctorList(item.getKey()).get(i).getId();
						doctorData[1]=clinic.getClinicDoctorList(item.getKey()).get(i).getName();
						doctorModel.addRow(doctorData);
					}
					
					
				}
				else {
					DefaultTableModel clearModel=(DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
				
			}
		});
		
		pane_rezerv.add(sel_clinic);
		 
		
		JLabel pol_name = new JLabel("Poliklinik adı");
		pol_name.setBounds(180, 37, 100, 16);
		pane_rezerv.add(pol_name);
		
		JScrollPane w_scrollDoctor_1 = new JScrollPane();
		w_scrollDoctor_1.setBounds(300, 37, 266, 222);
		pane_rezerv.add(w_scrollDoctor_1);
		
		table_whour = new JTable(whourModel);
		w_scrollDoctor_1.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(2);
		
		JLabel lblWhour = new JLabel("Seçilen doktorun uygun saatleri");
		lblWhour.setBounds(311, 9, 202, 16);
		pane_rezerv.add(lblWhour);
		
		JLabel select_doctor_lbl = new JLabel("Doktor Seç");
		select_doctor_lbl.setBounds(180, 103, 100, 16);
		pane_rezerv.add(select_doctor_lbl);
		
		JButton select_doctor_btn = new JButton("Seç");
		select_doctor_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow=table_doctor.getSelectedRow();
				if(selRow>=0) {
					String value=table_doctor.getModel().getValueAt(selRow, 0).toString();
					int id=Integer.parseInt(value);
					DefaultTableModel clearModel=(DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					for(int i=0;i<whour.getWhourList(id).size();i++) {
						whourData[0]=whour.getWhourList(id).get(i).getId();
						whourData[1]=whour.getWhourList(id).get(i).getwDate();
						whourModel.addRow(whourData);
					}
					
					selectDoctorID=id;
					selectDoctorName=table_doctor.getModel().getValueAt(selRow, 1).toString();
					table_whour.setModel(whourModel);
				}
				else {
					Helper.showMsg("Lütfen bir doktor seçiniz.");
				}
			}
		});
		select_doctor_btn.setBounds(177, 119, 94, 29);
		pane_rezerv.add(select_doctor_btn);
		
		JLabel select_doctor_lbl_1 = new JLabel("Randevu");
		select_doctor_lbl_1.setBounds(188, 173, 100, 16);
		pane_rezerv.add(select_doctor_lbl_1);
		
		JButton select_doctor_btn_1 = new JButton("Randevu Al");
		select_doctor_btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_whour.getSelectedRow();
				if(selRow>=0) {
					String date=table_whour.getModel().getValueAt(selRow, 1).toString();
					boolean control=hasta.addAppointment(selectDoctorID,hasta.getId(), selectDoctorName, hasta.getName(), date);
					if(control) {
						Helper.showMsg("success");
						hasta.updateWhourStatus(selectDoctorID, date);
						updateWhourModel(selectDoctorID); 
						updateAppointModel(hasta.getId());
					}
					else {
						Helper.showMsg("error");
					}
				}
				else {
					Helper.showMsg("Lütfen geçerli bir tarih seçiniz!");
				}
				
				
			}
		});
		select_doctor_btn_1.setBounds(174, 189, 94, 29);
		pane_rezerv.add(select_doctor_btn_1);
		
		JPanel p_appointment = new JPanel();
		tab_hasta.addTab("Randevularım", null, p_appointment, null);
		p_appointment.setLayout(null);
		
		JScrollPane scrollPane_appoint = new JScrollPane();
		scrollPane_appoint.setBounds(6, 6, 555, 253);
		p_appointment.add(scrollPane_appoint);
		
		table_appoint = new JTable(appointModel);
		scrollPane_appoint.setViewportView(table_appoint);
	}
	
	public void updateWhourModel(int doctor_id) {
		DefaultTableModel clearModel=(DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<whour.getWhourList(doctor_id).size();i++) {
			whourData[0]=whour.getWhourList(doctor_id).get(i).getId();
			whourData[1]=whour.getWhourList(doctor_id).get(i).getwDate();
			whourModel.addRow(whourData);
		}
		table_whour.setModel(whourModel);
		
		
	}
	public void updateAppointModel(int hasta_id) {
		DefaultTableModel clearModel=(DefaultTableModel)table_appoint.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<appointment.getHastaList(hasta_id).size();i++) {
			appointData[0]=appointment.getHastaList(hasta_id).get(i).getId();
			appointData[1]=appointment.getHastaList(hasta_id).get(i).getDoctor_name();
			appointData[2]=appointment.getHastaList(hasta_id).get(i).getApp_date();
			appointModel.addRow(appointData); 
		}
		table_appoint.setModel(appointModel);
		
		
	}
}
