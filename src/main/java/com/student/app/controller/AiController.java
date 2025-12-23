package com.student.app.controller;

import com.student.app.bean.ChatMessage;
import com.student.app.service.AiAssistantService;
import com.student.app.service.ChatMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import java.util.List;
import java.util.Map;

/**
 * AI 助手控制器
 */
@Controller
@RequestMapping("/ai")
public class AiController {

    @Autowired
    private AiAssistantService aiAssistantService;

    @Autowired
    private ChatMemoryService chatMemoryService;

    /**
     * 获取当前用户名作为会话ID
     */
    private String getUserId(Authentication auth) {
        return auth != null ? auth.getName() : "anonymous";
    }

    /**
     * AI聊天页面
     */
    @GetMapping("/page")
    public String chatPage(Authentication auth, Model model) {
        String userId = getUserId(auth);
        List<ChatMessage> history = chatMemoryService.getFullHistory(userId);
        model.addAttribute("chatHistory", history);
        return "ai/chat";
    }

    /**
     * 流式聊天接口（带记忆）
     */
    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<String> chat(@RequestParam String message, Authentication auth) {
        String userId = getUserId(auth);

        // 保存用户消息
        chatMemoryService.saveUserMessage(userId, message);

        // 构建带上下文的消息
        String contextPrompt = chatMemoryService.buildContextPrompt(userId);
        String fullMessage = contextPrompt.isEmpty() ? message : contextPrompt + "\n\n" + message;

        // 收集AI回复
        StringBuilder aiResponse = new StringBuilder();

        return aiAssistantService.chat(fullMessage)
                .doOnNext(chunk -> aiResponse.append(chunk))
                .doOnComplete(() -> {
                    // 保存AI回复
                    if (aiResponse.length() > 0) {
                        chatMemoryService.saveAssistantMessage(userId, aiResponse.toString());
                    }
                });
    }

    /**
     * 获取聊天历史
     */
    @GetMapping("/history")
    @ResponseBody
    public List<ChatMessage> getHistory(Authentication auth) {
        return chatMemoryService.getFullHistory(getUserId(auth));
    }

    /**
     * 清除聊天历史
     */
    @PostMapping("/clear")
    @ResponseBody
    public Map<String, Object> clearHistory(Authentication auth) {
        chatMemoryService.clearHistory(getUserId(auth));
        return Map.of("success", true, "message", "对话已清除");
    }

    /**
     * 查看所有聊天记录（管理页面）
     */
    @GetMapping("/history/all")
    public String allHistory(Model model) {
        List<ChatMessage> messages = chatMemoryService.getAllMessages();
        model.addAttribute("messages", messages);
        return "ai/history";
    }
}
