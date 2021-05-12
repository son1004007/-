package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FrameBegin extends JPanel {
	public FrameBegin() {
		//JPanel 구조
		setBackground(Color.white);
		setLayout(null);
		setSize(600, 800);
		
		// 배경
		ImageIcon background = new ImageIcon("background.png");
		JLabel lblBackground = new JLabel(background);
		lblBackground.setSize(600, 900);
//		lblBackground.setLocation(-10, (int) lbTitle.getLocation().getY()+30);
		add(lblBackground);
	

		
		// 프로그램 제목
		Label lbTitle = new Label("운동 기록 프로그램");
		lbTitle.setBounds(30 , 0 , 200, 50);
		lbTitle.setFont(new Font("굴림", Font.BOLD, 20));
		
		// 확인 버튼
		JButton btnAccess = new JButton("시 작");
		btnAccess.setBounds( 250, 0 , 80, 50);
		

		lblBackground.add(lbTitle);
		lblBackground.add(btnAccess);

				
		// 확인 버튼 액션
		btnAccess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FrameRecord 열기
				FrameBase.getInstance(new FrameRecord());
			}
		});
		
	}// 생성자
}









