package com.example.demo;

import javax.swing.*;
import java.awt.*;

public class BackendSetupForm extends JFrame {

    private JTextField companyNameField;
    private JButton uploadLogoButton;
    private JLabel logoLabel;
    private JRadioButton[] versionRadios; // 假设有多个版本单选按钮
    private JTextArea descriptionArea;
    private JButton submitButton;

    public BackendSetupForm() {
        super("后台设置");

        // 设置标题字体为红色加粗
        setFont(new Font("Arial", Font.BOLD, 18));
        setTitle("后台设置");
        setForeground(Color.RED);

        // 初始化组件
        companyNameField = new JTextField(20);
        companyNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 设置黑色边线

        JLabel companyNameLabel = new JLabel("企业名称:");
        companyNameLabel.setFont(new Font("Arial", Font.BOLD, 14)); // 设置加粗字体

        // 使用JPanel来组织企业名称和输入框在同一行
        JPanel companyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        companyPanel.add(companyNameLabel);
        companyPanel.add(companyNameField);

        // 假设有3个版本单选按钮
        versionRadios = new JRadioButton[3];
        ButtonGroup versionGroup = new ButtonGroup();
        for (int i = 0; i < 3; i++) {
            versionRadios[i] = new JRadioButton("版本" + (i + 1));
            versionGroup.add(versionRadios[i]);
        }

        // 使用JPanel来组织LOGO标签和上传按钮在同一行
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        logoLabel = new JLabel("请上传LOGO文件:");
        uploadLogoButton = new JButton("选择文件");
        logoPanel.add(logoLabel);
        logoPanel.add(uploadLogoButton);

        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // 设置黑色边线
        JScrollPane scrollPane = new JScrollPane(descriptionArea);

        // 提交按钮，蓝底白字
        submitButton = new JButton("确认提交");
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.WHITE);

        // 布局
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); // 使用BorderLayout来组织组件
        mainPanel.add(companyPanel, BorderLayout.NORTH);
        mainPanel.add(logoPanel, BorderLayout.CENTER); // 或者使用其他合适的区域
        mainPanel.add(new JScrollPane(descriptionArea), BorderLayout.BEFORE_LINE_BEGINS); // 假设这是一个新的区域，或者可以使用PAGE_START等
        // 添加版本单选按钮到另一个JPanel中，再添加到mainPanel
        JPanel versionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        for (JRadioButton radio : versionRadios) {
            versionPanel.add(radio);
        }
        mainPanel.add(versionPanel, BorderLayout.SOUTH);
        mainPanel.add(submitButton, BorderLayout.PAGE_END); // 底部

        // 添加panel到JFrame
        add(mainPanel);

        // 设置窗口属性并显示
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(400, 300)); // 根据需要调整大小
        setLocationRelativeTo(null); // 居中显示
        setVisible(true);

        // 添加事件监听器（例如，为提交按钮添加事件监听器）
        submitButton.addActionListener(e -> {
            // TODO: 处理提交逻辑
            JOptionPane.showMessageDialog(this, "提交成功！");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BackendSetupForm();
        });
    }
}