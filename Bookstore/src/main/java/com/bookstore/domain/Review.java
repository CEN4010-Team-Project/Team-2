package com.bookstore.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_review")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Date reviewDate;
  private double stars;
  private boolean anonymous;

  @Column(columnDefinition = "text")
  private String comment;

  @ManyToOne
  private User user;

  @ManyToOne
  private Book book;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getReviewDate() {
    return reviewDate;
  }

  public void setReviewDate(Date reviewDate) {
    this.reviewDate = reviewDate;
  }

  public double getStars() {
    return stars;
  }

  public void setStars(double stars) {
    this.stars = stars;
  }

  public boolean isAnonymous() {
    return anonymous;
  }

  public void setAnonymous(boolean anonymous) {
    this.anonymous = anonymous;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}
