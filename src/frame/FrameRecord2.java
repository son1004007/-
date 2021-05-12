package frame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import config.DBconfig;

public class FrameRecord2 extends JPanel {
	int count = 5;
	String kg = "60";
	String show = "";
	String rt = ""; // 운동 루틴 FrameComplete에 던져서 DB로 저장하기 위해 static

	Connection conn = null; // DB와 연결하는 인터페이스
	PreparedStatement pstm = null; // sql문 객체
	ResultSet r = null; // sql에 대한 반환(쿼리 실행에 대한 결과값 저장);

	public FrameRecord2() {
		setBackground(Color.white);
		setLayout(null);
		setSize(600, 2000);

		// A/B 루틴
		JRadioButton rdoA = new JRadioButton("RoutineA");
		JRadioButton rdoB = new JRadioButton("RoutineB");
		ButtonGroup buttongroup = new ButtonGroup();

		buttongroup.add(rdoA);
		buttongroup.add(rdoB);
		rdoA.setBounds(30, 100, 100, 50);
		rdoB.setBounds(300, 100, 100, 50);
		add(rdoA);
		add(rdoB);

		String[] routineA = { "Squat      ", "Bench Press", "Barbell Row" };
		String[] routineB = { "Squat      ", "Oh Press  ", "Deadlift    " };

		JButton rtAbttn[] = new JButton[routineA.length];
		JButton rtBbttn[] = new JButton[routineB.length];

		ImageIcon[] rtAimage = { new ImageIcon("squat2.png"), new ImageIcon("benchpress2.png"),
				new ImageIcon("barbellrow2.png") };

		ImageIcon[] rtBimage = { new ImageIcon("squat2.png"), new ImageIcon("Oh Press2.jpg"),
				new ImageIcon("Deadlift2.png") };

		// 횟수, 중량 입력
		Button minusBtA[] = new Button[routineA.length];
		Button plusBtA[] = new Button[routineA.length];
		TextField countA[] = new TextField[routineA.length];
		Label kgprintA[] = new Label[routineA.length];
		TextField kgA[] = new TextField[routineA.length];
		int setA[] = { 1, 1, 1 };

		Button minusBtB[] = new Button[routineB.length];
		Button plusBtB[] = new Button[routineB.length];
		TextField countB[] = new TextField[routineB.length];
		Label kgprintB[] = new Label[routineB.length];
		TextField kgB[] = new TextField[routineB.length];
		int setB[] = { 1, 1, 1 };

		// 날짜 표시
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
		Calendar time = Calendar.getInstance();
		String time1 = date.format(time.getTime());
		Label today = new Label("날짜 : " + time1);
		TextArea textArea = new TextArea("", 0, 0, 0);

		// A루틴 버튼 위치
		for (int i = 0; i < routineA.length; i++) {
			rtAbttn[i] = new JButton(routineA[i], rtAimage[i]);
			rtAbttn[i].setBounds(30, 200 + (i * 130), 150, 100);
			add(rtAbttn[i]);
			rtAbttn[i].setEnabled(false);

			plusBtA[i] = new Button("+");
			add(plusBtA[i]);
			plusBtA[i].setBounds(200, 200 + (i * 130), 20, 20);
			plusBtA[i].setEnabled(false);

			countA[i] = new TextField("5");
			countA[i].setBounds(200, 240 + (i * 130), 20, 20);
			add(countA[i]);
			countA[i].setEnabled(false);

			minusBtA[i] = new Button("-");
			minusBtA[i].setBounds(200, 280 + (i * 130), 20, 20);
			add(minusBtA[i]);
			minusBtA[i].setEnabled(false);

			kgprintA[i] = new Label("중량");
			kgprintA[i].setBounds(230, 200 + (i * 130), 30, 20);
			add(kgprintA[i]);
			kgprintA[i].setEnabled(false);

			kgA[i] = new TextField("20");
			kgA[i].setBounds(230, 240 + (i * 130), 30, 20);
			add(kgA[i]);
			kgA[i].setEnabled(false);
		}
		// B루틴 버튼 위치
		for (int i = 0; i < routineB.length; i++) {
			rtBbttn[i] = new JButton(routineB[i], rtBimage[i]);
			rtBbttn[i].setBounds(300, 200 + (i * 130), 150, 100);
			add(rtBbttn[i]);
			rtBbttn[i].setEnabled(false);

			plusBtB[i] = new Button("+");
			add(plusBtB[i]);
			plusBtB[i].setBounds(470, 200 + (i * 130), 20, 20);
			plusBtB[i].setEnabled(false);

			countB[i] = new TextField("5");
			countB[i].setBounds(470, 240 + (i * 130), 20, 20);
			add(countB[i]);
			countB[i].setEnabled(false);

			minusBtB[i] = new Button("-");
			minusBtB[i].setBounds(470, 280 + (i * 130), 20, 20);
			add(minusBtB[i]);
			minusBtB[i].setEnabled(false);

			kgprintB[i] = new Label("중량");
			kgprintB[i].setBounds(500, 200 + (i * 130), 30, 20);
			add(kgprintB[i]);
			kgprintB[i].setEnabled(false);

			kgB[i] = new TextField("20");
			kgB[i].setBounds(500, 240 + (i * 130), 30, 20);
			add(kgB[i]);
			kgB[i].setEnabled(false);
		}
		textArea.setText("날짜		운동명		횟수\t세트\t중량\n\n");
		textArea.setBounds(30, 600, 460, 200);
		textArea.setBackground(Color.white);
		textArea.setEditable(false);
		add(textArea);

		// 완료버튼
		JButton btnComplete = new JButton("완료");
		btnComplete.setBounds(490, 600, 80, 200);
		add(btnComplete);

		btnComplete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FrameRecord 열기
//				FrameBase.getInstance(new FrameComplete());

				String que = "INSERT INTO TRAINNING(MEM_ID,TRA_DATE,TRA_ROUTINE,TRA_EXECISE,TRA_WEIGHT,TRA_SET,TRA_REPEAT,TRA_ID) "
						+ "VALUES('mem1',?, ?, ?, ?, ?, ?, null)";

				System.out.println(time1);
				
				if (rt == "A") {
					for (int i = 0; i < routineA.length; i++) {

						try {
							conn = DBconfig.get();
							pstm = conn.prepareStatement(que);

							String tra_date = time1;
							System.out.println(tra_date);
							pstm.setString(1,tra_date);

							String tra_routine = rt;
							pstm.setString(2, tra_routine);

							String tra_execise = routineA[i];
							pstm.setString(3, tra_execise);

							String tra_weight = kgA[i].getText();
							pstm.setString(4, tra_weight);

							int tra_set = setA[i] - 1;
							pstm.setInt(5, tra_set);

							int tra_repeat = Integer.parseInt(countA[i].getText());
							pstm.setInt(6, tra_repeat);
							
							pstm.executeUpdate();

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					for (int i = 0; i < routineB.length; i++) {
						try {
							conn = DBconfig.get();
							pstm = conn.prepareStatement(que);
							
							String tra_date = time1;
							System.out.println(tra_date);
							pstm.setString(1,tra_date);

							String tra_routine = rt;
							pstm.setString(2, tra_routine);


							String tra_execise = routineB[i];
							pstm.setString(3, tra_execise);

							String tra_weight = kgB[i].getText();
							pstm.setString(4, tra_weight);

							int tra_set = setB[i] - 1;
							pstm.setInt(5, tra_set);

							int tra_repeat = Integer.parseInt(countB[i].getText());
							pstm.setInt(6, tra_repeat);
							
							pstm.executeUpdate();

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
				JOptionPane.showMessageDialog(null, textArea.getText() + "저장이 완료되었습니다.");
			}
		});

		// A 루틴(라디오버튼) 선택 버튼 액션
		rdoA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				if (e.getStateChange() == ItemEvent.DESELECTED) {
					for (int i = 0; i < routineA.length; i++) {
						rtAbttn[i].setEnabled(false);
						plusBtA[i].setEnabled(false);
						minusBtA[i].setEnabled(false);
						countA[i].setEnabled(false);
						kgprintA[i].setEnabled(false);
						kgA[i].setEnabled(false);

					}
					for (int i = 0; i < routineB.length; i++) {
						rtBbttn[i].setEnabled(false);
						plusBtB[i].setEnabled(false);
						minusBtB[i].setEnabled(false);
						countB[i].setEnabled(false);
						kgprintB[i].setEnabled(false);
						kgB[i].setEnabled(false);
					}
				}

				if (rdoA.isSelected()) {

					// rdoA 선택 액션
					rt = "A";
					// 루틴A 버튼 출력 : Squat / Bench Press / Barbell Row 를 표시한다.
					for (int i = 0; i < routineA.length; i++) {
						rtAbttn[i].setEnabled(true);
						plusBtA[i].setEnabled(true);
						minusBtA[i].setEnabled(true);
						countA[i].setEnabled(true);
						kgprintA[i].setEnabled(true);
						kgA[i].setEnabled(true);
						int j = i;

						// 빼기 :
						minusBtA[i].addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (count > 0) {
									count = count - 1;
									countA[j].setText(count + "");
								}
							}
						});
						// 더하기
						plusBtA[i].addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (count < 5) {
									count = count + 1;
									countA[j].setText(count + "");
								}

							}
						});

						// 운동 버튼 이벤트
						rtAbttn[i].addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {

								if (setA[j] >= 5) {
									minusBtA[j].setEnabled(false);
									plusBtA[j].setEnabled(false);
									rtAbttn[j].setEnabled(false);
									countA[j].setEnabled(false);
									kgprintA[j].setEnabled(false);
									kgA[j].setEnabled(false);
								}

								// 중량 입력

//    		                	String show ="";
								show = rtAbttn[j].getActionCommand();
//    		                	textArea.setText("날짜		운동명			횟수		set	\n\n");
								textArea.append(time1 + "	" + routineA[j] + "	" + count + "	 " + setA[j] + "	"
										+ kgA[j].getText() + "\n");
								setA[j]++;
							}

						});
					}
				}

			}

		});

		// B 루틴 선택 버튼 액션
		rdoB.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					for (int i = 0; i < routineA.length; i++) {
						rtAbttn[i].setEnabled(false);
						plusBtA[i].setEnabled(false);
						minusBtA[i].setEnabled(false);
						countA[i].setEnabled(false);
						kgprintA[i].setEnabled(false);
						kgA[i].setEnabled(false);

					}
					for (int i = 0; i < routineB.length; i++) {
						rtBbttn[i].setEnabled(false);
						plusBtB[i].setEnabled(false);
						minusBtB[i].setEnabled(false);
						countB[i].setEnabled(false);
						kgprintB[i].setEnabled(false);
						kgB[i].setEnabled(false);
					}
				}
				if (rdoB.isSelected()) {
					// rdoB 선택 액션
					rt = "B";
					// 루틴B 버튼 출력 : Squat / Oh Press / Deadlift 를 표시하고
					for (int i = 0; i < routineB.length; i++) {
						rtBbttn[i].setEnabled(true);
						plusBtB[i].setEnabled(true);
						minusBtB[i].setEnabled(true);
						countB[i].setEnabled(true);
						kgprintB[i].setEnabled(true);
						kgB[i].setEnabled(true);

						int j = i; // 각운동 세트에서 횟수(빼기, 더하기)를 카운트하기 위해 사용
						// i만 쓰면 어떤 운동이든 총 5세트 뿐이 수행못함.
						// j에 i를 대입해서 각운동에서 횟수(j)를 빼서 저장함.

						// 빼기 :
						minusBtB[i].addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (count > 0) {
									count = count - 1;
									countB[j].setText(count + "");
								}
							}
						});
						// 더하기
						plusBtB[i].addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (count < 5) {
									count = count + 1;
									countB[j].setText(count + "");
								}

							}
						});
						// 운동 버튼 이벤트
						rtBbttn[i].addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {

								if (setB[j] >= 5) {
									minusBtB[j].setEnabled(false);
									plusBtB[j].setEnabled(false);
									rtBbttn[j].setEnabled(false);
									countB[j].setEnabled(false);
									kgprintB[j].setEnabled(false);
									kgB[j].setEnabled(false);
								}

								// 중량 입력
								show = rtBbttn[j].getActionCommand();
//    		                	textArea.setText("날짜		운동명			횟수		set	\n\n");
								textArea.append(time1 + "	" + routineB[j] + "	" + count + "	 " + setB[j] + "	"
										+ kgB[j].getText() + "\n");
								setB[j]++;
							}
						});
					}
				}
			}
		});

	}

}
