package com.energizeglobal.service.service;

import com.energizeglobal.common.model.entity.Book;
import com.energizeglobal.common.model.entity.TakenBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by test on 7/26/2016.
 */
@Service
public interface BookService {

    Page<Book> findAll(Pageable pageable);

    Book save(Book s, MultipartFile file, MultipartFile iamge);

    Book findOne(Long id);

    List<Book> findAll();

    void delete(Long id);

    TakenBook moveToBasket(Long id);

    List<TakenBook> findAllFromBasket();
}
