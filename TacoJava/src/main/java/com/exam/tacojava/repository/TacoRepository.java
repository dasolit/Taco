package com.exam.tacojava.repository;

import com.exam.tacojava.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
