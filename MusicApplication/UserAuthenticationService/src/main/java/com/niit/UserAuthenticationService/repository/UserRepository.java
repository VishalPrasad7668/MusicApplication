package com.niit.UserAuthenticationService.repository;

import com.niit.UserAuthenticationService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public User findUserByUserIdAndPassword(String userId, String password);
}
