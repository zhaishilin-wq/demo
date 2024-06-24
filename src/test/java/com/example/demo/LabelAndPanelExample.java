package com.example.demo;

import javax.swing.*;
import java.awt.*;

public class LabelAndPanelExample extends JFrame {

    public LabelAndPanelExample() {
        super("Label and Panel on the Same Row");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // 创建标签
        JLabel label = new JLabel("标签:");

        // 创建一个简单的面板，这里只包含一个按钮作为示例
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JButton button = new JButton("按钮");
        panel.add(button);

        // 创建一个主面板，并使用FlowLayout来组织标签和子面板
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        mainPanel.add(label);
        mainPanel.add(panel); // 将子面板添加到主面板

        // 将主面板添加到JFrame
        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LabelAndPanelExample();
        });
    }
}