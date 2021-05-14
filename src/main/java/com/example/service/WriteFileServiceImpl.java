package com.example.service;

import com.example.dto.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class WriteFileServiceImpl implements WriteFileService{

    private String filePath;

    public WriteFileServiceImpl(@Value("${example.filePath}") String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(Comment comment) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write(comment.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
