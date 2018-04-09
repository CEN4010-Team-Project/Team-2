package com.bookstore.service;

import com.bookstore.domain.Book;

import java.util.List;

public interface BookService {
  List<Book> findAll();

  Book findOne(Long id);

  List<Book> findByCategory(String category);

  List<Book> findByAuthor(String author);

  List<Book> sortByAuthor(List<Book> existent);

  List<Book> sortBySells(List<Book> existent);

  List<Book> sortByTitle(List<Book> existent);

  List<Book> blurrySearch(String title);
}
