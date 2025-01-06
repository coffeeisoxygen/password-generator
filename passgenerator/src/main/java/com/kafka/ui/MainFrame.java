package com.kafka.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

    private JTextField passwordInput;
    private JTextArea resultArea;
    private JTextField generatedPasswordField;

    public MainFrame() {
        setTitle("Password Generator and Checker");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3, 1, 10, 10));
        leftPanel.setPreferredSize(new Dimension(250, 600));
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Password Analyzer Section
        JPanel analyzerPanel = new JPanel();
        analyzerPanel.setLayout(new GridLayout(3, 1, 5, 5));
        passwordInput = new JTextField(15);
        JButton analyzeButton = new JButton("Analyze Password");
        analyzeButton.addActionListener(new AnalyzePasswordAction());
        analyzerPanel.add(new JLabel("Password Analyzer:"));
        analyzerPanel.add(passwordInput);
        analyzerPanel.add(analyzeButton);
        leftPanel.add(analyzerPanel);

        // Password Generator Section
        JPanel generatorPanel = new JPanel();
        generatorPanel.setLayout(new GridLayout(3, 1, 5, 5));
        JButton generateButton = new JButton("Generate Password");
        generateButton.addActionListener(new GeneratePasswordAction());
        generatedPasswordField = new JTextField(15);
        generatedPasswordField.setEditable(false);
        generatorPanel.add(new JLabel("Password Generator:"));
        generatorPanel.add(generateButton);
        generatorPanel.add(generatedPasswordField);
        leftPanel.add(generatorPanel);

        add(leftPanel, BorderLayout.WEST);

        // Middle Panel
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(new JLabel("Right Panel (expandable)"), BorderLayout.NORTH);

        // Split Pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, rightPanel);
        splitPane.setDividerLocation(500);
        add(splitPane, BorderLayout.CENTER);
    }

    private class AnalyzePasswordAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String password = passwordInput.getText();
            StringBuilder analysis = new StringBuilder();
            analysis.append("Password Analysis:\n");
            analysis.append("Length: ").append(password.length()).append("\n");
            analysis.append("Contains Uppercase: ").append(password.chars().anyMatch(Character::isUpperCase)).append("\n");
            analysis.append("Contains Lowercase: ").append(password.chars().anyMatch(Character::isLowerCase)).append("\n");
            analysis.append("Contains Digits: ").append(password.chars().anyMatch(Character::isDigit)).append("\n");
            analysis.append("Contains Special Characters: ").append(password.chars().anyMatch(ch -> "!@#$%^&*()_+[]{}|;':\",.<>?/`~".indexOf(ch) >= 0)).append("\n");
            resultArea.setText(analysis.toString());
        }
    }

    private class GeneratePasswordAction implements ActionListener {

        private final SecureRandom random = new SecureRandom();
        private final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < 12; i++) { // Generate a 12-character password
                int index = random.nextInt(characters.length());
                password.append(characters.charAt(index));
            }
            generatedPasswordField.setText(password.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame app = new MainFrame();
            app.setVisible(true);
        });
    }
}
