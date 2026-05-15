package com.example.awsaidemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Slf4j
@SpringBootApplication
@RestController
@RequestMapping("/ai")
public class AwsaidemoApplication {

    private ChatClient chatClient;

    public static void main(String[] args) {
        SpringApplication.run(AwsaidemoApplication.class, args);
    }

    public AwsaidemoApplication(ChatClient.Builder builder, DevTools devTools,
                                @Autowired(required = false)
                                ToolCallbackProvider toolCallbackProvider) {
        ChatClient.Builder  configured = builder
                .defaultTools(devTools)
                ;

        if (toolCallbackProvider != null) {
          configured = configured.defaultToolCallbacks(toolCallbackProvider);
        }

        this.chatClient = configured.build();
    }

    // ============ REST Controller Endpoints ============
    @PostMapping("/ask")
    public String ask(@RequestBody String input) {
        final var response = askQuestion(input);
        System.out.println(response);
        return response;
    }

    // ============ Service Logic ============
    public String askQuestion(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();

    }



    @Bean
    ApplicationRunner showMcpTools(@Autowired(required = false) ToolCallbackProvider mcpTools) {
        return args -> {
            if (mcpTools == null) {
                log.info("=== No MCP Tools configured ===");
                return;
            }
            log.info("=== MCP Tools Loaded === {}", mcpTools.getToolCallbacks().length);
            Arrays.stream(mcpTools.getToolCallbacks())
                    .forEach(t -> log.info("  → " + t.getToolDefinition().name()
                            + ": " + t.getToolDefinition().description()));
        };
    }
}
