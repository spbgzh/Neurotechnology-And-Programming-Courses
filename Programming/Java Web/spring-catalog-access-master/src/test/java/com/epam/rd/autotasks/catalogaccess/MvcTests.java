package com.epam.rd.autotasks.catalogaccess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {MvcApplication.class, WebSecurityConfig.class})
@AutoConfigureMockMvc
class MvcTests {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;


    private static RequestPostProcessor manager() {
        return user("eddie").roles("MANAGER");
    }

    private static RequestPostProcessor customer() {
        return user("eddie").roles("CUSTOMER");
    }

    private static RequestPostProcessor employee() {
        return user("eddie").roles("EMPLOYEE");
    }

    @Test
    void testSalaries() throws Exception {
        mockMvc.perform(get("/salaries").with(manager()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(get("/salaries").with(employee()))
                .andDo(log())
                .andExpect(status().isForbidden())
                .andReturn();
        mockMvc.perform(get("/salaries").with(customer()))
                .andDo(log())
                .andExpect(status().isForbidden())
                .andReturn();

        mockMvc.perform(get("/salaries/my").with(manager()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(get("/salaries/my").with(employee()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(get("/salaries/my").with(customer()))
                .andDo(log())
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    void testEmployees() throws Exception {
        mockMvc.perform(get("/employees").with(manager()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(get("/employees").with(employee()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(get("/employees").with(customer()))
                .andDo(log())
                .andExpect(status().isForbidden())
                .andReturn();

        mockMvc.perform(get("/employees/1").with(manager()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(get("/employees/1").with(employee()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(get("/employees/1").with(customer()))
                .andDo(log())
                .andExpect(status().isForbidden())
                .andReturn();

        mockMvc.perform(post("/employees")
                .param("name", "Arnold")
                .param("position", "MANAGER")
                .with(manager()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(post("/employees")
                .param("name", "Arnold")
                .param("position", "MANAGER")
                .with(employee()))
                .andDo(log())
                .andExpect(status().isForbidden())
                .andReturn();
        mockMvc.perform(post("/employees")
                .param("name", "Arnold")
                .param("position", "MANAGER")
                .with(customer()))
                .andDo(log())
                .andExpect(status().isForbidden())
                .andReturn();
    }


    @Test
    void testCatalog() throws Exception {
        mockMvc.perform(get("/catalog").with(manager()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(get("/catalog").with(employee()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
        mockMvc.perform(get("/catalog").with(customer()))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();
    }
}
