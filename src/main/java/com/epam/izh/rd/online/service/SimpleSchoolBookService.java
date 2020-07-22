package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;
import com.epam.izh.rd.online.repository.SimpleSchoolBookRepository;

public class SimpleSchoolBookService implements  BookService<SchoolBook> {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private AuthorService authorService;

    public SimpleSchoolBookService() {
    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(SchoolBook book) {
        if(authorService == null) {
            authorService = new SimpleAuthorService();
        }
        if(authorService.findByFullName(book.getAuthorName(), book.getAuthorLastName()) == null) {
            return false;
        } else {
            if(schoolBookBookRepository == null) {
                schoolBookBookRepository = new SimpleSchoolBookRepository();
            }
            schoolBookBookRepository.save(book);
        }
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        if(schoolBookBookRepository == null) {
            schoolBookBookRepository = new SimpleSchoolBookRepository();
        }

        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        if(schoolBookBookRepository == null) {
            return 0;
        }
        SchoolBook[] books = findByName(name);
        return books.length;
    }

    @Override
    public boolean removeByName(String name) {
        if(schoolBookBookRepository == null) {
            schoolBookBookRepository = new SimpleSchoolBookRepository();
        }

        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        if(schoolBookBookRepository == null) {
            schoolBookBookRepository = new SimpleSchoolBookRepository();
        }

        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
        SchoolBook[] books = findByName(name);
        if(books.length == 0) {
            return null;
        }
        if(authorService == null) {
            authorService = new SimpleAuthorService();
        }

        return authorService.findByFullName(books[0].getAuthorName(), books[0].getAuthorLastName());
    }
}
