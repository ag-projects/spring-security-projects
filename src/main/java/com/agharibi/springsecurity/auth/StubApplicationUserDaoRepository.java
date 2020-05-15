package com.agharibi.springsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.agharibi.springsecurity.security.ApplicationUserRole.*;

@Repository("stub")
public class StubApplicationUserDaoRepository implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StubApplicationUserDaoRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return applicationUsers().stream()
            .filter(applicationUser -> username.equals(applicationUser.getUsername()))
            .findFirst();
    }

    private List<ApplicationUser> applicationUsers() {
        return Arrays.asList(

            new ApplicationUser("anna",
                passwordEncoder.encode("anna"),
                STUDENT.getGrantedAuthorities(),
                true,
                true,
                true,
                true),

            new ApplicationUser("linda",
                passwordEncoder.encode("linda"),
                ADMIN.getGrantedAuthorities(),
                true,
                true,
                true,
                true),

            new ApplicationUser("tom",
                passwordEncoder.encode("tom"),
                ADMIN_TRAINEE.getGrantedAuthorities(),
                true,
                true,
                true,
                true)
        );
    }
}
