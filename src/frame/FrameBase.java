package frame;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FrameBase extends JFrame {

	private static FrameBase instance;

	private FrameBase(JPanel e) {
		// 시스템 정보(화면 크기를 얻기 위한 Toolkit)
		Toolkit tk = Toolkit.getDefaultToolkit();
		// 기본 JFrame 구조
		setTitle("Light Weight Baby!");
		setLayout(new BorderLayout());
		
		
		
		
		setBounds(((int) tk.getScreenSize().getWidth()) / 2 - 300,	// 전체 화면 가로 크기/2 -300px 의 시작 x좌표
				((int) tk.getScreenSize().getHeight()) / 2 - 500,	// 전체 화면 세로 크기/2 -400px 의 시작 y좌표
				600, 900);	// 가로 600px, 세로 800px
		add(e);				// JPanel e를 호출
		setVisible(true);	// 화면 표시
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		// 프레임이 종료되면 어플리케이션을 종료한다.
		
	}
	// 생성자
	// 싱글톤 기법을 사용하려고 한다

	public static void getInstance(JPanel e) {
		// static으로 선언했으므로 해당 메서드가 생성자보다도 먼저 호출된다
		instance = new FrameBase(e);// 생성자를 통해 기본 프레임 정의
		instance.getContentPane().removeAll();
		instance.getContentPane().add(e);
		instance.revalidate(); // 레이아웃 관리자에게 레이아웃정보를 다시 계산하도록 지시
		instance.repaint(); // 레이아웃을 새로 그린다
	}// getInstance()
}
