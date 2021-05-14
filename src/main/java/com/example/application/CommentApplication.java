package com.example.application;

import com.apple.eawt.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class CommentApplication {

        public static void main(String[] args) {
            SpringApplication.run(CommentApplication.class, args);
        }

}
