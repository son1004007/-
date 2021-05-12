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

	Connection conn=null; // DB와 연결하는 인터페이스
	PreparedStatement pstm = null; //sql문 객체
	ResultSet r = null; // sql에 대한 반환(쿼리 실행에 대한 결과값 저장);


	SimpleDateFormat date = new SimpleDateFormat ("yyyy년 MM월 dd일");
    Calendar time = Calendar.getInstance();
    String time1 = date.format(time.getTime());
    String lookbook ="";
    String lookbook2 ="";
	
	public FrameRecord() {
		//JPanel 구조
		setBackground(Color.white);
		setLayout(null);
		setSize(600, 800);
		
		// 이전 기록 출력
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
			textArea.setText("날짜		루틴	운동이름	횟수	세트	중량\n"+lookbook2);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("오라클 연결 실패");
			
		}

		
		
		// 수동 입력
		JButton lbManual = new JButton("수동 입력");
		lbManual.setBounds(30, 30 , 100, 50);
		add(lbManual);
		lbManual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FrameRecord 열기
				FrameBase.getInstance(new FrameManual());
			}
		});
		
		// 수행 시작 버튼
		JButton lbRecord = new JButton("수행 시작");
		lbRecord.setBounds(390, 30 , 100, 50);
		add(lbRecord);
		
		lbRecord.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FrameRecord 열기
				FrameBase.getInstance(new FrameRecord2());
			}
		});
	}
	
}
