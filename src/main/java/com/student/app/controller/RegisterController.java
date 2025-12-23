package com.student.app.controller;

import com.student.app.bean.User;
import com.student.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * 用户注册控制器
 */
@Controller
public class RegisterController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 注册页面
     */
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    /**
     * 处理注册请求
     */
    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        String nickname = params.get("nickname");

        // 验证参数
        if (username == null || username.trim().isEmpty()) {
            return Map.of("success", false, "message", "用户名不能为空");
        }
        if (password == null || password.length() < 6) {
            return Map.of("success", false, "message", "密码至少6位");
        }

        // 检查用户名是否已存在
        if (userMapper.findByUsername(username) != null) {
            return Map.of("success", false, "message", "用户名已存在");
        }

        // 创建用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setNickname(nickname != null ? nickname : username);
        user.setStatus("启用");

        userMapper.insert(user);

        return Map.of("success", true, "message", "注册成功，请登录");
    }
}
