package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import jdbc.*;


public class ImportStudent {
	private File selectedFile;
	
	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Nhập danh sách lớp");
		mainFrame.setLocation(MouseInfo.getPointerInfo().getLocation());
		Container container = mainFrame.getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("Nhập tên lớp");
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(250, 30));
		
		JButton selectFileBtn = new JButton("Chọn file");

		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
		fileChooser.setFileFilter(filter);
		selectedFile = null;
		
		selectFileBtn.setPreferredSize(new Dimension(250, 30));
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
		submitBtn.setPreferredSize(new Dimension(250, 30));
		submitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedFile == null)
					return;
				BufferedReader reader;
				try {
					reader = new BufferedReader(
							new InputStreamReader(new FileInputStream(selectedFile), "UTF-8"));
				
					String line;
					while ((line = reader.readLine()) != null && line.length() != 0) {
						String[] values = line.split(",");
						SinhVienDAO.Insert(Integer.parseInt(values[0].trim()),
								Integer.parseInt(values[1].trim()), values[2].trim(),
								values[3].trim(), values[4].trim(), textField.getText());
					}
					JOptionPane.showMessageDialog(mainFrame, "Nhập dữ liệu thành công");
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, "Nhập dữ liệu thất bại");
				}
				mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
			}
		});
		container.add(label);
		container.add(textField);
		container.add(selectFileBtn);
		container.add(submitBtn);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
}