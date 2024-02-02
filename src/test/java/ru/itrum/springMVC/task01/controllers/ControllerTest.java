package ru.itrum.springMVC.task01.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.itrum.springMVC.task01.models.Order;
import ru.itrum.springMVC.task01.models.User;
import ru.itrum.springMVC.task01.services.UserService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
class ControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private User john;

    private List<User> userList;

    @BeforeEach
    void setUp() {
        john = new User("John", "john@mail.com");
        john.setId(1);
        Order orderByJohn = new Order("MacBook", 1000.0, "bought");
        orderByJohn.setId(1);
        orderByJohn.setUser(john);
        john.setOrders(Collections.singletonList(orderByJohn));

        User bob = new User("Bob", "bob@mail.com");
        bob.setId(2);
        Order orderByBob = new Order("IPhone", 800.0, "cancel");
        orderByBob.setId(1);
        orderByBob.setUser(bob);
        bob.setOrders(Collections.singletonList(orderByBob));

        userList = Arrays.asList(john, bob);
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.findAll()).thenReturn(userList);
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[0].email", is("john@mail.com")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Bob")))
                .andExpect(jsonPath("$[1].email", is("bob@mail.com")));
        verify(userService, times(1)).findAll();
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.findById(1)).thenReturn(Optional.ofNullable(john));
        mockMvc.perform(get("/api/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("john@mail.com"))
                .andExpect(jsonPath("$.orders[0].id").value(1))
                .andExpect(jsonPath("$.orders[0].product").value("MacBook"))
                .andExpect(jsonPath("$.orders[0].totalAmount").value(1000.0))
                .andExpect(jsonPath("$.orders[0].status").value("bought"));
        verify(userService, times(1)).findById(1);
    }

    @Test
    void testGetUserById_UserNotFound() throws Exception {
        when(userService.findById(1)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/users/{id}", 1)).andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.save(john)).thenReturn(john);
        String userJson = objectMapper.writeValueAsString(john);
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.email").value("john@mail.com"))
                .andExpect(jsonPath("$.orders[0].id").value(1))
                .andExpect(jsonPath("$.orders[0].product").value("MacBook"))
                .andExpect(jsonPath("$.orders[0].totalAmount").value(1000))
                .andExpect(jsonPath("$.orders[0].status").value("bought"));
        verify(userService, times(1)).save(john);
    }

    @Test
    void testCreateUser_badRequest() throws Exception {
        john.setEmail("noValidEmail");
        when(userService.save(john)).thenReturn(john);
        String userJson = objectMapper.writeValueAsString(john);
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.findById(1)).thenReturn(Optional.ofNullable(john));
        User updatedUser = new User("Updated John", "updatedjohn@mail.com");
        updatedUser.setId(1);
        when(userService.save(updatedUser)).thenReturn(updatedUser);
        String updatedUserJson = objectMapper.writeValueAsString(updatedUser);
        mockMvc.perform(put("/api/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated John"))
                .andExpect(jsonPath("$.email").value("updatedjohn@mail.com"))
                .andExpect(jsonPath("$.orders[0].id").value(1))
                .andExpect(jsonPath("$.orders[0].product").value("MacBook"))
                .andExpect(jsonPath("$.orders[0].totalAmount").value(1000.0))
                .andExpect(jsonPath("$.orders[0].status").value("bought"));
        verify(userService, times(1)).findById(1);
        verify(userService, times(1)).save(updatedUser);
    }

    @Test
    void testUpdateUser_notFound() throws Exception {
        when(userService.findById(1)).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/users/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(john)))
                .andExpect(status().isNotFound());
        verify(userService, times(1)).findById(1);
    }

    @Test
    void testDeleteUser() throws Exception {
        when(userService.findById(1)).thenReturn(Optional.ofNullable(john));
        mockMvc.perform(delete("/api/users/{id}", 1))
                .andExpect(status().isOk());
        verify(userService, times(1)).findById(1);
        verify(userService, times(1)).deleteById(1);
    }

    @Test
    void testDeleteUser_notFound() throws Exception {
        when(userService.findById(1)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/api/users/{id}", 1))
                .andExpect(status().isNotFound());
        verify(userService, times(1)).findById(1);
    }
}
