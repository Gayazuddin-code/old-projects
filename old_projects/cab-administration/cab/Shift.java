package cab;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Shift {

	JFrame frame;
	private JTextField txtshiftid;
	private JTextField txtdriverid;
	private JTextField txtcabid;
	private JTextField txtstartime;
	private JTextField txtendtime;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shift window = new Shift();
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
	public Shift() {
		initialize();
		Connect();
		table_load();
	}

	
	
	
	Connection con;
	 PreparedStatement pst;
	 ResultSet rs;
	 
	 public void Connect()
	     {
	         try {
	             Class.forName("com.mysql.jdbc.Driver");
	             con = DriverManager.getConnection("jdbc:mysql://localhost/cab", "root","acnologia");
	         }
	         catch (ClassNotFoundException ex) 
	         {
	           ex.printStackTrace();
	         }
	         catch (SQLException ex) 
	         {
	         	   ex.printStackTrace();
	         }
	 
	     }
	 
	  public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from shift");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1056, 543);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Shift Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 372, 454);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Shift id");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 49, 144, 38);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Driver id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 97, 144, 38);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cab id");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(10, 145, 144, 38);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Start time");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(10, 193, 144, 38);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("End time");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(10, 241, 144, 38);
		panel.add(lblNewLabel_1_3);
		
		txtshiftid = new JTextField();
		txtshiftid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				try {
			           
		             String id = txtshiftid.getText();
		 
		                 pst = con.prepareStatement("select id, driver_id, cab_id, shift_start_time, shift_end_time from shift where id = ?");
		                 pst.setString(1, id);
		                 ResultSet rs = pst.executeQuery();
		 
		             if(rs.next()==true)
		             {
		                 id = rs.getString(1);
		                 String driver_id = rs.getString(2);
		                 String cab_id = rs.getString(3);
		                 String shift_start_time = rs.getString(4);
		                 String shift_end_time = rs.getString(5);
		                 
		                 
		                 txtshiftid.setText(id);
		                 txtdriverid.setText(driver_id);
		                 txtcabid.setText(cab_id);
		                 txtstartime.setText(shift_start_time);
		                 txtendtime.setText(shift_end_time);
		                 
		                 
		             }   
		             else
		             {
		                 txtdriverid.setText("");
		                 txtcabid.setText("");
		                 txtstartime.setText("");
		                 txtendtime.setText("");
		                  
		             }
		             
		 
		         } 
		 
		 catch (SQLException ex) {
		            
		         }
				
				
				
				
				
			}
		});
		txtshiftid.setBounds(164, 63, 182, 19);
		panel.add(txtshiftid);
		txtshiftid.setColumns(10);
		
		txtdriverid = new JTextField();
		txtdriverid.setColumns(10);
		txtdriverid.setBounds(164, 111, 182, 19);
		panel.add(txtdriverid);
		
		txtcabid = new JTextField();
		txtcabid.setColumns(10);
		txtcabid.setBounds(164, 159, 182, 19);
		panel.add(txtcabid);
		
		txtstartime = new JTextField();
		txtstartime.setColumns(10);
		txtstartime.setBounds(164, 207, 182, 19);
		panel.add(txtstartime);
		
		txtendtime = new JTextField();
		txtendtime.setColumns(10);
		txtendtime.setBounds(164, 255, 182, 19);
		panel.add(txtendtime);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String sid, driver_id, cab_id, shift_start_time, shift_end_time;
				
				sid = txtshiftid.getText();
				driver_id = txtdriverid.getText();
				cab_id = txtcabid.getText();
				shift_start_time = txtstartime.getText();
				shift_end_time = txtendtime.getText();
				
				try 
				{
					pst = con.prepareStatement("insert into shift(id, driver_id, cab_id, shift_start_time, shift_end_time)values(?,?,?,?,?)");
					pst.setString(1, sid);
					pst.setString(2, driver_id);
					pst.setString(3, cab_id);
					pst.setString(4, shift_start_time);
					pst.setString(5, shift_end_time);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede added!!!!");
					table_load();
					txtshiftid.setText("");
					txtdriverid.setText("");
					txtcabid.setText("");
					txtstartime.setText("");
					txtendtime.setText("");
					txtshiftid.requestFocus();
				} 
				
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 329, 112, 47);
		panel.add(btnNewButton);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
String sid, driver_id, cab_id, shift_start_time, shift_end_time;
				
				sid = txtshiftid.getText();
				driver_id = txtdriverid.getText();
				cab_id = txtcabid.getText();
				shift_start_time = txtstartime.getText();
				shift_end_time = txtendtime.getText();
				
				try 
				{
					pst = con.prepareStatement("update shift set id= ?, driver_id= ?, cab_id= ?, shift_start_time= ?, shift_end_time= ? where id= ?");
					pst.setString(1, sid);
					pst.setString(2, driver_id);
					pst.setString(3, cab_id);
					pst.setString(4, shift_start_time);
					pst.setString(5, shift_end_time);
					pst.setString(6, sid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede Updated!!!!");
					table_load();
					txtshiftid.setText("");
					txtdriverid.setText("");
					txtcabid.setText("");
					txtstartime.setText("");
					txtendtime.setText("");
					txtshiftid.requestFocus();
				} 
				
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(135, 329, 112, 47);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String sid;
				
				sid = txtshiftid.getText();
				
				try 
				{
					pst = con.prepareStatement("delete from shift where id= ?");
					pst.setString(1, sid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede Deleted!!!!");
					table_load();
					txtshiftid.setText("");
					txtdriverid.setText("");
					txtcabid.setText("");
					txtstartime.setText("");
					txtendtime.setText("");
					txtshiftid.requestFocus();
				} 
				
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(10, 386, 112, 47);
		panel.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main window = new Main();
				frame.setVisible(false);
				window.frame.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancel.setBounds(135, 386, 112, 47);
		panel.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(392, 10, 640, 454);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
