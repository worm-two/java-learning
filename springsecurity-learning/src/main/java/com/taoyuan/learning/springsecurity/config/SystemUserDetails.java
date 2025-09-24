package com.taoyuan.learning.springsecurity.config;

import cn.hutool.core.collection.CollectionUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taoyuan.learning.entity.mybatis.plus.SystemUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yuming
 * @CreateTime: 2025-09-19 12:24
 * @Description:
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class SystemUserDetails implements UserDetails {

    /**
     * 封装用户信息
     */
    private SystemUser systemUser;

    /**
     * 封装用户权限
     */
    private List<String> permissions;

    @JsonIgnore
    List<SimpleGrantedAuthority> authorities;

    public SystemUserDetails(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public SystemUserDetails(SystemUser systemUser, List<String> permissions) {
        this.systemUser = systemUser;
        this.permissions = permissions;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 检查账户是否过期。
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        // 检查账户是否被锁定。
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 检查凭据（密码）是否过期。
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        // 检查账户是否启用。
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtil.isEmpty(authorities)) {
            authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getUsername();
    }
}
