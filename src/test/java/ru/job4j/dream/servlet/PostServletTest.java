package ru.job4j.dream.servlet;

import org.junit.Test;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServletTest {
    @Test
    public void whenCreatePost() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Post post = new Post(0, "name of new post");
        when(request.getParameter("id")).thenReturn(String.valueOf(post.getId()));
        when(request.getParameter("name")).thenReturn(post.getName());
        new PostServlet().doPost(request, response);
        var posts = DbStore.instOf().findAllPosts().toArray();
        var rsl = (Post) posts[posts.length - 1];
        assertThat(rsl.getName(), is("name of new post"));
    }
}