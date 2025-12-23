package com.student.app.config;

import com.student.app.bean.User;
import com.student.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 应用启动时初始化管理员用户
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 检查是否已有admin用户
        User admin = userMapper.findByUsername("admin");

        if (admin == null) {
            // 创建admin用户
            User newAdmin = new User();
            newAdmin.setUsername("admin");
            newAdmin.setPassword(passwordEncoder.encode("123456"));
            newAdmin.setNickname("管理员");
            newAdmin.setStatus("启用");
            userMapper.insert(newAdmin);
            System.out.println(">>> Admin user created successfully!");
        } else {
            // 更新admin密码确保正确
            String encodedPassword = passwordEncoder.encode("123456");
            userMapper.updatePassword(admin.getId(), encodedPassword);
            System.out.println(">>> Admin password updated successfully!");
        }

        // 创建贾晨璇管理员账号
        User jcx = userMapper.findByUsername("jiachenxuan");
        if (jcx == null) {
            User newUser = new User();
            newUser.setUsername("jiachenxuan");
            newUser.setPassword(passwordEncoder.encode("123456"));
            newUser.setNickname("贾晨璇");
            newUser.setStatus("启用");
            userMapper.insert(newUser);
            System.out.println(">>> User jiachenxuan created successfully!");
        } else {
            // 确保密码正确
            userMapper.updatePassword(jcx.getId(), passwordEncoder.encode("123456"));
            System.out.println(">>> User jiachenxuan password updated!");
        }

        // 创建农豪进管理员账号
        User nhj = userMapper.findByUsername("nonghaojin");
        if (nhj == null) {
            User newUser = new User();
            newUser.setUsername("nonghaojin");
            newUser.setPassword(passwordEncoder.encode("123456"));
            newUser.setNickname("农豪进");
            newUser.setStatus("启用");
            userMapper.insert(newUser);
            System.out.println(">>> User nonghaojin created successfully!");
        } else {
            userMapper.updatePassword(nhj.getId(), passwordEncoder.encode("123456"));
            System.out.println(">>> User nonghaojin password updated!");
        }
    }
}
