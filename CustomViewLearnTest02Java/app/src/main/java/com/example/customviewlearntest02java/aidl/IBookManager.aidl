package com.example.customviewlearntest02java;
import com.example.customviewlearntest02java.Book;
interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
}