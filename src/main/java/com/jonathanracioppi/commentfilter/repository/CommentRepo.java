package com.jonathanracioppi.commentfilter.repository;

import com.jonathanracioppi.commentfilter.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo {

    public List<Comment> getComments();

    public List<List<Comment>> getCommentsForUsers(List<String> usernames);

}
