package frame;

import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import config.DBconfig;

public class FrameManual extends JPanel{
	String rt = ""; // 라디오버튼 (루틴 A/B) 선택 저장
	String exename1, exename2, exename3;
	Connection conn = null; // DB와 연결하는 인터페이스
	PreparedStatement pstm = null; // sql문 객체
	ResultSet r = null; // sql에 대한 반환(쿼리 실행에 대한 결과값 저장);
	String last="";

	public FrameManual() {
		// JPanel 구조
		setBackground(Color.white);
		setLayout(null);
		setSize(600, 800);

		// 날짜 선택
		Label selectDay = new Label("날짜 입력 : ");
		selectDay.setBounds(30, 30, 60, 30);
		add(selectDay);

		TextField day1, day2, day3;
		day1 = new TextField("2021");
		day2 = new TextField("03");
		day3 = new TextField("15");
		day1.setBounds(100, 30, 50, 30);
		day2.setBounds(160, 30, 30, 30);
		day3.setBounds(200, 30, 30, 30);
		add(day1);
		add(day2);
		add(day3);

		// A/B 루틴 선택
		Label routinname = new Label("루틴 : ");
		routinname.setBounds(30, 100, 50, 30);
		add(routinname);

		JRadioButton rdoA = new JRadioButton("RoutineA");
		JRadioButton rdoB = new JRadioButton("RoutineB");
		ButtonGroup buttongroup = new ButtonGroup();

		buttongroup.add(rdoA);
		buttongroup.add(rdoB);
		rdoA.setBounds(100, 100, 100, 30);
		rdoB.setBounds(300, 100, 100, 30);

		add(rdoA);
		add(rdoB);

		// A 버튼 액션

		rdoA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				루틴A를 선택하면,rt = A exename1=squat, exename2 = bench press , exename3= barbell row를 선택한다.
				rt = "A";
				exename1 = "Squat";
				exename2 = "bench press";
				exename3 = "barbell row";

			}
		});

		rdoB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				루틴B를 선택하면, rt =B, exename1=squat, exename2 = Over Head Press , exename3= Dead Lift를 선택한다.				
				rt = "B";
				exename1 = "Squat";
				exename2 = "OverHeadPress";
				exename3 = "Dead Lift";

			}
		});

		// 운동명 출력
		Label exename = new Label("운동명 : ");
		exename.setBounds(30, 160, 60, 30);
		add(exename);

		Label exe1 = new Label("Squat");
		exe1.setBounds(100, 160, 60, 30);
		add(exe1);

		Label exe2 = new Label("Bench Press/Over Head Press");
		exe2.setBounds(200, 160, 180, 30);
		add(exe2);

		Label exe3 = new Label("Barbell Row/Dead Lift");
		exe3.setBounds(400, 160, 180, 30);
		add(exe3);

		// 중량 입력
		Label kgname = new Label("중량 kg :");
		kgname.setBounds(30, 200, 60, 30);
		add(kgname);

		TextField kg1, kg2, kg3;
		kg1 = new TextField("60");
		kg2 = new TextField("60");
		kg3 = new TextField("60");
		kg1.setBounds(100, 200, 30, 30);
		kg2.setBounds(200, 200, 30, 30);
		kg3.setBounds(400, 200, 30, 30);
		add(kg1);
		add(kg2);
		add(kg3);

		// 세트 입력
		Label setNum = new Label("세트 :");
		setNum.setBounds(30, 250, 60, 30);
		add(setNum);

		TextField set1, set2, set3;
		set1 = new TextField("5");
		set2 = new TextField("5");
		set3 = new TextField("5");
		set1.setBounds(100, 250, 30, 30);
		set2.setBounds(200, 250, 30, 30);
		set3.setBounds(400, 250, 30, 30);
		add(set1);
		add(set2);
		add(set3);

		// 횟수 입력
		Label countNum = new Label("횟수 :");
		countNum.setBounds(30, 300, 60, 30);
		add(countNum);

		TextField cnt1, cnt2, cnt3;
		cnt1 = new TextField("5");
		cnt2 = new TextField("5");
		cnt3 = new TextField("5");
		cnt1.setBounds(100, 300, 30, 30);
		cnt2.setBounds(200, 300, 30, 30);
		cnt3.setBounds(400, 300, 30, 30);
		add(cnt1);
		add(cnt2);
		add(cnt3);

		// 수동 입력 완료
		JButton mnCompl = new JButton("입력 완료");
		mnCompl.setBounds(30, 350, 100, 50);
		add(mnCompl);

		mnCompl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String que = "INSERT INTO TRAINNING(MEM_ID,TRA_DATE,TRA_ROUTINE,TRA_EXECISE,TRA_WEIGHT,TRA_SET,TRA_REPEAT,TRA_ID) "
						+ "VALUES('mem1',?, ?, ?, ?, ?, ?, null)";

				try {
					conn = DBconfig.get();
					pstm = conn.prepareStatement(que);
					String exenameAry[]= {exename1, exename2, exename3};
					String kgAry[] = {kg1.getText(), kg2.getText(), kg3.getText()};
					int setAry[] = {Integer.parseInt(set1.getText()), Integer.parseInt(set2.getText()), Integer.parseInt(set3.getText())};
					int cntAry[] = {Integer.parseInt(cnt1.getText()), Integer.parseInt(cnt2.getText()), Integer.parseInt(cnt3.getText())};
					
					for(int i=0; i<exenameAry.length ; i++) {
						String tra_date = day1.getText() + "-" + day2.getText() + "-" + day3.getText();
						pstm.setString(1, tra_date);
						
						String tra_routine = rt;
						pstm.setString(2, tra_routine);
						
						String tra_execise = exenameAry[i];
						pstm.setString(3, tra_execise);
						
						String tra_weight = kgAry[i];
						pstm.setString(4, tra_weight);
						
						int tra_set = setAry[i];
						pstm.setInt(5, tra_set);
						
						int tra_repeat = cntAry[i];
						pstm.setInt(6, tra_repeat);
						
						last += tra_routine+" "+tra_execise+" "+tra_weight+" "+tra_set+" "+tra_repeat+"\n";
						
						pstm.executeUpdate();
					}
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, last + "저장이 완료되었습니다.");
			}
		});

	}
}
