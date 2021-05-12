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
		//JPanel ����
		setBackground(Color.white);
		setLayout(null);
		setSize(600, 800);
		
		// ���
		ImageIcon background = new ImageIcon("background.png");
		JLabel lblBackground = new JLabel(background);
		lblBackground.setSize(600, 900);
//		lblBackground.setLocation(-10, (int) lbTitle.getLocation().getY()+30);
		add(lblBackground);
	

		
		// ���α׷� ����
		Label lbTitle = new Label("� ��� ���α׷�");
		lbTitle.setBounds(30 , 0 , 200, 50);
		lbTitle.setFont(new Font("����", Font.BOLD, 20));
		
		// Ȯ�� ��ư
		JButton btnAccess = new JButton("�� ��");
		btnAccess.setBounds( 250, 0 , 80, 50);
		

		lblBackground.add(lbTitle);
		lblBackground.add(btnAccess);

				
		// Ȯ�� ��ư �׼�
		btnAccess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FrameRecord ����
				FrameBase.getInstance(new FrameRecord());
			}
		});
		
	}// ������
}









