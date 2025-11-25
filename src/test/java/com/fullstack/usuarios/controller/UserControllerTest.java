package com.fullstack.usuarios.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fullstack.usuarios.dto.User;
import com.fullstack.usuarios.service.UserService;




@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService servicio;

    @Test
    public void testGetAllUsers() throws Exception {
        User usr1 = new User();
        usr1.setId(1L);
        usr1.setName("eduardo");
        usr1.setEmail("eduardo@mail.com");
        
        User usr2 = new User();
        usr2.setId(2L);
        usr2.setName("juan");
        usr2.setEmail("juan@mail.com");
        
        when(servicio.getAll()).thenReturn(List.of(usr1, usr2));

        mockMvc.perform(get("/api/v1/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("eduardo"))
            .andExpect(jsonPath("$[1].name").value("juan"));
        
    }     
}
