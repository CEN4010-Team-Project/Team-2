package com.bookstore.controller;

import com.bookstore.domain.Book;
import com.bookstore.domain.User;
import com.bookstore.service.BookService;
import com.bookstore.service.UserService;
import com.bookstore.utility.comparators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SearchController {
  @Autowired
  private UserService userService;

  @Autowired
  private BookService bookService;

  @RequestMapping("/searchByCategory")
  public String searchByCategory(
      @RequestParam("category") String category,
      Model model, Principal principal
  ) {
    if(principal != null) {
      String username = principal.getName();
      User user = userService.findByUsername(username);
      model.addAttribute("user", user);
    }

    String classActiveCategory = "active" + category;
    classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
    classActiveCategory = classActiveCategory.replaceAll("&", "");
    model.addAttribute(classActiveCategory, true);

    List<Book> bookList = bookService.findByCategory(category);

    if(bookList.isEmpty()) {
      model.addAttribute("emptyList", true);
      return "bookshelf";
    }
    List<Long> bookIds = bookList.parallelStream().map(Book::getId).collect(Collectors.toList());

    model.addAttribute("bookIds", bookIds);
    model.addAttribute("bookList", bookList);

    return "bookshelf";
  }

  @RequestMapping("/searchBook")
  public String searchBook(
      @RequestParam("keyword") String keyword,
      Principal principal, Model model
  ) {
    if(principal != null) {
      String username = principal.getName();
      User user = userService.findByUsername(username);
      model.addAttribute("user", user);
    }

    List<Book> bookList = bookService.blurrySearch(keyword);

    if(bookList.isEmpty()) {
      model.addAttribute("emptyList", true);
      return "bookshelf";
    }
    List<Long> bookIds = bookList.parallelStream().map(Book::getId).collect(Collectors.toList());

    model.addAttribute("bookIds", bookIds);
    model.addAttribute("bookList", bookList);

    return "bookshelf";
  }

  @RequestMapping("/searchTopSellers")
  public String searchAuthor(
      Model model, Principal principal
  ) {
    if(principal != null) {
      String username = principal.getName();
      User user = userService.findByUsername(username);
      model.addAttribute("user", user);
    }

    String classActiveCategory = "active" + "TopSeller";
    classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
    classActiveCategory = classActiveCategory.replaceAll("&", "");
    model.addAttribute(classActiveCategory, true);

    List<Book> bookList = bookService.findAll().parallelStream().filter(e -> e.getSoldNumber() > 0)
        .sorted(new BookSalesComparator()).collect(Collectors.toList());
    if(bookList.size() > 10) {
      bookList = bookList.subList(0, 9);
    }
    if(bookList.isEmpty()) {
      model.addAttribute("emptyList", true);
      return "bookshelf";
    }
    List<Long> bookIds = bookList.parallelStream().map(Book::getId).collect(Collectors.toList());

    model.addAttribute("bookIds", bookIds);
    model.addAttribute("bookList", bookList);

    return "bookshelf";
  }

  @RequestMapping("/searchByRatings")
  public String searchByRatings(
      @RequestParam("baseLine") int baseLine,
      Model model, Principal principal
  ) {
    if(principal != null) {
      String username = principal.getName();
      User user = userService.findByUsername(username);
      model.addAttribute("user", user);
    }

    String classActiveCategory = "active" + baseLine + "Stars";
    classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
    classActiveCategory = classActiveCategory.replaceAll("&", "");
    model.addAttribute(classActiveCategory, true);

    List<Book> bookList = bookService.findAll();

    bookList = bookList.parallelStream().filter(e -> e.getRating() >= baseLine)
        .sorted(new BookRatingsComparator()).collect(Collectors.toList());

    if(bookList.isEmpty()) {
      model.addAttribute("emptyList", true);
      return "bookshelf";
    }
    List<Long> bookIds = bookList.parallelStream().map(Book::getId).collect(Collectors.toList());

    model.addAttribute("bookIds", bookIds);
    model.addAttribute("bookList", bookList);

    return "bookshelf";
  }

  @RequestMapping("/bestSeller")
  public String searchByRatings(
      Model model, Principal principal
  ) {
    if(principal != null) {
      String username = principal.getName();
      User user = userService.findByUsername(username);
      model.addAttribute("user", user);
    }

    List<Book> bookList = bookService.findAll().parallelStream().filter(e -> e.getSoldNumber() > 0)
        .sorted(new BookSalesComparator()).collect(Collectors.toList());

    if(bookList.isEmpty()) {
      model.addAttribute("emptyList", true);
      return "index";
    }

    return "forward:/bookDetail?id=" + bookList.get(0).getId();
  }

  @RequestMapping("/sort")
  public String sort(
      @RequestParam("bookListed") List<String> bookListed,
      @RequestParam("sort") String sortType,
      //      @RequestParam("active") String activated,
      Model model, Principal principal
  ) {
    if(principal != null) {
      String username = principal.getName();
      User user = userService.findByUsername(username);
      model.addAttribute("user", user);
    }
    Comparator comparator = null;

    if(sortType.equalsIgnoreCase("Sales")) {
      comparator = new BookSalesComparator();
    } else if(sortType.equalsIgnoreCase("Title")) {
      comparator = new BookTitleComparator();
    } else if(sortType.equalsIgnoreCase("Author")) {
      comparator = new BookAuthorComparator();
    } else if(sortType.equalsIgnoreCase("Ratings")) {
      comparator = new BookRatingsComparator();
    } else if(sortType.equalsIgnoreCase("Date")) {
      comparator = new BookDateComparator();
    } else if(sortType.equalsIgnoreCase("HighestPrice")) {
      comparator = new BookHighestPriceComparator();
    } else if(sortType.equalsIgnoreCase("LowestPrice")) {
      comparator = new BookLowestPriceComparator();
    } else if(sortType.equalsIgnoreCase("Deals")) {
      comparator = new BookDealsComparator();
    }


    String classActiveCategory = "active" + sortType;
    classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
    classActiveCategory = classActiveCategory.replaceAll("&", "");
    model.addAttribute(classActiveCategory, true);

    List<Book> bookList =
        bookListed.parallelStream().map(e -> bookService.findOne(Long.parseLong(e)))
            .collect(Collectors.toList());
    Collections.sort(bookList, comparator);

    if(bookList.isEmpty()) {
      model.addAttribute("emptyList", true);
      return "bookshelf";
    }

    List<Long> bookIds = bookList.parallelStream().map(Book::getId).collect(Collectors.toList());

    model.addAttribute("bookIds", bookIds);
    model.addAttribute("bookList", bookList);

    return "bookshelf";
  }


}

