package com.bookstore.utility.comparators;

import com.bookstore.domain.Book;

import java.util.Comparator;

public class BookDateComparator implements Comparator<Book> {

  private static int compare(String str1, String str2) {
    return str2.compareToIgnoreCase(str1);
  }

  @Override
  public int compare(Book o1, Book o2) {
    return compare(o1.getPublicationDate(), o2.getPublicationDate());
  }
}
