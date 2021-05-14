package com.example.controller;

import com.example.dto.Comment;
import com.example.dto.CommentsResponse;
import com.example.dto.CommentsResponseError;
import com.example.exception.ResponseException;
import com.example.service.CommentService;
import com.example.service.WriteFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CommentService commentService;

    @Autowired
    private WriteFileService writeFileService;

    @Autowired
    private ErrorHandlerController errorHandlerController;

    @RequestMapping(value="/getComments", method = RequestMethod.GET)
    public ResponseEntity<Object> getComments(){
        try {
            List<Comment> comments = commentService.getCommentsFromApi();
            if(!comments.isEmpty()){
                writeFileService.write(comments.get(comments.size()-1));
            }
            return new ResponseEntity<>(new CommentsResponse(comments), new HttpHeaders(), HttpStatus.OK);
        }catch (ResponseException ex){
            return errorHandlerController.getError();
        }
    }
}
