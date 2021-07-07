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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Cab {

	JFrame frame;
	private JTextField txtcid;
	private JTextField txtlicenceplate;
	private JTextField txtcarmodelid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cab window = new Cab();
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
	public Cab() {
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
		    pst = con.prepareStatement("select * from cab");
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
		frame.setBounds(100, 100, 1090, 689);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cab-Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 477, 536);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cab id");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 77, 189, 44);
		panel.add(lblNewLabel);
		
		JLabel lblLicencePlateNo = new JLabel("Licence Plate NO");
		lblLicencePlateNo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLicencePlateNo.setBounds(10, 161, 189, 44);
		panel.add(lblLicencePlateNo);
		
		JLabel lblCarModelId = new JLabel("Car Model Id");
		lblCarModelId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCarModelId.setBounds(10, 252, 189, 44);
		panel.add(lblCarModelId);
		
		txtcid = new JTextField();
		txtcid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			           
		             String id = txtcid.getText();
		 
		                 pst = con.prepareStatement("select id,license_plate,car_model_id from cab where id = ?");
		                 pst.setString(1, id);
		                 ResultSet rs = pst.executeQuery();
		 
		             if(rs.next()==true)
		             {
		                 id = rs.getString(1);
		                 String licence_plate_no = rs.getString(2);
		                 String car_model_id = rs.getString(3);
		                 
		                 txtcid.setText(id);
		                 txtlicenceplate.setText(licence_plate_no);
		                 txtcarmodelid.setText(car_model_id);
		                 
		                 
		             }   
		             else
		             {
		                 txtlicenceplate.setText("");
		                 txtcarmodelid.setText("");
		                  
		             }
		             
		 
		         } 
		 
		 catch (SQLException ex) {
		            
		         }
				
				
				
			}
		});
		txtcid.setBounds(209, 86, 198, 27);
		panel.add(txtcid);
		txtcid.setColumns(10);
		
		txtlicenceplate = new JTextField();
		txtlicenceplate.setColumns(10);
		txtlicenceplate.setBounds(209, 167, 198, 27);
		panel.add(txtlicenceplate);
		
		txtcarmodelid = new JTextField();
		txtcarmodelid.setColumns(10);
		txtcarmodelid.setBounds(209, 265, 198, 27);
		panel.add(txtcarmodelid);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cid, licence_plate_no, car_model_id;
				
				cid = txtcid.getText();
				licence_plate_no = txtlicenceplate.getText();
				car_model_id = txtcarmodelid.getText();
//				System.out.println(name);
//				System.out.println(licence_no);
//				System.out.println(salary);
				try 
				{
					pst = con.prepareStatement("insert into cab(id, license_plate, car_model_id)values(?,?,?)");
					pst.setString(1, cid);
					pst.setString(2, licence_plate_no);
					pst.setString(3, car_model_id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede added!!!!");
					table_load();
					txtcid.setText("");
					txtlicenceplate.setText("");
					txtcarmodelid.setText("");
					txtcid.requestFocus();
				} 
				
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(10, 375, 129, 44);
		panel.add(btnNewButton);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String cid, licence_plate_no, car_model_id;
				
				cid = txtcid.getText();
				licence_plate_no = txtlicenceplate.getText();
				car_model_id = txtcarmodelid.getText();
//				System.out.println(name);	
//				System.out.println(licence_no);
//				System.out.println(salary);
				try 
				{
					pst = con.prepareStatement("update cab set id= ?,license_plate= ?,car_model_id= ? where id= ?");
					pst.setString(1, cid);
					pst.setString(2, licence_plate_no);
					pst.setString(3, car_model_id);
					pst.setString(4, cid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede Updated!!!!");
					table_load();
					txtcid.setText("");
					txtlicenceplate.setText("");
					txtcarmodelid.setText("");
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
		btnEdit.setBounds(209, 375, 129, 44);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cid;
				
				cid = txtcid.getText();
				
				try 
				{
					pst = con.prepareStatement("delete from cab where id= ?");
					pst.setString(1, cid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede Deleted!!!!");
					table_load();
					txtcid.setText("");
					txtlicenceplate.setText("");
					txtcarmodelid.setText("");
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
		btnDelete.setBounds(10, 445, 129, 44);
		panel.add(btnDelete);
		
		JButton btnCancle = new JButton("Cancel");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main window = new Main();
				frame.setVisible(false);
				window.frame.setVisible(true);
			}
		});
		btnCancle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCancle.setBounds(209, 445, 129, 44);
		panel.add(btnCancle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(515, 9, 496, 536);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
