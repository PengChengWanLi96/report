package com.fpj.report.controller; // 请替换为您的实际包名

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * HelloController 的WebMvc测试
 */
// 此注解用于切片测试，它只会加载Web层相关的Bean（如@Controller, @RestController）
// 专注于测试HelloController[6,7](@ref)
@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    // 自动注入MockMvc实例以模拟HTTP请求[6,8](@ref)
    @Autowired
    private MockMvc mockMvc;

    // 如果被测试的控制器（HelloController）注入了其他Service Bean，你必须使用 @MockBean来模拟这些依赖，否则Spring无法初始化测试上下文
    // 如果HelloController依赖了某个Service，请用@MockBean模拟它[1,5](@ref)
    // @MockBean
    // private SomeService someService;

    @Test
    void testHealthCheck() throws Exception {
        mockMvc.perform(get("/health") // 模拟GET请求到/health端点
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // 断言HTTP状态为200
                .andExpect(jsonPath("$.status").value("UP")) // 验证JSON响应体中的status字段值
                .andExpect(jsonPath("$.service").exists())
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void testSayHello() throws Exception {
        mockMvc.perform(get("/hello")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, 欢迎使用报表服务系统!")); // 验证响应体内容
    }

    @Test
    void testPersonalizedWelcome_WithName() throws Exception {
        String testName = "张三";
        mockMvc.perform(get("/welcome")
                        .param("name", testName) // 设置请求参数
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("你好 " + testName + "，欢迎访问我们的服务系统！"));
    }

    @Test
    void testPersonalizedWelcome_WithoutName() throws Exception {
        mockMvc.perform(get("/welcome")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("你好 访客，欢迎访问我们的服务系统！"));
    }

    @Test
    void testServiceStatus() throws Exception {
        mockMvc.perform(get("/status")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service").value("报表服务系统"))
                .andExpect(jsonPath("$.status").value("正常运行"))
                .andExpect(jsonPath("$.metrics").exists());
    }
}