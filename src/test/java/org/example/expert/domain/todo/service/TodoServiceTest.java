package org.example.expert.domain.todo.service;

import org.example.expert.client.WeatherClient;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
    @Mock
    private WeatherClient weatherClient;
    @InjectMocks
    private TodoService todoService;

    @Test
    public void 할일저장_성공() {
        // given
        TodoSaveRequest todoSaveRequest = new TodoSaveRequest("title","contenst");
        AuthUser authUser = new AuthUser(1L, "example@example.com", UserRole.USER);
        User user = User.fromAuthUser(authUser);
        String weather = weatherClient.getTodayWeather();
        Todo newTodo = new Todo("title", "contents", weather, user);

        given(todoRepository.save(any())).willReturn(newTodo);

        // when
        TodoSaveResponse result = todoService.saveTodo(authUser, todoSaveRequest);

        // then
        assertNotNull(result);
    }

//    @Test
//    public void 할일다건조회_성공() {
//        // given
//
//        // when
//
//        // then
//
//    }
}
