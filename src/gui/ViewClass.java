package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jdbc.SinhVienDAO;
import pojo.SinhVien;

public class ViewClass {
	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Xem danh sách lớp");
		Container container = mainFrame.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		JLabel label = new JLabel("Nhập mã lớp");
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(200, 30));
		JButton searchBtn = new JButton("Tra cứu");
		searchBtn.setPreferredSize(new Dimension(200, 30));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(200, 150));
		JTable table = new JTable();
		scrollPane.setViewportView(table);

		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					List<SinhVien> dsSinhVien = SinhVienDAO.GetList(textField.getText());
					DefaultTableModel model = new DefaultTableModel(0, 0);
					String[] headers = new String[] { "MSSV", "Mã Lớp", "Họ tên", "Giới tính", "CMND" };
					model.setColumnIdentifiers(headers);
					table.setModel(model);
					for (SinhVien sinhVien : dsSinhVien) {
						model.addRow(new Object[] { sinhVien.getMssv(), sinhVien.getMaLop(), sinhVien.getHoTen(),
								sinhVien.getGioiTinh(), sinhVien.getCmnd() });
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên trong lớp");
				}
			}
		});

		container.add(label);
		container.add(textField);
		container.add(searchBtn);
		container.add(scrollPane);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}
