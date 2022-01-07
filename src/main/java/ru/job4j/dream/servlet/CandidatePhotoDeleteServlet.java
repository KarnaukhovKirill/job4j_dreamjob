package ru.job4j.dream.servlet;

import ru.job4j.dream.util.CandidatePhotoSearcher;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.util.PhotoSearcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CandidatePhotoDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var idCandidate = req.getParameter("id");
        MemStore.instOf().delCandidate(Integer.parseInt(idCandidate));
        PhotoSearcher searcher = new CandidatePhotoSearcher();
        var candidatePhoto = searcher.search(idCandidate);
        candidatePhoto.delete();
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
