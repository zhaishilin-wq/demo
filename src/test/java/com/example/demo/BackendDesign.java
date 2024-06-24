package com.example.demo;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class BackendDesign {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("后台设计");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.anchor = GridBagConstraints.WEST;
            gc.insets = new Insets(10, 10, 10, 10); // 增大外边距

            // 页面标题
            JLabel titleLabel = new JLabel("后台设计");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
            titleLabel.setForeground(Color.RED);
            gc.gridy = 0;
            gc.gridwidth = 2;
            frame.add(titleLabel, gc);

            // 其他组件添加逻辑保持不变...

            // 修复的圆角边框方法应用
            // 例如，企业名称输入框
            JTextField companyNameField = new JTextField(20);
            companyNameField.setBorder(new RoundedBorder(10, Color.BLACK));

            // 同样应用于其他需要圆角边框的组件

            frame.pack();
            frame.setVisible(true);
        });
    }

    // 创建自定义圆角边框类
    static class RoundedBorder implements Border {
        private int radius;
        private Color borderColor;

        RoundedBorder(int radius, Color borderColor) {
            this.radius = radius;
            this.borderColor = borderColor;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.drawRoundRect(x, y, width-1, height-1, radius, radius); // 注意减1以适应Swing边框绘制规则
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius+1, radius+1, radius+2, radius); // 适应Swing边框内边距规则
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }
}