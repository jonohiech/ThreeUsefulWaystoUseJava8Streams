package com.jonathanracioppi.commentfilter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private String comment;
    private boolean display;
    private boolean flagged;
    private String username;


}
