package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jdbc.TaiKhoanDAO;
import pojo.TaiKhoan;

public class SignIn {
	public void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame signInFrame = new JFrame("Đăng nhập");
		JPanel mainPanel = new JPanel();

		GridLayout layout = new GridLayout(5, 1);

		mainPanel.setLayout(layout);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

		signInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension preferredSize = new Dimension(200, 30);

		JLabel usernameLabel = new JLabel("Tên đăng nhập: ");
		usernameLabel.setPreferredSize(preferredSize);
		JTextField userNameText = new JTextField();
		userNameText.setPreferredSize(preferredSize);

		JLabel passwordLabel = new JLabel("Mật khẩu: ");
		passwordLabel.setPreferredSize(preferredSize);
		JTextField passwordText = new JTextField();
		passwordText.setPreferredSize(preferredSize);

		JButton button = new JButton("Đăng nhập");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					TaiKhoan tk = TaiKhoanDAO.get(userNameText.getText());
					if (tk.getMatKhau().equals(passwordText.getText())) {
						if (tk.getLoaiTaiKhoan().equals("gv")) {
							Admin admin = new Admin();
							admin.createAndShowGUI();
						} else {

						}
						signInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						signInFrame.dispatchEvent(new WindowEvent(signInFrame, WindowEvent.WINDOW_CLOSING));
						return;
					}
					JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng");
				}
			}
		});

		mainPanel.add(usernameLabel);
		mainPanel.add(userNameText);
		mainPanel.add(passwordLabel);
		mainPanel.add(passwordText);
		mainPanel.add(button);

		signInFrame.getContentPane().add(mainPanel);

		signInFrame.pack();
		signInFrame.setLocationRelativeTo(null);
		signInFrame.setVisible(true);
	}
}
