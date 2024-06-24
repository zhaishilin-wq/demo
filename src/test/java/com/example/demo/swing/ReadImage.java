package com.example.demo.swing;

import java.io.IOException;
import java.io.InputStream;

public class ReadImage {
    public static void main(String[] args) {
        // 注意这里使用了"/"作为开头，表示从类路径根开始查找
        InputStream is = ReadImage.class.getResourceAsStream("/com/example/demo/swing/log.jpg");
        if (is != null) {
            System.out.println("图片文件已成功读取！");
            // 同样，根据需求进一步处理InputStream
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("图片文件未找到！");
        }
    }
}
