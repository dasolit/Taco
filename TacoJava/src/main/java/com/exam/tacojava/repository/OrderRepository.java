package com.exam.tacojava.repository;

import com.exam.tacojava.domain.Order;
import com.exam.tacojava.domain.User;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

  List<User> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
