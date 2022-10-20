package com.example.pp_3_1_3_bootstrap.service;

import com.example.pp_3_1_3_bootstrap.models.Role;
import com.example.pp_3_1_3_bootstrap.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Collection<Role> getRoleList() {
        return roleRepository.findAll();
    }

    public Role getRoleById(long id) {
        return roleRepository.getById(id);
    }


}
