package com.fpj.report.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fangpengjun
 * @date 2025/12/09
 * @description 系统健康检查和欢迎接口
 */
@RestController
@Slf4j
@Tag(name = "系统健康模块", description = "系统健康状态和欢迎接口")
public class HelloController {

    /**
     * 健康检查接口
     * 返回服务的健康状态信息[6,7](@ref)
     */
    @GetMapping("/health")
    @Operation(summary = "健康检查", description = "检查服务当前的健康状态")
    public Map<String, Object> healthCheck() {
        log.info("健康检查接口被调用");

        Map<String, Object> healthInfo = new HashMap<>();
        healthInfo.put("status", "UP");
        healthInfo.put("timestamp", System.currentTimeMillis());
        healthInfo.put("service", "report-service");
        healthInfo.put("version", "1.0.0");

        return healthInfo;
    }

    /**
     * 欢迎接口
     * 返回简单的欢迎信息[1,4](@ref)
     */
    @GetMapping("/hello")
    @Operation(summary = "欢迎语", description = "获取系统欢迎信息")
    public String sayHello() {
        log.info("欢迎接口被调用");
        System.out.println("欢迎接口被调用");
        return "Hello, 欢迎使用报表服务系统!";
    }

    /**
     * 个性化欢迎接口
     * 支持传入名称参数进行个性化问候[4](@ref)
     */
    @GetMapping("/welcome")
    @Operation(summary = "个性化欢迎", description = "根据用户名返回个性化欢迎信息")
    public String personalizedWelcome(
            @Parameter(description = "用户名称", required = false, example = "张三")
            @RequestParam(required = false, defaultValue = "访客") String name) {
        log.info("个性化欢迎接口被调用，用户名: {}", name);
        return String.format("你好 %s，欢迎访问我们的服务系统！", name);
    }

    /**
     * 服务状态概览接口
     * 返回服务的详细状态信息[6,7](@ref)
     */
    @GetMapping("/status")
    @Operation(summary = "服务状态概览", description = "获取服务的详细状态信息")
    public Map<String, Object> serviceStatus() {
        log.info("服务状态概览接口被调用");

        Map<String, Object> statusInfo = new HashMap<>();
        statusInfo.put("service", "报表服务系统");
        statusInfo.put("status", "正常运行");
        statusInfo.put("timestamp", System.currentTimeMillis());
        statusInfo.put("uptime", "可用");
        statusInfo.put("version", "1.0.0");

        // 模拟一些系统指标
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("memoryUsage", "65%");
        metrics.put("activeConnections", 42);
        metrics.put("responseTime", "125ms");

        statusInfo.put("metrics", metrics);

        return statusInfo;
    }
}
