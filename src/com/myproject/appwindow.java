package com.myproject;
import java.sql.*;
import java.text.Bidi;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.mysql.cj.exceptions.RSAException;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class appwindow {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application..
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					appwindow window = new appwindow();
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
	public appwindow() {
		initialize();
		Connect();
		table_load();
	}
	
	// making database connection
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud","root","root1234");
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO: handle exception
		}
		catch (SQLException  e) 
		{
			// TODO: handle exception
		}
	}
	
	// To data in table
	
	public void table_load()
	{
		
		try 
		{
			pst = con.prepareStatement("select * from book");
			
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1089, 689);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(391, -15, 293, 125);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		panel.setBounds(23, 132, 547, 326);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1.setBounds(28, 47, 119, 43);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(28, 136, 119, 43);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(28, 218, 119, 43);
		panel.add(lblNewLabel_1_2);
		
		txtname = new JTextField();
		txtname.setBounds(183, 47, 309, 43);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(183, 136, 309, 43);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtprice.setColumns(10);
		txtprice.setBounds(183, 222, 309, 43);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				// making variables for the labels and getting value in them through text box
				String bname,edition,price;
				
				bname = txtname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				
				try {
					pst = con.prepareStatement("insert into book (name,edition,price)values(?,?,?)");
					
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					
					table_load();
					
					JOptionPane.showMessageDialog(null, "Record Added Successfully!");
					
					
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					
					txtname.requestFocus(); //to clear text box  and mouse cursor should be focus on the fist text box
					
				} 
				catch (SQLException e0) {
					// TODO: handle exception
					e0
					.printStackTrace();
				}
				catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 22));
		btnNewButton.setBounds(33, 468, 124, 52);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				System.exit(0);
				
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 22));
		btnNewButton_1.setBounds(231, 468, 124, 52);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				
				
				txtname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				
				txtname.requestFocus(); 
				
				
				
				
				
			}
		});
		btnNewButton_2.setFont(new Font("Dialog", Font.BOLD, 22));
		btnNewButton_2.setBounds(423, 468, 124, 52);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(590, 132, 452, 402);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(23, 530, 547, 95);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					String id = txtbid.getText();
					
					pst = con.prepareStatement("select name,edition,price from book where id = ?");
					
					pst.setString(1, id);
					
					ResultSet rs  = pst.executeQuery();
					
					if(rs.next() == true)
					{
						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);
						
						
						txtname.setText(name);
						txtedition.setText(edition);
						txtprice.setText(price);
					}
					
					else {
						txtname.setText("");
						txtedition.setText("");
						txtprice.setText("");
					}
				} 
				catch (SQLException e3) {
					// TODO: handle exception
					e3.printStackTrace();
				}
				catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
				
				
				
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(183, 32, 309, 43);
		panel_1.add(txtbid);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Edition");
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(28, 28, 119, 43);
		panel_1.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton_2_1 = new JButton("Update");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				String bname,edition,price,bid;
				
				bname = txtname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				
				bid = txtbid.getText();
				
				try {
					pst = con.prepareStatement("update book set name=?,edition=?,price=? where id=?");
					
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					table_load();
					
					JOptionPane.showMessageDialog(null, "Record Updated Successfully!");
					
					
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					
					txtname.requestFocus(); //to clear text box  and mouse cursor should be focus on the fist text box
					
				} 
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				catch (Exception e2) 
				{
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			
			}
		});
		btnNewButton_2_1.setFont(new Font("Dialog", Font.BOLD, 22));
		btnNewButton_2_1.setBounds(665, 548, 124, 52);
		frame.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("Delete");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bid;
				
				
				
				bid = txtbid.getText();
				
				try {
					pst = con.prepareStatement("delete from book  where id=?");
					
					
					pst.setString(1, bid);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Record Deleted Successfully!");
					
					table_load();
					txtname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					
					txtname.requestFocus(); //to clear text box  and mouse cursor should be focus on the fist text box
					
				} 
				catch (SQLException e1) 
				{
					// TODO: handle exception
					e1.printStackTrace();
				}
				catch (Exception e2) 
				{
					// TODO: handle exception
					e2.printStackTrace();
				}

				
				
				
				
				
				
			}
		});
		btnNewButton_2_1_1.setFont(new Font("Dialog", Font.BOLD, 22));
		btnNewButton_2_1_1.setBounds(876, 548, 124, 52);
		frame.getContentPane().add(btnNewButton_2_1_1);
	}
}
