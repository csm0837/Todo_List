package com.ssg.w1.todo.DAO;

import com.ssg.w1.todo.domain.TodoVO;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    public void insert(TodoVO vo) throws Exception {
        String sql = "INSERT INTO tbl_todo (title, dueDate, finished) VALUES (?, ?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

        psmt.setString(1, vo.getTitle());
        psmt.setDate(2, Date.valueOf(vo.getDueDate()));
        psmt.setBoolean(3, vo.isFinished());

        psmt.executeUpdate();
    }

    public List<TodoVO> selectAllList() throws Exception {
        String sql = "SELECT * FROM tbl_todo";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
        @Cleanup ResultSet rs = psmt.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (rs.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();
            list.add(vo);
        }

        return list;
    }

    public TodoVO selectOne(long tno) throws Exception {
        String sql = "SELECT * FROM tbl_todo WHERE tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

        psmt.setLong(1, tno);

        @Cleanup ResultSet rs = psmt.executeQuery();

        rs.next();
        return TodoVO.builder()
                .tno(rs.getLong("tno"))
                .title(rs.getString("title"))
                .dueDate(rs.getDate("dueDate").toLocalDate())
                .finished(rs.getBoolean("finished"))
                .build();
    }

    public void deledtOne(Long tno) throws Exception {
        String sql = "DELETE FROM tbl_todo WHERE tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setLong(1, tno);
        psmt.executeUpdate();
    }

    public void updateOne(TodoVO vo) throws Exception {
        String sql = "UPDATE tbl_todo SET title = ?, dueDate = ?, finished = ? WHERE tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = connection.prepareStatement(sql);

        psmt.setString(1, vo.getTitle());
        psmt.setDate(2, Date.valueOf(vo.getDueDate()));
        psmt.setBoolean(3, vo.isFinished());
        psmt.setLong(4, vo.getTno());

        psmt.executeUpdate();
    }
}
