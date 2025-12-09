package com.fpj.report.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author fangpengjun
 * @date 2024/11/22
 */
public class VersionConfig {

    /**
     * Git构建信息文件名称
     */
    private static final String GIT_BUILD_FILE_NAME = "git.properties";
    private static final String GIT_BRANCH_NAME = "git.branch";
    private static final String GIT_BUILD_TIME = "git.build.time";
    private static final String GIT_COMMIT_ID = "git.commit.id";
    private static final String GIT_COMMIT_ID_SHORT = "git.commit.id.abbrev";
    private static final String GIT_COMMIT_TIME = "git.commit.time";
    private static final String GIT_COMMIT_MESSAGE_FULL = "git.commit.message.full";
    private static final String GIT_COMMIT_MESSAGE_SHORT = "git.commit.message.short";

    private static final String DEFAULT_CLOUDLOG_VERSION = "Report v1.0";

    public static void printVersion(String[] args) {
        Boolean printVersionStatus = Boolean.FALSE;
        for (String arg : args) {
            if ("--help".equals(arg) || "-h".equals(arg)) {
                System.out.println(String.format("查看帮助信息"));
                System.out.println(String.format("用法及参数"));
                System.out.println(String.format("-h 或 --help    : %s", "查看帮助信息，可以使用的命令"));
                System.out.println(String.format("-v 或 --version : %s", "查看服务版本信息"));
                System.out.println(String.format("-d 或 --detail  : %s", "查看服务详细信息"));
                printVersionStatus = Boolean.TRUE;
            }
            // 检查参数是否为"--version"
            if ("--version".equals(arg) || "-v".equals(arg)) {
                System.out.println(String.format("Server Version  : %s", DEFAULT_CLOUDLOG_VERSION));
                System.out.println(String.format("Server Build    : %s", getPropertyValue(GIT_BUILD_TIME)));
                System.out.println(String.format("OS Name:        : %s", System.getProperty("os.name")));
                System.out.println(String.format("OS Version      : %s", System.getProperty("os.version")));
                System.out.println(String.format("OS Arch         : %s", System.getProperty("os.arch")));
                System.out.println(String.format("JVM Version     : %s", System.getProperty("java.version")));
                System.out.println(String.format("JVM Vendor      : %s", System.getProperty("java.vendor")));
                printVersionStatus = Boolean.TRUE;
            }
            if ("--detail".equals(arg) || "-d".equals(arg)) {
                System.out.println(String.format("Server Id       : %s", getPropertyValue(GIT_COMMIT_ID)));
                System.out.println(String.format("Server Short Id : %s", getPropertyValue(GIT_COMMIT_ID_SHORT)));
                System.out.println(String.format("Server Branch   : %s", getPropertyValue(GIT_BRANCH_NAME)));
                System.out.println(String.format("Server Commit   : %s", getPropertyValue(GIT_COMMIT_TIME)));
                System.out.println(String.format("Server Short Msg: %s", getPropertyValue(GIT_COMMIT_MESSAGE_SHORT)));
                printVersionStatus = Boolean.TRUE;
            }
        }
        if (printVersionStatus) {
            System.exit(0);
        }
    }

    public static final String getPropertyValue(String key) {
        Properties props = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = VersionConfig.class.getClassLoader().getResourceAsStream(GIT_BUILD_FILE_NAME);
            if (inputStream != null) {
                props.load(inputStream);
                return props.getProperty(key);
            } else {
                return "";
            }
        } catch (IOException e) {
            return "";
        }
    }
}
