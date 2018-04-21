package com.bookstore.utility.comparators;

import com.bookstore.domain.Book;

import java.util.Comparator;

public class BookTitleComparator implements Comparator<Book> {

  private static int compare(String str1, String str2) {
    return str1.toLowerCase().compareTo(str2.toLowerCase());
  }

  @Override
  public int compare(Book o1, Book o2) {
    return compare(o1.getTitle(), o2.getTitle());
  }
}
