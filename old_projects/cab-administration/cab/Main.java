package cab;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 503, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Driver-details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Driver window = new Driver();
				frame.setVisible(false);
				window.frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(10, 108, 198, 61);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Main Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 10, 348, 77);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnShiftdetails = new JButton("Shift-details");
		btnShiftdetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shift window = new Shift();
				frame.setVisible(false);
				window.frame.setVisible(true);
			}
		});
		btnShiftdetails.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnShiftdetails.setBounds(10, 250, 198, 61);
		frame.getContentPane().add(btnShiftdetails);
		
		JButton btnCabdetails = new JButton("Cab-details");
		btnCabdetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cab window = new Cab();
				frame.setVisible(false);
				window.frame.setVisible(true);
				
				
			}
		});
		btnCabdetails.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCabdetails.setBounds(10, 396, 198, 61);
		frame.getContentPane().add(btnCabdetails);
		
		JButton btnCabservice = new JButton("Cab-service");
		btnCabservice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CabService window = new CabService();
				frame.setVisible(false);
				window.frame.setVisible(true);
			}
		});
		btnCabservice.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCabservice.setBounds(10, 179, 198, 61);
		frame.getContentPane().add(btnCabservice);
		
		JButton btnCarmodels = new JButton("Car-models");
		btnCarmodels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarModel window = new CarModel();
				frame.setVisible(false);
				window.frame.setVisible(true);
			}
		});
		btnCarmodels.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCarmodels.setBounds(10, 323, 198, 61);
		frame.getContentPane().add(btnCarmodels);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnExit.setBounds(279, 361, 198, 61);
		frame.getContentPane().add(btnExit);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login window = new Login();
				frame.setVisible(false);
				window.frame.setVisible(true);
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogout.setBounds(279, 290, 198, 61);
		frame.getContentPane().add(btnLogout);
	}

}
