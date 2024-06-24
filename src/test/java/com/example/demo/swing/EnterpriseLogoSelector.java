package com.example.demo.swing;//import org.springframework.scheduling.config.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.border.LineBorder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class EnterpriseLogoSelector {
    private static final int SIMULATED_DELAY = 2; // 20秒

    private JComboBox<String> enterpriseComboBox;
    private Map<String, String> enterpriseLogoMap; // 假设企业名称到logo文件路径的映射

    public EnterpriseLogoSelector() {
        // 初始化企业名称和logo映射（这里只是示例数据）
        enterpriseLogoMap = new HashMap<>();

        enterpriseLogoMap.put("企业A", "/log.jpg");
        enterpriseLogoMap.put("企业B", "/Users/forest/IdeaProjects/demo/src/test/java/com/example/demo/util/log.jpg");
        // ... 添加更多企业

        // 创建主窗口
        JFrame frame = new JFrame("企业Logo选择器");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // 创建一个新的JPanel来容纳secondRowPanel和thirdRowPanel，并设置垂直BoxLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


        // 创建企业名称选择框
        enterpriseComboBox = new JComboBox<>(enterpriseLogoMap.keySet().toArray(new String[0]));
        enterpriseComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEnterprise = (String) enterpriseComboBox.getSelectedItem();
                String logoPath = enterpriseLogoMap.get(selectedEnterprise);
                showEnterpriseLogo(logoPath);
            }
        });


        enterpriseComboBox.setLayout(new BoxLayout(enterpriseComboBox, BoxLayout.Y_AXIS));
        enterpriseComboBox.setBorder(new LineBorder(Color.BLACK));

        // 第一行：标签和文本字段
        JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JLabel label = new JLabel("企业名称:");
        firstRowPanel.add(label);
        firstRowPanel.add(enterpriseComboBox);
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


        // 创建企业名称选择框
        enterpriseComboBox = new JComboBox<>(enterpriseLogoMap.keySet().toArray(new String[0]));
        enterpriseComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEnterprise = (String) enterpriseComboBox.getSelectedItem();
                String logoPath = enterpriseLogoMap.get(selectedEnterprise);
                showEnterpriseLogo(logoPath);
            }
        });


        enterpriseComboBox.setLayout(new BoxLayout(enterpriseComboBox, BoxLayout.Y_AXIS));
        // 将选择框添加到主窗口
        frame.add(enterpriseComboBox, BorderLayout.NORTH);

        // 显示主窗口
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(centerPanel, BorderLayout.CENTER);
    }

    // 展示企业logo的新窗口
    private void showEnterpriseLogo(String logoPath) {
        try {
            // 读取logo图片
            BufferedImage logoImage = ImageIO.read(new File(logoPath));

            // 创建一个新的窗口来显示logo
            JFrame logoFrame = new JFrame("企业 " + enterpriseComboBox.getSelectedItem() + " 的Logo");
            logoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            logoFrame.setLayout(new BorderLayout());



            // 将logo添加到新窗口的JLabel中
            JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
            logoFrame.add(logoLabel, BorderLayout.CENTER);

            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            centerPanel.add(logoLabel);


            // 创建标签并设置字体
            JLabel enterpriseNameLabel = new JLabel("企业名称", SwingConstants.CENTER);
            enterpriseNameLabel.setFont(new Font("Arial", Font.BOLD, 42));
            enterpriseNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            enterpriseNameLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置文本居中对齐
            enterpriseNameLabel.setVerticalAlignment(SwingConstants.CENTER); // 设置垂直居中对齐

            JLabel enterpriseDescriptionLabel = new JLabel("企业描述", SwingConstants.CENTER);
            enterpriseDescriptionLabel.setFont(new Font("Arial", Font.BOLD, 24));
            enterpriseDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
            enterpriseDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置文本居中对齐
            enterpriseDescriptionLabel.setVerticalAlignment(SwingConstants.CENTER); // 设置垂直居中对齐


            // 设置自动换行

            centerPanel.add(enterpriseNameLabel, BorderLayout.CENTER);
            JPanel contentPanel = new JPanel(new BorderLayout(20, 20)); // 间距为10
            centerPanel.add(contentPanel);
            centerPanel.add(enterpriseDescriptionLabel, BorderLayout.CENTER);

            JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            JLabel label = new JLabel("企业名称:");
            JTextField textField = new JTextField(20);
            firstRowPanel.add(label);
            firstRowPanel.add(textField);
            centerPanel.add(firstRowPanel); // 将第一行添加到NORTH位置

            JPanel seRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
            JLabel label1 = new JLabel("企业电话:");
            JTextField textField1 = new JTextField(20);
            seRowPanel.add(label1);
            seRowPanel.add(textField1);
            centerPanel.add(seRowPanel); // 将第一行添加到NORTH位置

            JButton submitButton = new JButton("确认提交");
            submitButton.setBackground(Color.black);
            submitButton.setForeground(Color.BLUE);
            submitButton.setPreferredSize(new Dimension(100, 50)); // 可选：设置按钮的预设大小
            JPanel buttonPanel = new JPanel(new GridBagLayout()); // 使用GridBagLayout来居中组件

            // 加载面板
            JPanel loadingPanel = new JPanel(new BorderLayout());
            JLabel loadingLabel = new JLabel(new ImageIcon("/Users/forest/IdeaProjects/demo/src/test/java/com/example/demo/util/loading.png")); // 加载图标图片
            loadingPanel.add(loadingLabel, BorderLayout.CENTER);
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // 显示加载弹窗
                    final JDialog loadingDialog = new JDialog(logoFrame, "Loading...", true);
                    loadingDialog.setSize(400, 400);
                    JLabel label = new JLabel("Please wait...");
                    loadingDialog.add(label, BorderLayout.CENTER);
                    loadingDialog.setModal(false); // 如果需要非模态对话框，则设置为false
                    loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // 不执行任何关闭操作

                    BufferedImage loadingImage = null;
                    try {
                        loadingImage = ImageIO.read(new File("/Users/forest/IdeaProjects/demo/src/test/java/com/example/demo/util/loading.png"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    JLabel loadingLabel = new JLabel(new ImageIcon(loadingImage));
                    JPanel centerPanel1 = new JPanel();
                    centerPanel1.setLayout(new BoxLayout(centerPanel1, BoxLayout.Y_AXIS));
                    centerPanel1.add(loadingLabel);
                    loadingDialog.add(centerPanel1);



                    loadingDialog.setVisible(true);

                    // 使用Timer在2秒后关闭加载弹窗并显示结果

                    Timer timer = new javax.swing.Timer(5000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
//                            loadingDialog.setVisible(false);
                            // 假设操作成功
                            boolean success = true; // 你可以根据实际情况设置这个值
                            loadingDialog.dispose();
                            // 显示结果弹窗
                            showResultDialog(logoFrame,success);
                            ((javax.swing.Timer) e.getSource()).stop();
                        }
                    });

                    timer.start(); // 启动定时器
                }

            });



            buttonPanel.add(submitButton);
            centerPanel.add(buttonPanel, BorderLayout.PAGE_END);
            logoFrame.add(centerPanel); // 将第一行添加到NORTH位置


            // 显示窗口
            logoFrame.pack();
            logoFrame.setLocationRelativeTo(null);
            logoFrame.setVisible(true);

        } catch (IOException ex) {
            // 处理图片读取错误
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "无法加载企业logo");
        }
    }

    private void showResultDialog(JFrame frame,boolean success) {
        JDialog resultDialog = new JDialog(frame, success ? "Success" : "Failure", true);
        resultDialog.setSize(400, 400);
//        String message = success ? "Operation successful!" : "Operation failed!";
//        JLabel label = new JLabel(message);
//        JTextArea logArea = new JTextArea("成功"); // 你可以填充实际的日志内容
//        logArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(logArea);
//
//        resultDialog.add(label, BorderLayout.NORTH);
//        resultDialog.add(scrollPane, BorderLayout.CENTER);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


        BufferedImage successImage = null;
        BufferedImage failImage = null;

        try {
            successImage = ImageIO.read(new File("/Users/forest/IdeaProjects/demo/src/test/java/com/example/demo/util/logos.jpg"));
            failImage = ImageIO.read(new File("/Users/forest/IdeaProjects/demo/src/test/java/com/example/demo/util/logof.jpg"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        JLabel loadingLabe = new JLabel(new ImageIcon(success ? successImage: failImage));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(loadingLabe);





        // 创建标签并设置字体
        JLabel enterpriseNameLabel = new JLabel(success? "成功":"本次失败请联系", SwingConstants.CENTER);
        enterpriseNameLabel.setFont(new Font("Arial", Font.BOLD, 42));
        enterpriseNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enterpriseNameLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置文本居中对齐
        enterpriseNameLabel.setVerticalAlignment(SwingConstants.CENTER); // 设置垂直居中对齐

        JLabel enterpriseDescriptionLabel = new JLabel(success? "恭喜":"下次在努力", SwingConstants.CENTER);
        enterpriseDescriptionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        enterpriseDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enterpriseDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER); // 设置文本居中对齐
        enterpriseDescriptionLabel.setVerticalAlignment(SwingConstants.CENTER); // 设置垂直居中对齐

        centerPanel.add(enterpriseNameLabel, BorderLayout.CENTER);
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10)); // 间距为10
        centerPanel.add(contentPanel);
        centerPanel.add(enterpriseDescriptionLabel, BorderLayout.CENTER);

        resultDialog.add(centerPanel);

//        JButton okButton = new JButton("OK");
////        okButton.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                resultDialog.dispose();
////            }
////        });
//        resultDialog.add(okButton, BorderLayout.SOUTH);
        resultDialog.setVisible(true);
    }




    // 模拟的任务类

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EnterpriseLogoSelector::new);
    }
}