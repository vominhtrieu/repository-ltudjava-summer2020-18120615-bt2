package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import jdbc.BangDiemDAO;

public class ImportScore {
	private File selectedFile;

	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Nhập bảng điểm");
		Container container = mainFrame.getContentPane();
		container.setLayout(new GridLayout(7, 1, 0, 5));

		Dimension preferredSize = new Dimension(200, 30);
		JLabel classIdLabel = new JLabel("Nhập mã lớp");
		JTextField classIdTextField = new JTextField();
		classIdTextField.setPreferredSize(preferredSize);

		JLabel subjectIdLabel = new JLabel("Nhập mã môn");
		JTextField subjectIdTextField = new JTextField();
		subjectIdTextField.setPreferredSize(preferredSize);

		JButton selectFileBtn = new JButton("Chọn file");
		selectFileBtn.setPreferredSize(preferredSize);

		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
		fileChooser.setFileFilter(filter);
		selectedFile = null;
		selectFileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					selectFileBtn.setText("Đã chọn: " + selectedFile.getName());
				}
			}
		});

		JButton submitBtn = new JButton("Nhập dữ liệu");
		submitBtn.setPreferredSize(preferredSize);
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (classIdTextField.getText().length() == 0) {
					JOptionPane.showMessageDialog(mainFrame, "Chưa nhập mã lớp");
					return;
				}
				if (subjectIdTextField.getText().length() == 0) {
					JOptionPane.showMessageDialog(mainFrame, "Chưa nhập mã môn");
					return;
				}
				if (selectedFile == null) {
					JOptionPane.showMessageDialog(mainFrame, "Chưa chọn file để import");
					return;
				}
				BufferedReader reader;
				try {
					reader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), "UTF-8"));

					String line;
					while ((line = reader.readLine()) != null && line.length() != 0) {
						String[] values = line.split(",");
						if (values.length != 5) {
							JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ");
							reader.close();
							return;
						} else {
							String mssv = values[0];
							double diemGk = Double.parseDouble(values[1]);
							double diemCk = Double.parseDouble(values[2]);
							double diemKhac = Double.parseDouble(values[3]);
							double diemTong = Double.parseDouble(values[4]);
							BangDiemDAO.Insert(mssv, subjectIdTextField.getText(), classIdTextField.getText(), diemGk,
									diemCk, diemKhac, diemTong);
						}
					}
					reader.close();
					JOptionPane.showMessageDialog(mainFrame, "Nhập dữ liệu thành công");
					mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(mainFrame, "Nhập dữ liệu thất bại");
				}
			}
		});

		container.add(classIdLabel);
		container.add(classIdTextField);
		container.add(subjectIdLabel);
		container.add(subjectIdTextField);
		container.add(selectFileBtn);
		container.add(submitBtn);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}