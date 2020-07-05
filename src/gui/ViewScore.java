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

import jdbc.BangDiemDAO;
import pojo.BangDiem;
import pojo.SinhVien;

public class ViewScore {
	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Xem danh sách lớp");
		Container container = mainFrame.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

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

		JLabel passCountLabel = new JLabel("Số lượng đậu: ");
		JLabel failCountLabel = new JLabel("Số lượng rớt: ");
		JLabel passPercentLabel = new JLabel("Tỉ lệ đậu: ");
		JLabel failPercentLabel = new JLabel("Tỉ lệ rớt: ");

		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String classId = classIdTextField.getText();
					String subjectId = subjectIdTextField.getText();

					List<BangDiem> dsBangDiem = BangDiemDAO.getList(classId, subjectId);
					DefaultTableModel model = new DefaultTableModel(0, 0);
					String[] headers = new String[] { "MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm Khác",
							"Điểm tổng" };
					model.setColumnIdentifiers(headers);
					table.setModel(model);

					int soLuongDau = 0;
					for (BangDiem bangDiem : dsBangDiem) {
						SinhVien sinhVien = bangDiem.getSinhVien();
						if (bangDiem.getDiemTong() >= 5)
							soLuongDau++;
						model.addRow(new Object[] { sinhVien.getMssv(), sinhVien.getHoTen(), bangDiem.getDiemGk(),
								bangDiem.getDiemCk(), bangDiem.getDiemKhac(), bangDiem.getDiemTong() });
					}
					int soLuongRot = dsBangDiem.size() - soLuongDau;
					double tiLeDau = soLuongDau * 100.0 / dsBangDiem.size();
					double tiLeRot = 100 - tiLeDau;
					passCountLabel.setText("Số lượng đậu: " + Integer.toString(soLuongDau));
					failCountLabel.setText("Số lượng rớt: " + Integer.toString(soLuongRot));
					passPercentLabel.setText("Tỉ lệ đậu: " + Double.toString(tiLeDau));
					failPercentLabel.setText("Tỉ lệ rớt: " + Double.toString(tiLeRot));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy lớp này");
				}
			}
		});

		container.add(classIdLabel);
		container.add(classIdTextField);
		container.add(subjectIdLabel);
		container.add(subjectIdTextField);
		container.add(searchBtn);
		container.add(scrollPane);
		container.add(passCountLabel);
		container.add(failCountLabel);
		container.add(passPercentLabel);
		container.add(failPercentLabel);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}
