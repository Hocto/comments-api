import com.example.dto.Comment;
import com.example.service.CommentService;
import com.example.service.CommentServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class CommentServiceTest {

    private CommentService commentService;

    private static final String URL = "https://my-json-server.typicode.com/typicode/demo/comments";

    @Test
    public void fetchedComments(){
        commentService = new CommentServiceImpl(URL);
        List<Comment> commentList = commentService.getCommentsFromApi();
        assertThat(commentList.get(0)).isEqualToComparingFieldByField(new Comment(1,"some comment",1));
    }

    @Test
    public void fetchedCommentsListSize(){
        commentService = new CommentServiceImpl(URL);
        List<Comment> commentList = commentService.getCommentsFromApi();
        List<Comment> expectedLists = new ArrayList<>();
        expectedLists.add(new Comment(1,"some comment",1));
        expectedLists.add(new Comment(2,"some comment",1));

        assertThat(commentList.size()).isEqualTo(expectedLists.size());
    }
}
