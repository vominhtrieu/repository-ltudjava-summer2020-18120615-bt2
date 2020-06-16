package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HandleSignIn implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Admin admin = new Admin();
        admin.createAndShowGUI();
    }
}

public class SignIn {
    public void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame signInFrame = new JFrame("Đăng nhập");
        signInFrame.setLocation(MouseInfo.getPointerInfo().getLocation());
        JPanel mainPanel = new JPanel();

        GridLayout layout = new GridLayout(5, 1);

        mainPanel.setLayout(layout);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));

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
        button.addActionListener(new HandleSignIn());

        mainPanel.add(usernameLabel);
        mainPanel.add(userNameText);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordText);
        mainPanel.add(button);

        signInFrame.getContentPane().add(mainPanel);
        signInFrame.pack();

        signInFrame.setVisible(true);
    }
}
