package com.student.app.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

/**
 * 聊天消息实体（MongoDB）
 */
@Data
@Document(collection = "chat_messages")
public class ChatMessage {

    @Id
    private String id;

    /** 会话ID */
    private String sessionId;

    /** 消息角色：user/assistant */
    private String role;

    /** 消息内容 */
    private String content;

    /** 创建时间 */
    private LocalDateTime createdAt;

    public ChatMessage() {
        this.createdAt = LocalDateTime.now();
    }

    public ChatMessage(String sessionId, String role, String content) {
        this.sessionId = sessionId;
        this.role = role;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
