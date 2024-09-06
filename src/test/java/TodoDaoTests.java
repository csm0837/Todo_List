import com.ssg.w1.todo.DAO.TodoDAO;
import com.ssg.w1.todo.DTO.TodoDTO;
import com.ssg.w1.todo.domain.TodoVO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TodoDaoTests {


    TodoDAO dao = new TodoDAO();

    @Test
    public void testInsert() throws Exception {
        TodoVO vo = TodoVO.builder()
                .title("Sample Title.....")
                .dueDate(LocalDate.of(2024,9,5))
                .build();

        dao.insert(vo);

    }

    @Test
    public void testList() throws Exception {
        List<TodoVO> list = dao.selectAllList();
        list.forEach(vo -> System.out.println(vo));

    }
    @Test
    public void testSelectOne() throws Exception {
        long tno = 1L;
        TodoVO vo = dao.selectOne(tno);
      }

      @Test
    public void testUpdate() throws Exception {


          TodoVO voTest = TodoVO.builder().tno(1L).title("update test...").dueDate(LocalDate.of(2024,9,2)).finished(true).build();
          dao.updateOne(voTest);


    }


}
