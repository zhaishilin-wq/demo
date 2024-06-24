package com.example.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CompanyInfoForm extends JFrame {

    private JTextField companyNameField;
    private JTextArea descriptionArea;
    private JRadioButton[] versionButtons;
    private JFileChooser fileChooser;
    private JLabel logoLabel;

    public CompanyInfoForm() {
        super("后台设置界面");

        // 初始化组件
        companyNameField = new JTextField(20);
        descriptionArea = new JTextArea(5, 40);
        companyNameField.setBorder(BorderFactory.createLineBorder(Color.black));
        JScrollPane scrollPane = new JScrollPane(descriptionArea);



        String[] versions = {"个人", "企业"}; // 示例版本
        versionButtons = new JRadioButton[versions.length];
        ButtonGroup versionGroup = new ButtonGroup();
        for (int i = 0; i < versions.length; i++) {
            versionButtons[i] = new JRadioButton(versions[i]);
            versionGroup.add(versionButtons[i]);
        }
        versionButtons[0].setSelected(true); // 默认选中第一个版本

        fileChooser = new JFileChooser();
        JButton chooseLogoButton = new JButton("选择LOGO");
        logoLabel = new JLabel("请选择LOGO文件...");

        JButton submitButton = new JButton("提交");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 假设已经通过JFileChooser选择了文件
                // 这里只是模拟提交过程，不实际发送数据到后端
                String companyName = companyNameField.getText();
                String description = descriptionArea.getText();
                String selectedVersion = "";
                for (JRadioButton button : versionButtons) {
                    if (button.isSelected()) {
                        selectedVersion = button.getText();
                        break;
                    }
                }
                // 假设logoLabel的文本包含了文件路径（实际上应该是通过fileChooser获取的）
                String logoPath = logoLabel.getText().replace("请选择LOGO文件...", ""); // 移除默认文本

                // 在这里可以添加验证逻辑，比如检查字段是否为空等

                // 模拟提交成功
                JOptionPane.showMessageDialog(CompanyInfoForm.this, "提交成功！\n" +
                        "企业名称: " + companyName + "\n" +
                        "版本: " + selectedVersion + "\n" +
                        "描述: " + description + "\n" +
                        "LOGO文件路径: " + logoPath);
            }
        });

        chooseLogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(CompanyInfoForm.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    logoLabel.setText("选择的文件: " + selectedFile.getAbsolutePath());
                }
            }
        });

        // 布局组件（这里简化处理，仅使用BoxLayout）
        // 使用JPanel来组织企业名称和输入框在同一行
        JPanel companyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0)); // 左对齐，水平间距5，垂直间距5
        companyPanel.add(companyNameField);



        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("企业名称:"));
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        mainPanel.add(new JLabel("企业名称:"));
        mainPanel.add(companyNameField);
        panel.add(mainPanel);


        // 创建JLabel和JTextField
        JLabel label = new JLabel("标签:");
        JTextField textField = new JTextField(20); // 20列宽

        // 将组件添加到JPanel中
        panel.add(label);
        panel.add(textField);




        panel.add(companyNameField);


        panel.add(logoLabel);
        panel.add(chooseLogoButton);
        panel.add(new JLabel("版本:"));
        for (JRadioButton button : versionButtons) {
            panel.add(button);
        }


        panel.add(submitButton);

        // 添加panel到JFrame
        add(panel);

        // 设置窗口属性并显示
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(400, 300));
        setLocationRelativeTo(null); // 居中显示
        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CompanyInfoForm();
            }
        });
    }
}