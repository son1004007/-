package frame;


import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JPanel;

import config.DBconfig;


public class FrameRecord extends JPanel {

	Connection conn=null; // DB�� �����ϴ� �������̽�
	PreparedStatement pstm = null; //sql�� ��ü
	ResultSet r = null; // sql�� ���� ��ȯ(���� ���࿡ ���� ����� ����);


	SimpleDateFormat date = new SimpleDateFormat ("yyyy�� MM�� dd��");
    Calendar time = Calendar.getInstance();
    String time1 = date.format(time.getTime());
    String lookbook ="";
    String lookbook2 ="";
	
	public FrameRecord() {
		//JPanel ����
		setBackground(Color.white);
		setLayout(null);
		setSize(600, 800);
		
		// ���� ��� ���
		TextArea textArea = new TextArea("", 0, 0, 0);
		textArea.setBounds(30, 100, 460, 500);
		textArea.setBackground(Color.white);
		textArea.setEditable(false);
		add(textArea);
		

		
		try {
			String que ="select * from trainning where MEM_ID='mem1' order by TRA_DATE desc";
			conn=DBconfig.get();
			pstm = conn.prepareStatement(que);
			r=pstm.executeQuery();
			
			while(r.next()) {
				String MEM_ID = r.getString(1);
				String TRA_DATE = r.getString(2);
				String TRA_ROUTINE = r.getString(3);
				String TRA_EXECISE = r.getString(4);
				String TRA_EXECISE2="\t";
				if(TRA_EXECISE.length()<4) {
					TRA_EXECISE= TRA_EXECISE+TRA_EXECISE2;
				}
				
				int TRA_WEIGHT = r.getInt(5);
				int TRA_SET = r.getInt(6);
				int TRA_REPEAT = r.getInt(7);
				String TRA_ID = r.getString(8);
				
				System.out.println(TRA_DATE+"\t"+TRA_ROUTINE+"\t"+TRA_EXECISE+" \t"+TRA_REPEAT+"\t"+TRA_SET+"\t"+TRA_WEIGHT+"\n");
				lookbook = TRA_DATE+"\t"+TRA_ROUTINE+"\t"+TRA_EXECISE+" \t"+TRA_REPEAT+"\t"+TRA_SET+"\t"+TRA_WEIGHT+"\n";
				lookbook2 += lookbook;
				
			}
			textArea.setText("��¥		��ƾ	��̸�	Ƚ��	��Ʈ	�߷�\n"+lookbook2);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("����Ŭ ���� ����");
			
		}

		
		
		// ���� �Է�
		JButton lbManual = new JButton("���� �Է�");
		lbManual.setBounds(30, 30 , 100, 50);
		add(lbManual);
		lbManual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FrameRecord ����
				FrameBase.getInstance(new FrameManual());
			}
		});
		
		// ���� ���� ��ư
		JButton lbRecord = new JButton("���� ����");
		lbRecord.setBounds(390, 30 , 100, 50);
		add(lbRecord);
		
		lbRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FrameRecord ����
				FrameBase.getInstance(new FrameRecord2());
			}
		});
	}
	
}
