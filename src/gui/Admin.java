package gui;

import javax.swing.*;
import java.awt.*;

public class Admin {
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Giáo vụ");
        frame.setLocation(MouseInfo.getPointerInfo().getLocation());
        JPanel mainPanel = new JPanel();

        GridLayout layout = new GridLayout(9, 1);

        mainPanel.setLayout(layout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension preferredSize = new Dimension(200, 30);

        JButton importClasses = new JButton("Nhập danh sách lớp");
        importClasses.setPreferredSize(preferredSize);

        JButton addNewStudent = new JButton("Thêm sinh viên");
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

        JButton editScore = new JButton("Sửa điểm");
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
