package com.bookstore.repository;

import com.bookstore.domain.Book;
import com.bookstore.domain.Review;
import com.bookstore.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReviewRepository extends CrudRepository<Review, Long> {
  List<Review> findByUser(User user);

  Review findById(Long id);

  List<Review> findByBook(Book book);

}
