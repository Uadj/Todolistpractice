package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDto dto){
        try {
            String temporaryUserId = "temporary-user";

            TodoEntity entity = TodoDto.toEntity(dto);

            entity.setId(null);

            entity.setUserId(null);

            List<TodoEntity> entities = service.create(entity);

            List<TodoDto> dtos = entities.stream().map(TodoDto::new).collect(Collectors.toList());

            // 변환된 TodoDto 리스트를 이용해 ResponseDto를 초기화한다.
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().data(dtos).build();

            // ResponseDto를 리턴한다.
            return ResponseEntity.ok().body(response);
        } catch(Exception e){
            String error = e.getMessage();
            ResponseDto<TodoDto> response = ResponseDto.<TodoDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
