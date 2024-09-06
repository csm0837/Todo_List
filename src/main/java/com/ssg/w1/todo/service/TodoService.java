package com.ssg.w1.todo.service;

import com.ssg.w1.todo.DAO.TodoDAO;
import com.ssg.w1.todo.DTO.TodoDTO;
import com.ssg.w1.todo.domain.TodoVO;

import java.util.List;
import java.util.stream.Collectors;

public enum TodoService {

    INSTANCE;

    private TodoDAO dao = new TodoDAO();

    public void register(TodoDTO dto) {

        TodoVO vo = TodoVO.builder()
                .title(dto.getTitle())
                .dueDate(dto.getDueDate())
                .finished(dto.isFinished())
                .build();

        try {

            dao.insert(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TodoDTO> getList() {
        try {

            List<TodoVO> voList = dao.selectAllList();


            return voList.stream().map(vo -> new TodoDTO(
                    vo.getTno(),
                    vo.getTitle(),
                    vo.getDueDate(),
                    vo.isFinished()
            )).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public TodoDTO get(Long tno) {
        try {

            TodoVO vo = dao.selectOne(tno);


            return new TodoDTO(
                    vo.getTno(),
                    vo.getTitle(),
                    vo.getDueDate(),
                    vo.isFinished()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void modify(TodoDTO dto) {

        TodoVO vo = TodoVO.builder()
                .tno(dto.getTno())
                .title(dto.getTitle())
                .dueDate(dto.getDueDate())
                .finished(dto.isFinished())
                .build();

        try {

            dao.updateOne(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(Long tno) {
        try {

            dao.deledtOne(tno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
