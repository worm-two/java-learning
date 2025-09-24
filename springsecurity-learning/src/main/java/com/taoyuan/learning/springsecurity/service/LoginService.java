package com.taoyuan.learning.springsecurity.service;

import com.taoyuan.learning.entity.mybatis.plus.SystemUser;
import com.taoyuan.learning.springsecurity.config.SystemUserDetails;
import com.taoyuan.learning.springsecurity.util.JwtUtil;
import com.taoyuan.sun.common.global.result.Result;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-19 15:37
 * @Description:
 * @Version: 1.0
 */
@Service
public class LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public Result<SystemUser> login(SystemUser systemUser) {
        // 一 进行用户认证(调用SystemUserDetailsService类的loadUserByUsername方法,判断用户密码是否一致)
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(systemUser.getUsername(), systemUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        // 如果用户输入密码和数据库密码一致,authenticate为null
        if (authenticate == null) {
            return Result.fail("登陆失败");
        }

        // 二 获取自定义用户信息
        SystemUserDetails principal = (SystemUserDetails) authenticate.getPrincipal();
        SystemUser user =  principal.getSystemUser();
        Long id = user.getId();

        // 三 使用jwt跟用户信息生成token
        String jwt = JwtUtil.createJWT(id.toString());

        // 四 将用户信息保存到redis,如果请求头携带token,就在redis查询用户信息
        redisTemplate.opsForValue().set("login:" + id, principal);

        return Result.success(systemUser).other(jwt);
    }

    public Result<String> logout() {
        // 一 获取SecurityContextHolder中的用户id
       UsernamePasswordAuthenticationToken token= (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        SystemUserDetails principal = (SystemUserDetails) token.getPrincipal();

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();


        Long id = principal.getSystemUser().getId();

        String redisKey="login:"+id;

        redisTemplate.delete(redisKey);


        // 二 删除redis中的数据
        return Result.success("注销成功");
    }

    public Result<String> test() {


        return Result.success("孤舟蓑笠翁,独钓寒江雪");
    }
}
