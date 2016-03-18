package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class sql_Dmeo {
	
	private JFrame f;
	private JButton jbt;
	private JTextField jtfs;
	private JTextArea jta;
	
	sql_Dmeo(){
		init();
	}
	
	
	private void init() {
		
		f = new JFrame("ְ������ϵͳ");
		f.setBounds(200,200,500,400);
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.add(new JLabel("ְ������"));
		jtfs = new JTextField(20);
		f.add(jtfs);
		
		jbt = new JButton("��ѯ");
		f.add(jbt);
		 
		f.add(new JLabel("ְ����Ϣ"));
		jta = new JTextArea(8,30);
		f.add(jta);
		
		eventDemo();
		
		f.setVisible(true);
	}


	private void eventDemo() {
		
		jbt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				show();
			}
		});
		
	}


	public void show(){
		
		//����������
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//�������ݿ�����
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//�������ݿ�����
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=SM", "sa", "cdl923925");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//�������
		//String str = "insert into ְ�� values('10003','����','��',17,89,92,91,272)";
		String str = "select * from ְ��";
		//ִ�����
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(str);
			int flag = 1;
			//���������
			while(rs.next()){
				String Zno = rs.getString(1);
				String Zname = rs.getString(2);
				String Zsex = rs.getString(3);
				int Zyear = rs.getInt(4);
				int Zc1 = rs.getInt(5);
				int Zc2 = rs.getInt(6);
				int Zc3 = rs.getInt(7);
				int Zsum = rs.getInt(8);
				if(Zname.equals(jtfs.getText().trim())){
					jta.setText(jta.getText()+Zno+" "+Zname+" "+Zsex+" "+Zyear+" "+Zc1+" "+Zc2+" "+Zc3+" "+Zsum+"\n");
					flag = 0;
					break;
				}
				if(flag==0)
					jta.setText("�޴�ְԱ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		
		new sql_Dmeo();
	}

}
