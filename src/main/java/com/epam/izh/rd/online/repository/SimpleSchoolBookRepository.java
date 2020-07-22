package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] newSchoolBooks = new SchoolBook[schoolBooks.length + 1];
        System.arraycopy(schoolBooks, 0, newSchoolBooks, 0, schoolBooks.length);
        newSchoolBooks[newSchoolBooks.length - 1] = book;
        schoolBooks = newSchoolBooks;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int tmp = 0;
        for(SchoolBook book : schoolBooks) {
            if(book.getName().equals(name)) {
                tmp++;
            }
        }
        SchoolBook[] books = new SchoolBook[tmp];

        if(tmp == 0) {
            return books;
        } else {
            int numBooks = 0;
            for(int i =0; i < schoolBooks.length; i++) {
                if(schoolBooks[i].getName().equals(name)) {
                    books[numBooks] = schoolBooks[i];
                    numBooks++;
                }
            }
        }
        return books;
    }

    @Override
    public boolean removeByName(String name) {
        int tmp = 0;
        for(SchoolBook book : schoolBooks) {
            if(book.getName().equals(name)) {
                tmp++;
            }
        }

        if(tmp == 0) {
            return false;
        }
        int numBook = 0;
        SchoolBook[] books = new SchoolBook[schoolBooks.length - tmp];
        if(books.length == 0) {
            schoolBooks = books;
            return true;
        }
        for(int i = 0; i < schoolBooks.length; i++) {
            if(schoolBooks[i].equals(name)) {

            } else {
                books[numBook] = schoolBooks[i];
                numBook++;
            }
        }
        schoolBooks = books;
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
