package com.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String author;
  private String publisher;
  private String publicationDate;
  private String language;
  private String category;
  private int numberOfPages;
  private String format;
  private String isbn;
  private double rating;

  private double shippingWeight;
  private double listPrice;
  private double ourPrice;
  private boolean active = true;

  @Column(columnDefinition = "text")
  private String description;
  private int inStockNumber;
  private int soldNumber;
  private String authorBio;

  @Transient
  private MultipartFile bookImage;

  @OneToMany(mappedBy = "book")
  @JsonIgnore
  private List<BookToCartItem> bookToCartItemList;

  @OneToMany(mappedBy = "book")
  private List<Review> reviewList;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public int getSoldNumber() {
    return soldNumber;
  }

  public void setSoldNumber(int soldNumber) {
    this.soldNumber = soldNumber;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public int getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(int numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public double getShippingWeight() {
    return shippingWeight;
  }

  public void setShippingWeight(double shippingWeight) {
    this.shippingWeight = shippingWeight;
  }

  public double getListPrice() {
    return listPrice;
  }

  public void setListPrice(double listPrice) {
    this.listPrice = listPrice;
  }

  public double getOurPrice() {
    return ourPrice;
  }

  public void setOurPrice(double ourPrice) {
    this.ourPrice = ourPrice;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAuthorBio() {
    return authorBio;
  }

  public void setAuthorBio(String authorBio) {
    this.authorBio = authorBio;
  }

  public int getInStockNumber() {
    return inStockNumber;
  }

  public void setInStockNumber(int inStockNumber) {
    this.inStockNumber = inStockNumber;
  }

  public MultipartFile getBookImage() {
    return bookImage;
  }

  public void setBookImage(MultipartFile bookImage) {
    this.bookImage = bookImage;
  }

  public List<BookToCartItem> getBookToCartItemList() {
    return bookToCartItemList;
  }

  public List<Review> getReviewList() {
    return reviewList;
  }

  public void setReviewList(List<Review> reviewList) {
    this.reviewList = reviewList;
  }


  public void setBookToCartItemList(List<BookToCartItem> bookToCartItemList) {
    this.bookToCartItemList = bookToCartItemList;
  }

}
