package cab;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

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

public class CabService {

	JFrame frame;
	private JTextField txtcarmodel;
	private JTextField txtgivendate;
	private JTextField txtreciveddate;
	private JTextField txtcost;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CabService window = new CabService();
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
	public CabService() {
		initialize();
		Connect();
		table_load();
	}
	
	
	
	Connection con;
	 PreparedStatement pst;
	 ResultSet rs;
	 private JTextField txtcid;
	 
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
		    pst = con.prepareStatement("select * from cab_service");
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
		frame.setBounds(100, 100, 979, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cab-service", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 393, 518);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cab id");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 55, 125, 42);
		panel.add(lblNewLabel);
		
		JLabel lblCarModel = new JLabel("Car model");
		lblCarModel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCarModel.setBounds(10, 119, 125, 42);
		panel.add(lblCarModel);
		
		JLabel lblGivenDate = new JLabel("Given date");
		lblGivenDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGivenDate.setBounds(10, 185, 125, 42);
		panel.add(lblGivenDate);
		
		JLabel lblRecivedDate = new JLabel("Recived date");
		lblRecivedDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRecivedDate.setBounds(10, 246, 137, 42);
		panel.add(lblRecivedDate);
		
		JLabel lblServiceCost = new JLabel("Service cost");
		lblServiceCost.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblServiceCost.setBounds(10, 319, 125, 42);
		panel.add(lblServiceCost);
		
		txtcarmodel = new JTextField();
		txtcarmodel.setColumns(10);
		txtcarmodel.setBounds(154, 132, 221, 25);
		panel.add(txtcarmodel);
		
		txtgivendate = new JTextField();
		txtgivendate.setColumns(10);
		txtgivendate.setBounds(154, 201, 221, 25);
		panel.add(txtgivendate);
		
		txtreciveddate = new JTextField();
		txtreciveddate.setColumns(10);
		txtreciveddate.setBounds(157, 262, 221, 25);
		panel.add(txtreciveddate);
		
		txtcost = new JTextField();
		txtcost.setColumns(10);
		txtcost.setBounds(154, 335, 221, 25);
		panel.add(txtcost);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String cid, car_model_id, given_date, recived_date, service_cost;
				
				cid = txtcid.getText();
				car_model_id = txtcarmodel.getText();
				given_date = txtgivendate.getText();
				recived_date = txtreciveddate.getText();
				service_cost = txtcost.getText();
				
				try 
				{
					pst = con.prepareStatement("insert into cab_service(cab_id, car_model_id, date_given_service, data_recived_service, service_cost)values(?,?,?,?,?)");
					pst.setString(1, cid);
					pst.setString(2, car_model_id);
					pst.setString(3, given_date);
					pst.setString(4, recived_date);
					pst.setString(5, service_cost);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede added!!!!");
					table_load();
					txtcid.setText("");
					txtcarmodel.setText("");
					txtgivendate.setText("");
					txtreciveddate.setText("");
					txtcost.setText("");
					txtcid.requestFocus();
				} 
				
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(77, 406, 105, 42);
		panel.add(btnNewButton);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
String cid, car_model_id, given_date, recived_date, service_cost;
				
				cid = txtcid.getText();
				car_model_id = txtcarmodel.getText();
				given_date = txtgivendate.getText();
				recived_date = txtreciveddate.getText();
				service_cost = txtcost.getText();
				
				try 
				{
					pst = con.prepareStatement("update cab_service set cab_id= ?, car_model_id= ?, date_given_service= ?, data_recived_service= ?, service_cost=? where cab_id= ?");
					pst.setString(1, cid);
					pst.setString(2, car_model_id);
					pst.setString(3, given_date);
					pst.setString(4, recived_date);
					pst.setString(5, service_cost);
					pst.setString(6, cid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede Updated!!!!");
					table_load();
					txtcid.setText("");
					txtcarmodel.setText("");
					txtgivendate.setText("");
					txtreciveddate.setText("");
					txtcost.setText("");
					txtcid.requestFocus();
				} 
				
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEdit.setBounds(208, 406, 105, 42);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String cid;
				
				cid = txtcid.getText();
				
				try 
				{
					pst = con.prepareStatement("delete from cab_service where cab_id= ?");
					pst.setString(1, cid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede Deleted!!!!");
					table_load();
					txtcid.setText("");
					txtcarmodel.setText("");
					txtgivendate.setText("");
					txtreciveddate.setText("");
					txtcost.setText("");
					txtcid.requestFocus();
				} 
				
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setBounds(77, 466, 105, 42);
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
		btnCancel.setBounds(208, 466, 105, 42);
		panel.add(btnCancel);
		
		txtcid = new JTextField();
		txtcid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			           
		             String id = txtcid.getText();
		 
		                 pst = con.prepareStatement("select cab_id,car_model_id,date_given_service,data_recived_service,service_cost from cab_service where cab_id = ?");
		                 pst.setString(1, id);
		                 ResultSet rs = pst.executeQuery();
		 
		             if(rs.next()==true)
		             {
		               
		                 id = rs.getString(1);
		                 String car_model_id = rs.getString(2);
		                 String given_date = rs.getString(3);
		                 String recived_date = rs.getString(4);
		                 String serviced_cost = rs.getString(5);
		                 
		                 
		                 txtcid.setText(id);
		                 txtcarmodel.setText(car_model_id);
		                 txtgivendate.setText(given_date);
		                 txtreciveddate.setText(recived_date);
		                 txtcost.setText(serviced_cost);
		                 
		                 
		             }   
		             else
		             {
		                 txtcarmodel.setText("");
		                 txtgivendate.setText("");
		                 txtreciveddate.setText("");
		                 txtcost.setText("");    
		             }
		             
		 
		 
		         } 
		 
		 catch (SQLException ex) {
		            
		         }
				
				
				
				
			}
		});
		txtcid.setColumns(10);
		txtcid.setBounds(154, 71, 221, 25);
		panel.add(txtcid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(413, 10, 542, 518);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
