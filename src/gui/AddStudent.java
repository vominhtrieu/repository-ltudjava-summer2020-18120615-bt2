package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import pojo.SinhVien;
import jdbc.*;

public class AddStudent {
	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Thêm Sinh Viên");
		Container container = mainFrame.getContentPane();
		container.setLayout(new GridLayout(12, 1));
		
		Dimension preferredSize = new Dimension(200, 30);
		
		JLabel idLabel = new JLabel("MSSV: ");
		JTextField idTextField = new JTextField();
		idTextField.setPreferredSize(preferredSize);
		
		JLabel nameLabel = new JLabel("Họ tên: ");
		JTextField nameTextField = new JTextField();
		nameTextField.setPreferredSize(preferredSize);
		
		JLabel genderLabel = new JLabel("Giới tính: ");
		JTextField genderTextField = new JTextField();
		genderTextField.setPreferredSize(preferredSize);
		
		JLabel cmndLabel = new JLabel("CMND: ");
		JTextField cmndTextField = new JTextField();
		cmndTextField.setPreferredSize(preferredSize);
		
		JLabel classLabel = new JLabel("Lớp: ");
		JTextField classTextField = new JTextField();
		classTextField.setPreferredSize(preferredSize);
		
		JButton submitBtn = new JButton("Thêm");
		submitBtn.setPreferredSize(preferredSize);
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText();
				String name = nameTextField.getText();
				String gender = genderTextField.getText();
				String cmnd = cmndTextField.getText();
				String classId = classTextField.getText();
				
				if (id.length() == 0 || name.length() == 0 || gender.length() == 0
						|| gender.length() == 0 || cmnd.length() == 0 || classId.length() == 0) {
					JOptionPane.showMessageDialog(mainFrame, "Chưa nhập đủ thông tin");
					return;
				}
				
				try {
					SinhVienDAO.Insert(Integer.parseInt(id), classId, name, gender, cmnd);
					JOptionPane.showMessageDialog(mainFrame, "Nhập dữ liệu thành công");
					mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(mainFrame, "Thông tin không hợp lệ. Lỗi: " + ex.getMessage());
				}
			}
		});
		
		container.add(idLabel);
		container.add(idTextField);
		container.add(nameLabel);
		container.add(nameTextField);
		container.add(genderLabel);
		container.add(genderTextField);
		container.add(cmndLabel);
		container.add(cmndTextField);
		container.add(classLabel);
		container.add(classTextField);
		container.add(submitBtn);
		
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}
