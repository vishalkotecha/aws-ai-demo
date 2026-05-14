package com.example.awsaidemo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ChatConfig {

    @Bean
    public ChatClient.Builder chatClientBuilder(org.springframework.ai.chat.model.ChatModel chatModel) {
        return ChatClient.builder(chatModel);
    }



  /*  @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder().build();
    }
*/
  /*  @Bean
    public ChatMemory chatMemory() {
        return new SimpleChatMemory();
    }

    @Bean
    public ChatClient.Builder chatClientBuilder(org.springframework.ai.chat.model.ChatModel chatModel) {
        return ChatClient.builder(chatModel);
    }

    // ============ Simple In-Memory ChatMemory Implementation ============
    public static class SimpleChatMemory implements ChatMemory {
        private final Map<String, List<Message>> conversationHistory = new ConcurrentHashMap<>();

        @Override
        public void add(String conversationId, List<Message> messages) {
            conversationHistory.computeIfAbsent(conversationId, k -> new ArrayList<>())
                    .addAll(messages);
        }

        @Override
        public List<Message> get(String conversationId) {
            return new ArrayList<>(conversationHistory.getOrDefault(conversationId, new ArrayList<>()));
        }

        @Override
        public void clear(String conversationId) {
            conversationHistory.remove(conversationId);
        }
    }*/
}

