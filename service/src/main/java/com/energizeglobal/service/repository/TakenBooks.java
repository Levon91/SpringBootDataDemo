package com.energizeglobal.service.repository;

import com.energizeglobal.common.model.entity.TakenBook;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by test on 7/26/2016.
 */
public interface TakenBooks extends PagingAndSortingRepository<TakenBook, Long> {

    @Override
    TakenBook save(TakenBook s);

//    @Query(value = "select count(b) from TakenBook b where b.status = ?1")
//    int findByStatus(int status);


//    @Query(value = "select '*' from TakenBook b where b.status = ?1")
//    List<TakenBook> findAllByStatus(int status);
}
