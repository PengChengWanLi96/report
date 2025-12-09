package com.fpj.report;

import com.fpj.report.config.VersionConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Report {
    public static void main(String[] args) {

        VersionConfig.printVersion(args);

        SpringApplication.run(Report.class, args);
    }
}
