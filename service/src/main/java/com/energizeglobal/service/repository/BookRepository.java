package com.energizeglobal.service.repository;


import com.energizeglobal.common.model.entity.Book;
import com.energizeglobal.common.model.entity.TakenBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by test on 7/26/2016.
 */
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @Override
    Page<Book> findAll(Pageable pageable);

    @Override
    Book save(Book s);

    @Override
    Book findOne(Long id);

    @Override
    List<Book> findAll();

    List<Book> findByFileUriIsNotNull();

}
