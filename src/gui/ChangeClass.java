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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import jdbc.MonHocDAO;
import pojo.MonHoc;

class OldTableActionListener implements ListSelectionListener {
	private JTable oldTable;
	private JTable newTable;

	public OldTableActionListener(JTable oldTable, JTable newTable) {
		this.oldTable = oldTable;
		this.newTable = newTable;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			if (oldTable.getSelectedRow() < 0)
				return;
			List<MonHoc> dsMonHoc2 = MonHocDAO
					.getListBySubjectID(oldTable.getValueAt(oldTable.getSelectedRow(), 0).toString());

			DefaultTableModel model = new DefaultTableModel(0, 0);
			String[] headers = new String[] { "Mã Môn học", "Mã Lớp", "Tên Môn học", "Phòng học" };

			model.setColumnIdentifiers(headers);
			newTable.setModel(model);
			for (MonHoc monHoc : dsMonHoc2) {
				model.addRow(new Object[] { monHoc.getMaMon(), monHoc.getMaLop(), monHoc.getTenMon(),
						monHoc.getPhongHoc() });
			}
		} catch (Exception ex2) {
			JOptionPane.showMessageDialog(null, ex2.getMessage());
		}
	}
}

class FindSubjectsActionListener implements ActionListener {
	private JTable oldTable;
	private JTable newTable;
	private JTextField idTextField;

	public FindSubjectsActionListener(JTable oldTable, JTable newTable, JTextField idTextField) {
		this.oldTable = oldTable;
		this.newTable = newTable;
		this.idTextField = idTextField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			List<MonHoc> dsMonHoc = MonHocDAO.getListByID(idTextField.getText().trim());
			if (dsMonHoc == null || dsMonHoc.size() == 0) {
				JOptionPane.showMessageDialog(null, "Sinh viên này chưa đăng ký môn");
			} else {
				newTable.setModel(new DefaultTableModel(0, 0));

				DefaultTableModel model = new DefaultTableModel(0, 0);
				String[] headers = new String[] { "Mã Môn học", "Mã Lớp", "Tên Môn học", "Phòng học" };
				model.setColumnIdentifiers(headers);
				oldTable.setModel(model);
				oldTable.getSelectionModel().addListSelectionListener(new OldTableActionListener(oldTable, newTable));

				for (MonHoc monHoc : dsMonHoc) {
					model.addRow(new Object[] { monHoc.getMaMon(), monHoc.getMaLop(), monHoc.getTenMon(),
							monHoc.getPhongHoc() });
				}
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Không lấy được danh sách môn học sinh viên này đăng ký");
		}
	}
}

class SubmitActionListener implements ActionListener {
	private JTextField idTextField;
	private JTable oldTable, newTable;

	public SubmitActionListener(JTextField idTextField, JTable oldTable, JTable newTable) {
		this.idTextField = idTextField;
		this.oldTable = oldTable;
		this.newTable = newTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String mssv = idTextField.getText();
			String maMon = oldTable.getValueAt(oldTable.getSelectedRow(), 0).toString();
			String maLopCu = oldTable.getValueAt(oldTable.getSelectedRow(), 1).toString();
			String maLopMoi = newTable.getValueAt(newTable.getSelectedRow(), 1).toString();
			MonHocDAO.ChangeClass(mssv, maMon, maLopCu, maLopMoi);
			JOptionPane.showMessageDialog(null, "Đã chuyển từ lớp " + maLopCu + " sang " + maLopMoi);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Lựa chọn không hợp lệ");
		}
	}
}

public class ChangeClass {
	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Đổi lớp");
		Container container = mainFrame.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		JLabel idLabel = new JLabel("Nhập MSSV để tìm:");
		JTextField idTextField = new JTextField();
		idTextField.setPreferredSize(new Dimension(200, 30));

		JButton searchBtn = new JButton("Tìm kiếm");
		searchBtn.setPreferredSize(new Dimension(200, 30));

		JLabel oldSubjectsLabel = new JLabel("Chọn 1 môn để đổi lớp");
		JScrollPane oldScrollPane = new JScrollPane();
		oldScrollPane.setPreferredSize(new Dimension(200, 150));

		JTable oldTable = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		oldScrollPane.setViewportView(oldTable);

		JLabel newSubjectsLabel = new JLabel("Chọn lớp mới");
		JScrollPane newScrollPane = new JScrollPane();
		newScrollPane.setPreferredSize(new Dimension(200, 150));

		JTable newTable = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		newScrollPane.setViewportView(newTable);

		searchBtn.addActionListener(new FindSubjectsActionListener(oldTable, newTable, idTextField));

		JButton submitBtn = new JButton("Xác nhận đổi lớp");
		submitBtn.setPreferredSize(new Dimension(200, 30));
		submitBtn.addActionListener(new SubmitActionListener(idTextField, oldTable, newTable));

		container.add(idLabel);
		container.add(idTextField);
		container.add(searchBtn);
		container.add(oldSubjectsLabel);
		container.add(oldScrollPane);
		container.add(newSubjectsLabel);
		container.add(newScrollPane);
		container.add(submitBtn);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}
