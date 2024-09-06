package com.ssg.w1.todo;

import com.ssg.w1.todo.DTO.TodoDTO;
import com.ssg.w1.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "todoModifyController", urlPatterns = {"/todo/modify", "/todo/remove"})
public class TodoModifyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long tno = Long.parseLong(req.getParameter("tno"));
        TodoDTO dto = TodoService.INSTANCE.get(tno);
        req.setAttribute("dto", dto);


        req.getRequestDispatcher("/todo/modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        Long tno = Long.parseLong(req.getParameter("tno"));

        if (action.equals("/todo/modify")) {

            String title = req.getParameter("title");
            String dueDate = req.getParameter("dueDate");
            boolean finished = req.getParameter("finished") != null;

            TodoDTO dto = new TodoDTO(tno, title, LocalDate.parse(dueDate), finished);
            TodoService.INSTANCE.modify(dto);
        } else if (action.equals("/todo/remove")) {

            TodoService.INSTANCE.remove(tno);
        }


        resp.sendRedirect("/todo/list");
    }
}
