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

import jdbc.MonHocDAO;

public class ImportTimeTable {
	private File selectedFile;

	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Nhập thời khóa biểu");
		Container container = mainFrame.getContentPane();
		container.setLayout(new GridLayout(5, 1, 0, 5));

		Dimension preferredSize = new Dimension(200, 30);
		JLabel label = new JLabel("Nhập tên lớp");
		JTextField textField = new JTextField();
		textField.setPreferredSize(preferredSize);

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
				if (textField.getText().length() == 0) {
					JOptionPane.showMessageDialog(mainFrame, "Chưa nhập tên lớp");
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
						if (values.length != 3) {
							JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ");
							return;
						} else
							MonHocDAO.Insert(values[0].trim(), textField.getText(), values[1].trim(), values[2].trim());
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

		container.add(label);
		container.add(textField);
		container.add(selectFileBtn);
		container.add(submitBtn);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}