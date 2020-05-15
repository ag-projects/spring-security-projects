package com.agharibi.springsecurity.auth;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUserName(String username);
}
