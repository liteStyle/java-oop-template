package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if(findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        } else {
            Author[] newAuthors = new Author[authors.length + 1];
            System.arraycopy(authors, 0, newAuthors, 0, authors.length);
            newAuthors[newAuthors.length - 1] = author;
            authors = newAuthors;
            return true;
        }
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        if(name == null || lastname == null) {
            return null;
        }

        for(Author auth : authors) {
            if (name.equals(auth.getName()) && lastname.equals(auth.getLastName())) {
                return auth;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {

        for(int i = 0; i < authors.length; i++) {
            if (author.getName().equals(authors[i].getName()) && author.getLastName().equals(authors[i].getLastName())) {

                Author[] newAuthors = new Author[authors.length - 1];
                if(i == 0) {
                    System.arraycopy(authors, i + 1, newAuthors, 0, newAuthors.length);
                }
                if(i == authors.length - 1) {
                    System.arraycopy(authors, 0, newAuthors, 0, newAuthors.length);
                }

                System.arraycopy(authors, 0, newAuthors, 0, i);
                System.arraycopy(authors, i + 1, newAuthors, i, newAuthors.length - i);
                authors = newAuthors;
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
            return authors.length;
    }
}
