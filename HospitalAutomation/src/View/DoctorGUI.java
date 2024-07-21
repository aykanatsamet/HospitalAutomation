 package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import Model.Doctor;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;


public class DoctorGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private static Doctor doctor =new Doctor();
	private JTable table_Whour;
    private DefaultTableModel wHourModel;
    private Object[] whourData=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {
		setResizable(false);
		
		wHourModel=new DefaultTableModel();
		Object[] whourCol=new Object[2];
		whourCol[0]="ID";
		whourCol[1]="Tarih";
		wHourModel.setColumnIdentifiers(whourCol);
		whourData=new Object[2];
		for(int i=0;i<doctor.getWhourList(doctor.getId()).size();i++) {
			whourData[0]=doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]=doctor.getWhourList(doctor.getId()).get(i).getwDate();
			wHourModel.addRow(whourData);
		}
		
		setTitle("Hospital Automation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		w_pane.setBackground(new Color(255, 255, 255));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın "+doctor.getName());
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel.setBounds(17, 6, 231, 25);
		w_pane.add(lblNewLabel);
		
		JButton doctor_logOut = new JButton("Çıkış Yap");
		doctor_logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI=new LoginGUI();
				loginGUI.setVisible(true);
				dispose(); 
			}
		});
		doctor_logOut.setBounds(377, 6, 117, 29);
		w_pane.add(doctor_logOut);
		
		JTabbedPane tabbedPane_doctor = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_doctor.setBounds(6, 43, 488, 311);
		w_pane.add(tabbedPane_doctor);
		
		JPanel w_whour = new JPanel();
		tabbedPane_doctor.addTab("Çalışma saatleri", null, w_whour, null);
		w_whour.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(6, 6, 136, 26);
		w_whour.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30"}));
		select_time.setBounds(149, 6, 92, 26);
		w_whour.add(select_time);
		
		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String date="";
				
				try {
					if(select_date.getDate()!=null) {
						date=sdf.format(select_date.getDate());
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				if(date.length()==0) {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz! ");
				}
				else {
					String time=" "+select_time.getSelectedItem().toString()+":00";
					String selectDate=date+time;
					try {
						boolean control=doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
						if(control) {
							UpdateWhourModel(doctor);
							Helper.showMsg("İşlem başarılı");
						}
						else {
							Helper.showMsg("Error");
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				}
			}
		});
		btn_addWhour.setBounds(240, 6, 92, 26);
		w_whour.add(btn_addWhour);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(0, 44, 467, 221);
		w_whour.add(w_scrollWhour);
		
		table_Whour = new JTable(wHourModel);
		w_scrollWhour.setViewportView(table_Whour);
		
		JButton btn_delWhour = new JButton("Sil");
		btn_delWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_Whour.getSelectedRow();
				if(selRow>=0) {
					String selectRow=table_Whour.getModel().getValueAt(selRow, 0).toString();
					int selID=Integer.parseInt(selectRow); 
					boolean control=doctor.delWhour(selID);
					if(control) {
						UpdateWhourModel(doctor);
						Helper.showMsg("success");
					}
					else {
						Helper.showMsg("error");
					}
					
				}
				else {
					Helper.showMsg("Lütfen bir tarih seçiniz.");
				}
				
			}
		});
		btn_delWhour.setBounds(375, 6, 92, 26);
		w_whour.add(btn_delWhour);
		
		
	}
	
	public void UpdateWhourModel(Doctor doctor ){
		DefaultTableModel clearModel=  (DefaultTableModel) table_Whour.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<doctor.getWhourList(doctor.getId()).size();i++) {
			whourData[0]=doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]=doctor.getWhourList(doctor.getId()).get(i).getwDate();
			wHourModel.addRow(whourData);
		}
		
	}
}
