package com.energizeglobal.service.repository;


import com.energizeglobal.common.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by test on 7/26/2016.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    @Override
    List<User> findAll();

    @Override
    User save(User u);

    @Override
    void delete(User u);

    @Override
    Page<User> findAll(Pageable var1);

    @Override
    User findOne(String s);

    @Override
    boolean exists(String s);

    @Override
    long count();

    @Override
    void delete(String s);

    User findByEmailAndPassword(String email, String password);

    User findById(Long id);

}