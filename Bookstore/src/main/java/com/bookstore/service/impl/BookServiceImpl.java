package com.bookstore.service.impl;

import com.bookstore.domain.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;
import com.bookstore.utility.BookAuthorComparator;
import com.bookstore.utility.BookTitleComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
  @Autowired
  private BookRepository bookRepository;

  public List<Book> findAll() {
    List<Book> bookList = (List<Book>) bookRepository.findAll();
    List<Book> activeBookList = new ArrayList<>();

    for (Book book : bookList) {
      if(book.isActive()) {
        activeBookList.add(book);
      }
    }

    return activeBookList;
  }

  public Book findOne(Long id) {
    return bookRepository.findOne(id);
  }

  public List<Book> findByCategory(String category) {
    List<Book> bookList = bookRepository.findByCategory(category);

    List<Book> activeBookList = new ArrayList<>();

    for (Book book : bookList) {
      if(book.isActive()) {
        activeBookList.add(book);
      }
    }

    return activeBookList;
  }

  @Override
  public List<Book> findByAuthor(String author) {
    List<Book> bookList = (List<Book>) bookRepository.findAll();

    List<Book> activeBookList = new ArrayList<>();

    for (Book book : bookList) {
      if(book.isActive() && book.getAuthor().equalsIgnoreCase(author)) {
        activeBookList.add(book);
      }
    }
    return activeBookList;
  }

  @Override
  public List<Book> sortByAuthor(List<Book> existent) {
    List<Book> bookList = existent;

    List<Book> activeBookList = new ArrayList<>();

    Collections.sort(bookList, new BookAuthorComparator());

    for (Book book : bookList) {
      if(book.isActive()) {
        activeBookList.add(book);
      }
    }
    return activeBookList;
  }

  @Override
  public List<Book> sortBySells(List<Book> existent) {
    return null;
  }

  public List<Book> blurrySearch(String title) {
    List<Book> bookList = bookRepository.findByTitleContaining(title);
    List<Book> activeBookList = new ArrayList<>();

    for (Book book : bookList) {
      if(book.isActive()) {
        activeBookList.add(book);
      }
    }

    return activeBookList;
  }

  @Override
  public List<Book> sortByTitle(List<Book> existent) {
    List<Book> bookList = existent;

    List<Book> activeBookList = new ArrayList<>();

    Collections.sort(bookList, new BookTitleComparator());

    for (Book book : bookList) {
      if(book.isActive()) {
        activeBookList.add(book);
      }
    }
    return activeBookList;
  }

}
