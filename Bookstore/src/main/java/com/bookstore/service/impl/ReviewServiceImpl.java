package com.bookstore.service.impl;

import com.bookstore.domain.Book;
import com.bookstore.domain.Review;
import com.bookstore.domain.User;
import com.bookstore.repository.ReviewRepository;
import com.bookstore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;

  @Override
  public List<Review> findByUser(User user) {
    return reviewRepository.findByUser(user);
  }

  @Override
  public Review updateReview(Review review) {
    reviewRepository.save(review);
    return review;
  }

  @Override
  public Review findById(Long id) {
    return reviewRepository.findOne(id);
  }

  @Override
  public void removeReview(Review review) {
    reviewRepository.delete(review);
  }

  @Override
  public Review save(Review review) {
    return reviewRepository.save(review);
  }

  @Override
  public List<Review> findByBook(Book book) {
    return reviewRepository.findByBook(book);
  }
}
