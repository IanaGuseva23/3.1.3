package com.example.pp_3_1_3_bootstrap.repository;

import com.example.pp_3_1_3_bootstrap.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
