package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jdbc.BangDiemDAO;
import pojo.BangDiem;

public class EditScore {
	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Chỉnh sửa điểm");
		Container container = mainFrame.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		JLabel idLabel = new JLabel("Nhập MSSV");
		JTextField idTextField = new JTextField();
		idTextField.setPreferredSize(new Dimension(200, 30));
		JLabel classIdLabel = new JLabel("Nhập mã lớp");
		JTextField classIdTextField = new JTextField();
		classIdTextField.setPreferredSize(new Dimension(200, 30));
		JLabel subjectIdLabel = new JLabel("Nhập mã môn");
		JTextField subjectIdTextField = new JTextField();
		subjectIdTextField.setPreferredSize(new Dimension(200, 30));
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
					String mssv = idTextField.getText();
					String classId = classIdTextField.getText();
					String subjectId = subjectIdTextField.getText();

					BangDiem bangDiem = BangDiemDAO.get(mssv, classId, subjectId);
					DefaultTableModel model = new DefaultTableModel(0, 0);
					String[] headers = new String[] { "Điểm GK", "Điểm CK", "Điểm Khác", "Điểm tổng" };
					model.setColumnIdentifiers(headers);
					table.setModel(model);

					model.addRow(new Object[] { bangDiem.getDiemGk(), bangDiem.getDiemCk(), bangDiem.getDiemKhac(),
							bangDiem.getDiemTong() });
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy lớp này");
				}
			}
		});

		JButton submitBtn = new JButton("Xác nhận đổi điểm");
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String mssv = idTextField.getText();
					String maLop = classIdTextField.getText();
					String maMon = subjectIdTextField.getText();
					double diemGk = Double.parseDouble(table.getValueAt(0, 0).toString());
					double diemCk = Double.parseDouble(table.getValueAt(0, 1).toString());
					double diemKhac = Double.parseDouble(table.getValueAt(0, 2).toString());
					double diemTong = Double.parseDouble(table.getValueAt(0, 3).toString());
					BangDiemDAO.edit(mssv, maLop, maMon, diemGk, diemCk, diemKhac, diemTong);
					JOptionPane.showMessageDialog(null, "Chỉnh sửa điểm hoàn tất");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Lựa chọn không hợp lệ");
				}
			}
		});

		container.add(idLabel);
		container.add(idTextField);
		container.add(classIdLabel);
		container.add(classIdTextField);
		container.add(subjectIdLabel);
		container.add(subjectIdTextField);
		container.add(searchBtn);
		container.add(scrollPane);
		container.add(submitBtn);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}
