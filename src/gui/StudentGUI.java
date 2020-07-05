package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ViewStudentScoreActionListener implements ActionListener {
	String id;

	public ViewStudentScoreActionListener(String id) {
		this.id = id;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ViewScore viewScore = new ViewScore();
		viewScore.createAndShowGUI(id);
	}
}

public class StudentGUI {
	private String id;

	public StudentGUI(String id) {
		this.id = id;
	}

	public void createAndShowGUI() {
		JFrame frame = new JFrame("Sinh Viên");
		JPanel mainPanel = new JPanel();

		GridLayout layout = new GridLayout(3, 1);

		mainPanel.setLayout(layout);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension preferredSize = new Dimension(200, 30);

		JButton viewScores = new JButton("Xem bảng điểm");
		viewScores.setPreferredSize(preferredSize);
		viewScores.addActionListener(new ViewStudentScoreActionListener(id));

		JButton changePassword = new JButton("Đổi mật khẩu");
		changePassword.setPreferredSize(preferredSize);
		changePassword.addActionListener(new ChangePasswordActionListener(id));

		JButton logout = new JButton("Đăng xuất");
		logout.setPreferredSize(preferredSize);
		logout.addActionListener(new LogoutActionListener(frame));

		mainPanel.add(viewScores);
		mainPanel.add(changePassword);
		mainPanel.add(logout);

		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
