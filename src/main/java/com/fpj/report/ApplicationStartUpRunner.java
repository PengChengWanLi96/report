package com.fpj.report;

import com.fpj.report.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author fangpengjun
 * @date 2025/12/13
 */
@Component
@Slf4j
public class ApplicationStartUpRunner implements ApplicationRunner {

    @Value("${server.port}")
    private String port;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String serverIp = IPUtil.getServerIP();
        String serverUrl = String.format("%s:%s", serverIp, port);
        String serverVisitUrl = String.format("http://%s/home.html", serverUrl);
        log.info("服务启动成功，访问地址：{}", serverVisitUrl);
    }
}