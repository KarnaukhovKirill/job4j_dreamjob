package ru.job4j.dream.servlet;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dream.model.Candidate;
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

public class CandidateServletTest {
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
    public void whenCreatePost() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn("0");
        when(request.getParameter("name")).thenReturn("Harry Potter");
        new CandidateServlet().doPost(request, response);
        var candidates = DbStore.instOf().findAllCandidates().toArray();
        var candidate = (Candidate) candidates[candidates.length - 1];
        assertThat(candidate.getName(), is("Harry Potter"));
    }
}