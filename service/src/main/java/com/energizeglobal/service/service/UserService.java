package com.energizeglobal.service.service;


import com.energizeglobal.common.model.entity.User;

import java.util.List;

/**
 * Created by test on 7/26/2016.
 */
public interface UserService {
    List<User> findAll();

    User addUser(User user);

    User getUserById(Long id);

    User authenticate(String username, String password);


}
