package com.example.demo.util.log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LabelAndTextFieldExample {

    public static void main(String[] args) {
        // 创建JFrame和设置基本属性
        JFrame frame = new JFrame("后台管理设置");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout()); // 使用BorderLayout

        // 创建一个新的JPanel来容纳secondRowPanel和thirdRowPanel，并设置垂直BoxLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


        // 第一行：标签和文本字段
        JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel label = new JLabel("企业名称:");
        JTextField textField = new JTextField(20);
        firstRowPanel.add(label);
        firstRowPanel.add(textField);
        frame.add(firstRowPanel, BorderLayout.NORTH); // 将第一行添加到NORTH位置

        // 第二行：文件上传按钮和显示文件路径的标签
        JPanel secondRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); // 使用FlowLayout
        JButton uploadButton = new JButton("请点击上传");
        final JLabel filePathLabel = new JLabel("LOGO:");

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(frame);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePathLabel.setText(selectedFile.getAbsolutePath());
                    // 在这里可以添加文件上传的逻辑
                }
            }
        });

        JPanel thirdRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); // 使用FlowLayou
        String[] versions = {"个人", "企业"};
        thirdRowPanel.add(new JLabel("版本:"));

        JRadioButton[] versionButtons = new JRadioButton[versions.length];
        ButtonGroup versionGroup = new ButtonGroup();
        for (int i = 0; i < versions.length; i++) {
            versionButtons[i] = new JRadioButton(versions[i]);
            versionGroup.add(versionButtons[i]);
        }
        versionButtons[0].setSelected(true); // 默认选中第一个版本

        for (JRadioButton button : versionButtons) {
            thirdRowPanel.add(button);
        }

        secondRowPanel.add(filePathLabel);
        secondRowPanel.add(uploadButton); // 添加显示文件路径的标签



        JPanel fourRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); // 使用FlowLayou
        JTextArea descriptionArea  = new JTextArea(5, 20);
        fourRowPanel.add(new JLabel("返回弹窗信息:"));
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        fourRowPanel.add(scrollPane);





        centerPanel.add(firstRowPanel);
        centerPanel.add(Box.createVerticalStrut(0)); // 可选：添加垂直间距
        centerPanel.add(secondRowPanel);
        centerPanel.add(thirdRowPanel);
        centerPanel.add(fourRowPanel);



        // 创建一个蓝色的确认提交按钮
        JButton submitButton = new JButton("确认提交");
//        submitButton.setBackground(Color.BLUE); // 设置按钮背景为蓝色
        submitButton.setForeground(Color.BLUE); // 设置按钮文字颜色为白色（可选）

        // 设置按钮的大小
        // 注意：通常推荐使用首选大小（preferred size）而不是直接设置大小（size），
        // 因为首选大小会尊重布局管理器的决策
        submitButton.setPreferredSize(new Dimension(100, 30));
        // 将提交按钮添加到frame的SOUTH区域
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(submitButton); // 将按钮添加到southPanel中
        centerPanel.add(southPanel);
        frame.add(centerPanel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击按钮后执行的操作
                JOptionPane.showMessageDialog(frame, "success", "提交结果", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        // 显示窗口
        frame.setVisible(true);
    }
}