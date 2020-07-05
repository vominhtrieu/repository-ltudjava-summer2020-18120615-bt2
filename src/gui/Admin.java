package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ImportClassActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ImportStudent importStudent = new ImportStudent();
		importStudent.createAndShowGUI();
	}
}

class AddStudentActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		AddStudent addStudent = new AddStudent();
		addStudent.createAndShowGUI();
	}
}

class ImportTimeTableActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ImportTimeTable importTimeTable = new ImportTimeTable();
		importTimeTable.createAndShowGUI();
	}
}

class ChangeClassActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ChangeClass changeClass = new ChangeClass();
		changeClass.createAndShowGUI();
	}
}

class ViewClassActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ViewClass viewClass = new ViewClass();
		viewClass.createAndShowGUI();
	}
}

class ViewTimetableActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ViewTimetable viewTimeTable = new ViewTimetable();
		viewTimeTable.createAndShowGUI();
	}
}

class ImportScoreActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		ImportScore importScore = new ImportScore();
		importScore.createAndShowGUI();
	}
}

class ViewScoreActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		ViewScore viewScore = new ViewScore();
		viewScore.createAndShowGUI();
	}
}

class EditScoreActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		EditScore editScore = new EditScore();
		editScore.createAndShowGUI();
	}
}

class LogoutActionListener implements ActionListener {
	JFrame frame;

	public LogoutActionListener(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SignIn signIn = new SignIn();
		signIn.createAndShowGUI();
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}

public class Admin {
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Giáo viên");
		JPanel mainPanel = new JPanel();

		GridLayout layout = new GridLayout(11, 1);

		mainPanel.setLayout(layout);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension preferredSize = new Dimension(200, 30);

		JButton importClasses = new JButton("Nhập danh sách lớp(csv)");

		importClasses.setPreferredSize(preferredSize);
		importClasses.addActionListener(new ImportClassActionListener());

		JButton addNewStudent = new JButton("Thêm sinh viên");
		addNewStudent.setPreferredSize(preferredSize);
		addNewStudent.addActionListener(new AddStudentActionListener());

		JButton importTimetable = new JButton("Nhập thời khóa biểu");
		importTimetable.setPreferredSize(preferredSize);
		importTimetable.addActionListener(new ImportTimeTableActionListener());

		JButton changeClass = new JButton("Đổi lớp");
		changeClass.setPreferredSize(preferredSize);
		changeClass.addActionListener(new ChangeClassActionListener());

		JButton viewClass = new JButton("Xem danh sách lớp");
		viewClass.setPreferredSize(preferredSize);
		viewClass.addActionListener(new ViewClassActionListener());

		JButton viewTimetable = new JButton("Xem thời khóa biểu");
		viewTimetable.setPreferredSize(preferredSize);
		viewTimetable.addActionListener(new ViewTimetableActionListener());

		JButton importScores = new JButton("Nhập bảng điểm");
		importScores.setPreferredSize(preferredSize);
		importScores.addActionListener(new ImportScoreActionListener());

		JButton viewScores = new JButton("Xem bảng điểm");
		viewScores.setPreferredSize(preferredSize);
		viewScores.addActionListener(new ViewScoreActionListener());

		JButton editScore = new JButton("Chỉnh sửa điểm");
		editScore.setPreferredSize(preferredSize);
		editScore.addActionListener(new EditScoreActionListener());

		JButton changePassword = new JButton("Đổi mật khẩu");
		changePassword.setPreferredSize(preferredSize);

		JButton logout = new JButton("Đăng xuất");
		logout.setPreferredSize(preferredSize);
		logout.addActionListener(new LogoutActionListener(frame));

		mainPanel.add(importClasses);
		mainPanel.add(addNewStudent);
		mainPanel.add(importTimetable);
		mainPanel.add(changeClass);
		mainPanel.add(viewClass);
		mainPanel.add(viewTimetable);
		mainPanel.add(importScores);
		mainPanel.add(viewScores);
		mainPanel.add(editScore);
		mainPanel.add(changePassword);
		mainPanel.add(logout);

		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
