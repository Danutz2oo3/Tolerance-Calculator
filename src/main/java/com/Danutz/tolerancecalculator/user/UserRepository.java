package com.Danutz.tolerancecalculator.user;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    UserEntity findUserByUsernameAndPassword(String username, String password);

    UserEntity findUserByEmailAndPassword(String email, String password);

    UserEntity findUserByUsername(String username);
    UserEntity findUserByEmail(String email);

    UserEntity findUserById(Long id);
}
