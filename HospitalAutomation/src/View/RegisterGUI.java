package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tc;
	private JPasswordField fld_pass;
	private JButton btn_register;
	private JButton btn_GoBack;
	private Hasta hasta=new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("Hospital Automation");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNewLabel_1.setBounds(6, 6, 100, 16);
		w_pane.add(lblNewLabel_1);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(6, 24, 288, 26);
		w_pane.add(fld_name);
		
		fld_tc = new JTextField();
		fld_tc.setColumns(10);
		fld_tc.setBounds(6, 80, 288, 26);
		w_pane.add(fld_tc);
		
		JLabel lbl_tc = new JLabel("TC Numarası");
		lbl_tc.setFont(new Font("Dialog", Font.BOLD, 13));
		lbl_tc.setBounds(6, 62, 100, 16);
		w_pane.add(lbl_tc);
		
		JLabel lbl_tc_1 = new JLabel("Şifre");
		lbl_tc_1.setFont(new Font("Dialog", Font.BOLD, 13));
		lbl_tc_1.setBounds(6, 118, 100, 16);
		w_pane.add(lbl_tc_1);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(6, 146, 288, 26);
		w_pane.add(fld_pass);
		
		btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_name.getText().length()==0|| fld_pass.getText().length()==0||fld_tc.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					boolean control=hasta.register(fld_name.getText(), fld_tc.getText(), fld_pass.getText());
					if(control) {
						Helper.showMsg("success");
						LoginGUI loginGUI=new LoginGUI();
						loginGUI.setVisible(true);
						dispose();
						
					}else {
						Helper.showMsg("error");
					}
						
					
				}
			}
		});
		btn_register.setBounds(6, 184, 288, 29);
		w_pane.add(btn_register);
		
		btn_GoBack = new JButton("Geri Dön");
		btn_GoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI=new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_GoBack.setBounds(6, 215, 288, 29);
		w_pane.add(btn_GoBack);
	}
}
