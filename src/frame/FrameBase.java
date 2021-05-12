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
		// �ý��� ����(ȭ�� ũ�⸦ ��� ���� Toolkit)
		Toolkit tk = Toolkit.getDefaultToolkit();
		// �⺻ JFrame ����
		setTitle("Light Weight Baby!");
		setLayout(new BorderLayout());
		
		
		
		
		setBounds(((int) tk.getScreenSize().getWidth()) / 2 - 300,	// ��ü ȭ�� ���� ũ��/2 -300px �� ���� x��ǥ
				((int) tk.getScreenSize().getHeight()) / 2 - 500,	// ��ü ȭ�� ���� ũ��/2 -400px �� ���� y��ǥ
				600, 900);	// ���� 600px, ���� 800px
		add(e);				// JPanel e�� ȣ��
		setVisible(true);	// ȭ�� ǥ��
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		// �������� ����Ǹ� ���ø����̼��� �����Ѵ�.
		
	}
	// ������
	// �̱��� ����� ����Ϸ��� �Ѵ�

	public static void getInstance(JPanel e) {
		// static���� ���������Ƿ� �ش� �޼��尡 �����ں��ٵ� ���� ȣ��ȴ�
		instance = new FrameBase(e);// �����ڸ� ���� �⺻ ������ ����
		instance.getContentPane().removeAll();
		instance.getContentPane().add(e);
		instance.revalidate(); // ���̾ƿ� �����ڿ��� ���̾ƿ������� �ٽ� ����ϵ��� ����
		instance.repaint(); // ���̾ƿ��� ���� �׸���
	}// getInstance()
}
