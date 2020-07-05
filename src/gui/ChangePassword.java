package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import jdbc.TaiKhoanDAO;
import pojo.TaiKhoan;

public class ChangePassword {
	String id;

	public ChangePassword(String id) {
		this.id = id;
	}

	public void createAndShowGUI() {
		JFrame mainFrame = new JFrame("Đổi mật khẩu");
		Container container = mainFrame.getContentPane();
		container.setLayout(new GridLayout(8, 1));

		Dimension preferredSize = new Dimension(200, 30);

		JLabel currentPassLabel = new JLabel("Mật khẩu hiện tại: ");
		JTextField currentPassTextField = new JTextField();
		currentPassTextField.setPreferredSize(preferredSize);

		JLabel newPassLabel = new JLabel("Mật khẩu mới: ");
		JTextField newPassTextField = new JTextField();
		newPassTextField.setPreferredSize(preferredSize);

		JLabel reenterPassLabel = new JLabel("Nhập lại mật khẩu: ");
		JTextField reenterPassTextField = new JTextField();
		reenterPassTextField.setPreferredSize(preferredSize);

		JButton submitBtn = new JButton("Xác nhận");
		submitBtn.setPreferredSize(preferredSize);

		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String currentPass = currentPassTextField.getText();
				String newPass = newPassTextField.getText();
				String reenterPass = reenterPassTextField.getText();
				if (!newPass.equals(reenterPass)) {
					JOptionPane.showMessageDialog(null, "Mật khẩu không khớp");
					return;
				}

				TaiKhoan tk = TaiKhoanDAO.get(id);

				if (!tk.getMatKhau().equals(currentPass)) {
					JOptionPane.showMessageDialog(null, "Mật khẩu không chính xác");
					return;
				}

				try {
					TaiKhoanDAO.changePassword(tk.getTenDangNhap(), newPass);
					JOptionPane.showMessageDialog(mainFrame, "Đổi mật khẩu thành công");
					mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(mainFrame, "Thông tin không hợp lệ");
				}
			}
		});

		container.add(currentPassLabel);
		container.add(currentPassTextField);
		container.add(newPassLabel);
		container.add(newPassTextField);
		container.add(reenterPassLabel);
		container.add(reenterPassTextField);
		container.add(submitBtn);

		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}
