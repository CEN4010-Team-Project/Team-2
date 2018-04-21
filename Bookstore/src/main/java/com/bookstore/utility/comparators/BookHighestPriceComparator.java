package com.bookstore.utility.comparators;

import com.bookstore.domain.Book;

import java.util.Comparator;

public class BookHighestPriceComparator implements Comparator<Book> {

  private static int compare(double str1, double str2) {
    double op = str2 - str1;
    if(op > 0) {
      return 1;
    }
    if(op < 0) {
      return -1;
    }
    return 0;
  }

  @Override
  public int compare(Book o1, Book o2) {
    return compare(o1.getOurPrice(), o2.getOurPrice());
  }
}
