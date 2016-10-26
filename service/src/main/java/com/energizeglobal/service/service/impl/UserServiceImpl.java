package com.energizeglobal.service.service.impl;

import com.energizeglobal.common.model.entity.User;
import com.energizeglobal.service.repository.UserRepository;
import com.energizeglobal.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by test on 7/26/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User authenticate(String username, String password) {
        return null;
    }

    public User getUserByCretentials(String email, String password) {

        return userRepository.findByEmailAndPassword(email, password);

    }
}
