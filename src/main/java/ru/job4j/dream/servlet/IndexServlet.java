package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", req.getSession().getAttribute("user"));
        Collection<Post> posts = DbStore.instOf().findPostsByLastDay();
        Collection<Candidate> candidates = DbStore.instOf().findCandidatesByLastDay();
        req.setAttribute("posts", posts);
        req.setAttribute("candidates", candidates);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
