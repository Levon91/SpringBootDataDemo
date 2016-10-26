package com.energizeglobal.service.service.impl;

import com.energizeglobal.common.FileUtil;
import com.energizeglobal.common.model.entity.Book;
import com.energizeglobal.common.model.entity.TakenBook;
import com.energizeglobal.service.repository.BookRepository;
import com.energizeglobal.service.repository.TakenBooks;
import com.energizeglobal.service.service.BookService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.energizeglobal.common.Constants.FULL_STORAGE_PATH;


/**
 * Created by test on 7/26/2016.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TakenBooks takenBooks;

//    @Autowired
//    protected SessionFactory sessionFactory;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book save(Book book, MultipartFile file, MultipartFile image) {
        Book dbBook = null;
        try {
            book.setAddedDate(new Date());
            book.setName(file.getOriginalFilename());
            dbBook = bookRepository.save(book);
            String fileSavePath = FULL_STORAGE_PATH + dbBook.getId() + "\\";
            dbBook.setFileUri("data\\" + dbBook.getId() + "\\" + file.getOriginalFilename());
            dbBook.setImageUri("data\\" + dbBook.getId() + "\\" + image.getOriginalFilename());
            if (file.getInputStream() != null) {
                FileUtil.createBookFolderById(fileSavePath);
                FileUtil.saveFile(file.getInputStream(), fileSavePath, file.getOriginalFilename());
            }
            if (image.getInputStream() != null) {
                FileUtil.saveFile(image.getInputStream(), fileSavePath, image.getOriginalFilename());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookRepository.save(dbBook);
        return dbBook;
    }

    @Override
    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public TakenBook moveToBasket(Long id) {
        Book book = findOne(id);
        TakenBook takenBook = new TakenBook();
        takenBook.setBook(book);
        takenBook.setTakenDate(new Date());
        return takenBooks.save(takenBook);
    }

    @Override
    public List<TakenBook> findAllFromBasket() {
//        Session session = null;
//        List list = null;
//        try {
//            session = sessionFactory.openSession();
//            // Create a Hibernate query (HQL)
//            Query query = session.createQuery("FROM taken_books");
//            // Retrieve all
//            list = query.list();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//
//        return list;
        List<TakenBook> books = (List<TakenBook>) takenBooks.findAll();
        return books;
    }
}
