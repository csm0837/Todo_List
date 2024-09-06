package com.ssg.w1.todo.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class TodoVO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;


}
