package com.jonathanracioppi.commentfilter.service;

import com.jonathanracioppi.commentfilter.model.Comment;
import com.jonathanracioppi.commentfilter.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    public List<Comment> getAllDisplayComments() {
       List<Comment> comments = commentRepo.getComments();

       List<Comment> displayComments = comments.stream().filter(Comment::isDisplay).collect(Collectors.toList());

       return displayComments;

    }


    public Long getNumberOfFlaggedCommentsForUser(String username) {

        List<Comment> comments = commentRepo.getComments();

        Long flaggedForUser = comments.stream().filter(pizza -> pizza.isFlagged() && pizza.getUsername().equalsIgnoreCase(username)).count();

        return flaggedForUser;

    }

    public List<Comment> getAllCommentsForListOfUsers(List<String> usernames) {

        List<List<Comment>> comment2dList = commentRepo.getCommentsForUsers(usernames);

        List<Comment> commentsFlattened = comment2dList.stream().flatMap(Collection::stream).collect(Collectors.toList());

        return commentsFlattened;
    }
}
