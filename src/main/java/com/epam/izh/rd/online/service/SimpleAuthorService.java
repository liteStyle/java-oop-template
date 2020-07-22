package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.repository.AuthorRepository;
import com.epam.izh.rd.online.repository.SimpleAuthorRepository;

public class SimpleAuthorService implements AuthorService {
    private AuthorRepository authorRepository;

    public SimpleAuthorService() {
    }

    public SimpleAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean save(Author author) {
        if(authorRepository == null) {
            authorRepository = new SimpleAuthorRepository();
        }
        return authorRepository.save(author);
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        if(authorRepository == null) {
            authorRepository = new SimpleAuthorRepository();
        }
        return authorRepository.findByFullName(name, lastname);
    }

    @Override
    public boolean remove(Author author) {
        if(authorRepository == null) {
            authorRepository = new SimpleAuthorRepository();
        }
        return authorRepository.remove(author);
    }

    @Override
    public int count() {
        if(authorRepository == null) {
            authorRepository = new SimpleAuthorRepository();
        }
        return authorRepository.count();
    }
}
