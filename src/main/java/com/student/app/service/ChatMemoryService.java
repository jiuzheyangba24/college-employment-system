package com.student.app.service;

import com.student.app.bean.ChatMessage;
import com.student.app.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

/**
 * 聊天记忆服务
 */
@Service
public class ChatMemoryService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    /**
     * 保存用户消息
     */
    public void saveUserMessage(String sessionId, String content) {
        ChatMessage message = new ChatMessage(sessionId, "user", content);
        chatMessageRepository.save(message);
    }

    /**
     * 保存AI回复
     */
    public void saveAssistantMessage(String sessionId, String content) {
        ChatMessage message = new ChatMessage(sessionId, "assistant", content);
        chatMessageRepository.save(message);
    }

    /**
     * 获取会话历史（最近10条）
     */
    public List<ChatMessage> getRecentHistory(String sessionId) {
        List<ChatMessage> messages = chatMessageRepository.findTop10BySessionIdOrderByCreatedAtDesc(sessionId);
        Collections.reverse(messages); // 按时间正序
        return messages;
    }

    /**
     * 获取完整会话历史
     */
    public List<ChatMessage> getFullHistory(String sessionId) {
        return chatMessageRepository.findBySessionIdOrderByCreatedAtAsc(sessionId);
    }

    /**
     * 清除会话历史
     */
    public void clearHistory(String sessionId) {
        chatMessageRepository.deleteBySessionId(sessionId);
    }

    /**
     * 获取所有聊天消息（管理用）
     */
    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll(org.springframework.data.domain.Sort.by(
                org.springframework.data.domain.Sort.Direction.DESC, "createdAt"));
    }

    /**
     * 构建上下文提示词
     */
    public String buildContextPrompt(String sessionId) {
        List<ChatMessage> history = getRecentHistory(sessionId);
        if (history.isEmpty()) {
            return "";
        }

        StringBuilder context = new StringBuilder();
        context.append("以下是之前的对话记录：\n");
        for (ChatMessage msg : history) {
            String roleLabel = "user".equals(msg.getRole()) ? "学生" : "助手";
            context.append(roleLabel).append(": ").append(msg.getContent()).append("\n");
        }
        context.append("\n请基于以上对话继续回答：");
        return context.toString();
    }
}
