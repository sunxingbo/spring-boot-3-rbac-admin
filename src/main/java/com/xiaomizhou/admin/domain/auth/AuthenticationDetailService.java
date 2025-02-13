package com.xiaomizhou.admin.domain.auth;

import com.xiaomizhou.admin.domain.api.ApiEntity;
import com.xiaomizhou.admin.domain.permission.PermissionEntity;
import com.xiaomizhou.admin.domain.role.RoleEntity;
import com.xiaomizhou.admin.domain.user.UserEntity;
import com.xiaomizhou.admin.domain.user.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhangyaxi
 * @email 521jx123@gmail.com
 * @date 2023/11/14
 */
public class AuthenticationDetailService implements UserDetailsService {
    @Resource
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户" + username + "不存在"));
        Set<PermissionEntity> permissions = new HashSet<>();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            for (RoleEntity role : user.getRoles()) {
                if (role.getPermissions() != null && !role.getPermissions().isEmpty()) {
                    permissions.addAll(role.getPermissions());
                    for (PermissionEntity permission : permissions) {
                        authorities = permission.getApis().stream()
                                .map(ApiEntity::getCode)
                                .distinct()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
                    }
                }
            }
        }
        return new UserInfo(user, authorities, permissions);
    }
}
