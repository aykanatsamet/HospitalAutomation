package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fld_update;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 214, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_updateClinic = new JButton("Düzenle");
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					clinic.updateClinic(clinic.getId(),fld_update.getText());
					Helper.showMsg("success");
					dispose();
				}
			}
		});
		btn_updateClinic.setBounds(34, 80, 134, 26);
		contentPane.add(btn_updateClinic);
		
		fld_update = new JTextField();
		fld_update.setColumns(10);
		fld_update.setBounds(34, 49, 144, 26);
		fld_update.setText(clinic.getName());
		contentPane.add(fld_update);
		
		JLabel lbl_clinic = new JLabel("Poliklinik adı");
		lbl_clinic.setFont(new Font("Dialog", Font.BOLD, 13));
		lbl_clinic.setBounds(45, 32, 100, 16);
		contentPane.add(lbl_clinic);
	}
}
