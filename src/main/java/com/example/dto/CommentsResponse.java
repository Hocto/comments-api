package com.example.dto;

import java.util.List;

public class CommentsResponse {
    private List<Comment> comments;

    public CommentsResponse(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
