package com.bookstore.service;

import com.bookstore.domain.Book;
import com.bookstore.domain.Review;
import com.bookstore.domain.User;

import java.util.List;

public interface ReviewService {
  List<Review> findByUser(User user);

  Review updateReview(Review review);

  Review findById(Long id);

  void removeReview(Review review);

  Review save(Review review);

  List<Review> findByBook(Book book);

  Review findByBookAndUser(Book book, User user);

}
