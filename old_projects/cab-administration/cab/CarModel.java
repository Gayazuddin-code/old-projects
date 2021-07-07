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

public class CarModel {

	JFrame frame;
	private JTextField txtmfd;
	private JTextField txtmodelname;
	private JTextField txtcid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarModel window = new CarModel();
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
	public CarModel() {
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
		    pst = con.prepareStatement("select * from car_model");
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
		frame.setBounds(100, 100, 980, 558);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Car-model", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(23, 22, 441, 478);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Car id");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 56, 187, 33);
		panel.add(lblNewLabel);
		
		JLabel lblModelName = new JLabel("Model name");
		lblModelName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblModelName.setBounds(10, 132, 187, 33);
		panel.add(lblModelName);
		
		JLabel lblMfd = new JLabel("Manufacture date");
		lblMfd.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMfd.setBounds(10, 210, 187, 33);
		panel.add(lblMfd);
		
		txtmfd = new JTextField();
		txtmfd.setBounds(232, 210, 187, 30);
		panel.add(txtmfd);
		txtmfd.setColumns(10);
		
		txtmodelname = new JTextField();
		txtmodelname.setColumns(10);
		txtmodelname.setBounds(232, 135, 187, 30);
		panel.add(txtmodelname);
		
		txtcid = new JTextField();
		txtcid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				try {
			           
		             String id = txtcid.getText();
		 
		                 pst = con.prepareStatement("select id, model_name, manufacture_date from car_model where id = ?");
		                 pst.setString(1, id);
		                 ResultSet rs = pst.executeQuery();
		 
		             if(rs.next()==true)
		             {
		                 id = rs.getString(1);
		                 String model_name = rs.getString(2);
		                 String manufacture_date = rs.getString(3);
		                 
		                 txtcid.setText(id);
		                 txtmodelname.setText(model_name);
		                 txtmfd.setText(manufacture_date);
		                 
		                 
		             }   
		             else
		             {
		                 txtmodelname.setText("");
		                 txtmfd.setText("");
		                  
		             }
		             
		 
		         } 
		 
		 catch (SQLException ex) {
		            
		         }
				
				
				
				
				
				
			}
		});
		txtcid.setColumns(10);
		txtcid.setBounds(232, 59, 187, 30);
		panel.add(txtcid);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String cid, model_name, manufacture_date;
				
				cid = txtcid.getText();
				model_name = txtmodelname.getText();
				manufacture_date = txtmfd.getText();
//				System.out.println(name);
//				System.out.println(licence_no);
//				System.out.println(salary);
				try 
				{
					pst = con.prepareStatement("insert into car_model(id, model_name, manufacture_date)values(?,?,?)");
					pst.setString(1, cid);
					pst.setString(2, model_name);
					pst.setString(3, manufacture_date);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede added!!!!");
					table_load();
					txtcid.setText("");
					txtmodelname.setText("");
					txtmfd.setText("");
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
		btnNewButton.setBounds(10, 312, 138, 49);
		panel.add(btnNewButton);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String cid, model_name, manufacture_date;
				
				cid = txtcid.getText();
				model_name = txtmodelname.getText();
				manufacture_date = txtmfd.getText();
//				System.out.println(name);
//				System.out.println(licence_no);
//				System.out.println(salary);
				try 
				{
					pst = con.prepareStatement("update car_model set id= ?, model_name= ?, manufacture_date= ? where id= ?");
					pst.setString(1, cid);
					pst.setString(2, model_name);
					pst.setString(3, manufacture_date);
					pst.setString(4, cid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede Updated!!!!");
					table_load();
					txtcid.setText("");
					txtmodelname.setText("");
					txtmfd.setText("");
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
		btnEdit.setBounds(164, 312, 138, 49);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String cid;
				
				cid = txtcid.getText();
				
				try 
				{
					pst = con.prepareStatement("delete from car_model where id= ?");
					pst.setString(1, cid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Recorede Deleted!!!!");
					table_load();
					txtcid.setText("");
					txtmodelname.setText("");
					txtmfd.setText("");
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
		btnDelete.setBounds(10, 382, 138, 49);
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
		btnCancel.setBounds(164, 382, 138, 49);
		panel.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(505, 21, 441, 478);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
