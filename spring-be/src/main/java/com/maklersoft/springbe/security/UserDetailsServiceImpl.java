package com.maklersoft.springbe.security;

import com.maklersoft.springbe.models.User;
import com.maklersoft.springbe.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) {
        User user = userRepository.findByPhone(phone).get(0);
        if (user == null) {
            throw new UsernameNotFoundException(phone);
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }

}
