package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ImportClassActionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) { 
		ImportStudent importStudent = new ImportStudent();
		importStudent.createAndShowGUI();
	}
}

public class Admin {
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Giáo viên");
        frame.setLocation(MouseInfo.getPointerInfo().getLocation());
        JPanel mainPanel = new JPanel();

        GridLayout layout = new GridLayout(9, 1);

        mainPanel.setLayout(layout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension preferredSize = new Dimension(200, 30);
        
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv", "text");
        fileChooser.setFileFilter(filter);
        
        JButton importClasses = new JButton("Nhập danh sách lớp(csv)");
        
        importClasses.setPreferredSize(preferredSize);
        importClasses.addActionListener(new ImportClassActionListener());

        JButton addNewStudent = new JButton("Thêm sinh viên: ");
        addNewStudent.setPreferredSize(preferredSize);

        JButton importTimetable = new JButton("Nhập thời khóa biểu");
        importTimetable.setPreferredSize(preferredSize);

        JButton importStudents = new JButton("Xem danh sách lớp");
        importStudents.setPreferredSize(preferredSize);

        JButton viewTimetable = new JButton("Xem thời khóa biểu");
        viewTimetable.setPreferredSize(preferredSize);

        JButton importScores = new JButton("Nhập bảng điểm");
        importStudents.setPreferredSize(preferredSize);

        JButton viewScores = new JButton("Xem bảng điểm");
        viewScores.setPreferredSize(preferredSize);

        JButton editScore = new JButton("Chỉnh sửa điểm");
        editScore.setPreferredSize(preferredSize);

        JButton changePassword = new JButton("Đổi mật khẩu");
        changePassword.setPreferredSize(preferredSize);

        mainPanel.add(importClasses);
        mainPanel.add(addNewStudent);
        mainPanel.add(importTimetable);
        mainPanel.add(importStudents);
        mainPanel.add(viewTimetable);
        mainPanel.add(importScores);
        mainPanel.add(viewScores);
        mainPanel.add(editScore);
        mainPanel.add(changePassword);

        frame.getContentPane().add(mainPanel);
        frame.pack();

        frame.setVisible(true);
    }
}
