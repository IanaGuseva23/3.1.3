package com.example.pp_3_1_3_bootstrap.service;

import com.example.pp_3_1_3_bootstrap.models.Role;
import com.example.pp_3_1_3_bootstrap.models.User;
import com.example.pp_3_1_3_bootstrap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserRepository userRepository;
    private RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List <Role> getSetOfRoles (List <String> rolesId) {
        List<Role> roleSet = new ArrayList<>();
        for (String id : rolesId) {
            roleSet.add(roleService.getRoleById(Integer.parseInt(id)));
        }
        return roleSet;
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @Override
    public List<User>  findAll () {
        return userRepository.findAll();
    }

    @Override
    public void addUser (User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser (long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser (User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findOne(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostConstruct
    @Transactional
    public User addTestAdmin() {

        User admin = new User();
        admin.setUsername("admin");
        admin.setLastname("admin");
        admin.setAge(33);
        admin.setEmail("admin@mail.ru");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.addRole((new Role(admin.getId(), "Admin")));
        return userRepository.save(admin);
    }
}
