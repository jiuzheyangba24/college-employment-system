package com.student.app.repository;

import com.student.app.bean.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 聊天消息数据访问接口（MongoDB）
 */
@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {

    /**
     * 根据会话ID查询聊天记录，按时间排序
     */
    List<ChatMessage> findBySessionIdOrderByCreatedAtAsc(String sessionId);

    /**
     * 根据会话ID查询最近N条消息
     */
    List<ChatMessage> findTop10BySessionIdOrderByCreatedAtDesc(String sessionId);

    /**
     * 删除会话的所有消息
     */
    void deleteBySessionId(String sessionId);
}
