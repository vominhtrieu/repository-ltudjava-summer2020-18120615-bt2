package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class Admin {
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Giáo viên");
		JPanel mainPanel = new JPanel();

		GridLayout layout = new GridLayout(10, 1);

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

		JButton importScores = new JButton("Nhập bảng điểm");
		importScores.setPreferredSize(preferredSize);

		JButton viewScores = new JButton("Xem bảng điểm");
		viewScores.setPreferredSize(preferredSize);

		JButton editScore = new JButton("Chỉnh sửa điểm");
		editScore.setPreferredSize(preferredSize);

		JButton changePassword = new JButton("Đổi mật khẩu");
		changePassword.setPreferredSize(preferredSize);

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

		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
