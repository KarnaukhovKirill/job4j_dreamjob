package ru.job4j.dream.servlet;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;
import ru.job4j.dream.store.Store;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServletTest {
    private static Store store;

    @BeforeClass
    public static void init() {
        store = DbStore.instOf();
    }

    @After
    public void wipeTables() {
        store.delAllPosts();
    }

    @Test
    public void whenCreatePost() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Post post = new Post(0, "name of new post");
        when(request.getParameter("id")).thenReturn(String.valueOf(post.getId()));
        when(request.getParameter("name")).thenReturn(post.getName());
        new PostServlet().doPost(request, response);
        var posts = store.findAllPosts().toArray();
        var rsl = (Post) posts[posts.length - 1];
        assertThat(rsl.getName(), is("name of new post"));
    }
}