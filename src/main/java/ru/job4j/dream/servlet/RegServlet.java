package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.commons.validator.routines.EmailValidator;
import ru.job4j.dream.store.DbStore;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        boolean valid = EmailValidator.getInstance().isValid(email);
        if (valid) {
            var store = DbStore.instOf();
            store.save(new User(0, name, email, password));
            var user = store.findUserByEmail(email);
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        } else {
            req.setAttribute("error", "Не верный email");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
}
