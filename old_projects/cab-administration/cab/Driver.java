package cab;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Driver {

	JFrame frame;
	private JTextField txtdriverid;
	private JTextField txtfname;
	private JTextField txtlname;
	private JTextField txtbirthdate;
	private JTextField txtlicenceno;
	private JTextField txtsalary;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver window = new Driver();
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
	public Driver() {
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
		    pst = con.prepareStatement("select * from driver");
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
		frame.setBounds(100, 100, 1013, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Driver-Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 387, 496);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Driver id");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 48, 151, 41);
		panel.add(lblNewLabel);
		
		JLabel lblFirstName = new JLabel("First name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFirstName.setBounds(10, 99, 151, 41);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLastName.setBounds(10, 150, 151, 41);
		panel.add(lblLastName);
		
		JLabel lblBirthDate = new JLabel("Birth date");
		lblBirthDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBirthDate.setBounds(10, 201, 151, 41);
		panel.add(lblBirthDate);
		
		JLabel lblLicenceNo = new JLabel("Licence no");
		lblLicenceNo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLicenceNo.setBounds(10, 252, 151, 41);
		panel.add(lblLicenceNo);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSalary.setBounds(10, 303, 151, 41);
		panel.add(lblSalary);
		
		txtdriverid = new JTextField();
		txtdriverid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			           
		             String did = txtdriverid.getText();
		 
		                 pst = con.prepareStatement("select id, first_name, last_name, birth_date, driving_licence_number, salary from driver where id = ?");
		                 pst.setString(1, did);
		                 ResultSet rs = pst.executeQuery();
		 
		             if(rs.next()==true)
		             {
		                 did = rs.getString(1);
		                 String first_name = rs.getString(2);
		                 String last_name = rs.getString(3);
		                 String birth_date = rs.getString(4);
		                 String driver_licence_number = rs.getString(5);
		                 String salary = rs.getString(6);
		                 
		                 
		                 txtdriverid.setText(did);
		                 txtfname.setText(first_name);
		                 txtlname.setText(last_name);
		                 txtbirthdate.setText(birth_date);
		                 txtlicenceno.setText(driver_licence_number);
		                 txtsalary.setText(salary);
		                 
		                 
		             }   
		             else
		             {

		                 txtfname.setText("");
		                 txtlname.setText("");
		                 txtbirthdate.setText("");
		                 txtlicenceno.setText("");
		                 txtsalary.setText("");
		                  
		             }
		             
		 
		         } 
		 
		 catch (SQLException ex) {
		            
		         }
				
				
				
			}
		});
		txtdriverid.setBounds(171, 48, 200, 25);
		panel.add(txtdriverid);
		txtdriverid.setColumns(10);
		
		txtfname = new JTextField();
		txtfname.setColumns(10);
		txtfname.setBounds(171, 99, 200, 25);
		panel.add(txtfname);
		
		txtlname = new JTextField();
		txtlname.setColumns(10);
		txtlname.setBounds(171, 150, 200, 25);
		panel.add(txtlname);
		
		txtbirthdate = new JTextField();
		txtbirthdate.setColumns(10);
		txtbirthdate.setBounds(171, 201, 200, 25);
		panel.add(txtbirthdate);
		
		txtlicenceno = new JTextField();
		txtlicenceno.setColumns(10);
		txtlicenceno.setBounds(171, 252, 200, 25);
		panel.add(txtlicenceno);
		
		txtsalary = new JTextField();
		txtsalary.setColumns(10);
		txtsalary.setBounds(171, 303, 200, 25);
		panel.add(txtsalary);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String did, first_name, last_name, birth_date, driving_licence_number, salary;
				
				did = txtdriverid.getText();
				first_name = txtfname.getText();
				last_name = txtlname.getText();
				birth_date = txtbirthdate.getText();
				driving_licence_number = txtlicenceno.getText();
				salary = txtsalary.getText();
				
				try 
				{
					pst = con.prepareStatement("insert into driver(id,first_name,last_name,birth_date,driving_licence_number,salary)values(?,?,?,?,?,?)");
					pst.setString(1, did);
					pst.setString(2, first_name);
					pst.setString(3, last_name);
					pst.setString(4, birth_date);
					pst.setString(5, driving_licence_number);
					pst.setString(6, salary);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede added!!!!");
					table_load();
					txtdriverid.setText("");
					txtfname.setText("");
					txtlname.setText("");
					txtbirthdate.setText("");
					txtlicenceno.setText("");
					txtsalary.setText("");
					txtdriverid.requestFocus();
				} 
				
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 374, 118, 41);
		panel.add(btnNewButton);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
String did, first_name, last_name, birth_date, driving_licence_number, salary;
				
				did = txtdriverid.getText();
				first_name = txtfname.getText();
				last_name = txtlname.getText();
				birth_date = txtbirthdate.getText();
				driving_licence_number = txtlicenceno.getText();
				salary = txtsalary.getText();
				
				try 
				{
					pst = con.prepareStatement("update driver set id= ?, first_name= ?, last_name= ?, birth_date= ?, driving_licence_number= ?, salary= ? where id= ?");
					pst.setString(1, did);
					pst.setString(2, first_name);
					pst.setString(3, last_name);
					pst.setString(4, birth_date);
					pst.setString(5, driving_licence_number);
					pst.setString(6, salary);
					pst.setString(7, did);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede Updated!!!!");
					table_load();
					txtdriverid.setText("");
					txtfname.setText("");
					txtlname.setText("");
					txtbirthdate.setText("");
					txtlicenceno.setText("");
					txtsalary.setText("");
					txtdriverid.requestFocus();
				} 
				
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(158, 374, 118, 41);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String did;
				
				did = txtdriverid.getText();
				
				try 
				{
					pst = con.prepareStatement("delete from driver where id= ?");
					pst.setString(1, did);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede Deleted!!!!");
					table_load();
					txtdriverid.setText("");
					txtfname.setText("");
					txtlname.setText("");
					txtbirthdate.setText("");
					txtlicenceno.setText("");
					txtsalary.setText("");
					txtdriverid.requestFocus();
				} 
				
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(10, 433, 118, 41);
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
		btnCancel.setBounds(158, 433, 118, 41);
		panel.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(410, 10, 568, 496);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
