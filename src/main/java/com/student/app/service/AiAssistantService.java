package com.student.app.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

/**
 * AI 就业助手服务接口
 */
@AiService
public interface AiAssistantService {

    @SystemMessage("""
            你是一个专业的高校就业指导助手，帮助学生解答就业相关问题。

            你的能力包括：
            1. 提供职业规划建议
            2. 分析行业发展趋势
            3. 指导简历撰写和面试技巧
            4. 解答就业政策相关问题
            5. 提供求职心理辅导

            请友好、专业地回答学生的问题。如果问题超出就业范围，礼貌地引导回就业话题。
            回答要简洁明了，分点列出，方便学生理解。
            """)
    Flux<String> chat(String userMessage);
}
