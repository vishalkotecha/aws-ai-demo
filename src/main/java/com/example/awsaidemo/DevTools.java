package com.example.awsaidemo;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DevTools {

    // ============ Tool Definitions (DevTools) ============
    @Tool(description = "Get Java version of the project")
    public String getJavaVersion() {
        return "Java 21";
    }

    @Tool(description = "Get Spring Boot version")
    public String getSpringBootVersion() {
        return "Spring Boot 4.0.6";
    }

    @Tool(description = "Get current date time")
    public String getCurrentDateTime() {
        return LocalDateTime.now().toString();
    }
}
