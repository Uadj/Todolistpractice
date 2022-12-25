package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TodoController {
    @GetMapping("/RequestParam")
    public String testController(@RequestBody TestRequestBodyDto testRequestBodyDto){
        return "Hello world ID : " + testRequestBodyDto.getId() + "Message : " + testRequestBodyDto.getMessage();
    }
    @GetMapping("/ResponseBody")
    public ResponseDto<String> testRes(){
        List<String> list = new ArrayList<>();
        list.add("Hello World I'm ResponseDTO");
        ResponseDto<String> response = ResponseDto.<String>builder().data(list).build();
        return response;
    }
    @GetMapping
    public ResponseEntity<?> retrieveTodoList(){
        String temporaryUserId = "temporary-user";
        List<TodoEntity> entities = service.retrive(temporaryUserId);
    }
}
