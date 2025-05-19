package com.telu.schoolmanagement.common.security;

import com.telu.schoolmanagement.users.model.Users;
import com.telu.schoolmanagement.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.getUsersByNip(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserBuilder builder = org.springframework.security.core.userdetails.User
                .withUsername(user.getNip());
        builder.password(user.getPassword());
//        builder.roles(user.getRole().getName()); // ROLE_ADMIN, ROLE_USER, dll

        return builder.build();
    }
}
