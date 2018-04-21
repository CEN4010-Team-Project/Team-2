package com.bookstore.utility.comparators;

import com.bookstore.domain.Book;

import java.util.Comparator;

public class BookAuthorComparator implements Comparator<Book> {

  private static int compare(String str1, String str2) {
    return str1.compareToIgnoreCase(str2);
  }

  @Override
  public int compare(Book o1, Book o2) {
    return compare(o1.getAuthor(), o2.getAuthor());
  }
}
