package com.example.demo.controller;

import com.example.demo.entity.JsonValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    @PostMapping("/processJson")
    public ResponseEntity<Integer> processJson(@RequestBody JsonValue jsonValue) {
        // 从 JsonValue 对象获取 int 值
        int receivedValue = jsonValue.getValue();

        // 进行一些处理，例如计算
        int result = doSomethingWith(receivedValue);

        // 返回结果
        return ResponseEntity.ok(result);
    }

    private int doSomethingWith(int value) {
        // 这里进行你需要的处理，例如加 1
        int result = 0;
        if (value != 0) {
            for (int i = 0; i < value; i++) {
               result += i ;
            }
        } else {
            System.out.printf("请输入正确值" );
        }
//        String result = i;
        return result*10;
    }
}