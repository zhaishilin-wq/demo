package com.example.demo.service.impl;

import com.example.demo.service.Test;

public class TestImp implements Test {
    public static void main(String[] args) {
        int x = 10;
        while( x < 20 ) {
            System.out.print("value of x :: " + x );
            x++;
            System.out.print("\n");
        }
    }

}
