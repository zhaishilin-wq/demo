package com.example.demo.swing;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadImage1 {
    public static void main(String[] args) {
        try {
            // 图片路径相对于当前类的路径loading
            FileInputStream fis = new FileInputStream("log.jpg");
            // 这里可以根据需要进一步处理InputStream，例如转换为BufferedImage显示或保存等
            // 以下代码仅为示例，未展示如何处理图像数据
            System.out.println("图片文件已成功读取！");
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
