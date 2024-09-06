package com.ssg.w1.todo;

import com.ssg.w1.todo.DTO.TodoDTO;
import com.ssg.w1.todo.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "todoRegisterController", urlPatterns = "/todo/register")
public class TodoRegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("입력화면 register.jsp 으로 GET");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/todo/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("입력을 처리하고 목록 페이지로 이동");


        String title = req.getParameter("title");
        String dueDate = req.getParameter("dueDate");


        TodoDTO dto = new TodoDTO();
        dto.setTitle(title);
        dto.setDueDate(LocalDate.parse(dueDate));
        dto.setFinished(false);


        TodoService.INSTANCE.register(dto);


        resp.sendRedirect("/todo/list");
    }
}
