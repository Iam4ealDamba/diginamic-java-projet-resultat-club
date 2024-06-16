package fr.iamdamba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JPanel mainPanel;
    private JTextField nameField;
    private JLabel nameLabel;
    private JButton clickMeButton;

    public MainGUI() {
        clickMeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(clickMeButton, "Hello " + nameField.getText());
                }
            }
        });
    }

    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setContentPane(mainGUI.mainPanel);
        mainGUI.setTitle("Introduction Form");
        mainGUI.setResizable(false);
        mainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGUI.setLocationRelativeTo(null);
        mainGUI.setVisible(true);
        mainGUI.pack();
    }
}
