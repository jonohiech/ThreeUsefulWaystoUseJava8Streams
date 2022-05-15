package service;

import com.jonathanracioppi.commentfilter.model.Comment;
import com.jonathanracioppi.commentfilter.repository.CommentRepo;
import com.jonathanracioppi.commentfilter.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CommentService.class})
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @MockBean
    CommentRepo commentRepo;


    @Test
    public void testContextLoads() {
        assertNotNull(commentService);
    }

    @Test
    public void testDisplayComments() {

        System.out.println("testDisplayComments start");


        List<Comment> commentList = Arrays.asList(
                new Comment("DisplayCommentUnflagged", true, false, "jon"),
                new Comment("DisplayCommentFlagged", true, true, "jon"),
                new Comment("NoDisplayCommentUnflagged", false, false, "jon"),
                new Comment("NoDisplayCommentFlagged", false, true, "jon"),
                new Comment("DisplayCommentUnflagged", true, false, "mary"),
                new Comment("DisplayCommentFlagged", true, true, "mary"),
                new Comment("NoDisplayCommentUnflagged", false, false, "mary"),
                new Comment("NoDisplayCommentFlagged", false, true, "mary"),
                new Comment("DisplayCommentUnflagged", true, false, "pat"),
                new Comment("DisplayCommentFlagged", true, true, "pat"),
                new Comment("NoDisplayCommentUnflagged", false, false, "pat"),
                new Comment("NoDisplayCommentFlagged", false, true, "pat")

        );

        List<Comment> expected = Arrays.asList(new Comment(
                        "DisplayCommentUnflagged", true, false, "jon"),
                new Comment("DisplayCommentFlagged", true, true, "jon"),
                new Comment("DisplayCommentUnflagged", true, false, "mary"),
                new Comment("DisplayCommentFlagged", true, true, "mary"),
                new Comment("DisplayCommentUnflagged", true, false, "pat"),
                new Comment("DisplayCommentFlagged", true, true, "pat"));

        when(commentRepo.getComments()).thenReturn(commentList);

        List<Comment> actual = commentService.getAllDisplayComments();

        assertTrue(actual.stream().allMatch(x -> x.isDisplay()));
        assertEquals(expected, actual);
        assertTrue(actual.stream().allMatch(expected::contains));
        System.out.println("testDisplayComments passed");


    }

    @Test
    public void testGetNumberOfFlaggedCommentsForUser() {

        System.out.println("testDisplayComments start");


        List<Comment> commentList = Arrays.asList(
                new Comment("DisplayCommentUnflagged", true, false, "jon"),
                new Comment("DisplayCommentFlagged", true, true, "jon"),
                new Comment("NoDisplayCommentUnflagged", false, false, "jon"),
                new Comment("NoDisplayCommentFlagged", false, true, "jon"),
                new Comment("DisplayCommentUnflagged", true, false, "mary"),
                new Comment("DisplayCommentFlagged", true, true, "mary"),
                new Comment("NoDisplayCommentUnflagged", false, false, "mary"),
                new Comment("NoDisplayCommentFlagged", false, true, "mary"),
                new Comment("DisplayCommentUnflagged", true, false, "pat"),
                new Comment("DisplayCommentFlagged", true, true, "pat"),
                new Comment("NoDisplayCommentUnflagged", false, false, "pat"),
                new Comment("NoDisplayCommentFlagged", false, true, "pat")

        );


        Long expected = 2L;
        String username = "jon";

        when(commentRepo.getComments()).thenReturn(commentList);

        Long actual = commentService.getNumberOfFlaggedCommentsForUser(username);

        assertEquals(expected, actual);
        System.out.println("testDisplayComments passed");


    }

    @Test
    public void testGetAllCommentsForListUsers() {

        System.out.println("testDisplayComments start");


        List<List<Comment>> commentList = Arrays.asList(
                Arrays.asList(
                        new Comment("DisplayCommentUnflagged", true, false, "jon"),
                        new Comment("DisplayCommentFlagged", true, true, "jon"),
                        new Comment("NoDisplayCommentUnflagged", false, false, "jon"),
                        new Comment("NoDisplayCommentFlagged", false, true, "jon")
                ),
                Arrays.asList(
                        new Comment("DisplayCommentUnflagged", true, false, "pat"),
                        new Comment("DisplayCommentFlagged", true, true, "pat"),
                        new Comment("NoDisplayCommentUnflagged", false, false, "pat"),
                        new Comment("NoDisplayCommentFlagged", false, true, "pat")
                )



        );

        List<Comment> expected = Arrays.asList(
                new Comment("DisplayCommentUnflagged", true, false, "jon"),
                new Comment("DisplayCommentFlagged", true, true, "jon"),
                new Comment("NoDisplayCommentUnflagged", false, false, "jon"),
                new Comment("NoDisplayCommentFlagged", false, true, "jon"),
                new Comment("DisplayCommentUnflagged", true, false, "pat"),
                new Comment("DisplayCommentFlagged", true, true, "pat"),
                new Comment("NoDisplayCommentUnflagged", false, false, "pat"),
                new Comment("NoDisplayCommentFlagged", false, true, "pat"));

        List<String> usernames = Arrays.asList(
                "jon",
                "pat"

        );

        when(commentRepo.getCommentsForUsers(any())).thenReturn(commentList);

        List<Comment> actual = commentService.getAllCommentsForListOfUsers(usernames);

        assertEquals(expected, actual);
        System.out.println("testDisplayComments passed");


    }


}
