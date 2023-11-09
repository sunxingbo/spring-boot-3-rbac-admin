package com.xiaomizhou.admin.domain.auth;

import com.xiaomizhou.admin.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author zhangyaxi
 * @email 521jx123@gmail.com
 * @date 2023/11/9
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationUser extends UserEntity implements UserDetails {

    public AuthenticationUser(UserEntity entity) {
        this();
        BeanUtils.copyProperties(entity, this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
