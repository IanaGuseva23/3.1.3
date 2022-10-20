package com.example.pp_3_1_3_bootstrap.service;


import com.example.pp_3_1_3_bootstrap.models.Role;
import com.example.pp_3_1_3_bootstrap.models.User;

import java.util.List;

public interface UserService {
    List<User>  findAll ();
    User findOne(long id);
    void addUser (User user);
    void deleteUser (long id);
    void updateUser (User user);
    List <Role> getSetOfRoles (List <String> rolesId);
    User findByUsername(String username);
    String getCurrentUsername();
}
