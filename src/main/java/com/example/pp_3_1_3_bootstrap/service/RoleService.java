package com.example.pp_3_1_3_bootstrap.service;

import com.example.pp_3_1_3_bootstrap.models.Role;

import java.util.Collection;

public interface RoleService {

    Collection<Role> getRoleList();
    Role getRoleById(long id);
}
