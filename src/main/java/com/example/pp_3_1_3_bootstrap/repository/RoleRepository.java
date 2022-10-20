package com.example.pp_3_1_3_bootstrap.repository;


import com.example.pp_3_1_3_bootstrap.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
