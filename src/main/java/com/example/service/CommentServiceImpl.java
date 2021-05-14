package com.example.service;

import com.example.dto.Comment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    private String urlPath;

    public CommentServiceImpl(@Value("${example.url}") String urlPath) {
        this.urlPath = urlPath;
    }

    @Override
    public List<Comment> getCommentsFromApi() {
        List<Comment> commentList = new ArrayList<>();
        try {
            URL url = new URL(urlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Accept","application/json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String output;
            StringBuilder stringBuilder = new StringBuilder();
            logger.trace("Fetching comments from Comment API.");
            while ((output=bufferedReader.readLine()) != null){
                stringBuilder.append(output.trim().replace("\n",""));
            }
            ObjectMapper mapper = new ObjectMapper();
            commentList = mapper.readValue(stringBuilder.toString(), new TypeReference<List<Comment>>(){});
            logger.trace("Comment list was generated with {} comment(s)", commentList.size());
            httpURLConnection.disconnect();

        }catch (MalformedURLException e) {
            logger.error("MalformedURLException: ", e);
        } catch (IOException e) {
            logger.error("IOException: ", e);
        }
        return Optional.ofNullable(commentList).orElse(new ArrayList<>());
    }

}
