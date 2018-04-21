package com.bookstore.utility.comparators;

import com.bookstore.domain.Book;

import java.util.Comparator;

public class BookSalesComparator implements Comparator<Book> {

  private static int compare(int str1, int str2) {
    return (str2 - str1);
  }

  @Override
  public int compare(Book o1, Book o2) {
    return compare(o1.getSoldNumber(), o2.getSoldNumber());
  }
}
