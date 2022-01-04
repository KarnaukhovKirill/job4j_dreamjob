package ru.job4j.dream.servlet;

import ru.job4j.dream.util.CandidatePhotoSearcher;
import ru.job4j.dream.util.PhotoSearcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class CandidatePhotoLoadServer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idCandidate = req.getParameter("id");
        PhotoSearcher searcher = new CandidatePhotoSearcher();
        var candidatePhoto = searcher.search(idCandidate);
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + candidatePhoto.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(candidatePhoto)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}
