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

import jdbc.MonHocDAO;
import pojo.MonHoc;

public class ViewTimetable {
	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Xem thời khóa biểu");
		Container container = mainFrame.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		JLabel classIdLabel = new JLabel("Nhập mã lớp");
		JTextField classIdTextField = new JTextField();
		classIdTextField.setPreferredSize(new Dimension(200, 30));
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
					String classId = classIdTextField.getText();

					List<MonHoc> dsMonHoc;

					dsMonHoc = MonHocDAO.getListByClassID(classId);

					DefaultTableModel model = new DefaultTableModel(0, 0);
					String[] headers = new String[] { "Mã môn", "Tên môn", "Phòng học" };
					model.setColumnIdentifiers(headers);
					table.setModel(model);
					for (MonHoc monHoc : dsMonHoc) {
						model.addRow(new Object[] { monHoc.getMaMon(), monHoc.getTenMon(), monHoc.getPhongHoc() });
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên trong lớp");
				}
			}
		});

		container.add(classIdLabel);
		container.add(classIdTextField);
		container.add(searchBtn);
		container.add(scrollPane);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}
