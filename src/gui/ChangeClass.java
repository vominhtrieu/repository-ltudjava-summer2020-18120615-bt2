package gui;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jdbc.MonHocDAO;
import pojo.*;

public class ChangeClass {
	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Đổi lớp");
		Container container = mainFrame.getContentPane();
		container.setLayout(new GridLayout(3, 1));
		
		JLabel idLabel = new JLabel("Nhập MSSV để tìm:");
		JTextField idTextField = new JTextField();
		JButton searchBtn = new JButton("Tìm kiếm");
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					List<MonHoc> dsMonHoc = MonHocDAO.GetList(Integer.parseInt(idTextField.getText()));
					for (MonHoc monHoc : dsMonHoc) {
						System.out.println(monHoc.getMaMon() + " " + monHoc.getMaLop());
					}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(mainFrame,
							"Không lấy được danh sách môn học sinh viên này đăng ký");
				}
			}
		});
		searchBtn.setPreferredSize(new Dimension(200, 30));
		
		container.add(idLabel);
		container.add(idTextField);
		container.add(searchBtn);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}
