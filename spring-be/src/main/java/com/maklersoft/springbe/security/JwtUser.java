package com.maklersoft.springbe.security;

import com.maklersoft.springbe.models.utils.Confirmation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {
    private final String id;
    private final String accountId;
    private final String name;
    private final String lastName;
    private final String password;
    private final Collection<Confirmation> phones;
    private final Collection<Confirmation> emails;
    private final boolean enabled;
    private final LocalDateTime lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(String id,
                   String accountId,
                   String name,
                   String lastName,
                   String password,
                   Collection<Confirmation> phones,
                   Collection<Confirmation> emails,
                   Collection<? extends GrantedAuthority> authorities,
                   boolean enabled,
                   LocalDateTime lastPasswordResetDate) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.phones = phones;
        this.emails = emails;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
    }

    public String getId(){return id;}

    public String getAccountId(){return accountId;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
        return enabled;
    }
}
